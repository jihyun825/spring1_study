package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

public interface IBoardDAO {

	public List<BoardVO> boardList();

	public BoardVO detailBoard(int bono);

	public int updateHit(int bono);

	public int boardDel(int bono);

	public int insertBoard(HashMap<String, String> map);

	public BoardVO selectBoard(int bono);

	public int updateBoard(HashMap<String, String> map);



}
