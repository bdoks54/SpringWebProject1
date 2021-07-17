package com.myspring.sns.chat.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.sns.chat.vo.Chat1VO;

@Repository
public class Chat1DAOImpl implements Chat1DAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.myspring.sns.mapper.chat1Mapper";
	
	@Override
	public List<Chat1VO> selectChatList(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectChatList",vo);
	}
	
	@Override
	public List<Chat1VO> selectChatList2(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectChatList2",vo);
	}
	
	@Override
	public int insertChat(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace+".insertChat",vo);
	}
	@Override
	public int deleteChat(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(Namespace+".deleteChat", vo);
	}
}
