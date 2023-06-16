package kr.or.ddit.free.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.dao.IFreeDAO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
@Service
public class FreeServiceImpl implements IFreeService {
	
	@Inject
	private IFreeDAO freeDao;
	
	@Override
	public ServiceResult insertFree(FreeVO vo) {
		
		ServiceResult result = null;
		int status =  freeDao.insertFree(vo);
		
		if(status > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	@Override
	public FreeVO selectFree(int boNo) {
		freeDao.incrementHit(boNo);
		return freeDao.selectFree(boNo);
	}
	
	@Override
	public ServiceResult updateFree(FreeVO vo) {
		ServiceResult result = null;
				
		int status = freeDao.updateFree(vo);
		
		if(status>0) {
			result = ServiceResult.OK;
					
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public ServiceResult deleteFree(int boNo) {
		ServiceResult result = null;
		int status = freeDao.deleteFree(boNo);
		
		if(status >0 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVo) {
	
		return freeDao.selectFreeCount(pagingVo);
	}
	
	@Override
	public List<FreeVO> selectFree2(PaginationInfoVO<FreeVO> pagingVo) {
		// TODO Auto-generated method stub
		return freeDao.selectFree2(pagingVo);
	}
	@Override
	public List<FreeVO> alltFreeList() {
		// TODO Auto-generated method stub
		return freeDao.alltFreeList();
	}
	
	
}
