package com.myspring.sns.comment.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.sns.comment.vo.CommentVO;
@Repository
public class CommentDAOImpl implements CommentDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.myspring.sns.mapper.commentMapper";
	
	@Override
	public int insertComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace+".insertComment", vo);
	}

	@Override
	public List<CommentVO> selectCommentList(int page) throws Exception {
		// TODO Auto-generated method stub
		int startRow = (page-1)*10;
		return sqlSession.selectList(Namespace+".selectCommentList", startRow);
	}

	@Override
	public List<CommentVO> selectReCommentList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".selectReCommentList");
	}

	@Override
	public int insertReComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(Namespace+".insertReComment", vo);
	}

	@Override
	public int deleteComment(int comment_num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(Namespace+".deleteComment", comment_num);
	}

	@Override
	public List<CommentVO> getCommentList(int boardNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(Namespace+".getCommentList", boardNum);
	}



}
