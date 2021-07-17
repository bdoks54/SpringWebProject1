package com.myspring.sns.friends.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.sns.friends.vo.FriendsVO;

@Repository
public class FriendsDAOImpl implements FriendsDAO {
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.myspring.sns.mapper.friendsMapper";
	
	@Override
	public int insertFriend(FriendsVO vo) throws Exception {
		int result = sqlSession.insert(Namespace+".insertFriend", vo);
		return result;
	}
	
	@Override
	public List<FriendsVO> listFriends(FriendsVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectFriendsList",vo);
	}
	
	@Override
	public int deleteFriend(FriendsVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(Namespace+".deleteFriend",vo);
	}
}
