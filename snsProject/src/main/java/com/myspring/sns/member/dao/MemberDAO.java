package com.myspring.sns.member.dao;

import java.util.List;

import com.myspring.sns.member.vo.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectMember() throws Exception;
	public int insertMember(MemberVO vo) throws Exception;
	public boolean loginCheck(MemberVO vo) throws Exception;
	public MemberVO viewMember(MemberVO vo) throws Exception;
	public String confirmId(String id) throws Exception;
}
