package com.myspring.sns.board.dao;

import java.util.List;
import java.util.Map;

import com.myspring.sns.board.vo.BoardVO;

public interface BoardDAO {
	public List<BoardVO> selectBoard(int page) throws Exception;
	public int insertBoard(Map boardMap) throws Exception;
	public int deleteBoard(int board_num) throws Exception;
	public BoardVO selectBoardByNum(int board_num) throws Exception;
	public int updateBoard(BoardVO vo) throws Exception;
	public int countBoard(int board_num) throws Exception;
	public int selectMaxNum() throws Exception;
	public int alterAI(int number) throws Exception;
	public List<BoardVO> searchBoard(String str, int page) throws Exception;
	public List<BoardVO> selectImgBoard(int page) throws Exception;
}
