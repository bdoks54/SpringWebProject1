package com.myspring.sns.member.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.sns.HomeController;
import com.myspring.sns.member.service.MemberService;
import com.myspring.sns.member.vo.MemberVO;
import com.myspring.sns.seed.KISA_SEED_CBC;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final byte[] pbszUserKey = "testCrypt2020!@#".getBytes();
	private final byte[] pbszIV = "1234567890123456".getBytes();
	@Inject
	private MemberService service;

	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName= getViewName(request);

		List membersList = service.selectMember();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);
		return mav;
	}

	@RequestMapping(value = "/member/memberForm.do", method=RequestMethod.GET)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value="/member/addMember.do" ,method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		
		byte[] passwd = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, member.getMember_pw().getBytes(), 0, member.getMember_pw().getBytes().length);
		
		
		member.setMember_passwd(passwd);
		
		result = service.insertMember(member);
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}

	@RequestMapping(value = "/member/loginForm.do", method= RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	@RequestMapping(value = "/member/loginPro.do", method = RequestMethod.POST)
	public ModelAndView loginPro(@ModelAttribute("memberVO") MemberVO vo, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		boolean result = service.loginCheck(vo, session);
		if(result) {
			ModelAndView mav = new ModelAndView("redirect:/member/loginresult.do?msg=1");
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("redirect:/member/loginresult.do?msg=0");
			
			return mav;
		}
	}
	
	@RequestMapping(value = "/member/loginresult.do")
	private ModelAndView result(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
	@RequestMapping(value = "/member/logoutPro.do", method = RequestMethod.GET)
	public ModelAndView logoutPro(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/board/listBoard.do");
		return mav;
	}
	
	@RequestMapping(value="/member/confirmId.do", method = RequestMethod.GET)
	public ModelAndView confirmId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		String id = request.getParameter("id");
		String confirmId = service.confirmId(id);
		if(confirmId ==null) {
			confirmId ="";
		}
		
		mav.addObject("confirmId", confirmId);
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
