package com.myspring.sns.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.sns.board.dao.BoardDAO;
import com.myspring.sns.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardVO> selectBoard(int page) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectBoard(page);
	}

	@Override
	public int insertBoard(Map boardMap) throws Exception {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardMap);
	}

	@Override
	public int deleteBoard(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteBoard(board_num);
	}

	@Override
	public BoardVO selectBoardByNum(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectBoardByNum(board_num);
	}

	@Override
	public int updateBoard(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.updateBoard(vo);
	}
	
	@Override
	public int countBoard(int board_num) throws Exception {
		// TODO Auto-generated method stub
		return dao.countBoard(board_num);
	}

	@Override
	public int selectMaxNum() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectMaxNum();
	}

	@Override
	public int alterAI(int number) throws Exception {
		// TODO Auto-generated method stub
		return dao.alterAI(number);
	}

	@Override
	public List<BoardVO> searchBoard(String str, int page) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchBoard(str, page);
	}

	@Override
	public List<BoardVO> selectImgBoard(int page) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectImgBoard(page);
	}

}
