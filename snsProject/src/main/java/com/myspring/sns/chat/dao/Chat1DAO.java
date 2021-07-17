package com.myspring.sns.chat.dao;

import java.util.List;

import com.myspring.sns.chat.vo.Chat1VO;

public interface Chat1DAO {
	public List<Chat1VO> selectChatList(Chat1VO vo) throws Exception;
	public int insertChat(Chat1VO vo) throws Exception;
	public List<Chat1VO> selectChatList2(Chat1VO vo) throws Exception;
	public int deleteChat(Chat1VO vo) throws Exception;
}
