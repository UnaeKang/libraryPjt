package com.goodee.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
	@RequestMapping(value= {"","/"}, method = RequestMethod.GET)
	public String home() {
		LOGGER.info("도서관 관리 시스템 시작");
		return "home";
	}
}
