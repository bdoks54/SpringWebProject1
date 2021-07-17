package com.myspring.sns.board.service;

import java.util.List;
import java.util.Map;

import com.myspring.sns.board.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoard(int page) throws Exception;
	public int insertBoard(Map boardMap) throws Exception;
	public int deleteBoard(int board_num) throws Exception;
	public BoardVO selectBoardByNum(int board_num) throws Exception;
	public int updateBoard(BoardVO vo) throws Exception;
	int countBoard(int board_num) throws Exception;
	int selectMaxNum() throws Exception;
	int alterAI(int number) throws Exception;
	public List<BoardVO> searchBoard(String str, int page) throws Exception;
	public List<BoardVO> selectImgBoard(int page) throws Exception;
}
