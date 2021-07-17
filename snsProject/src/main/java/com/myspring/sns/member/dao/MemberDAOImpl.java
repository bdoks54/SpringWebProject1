package com.myspring.sns.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.sns.member.vo.MemberVO;
import com.myspring.sns.seed.KISA_SEED_CBC;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Inject
	private SqlSession sqlSession;
	private final byte[] pbszUserKey = "testCrypt2020!@#".getBytes();
	private final byte[] pbszIV = "1234567890123456".getBytes();
	
	private static final String Namespace = "com.myspring.sns.mapper.memberMapper";
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectMember");
	}

	@Override
	public int insertMember(MemberVO vo) throws Exception {
		int result = sqlSession.insert(Namespace+".insertMember", vo);
		return result;
	}

	@Override
	public boolean loginCheck(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		String strPw = vo.getMember_pw();
		byte[] bytePw = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, strPw.getBytes(), 0, strPw.getBytes().length);
		vo.setMember_passwd(bytePw);
		String name = sqlSession.selectOne(Namespace+".loginCheck", vo);
		return (name == null) ? false:true;
	}

	@Override
	public MemberVO viewMember(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".viewMember", vo);
	}

	@Override
	public String confirmId(String id) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".confirmId", id);
	}

	

}
