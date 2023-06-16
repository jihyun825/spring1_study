package kr.or.ddit.free.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeService {

	public ServiceResult insertFree(FreeVO vo);

	public FreeVO selectFree(int boNo);

	public ServiceResult updateFree(FreeVO vo);

	public ServiceResult deleteFree(int boNo);

	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVo);

	public List<FreeVO> selectFree2(PaginationInfoVO<FreeVO> pagingVo);

	public List<FreeVO> alltFreeList();

}
