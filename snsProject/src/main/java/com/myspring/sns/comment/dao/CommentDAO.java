package com.myspring.sns.comment.dao;

import java.util.List;

import com.myspring.sns.comment.vo.CommentVO;

public interface CommentDAO {
	public int insertComment(CommentVO vo) throws Exception;
	public List<CommentVO> selectCommentList(int page) throws Exception;
	public List<CommentVO> selectReCommentList() throws Exception;
	public int insertReComment(CommentVO vo) throws Exception;
	public int deleteComment(int comment_num) throws Exception;
	public List<CommentVO> getCommentList(int boardNum) throws Exception;
}
