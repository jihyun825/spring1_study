package kr.or.ddit.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.main.dao.IMainDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Service
public class MainServiceImple implements IMainService {
	
	@Inject
	private IMainDAO mainDao;

	@Override
	public List<BoardVO> selectBoardList() {
		// TODO Auto-generated method stub
		return mainDao.selectList();
	}

	@Override
	public List<NoticeVO> selectNoticeList() {
		// TODO Auto-generated method stub
		return mainDao.selectNoticeList();
	}
	
	@Override
	public List<FreeVO> selectFreeList() {
		// TODO Auto-generated method stub
		return mainDao.selectFreelist();
	}

	@Override
	public List<BoardVO> alltBoardList() {
		// TODO Auto-generated method stub
		return mainDao.alltBoardList();
	}

	@Override
	public List<NoticeVO> allNoticeList() {
		// TODO Auto-generated method stub
		return mainDao.allNoticeList();
	}

	@Override
	public List<FreeVO> alltFreeList() {
		// TODO Auto-generated method stub
		return mainDao.alltFreeList();
	}
}
