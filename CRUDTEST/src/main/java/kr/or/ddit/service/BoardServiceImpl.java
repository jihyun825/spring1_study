package kr.or.ddit.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

@Service
public class BoardServiceImpl implements IBoardService {
	
	@Inject
	private IBoardDAO dao;

	@Override
	public List<BoardVO> boardList() {
		// TODO Auto-generated method stub
		return dao.boardList();
	}
	@Override
	public BoardVO detailBoard(int bono) {
		// TODO Auto-generated method stub
		ServiceResult result = null;
		
		int status = dao.updateHit(bono);
		if(status>0) {
			result = ServiceResult.OK;
		}
		return dao.detailBoard(bono);
	}
	@Override
	public ServiceResult boardDel(int bono) {
		ServiceResult result = null;
		
		int status = dao.boardDel(bono);
		if(status>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	@Override
	public ServiceResult insertBoard(HashMap<String, String> map) {
		ServiceResult result = null;
		
		int status = dao.insertBoard(map);
		if(status>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
}
