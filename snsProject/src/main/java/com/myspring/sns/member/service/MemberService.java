package com.myspring.sns.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.myspring.sns.member.vo.MemberVO;

public interface MemberService {
	public List<MemberVO> selectMember() throws Exception;
	public int insertMember(MemberVO vo) throws Exception;
	public boolean loginCheck(MemberVO vo, HttpSession session) throws Exception;
	public MemberVO viewMember(MemberVO vo) throws Exception;
	public String confirmId(String id) throws Exception;
}
