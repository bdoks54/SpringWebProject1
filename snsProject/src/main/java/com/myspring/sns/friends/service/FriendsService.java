package com.myspring.sns.friends.service;

import java.util.List;

import com.myspring.sns.friends.vo.FriendsVO;

public interface FriendsService {
	public int insertFriend(FriendsVO vo) throws Exception;
	public List<FriendsVO> listFriends(FriendsVO vo) throws Exception;
	public int deleteFriend(FriendsVO vo) throws Exception;
}
