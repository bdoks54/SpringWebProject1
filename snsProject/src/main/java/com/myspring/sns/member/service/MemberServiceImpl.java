package com.myspring.sns.member.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myspring.sns.member.dao.MemberDAO;
import com.myspring.sns.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO dao;
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectMember();
	}

	@Override
	public int insertMember(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertMember(vo);
	}

	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		boolean result = dao.loginCheck(vo);
		if(result) {
			MemberVO vo2 = viewMember(vo);
			session.setAttribute("member_name", vo2.getMember_name());
			session.setAttribute("member_id", vo2.getMember_id());
		}
		return result;
	}
	
	@Override
	public MemberVO viewMember(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.viewMember(vo);
	}

	@Override
	public String confirmId(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.confirmId(id);
	}
}
