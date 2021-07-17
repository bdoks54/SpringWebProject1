package com.myspring.sns.chat.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.sns.HomeController;
import com.myspring.sns.chat.service.Chat1Service;
import com.myspring.sns.chat.vo.Chat1VO;

@Controller
public class ChatController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	Chat1Service service;
	
	@RequestMapping(value = "/chat/insertChat1.do", method = RequestMethod.POST)
	public ModelAndView insertChat(@ModelAttribute("chat1") Chat1VO chat1,
			@RequestParam("my_name") String my_name,@RequestParam("my_friend") String my_friend,
			@RequestParam("chat") String chat,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName= getViewName(request);
		request.setCharacterEncoding("utf-8");
		int result = 0;
		chat1.setMy_name(my_name);
		chat1.setMy_friend(my_friend);
		chat1.setChat(chat);
		result = service.insertChat(chat1);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("my_friend",chat1.getMy_friend());
		return mav;
	}
	
	@RequestMapping(value = "/chat/deleteChat1.do", method = RequestMethod.POST)
	public ModelAndView deleteChat(@ModelAttribute("chat1") Chat1VO chat1,
			@RequestParam("my_name") String my_name,@RequestParam("my_friend") String my_friend,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName= getViewName(request);
		request.setCharacterEncoding("utf-8");
		int result = 0;
		chat1.setMy_name(my_name);
		chat1.setMy_friend(my_friend);
		result = service.deleteChat(chat1);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("my_friend",chat1.getMy_friend());
		return mav;
	}
	
	@RequestMapping(value = "/chat/chat1Reset.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView reset(@ModelAttribute("chat1") Chat1VO chat1,
			@RequestParam("my_name") String my_name,@RequestParam("my_friend") String my_friend,
			@RequestParam("chat") String chat,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName= getViewName(request);
		request.setCharacterEncoding("utf-8");
		chat1.setMy_name(my_name);
		chat1.setMy_friend(my_friend);
		chat1.setChat(chat);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("my_friend",chat1.getMy_friend());
		return mav;
	}
	
	@RequestMapping(value = "/chat/chat1.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView listChat(@ModelAttribute("chat") Chat1VO chat,@RequestParam("my_name") String my_name,
			@RequestParam("my_friend") String my_friend,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName= getViewName(request);
		
		chat.setMy_name(my_name);
		chat.setMy_friend(my_friend);
		
		List chat1List = service.selectChatList(chat);
		List chat2List = service.selectChatList2(chat);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("chat1List", chat1List);
		mav.addObject("chat2List", chat2List);
		return mav;
	}
	
	
	
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
	
}
