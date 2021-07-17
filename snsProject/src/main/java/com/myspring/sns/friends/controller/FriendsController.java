package com.myspring.sns.friends.controller;

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
import com.myspring.sns.chat.vo.Chat1VO;
import com.myspring.sns.friends.service.FriendsService;
import com.myspring.sns.friends.vo.FriendsVO;

@Controller
public class FriendsController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	FriendsService service;
	
	@RequestMapping(value = "/friends/insertFriend.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView insertFirend(@ModelAttribute("friend") FriendsVO friend,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id_1") String id_1, @RequestParam("id_2") String id_2) throws Exception{
		friend.setId_1(id_1);
		friend.setId_2(id_2);
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = service.insertFriend(friend);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;	
	}
	
	@RequestMapping(value = "/friends/deleteFriend.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteFriend(@ModelAttribute("friend") FriendsVO friend,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("my_name") String id_1, @RequestParam("my_friend") String id_2) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName= getViewName(request);
		int result = 0;
		friend.setId_1(id_1);
		friend.setId_2(id_2);
		result = service.deleteFriend(friend);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}
	
	
	@RequestMapping(value = "/friends/listFriends.do", method = RequestMethod.POST)
	public ModelAndView listFriend(@ModelAttribute("friend") FriendsVO friend ,HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id_1") String id_1) throws Exception{
		String viewName= getViewName(request);

		friend.setId_1(id_1);
		
		List friendsList = service.listFriends(friend);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("friendsList", friendsList);

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
