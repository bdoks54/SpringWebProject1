package com.myspring.sns.chat.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.sns.chat.dao.Chat1DAO;
import com.myspring.sns.chat.vo.Chat1VO;

@Service
public class Chat1ServiceImpl implements Chat1Service{
	@Inject
	Chat1DAO dao;
	
	@Override
	public int insertChat(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertChat(vo);
	}
	
	@Override
	public List<Chat1VO> selectChatList(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectChatList(vo);
	}
	
	@Override
	public List<Chat1VO> selectChatList2(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectChatList2(vo);
	}
	@Override
	public int deleteChat(Chat1VO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteChat(vo);
	}
	
}
