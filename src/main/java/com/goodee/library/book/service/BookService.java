package com.goodee.library.book.service;

import java.util.List;

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
   
   
   
}
