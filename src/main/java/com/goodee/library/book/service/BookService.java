package com.goodee.library.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.library.book.dao.BookDao;
import com.goodee.library.book.dto.BookDto;

@Service
public class BookService {
   
   private static final Logger LOGGER =
         LogManager.getLogger(BookService.class);
   
   @Autowired
   BookDao bookDao;

   public int createBook(BookDto dto) {
      return bookDao.createBook(dto);
   }

   public int selectBookCount(String b_name) {
	   
	   int result = 0;
	   result = bookDao.selectBookCount(b_name);
      return result;
   }
   
   public List<BookDto> selectBookList(BookDto dto){
      return bookDao.selectBoookList(dto);
   }

public List<BookDto> selectBookListToday() {
	return bookDao.selectBookListToday();
}

public BookDto selectBookDetail(long b_no) {
	return bookDao.selectBookDetail(b_no);
}

public Map<String, String> editBookDetail(BookDto dto) {
		int result = bookDao.editBookDetail(dto);
		Map<String, String> map = new HashMap<String, String>();
		if(result > 0) {
			map.put("res_code", "200");
			 map.put("res_msg","도서 수정이 완료되었습니다.");
		}
	return map;
}
   
   
   
}
