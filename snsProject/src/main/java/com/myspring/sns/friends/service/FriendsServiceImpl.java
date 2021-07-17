package com.myspring.sns.friends.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.sns.friends.dao.FriendsDAO;
import com.myspring.sns.friends.vo.FriendsVO;


@Service
public class FriendsServiceImpl implements FriendsService {
	@Inject
	FriendsDAO dao;
	
	@Override
	public int insertFriend(FriendsVO vo) throws Exception {
		
		return dao.insertFriend(vo);
	}
	
	@Override
	public List<FriendsVO> listFriends(FriendsVO vo) throws Exception {
		
		return dao.listFriends(vo);
	}
	
	@Override
	public int deleteFriend(FriendsVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteFriend(vo);
	}
	

}
