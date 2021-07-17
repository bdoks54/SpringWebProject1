package com.myspring.sns.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myspring.sns.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.myspring.sns.mapper.boardMapper";
	
	@Override
	public List<BoardVO> selectBoard(int page) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		int startRow = (page-1)*10;
		map.put("startRow", startRow);
		
		return sqlSession.selectList(Namespace+".selectBoard", map);
	}

	@Override
	public int insertBoard(Map boardMap) throws Exception {
		// TODO Auto-generated method stub
		int boardNO = selectNewBoardNO();
		sqlSession.insert(Namespace+".insertBoard",boardMap);
		return boardNO;
	}

	@Override
	public int deleteBoard(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(Namespace+".deleteBoard", board_num);
	}

	@Override
	public BoardVO selectBoardByNum(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectBoardByNum", board_num);
	}

	@Override
	public int updateBoard(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(Namespace+".updateBoard", vo);
	}

	
	public int selectNewBoardNO() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectNewBoardNO");
	}
	
	@Override
	public int countBoard(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(Namespace+".countBoard", board_num);
	}

	@Override
	public int selectMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(Namespace+".selectMaxNum");
	}

	@Override
	public int alterAI(int number) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(Namespace+".alterAI", number);
	}

	@Override
	public List<BoardVO> searchBoard(String str, int page) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> searchMap = new HashMap<String, Object>();
		int startRow = (page-1)*10;
		searchMap.put("str", str);
		searchMap.put("startRow", startRow);
		
		return sqlSession.selectList(Namespace+".searchBoard", searchMap);
	}

	@Override
	public List<BoardVO> selectImgBoard(int page) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		int startRow = (page-1)*10;
		map.put("startRow", startRow);
		return sqlSession.selectList(Namespace+".selectImgBoard", map);
	}


	
}
