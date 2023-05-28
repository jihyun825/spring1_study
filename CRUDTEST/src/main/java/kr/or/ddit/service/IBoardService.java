package kr.or.ddit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
public interface IBoardService {

	public List<BoardVO> boardList();

	public BoardVO detailBoard(int bono);

	public ServiceResult boardDel(int bono);

	public ServiceResult insertBoard(HashMap<String, String> map);

	

}
