package com.goodee.library.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.library.book.dto.BookDto;
import com.goodee.library.book.service.BookService;
import com.goodee.library.book.utill.UploadFileService;



@Controller
public class BookApiController {
   
   private static final Logger LOGGER =
         LogManager.getLogger(BookApiController.class);
   
   @Autowired
   UploadFileService uploadFileService;
   
   @Autowired
   BookService bookService;
   
   // 1. Post 방식
   // 2. url "/book"
   // 3. Dto, Multipartfile 객체
   // 4. json -> RequestBody(X), @RequestParam(O)
   
   @ResponseBody
   @PostMapping("/book")
   public Map<String, String> createBook(BookDto dto, @RequestParam("file") MultipartFile file){
      LOGGER.info("도서 등록 기능");
      // UploadFileService에 upload 메소드 구성
      // return 업로드된 파일명
      Map<String, String> map = new HashMap<String, String>();
      map.put("res_code","404");
      map.put("res_msg","도서 등록 중 오류가 발생했습니다.");
      
      String savedFileName = uploadFileService.upload(file);
      if(savedFileName != null) {
         dto.setB_thumbnail(savedFileName);
         if(bookService.createBook(dto) > 0) {
            map.put("res_code", "200");
            map.put("res_msg", "파일 업로드 성공했습니다.");
         }
      }else {
         map.put("res_msg", "파일 업로드 조차 실패했습니다.");
      }
      return map;
   }
   
   @ResponseBody
   @PostMapping("/book/{b_no}")
   public Map<String, String> editBookDetail(BookDto dto, @RequestParam("file") MultipartFile file){
	   LOGGER.info("dto"+dto.getB_name());
	   
	   Map<String, String> map = new HashMap<String, String>();
	      map.put("res_code","404");
	      map.put("res_msg","도서 수정 중 오류가 발생했습니다.");
	      if("".equals(file.getOriginalFilename())==false) {
	    	  // 1. 새로운 파일을 서버에 업로드
	    	  String saveFileName = uploadFileService.upload(file);
	    	  if(saveFileName != null) {
	    		  // 2. 기존 파일 삭제(오류만 제거 -return true)'
	    		  if(uploadFileService.delete(dto.getB_thumbnail())) {
	    			  // 정상 삭제
	    			  // 3. b_thumbnail 정보 수정
	    			  dto.setB_thumbnail(saveFileName);
	    		  }else {
	    			  map.put("res_msg", "기존 파일 삭제에 실패하였습니다.");
	    		  }
	    	  }else {
	    		  map.put("res_msg", "파일 업로드 중 오류가 발생했습니다.");
	    	  }   	  
		      // 4. 도서 정보 수정(실제 데이터 update)
		      // b_thumbnail 여부에 따라 쿼리가 달라짐
	      }
	      
	      map = bookService.editBookDetail(dto);
	   return map;
   }
   
   
}
