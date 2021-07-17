package com.myspring.sns.comment.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.sns.comment.dao.CommentDAO;
import com.myspring.sns.comment.vo.CommentVO;
@Service
public class CommentServiceImpl implements CommentService{
	@Inject
	private CommentDAO dao;
	
	

	@Override
	public int insertComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertComment(vo);
	}



	@Override
	public List<CommentVO> selectCommentList(int page) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectCommentList(page);
	}



	@Override
	public List<CommentVO> selectReCommentList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectReCommentList();
	}



	@Override
	public int insertReComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertReComment(vo);
	}



	@Override
	public int deleteComment(int comment_num) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteComment(comment_num);
	}



	@Override
	public List<CommentVO> getCommentList(int boardNum) throws Exception {
		// TODO Auto-generated method stub
		return dao.getCommentList(boardNum);
	}

}
