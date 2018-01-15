package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 首页
 * @author Ati
 *
 */
@Controller
public class HomeController {
	
	//首页
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test/index");
		return modelAndView;
	}
	
	//登陆页
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws Exception{
		request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("test/login");
		return modelAndView;
	}
}
