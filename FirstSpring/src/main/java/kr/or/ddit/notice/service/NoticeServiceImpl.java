package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDAO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
import oracle.net.aso.b;
@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	INoticeDAO noticeDao;
	@Override
	public ServiceResult insertNotice(NoticeVO vo) {
		ServiceResult result = null;
		int status = noticeDao.insertNotice(vo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeDao.incrementHit(boNo);
		return noticeDao.selectNotice(boNo);
	}
	@Override
	public ServiceResult updateNotice(NoticeVO vo) {
		ServiceResult result = null;
		int status = noticeDao.updateNotice(vo);
		if(status > 0) {
			result = ServiceResult.OK;
		}else{
			result = ServiceResult.FAILED;
		
		}
		return result;
	}
	@Override
	public ServiceResult deleteNotice(int boNo) {
		ServiceResult result = null;
		int status = noticeDao.deleteNotice(boNo);
		
		if(status >0) {
			result = ServiceResult.OK;
		}else {
			
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo) {
		// TODO Auto-generated method stub
		return noticeDao.selectNoticeCount(pagingVo);
	}
	
	@Override
	public List<NoticeVO> selectNotice2(PaginationInfoVO<NoticeVO> pagingVo) {
		// TODO Auto-generated method stub
		return noticeDao.selectNotice2(pagingVo);
	}
	@Override
	public List<NoticeVO> alltNoticeList() {
		// TODO Auto-generated method stub
		return noticeDao.alltNoticeList();
	}
}
