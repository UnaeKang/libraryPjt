package com.goodee.library.book.dao;

import java.awt.print.Book;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodee.library.book.dto.BookDto;

@Repository
public class BookDao {
   
   private static final Logger LOGGER =
         LogManager.getLogger(BookDao.class);
   
   @Autowired
   private SqlSession sqlSession;
   
   private final String namespace = "com.goodee.library.bookMapper.";

   public int createBook(BookDto dto) {
      int result = 0;
      try {
         result = sqlSession.insert(namespace+"createBook",dto);
      } catch(Exception e) {
         StringWriter errors = new StringWriter();
           e.printStackTrace(new PrintWriter(errors));
           LOGGER.error(errors.toString());
      }
      return result;
   }

   public int selectBookCount(String b_name) {
      int result = 0;
      try {
         result = sqlSession.selectOne(namespace+"selectBookCount",b_name);
      } catch(Exception e) {
         StringWriter errors = new StringWriter();
           e.printStackTrace(new PrintWriter(errors));
           LOGGER.error(errors.toString());
      }
      return result;
   }

   public List<BookDto> selectBoookList(BookDto dto) {
      LOGGER.info("전체 도서 목록 조회");
      List<BookDto> resultList = new ArrayList<BookDto>();
      try {
         resultList = sqlSession.selectList(namespace+"selectBookList",dto);
      } catch(Exception e) {
         e.printStackTrace();
      }
      return resultList;
   }

public List<BookDto> selectBookListToday() {
	List<BookDto> resultList = new ArrayList<BookDto>();
	try {
		resultList = sqlSession.selectList(namespace+"selectBookListToday");
	} catch (Exception e) {
		e.printStackTrace();
	}
	return resultList;
}

}
