package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Service
public class BoardServiceImpl implements IBoardService {
	@Inject
	private IBoardDAO boardDao;
	
	@Override
	public ServiceResult insertBoard(BoardVO boardVo) {
		ServiceResult result = null;
		//성공하면 1, 실패하면 0
		// boardVo 안에 boNo 가 셋팅되어 들어올 예정
		//selectKey 설정에 의해서 최신 글 번호가 담겨서 들어온다.
		int status = boardDao.insertBoard(boardVo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public BoardVO selectBoard(int boNo) {
		boardDao.incrementHit(boNo);
		return boardDao.selectBoard(boNo);
	}

	@Override
	public ServiceResult updateBoard(BoardVO boardVo) {
		ServiceResult result = null;
		int status = boardDao.updateBoard(boardVo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		
		}
		return result;
	}

	@Override
	public ServiceResult deleteBoard(int boNo) {
		ServiceResult result = null;
		int status = boardDao.deleteBoard(boNo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		
		}
		return result;
	}

	@Override
	public List<BoardVO> selectBoardList() {
		
		return boardDao.selectBoardList();
	}

	@Override
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> selectBoardList2(PaginationInfoVO<BoardVO> pagingVO) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardList2(pagingVO);
	}
	
	@Override
	public List<BoardVO> alltBoardList() {
		// TODO Auto-generated method stub
		return boardDao.alltBoardList();
	}

}
