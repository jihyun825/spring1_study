package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {

	public int insertFree(FreeVO vo);

	public void incrementHit(int boNo);

	public FreeVO selectFree(int boNo);

	public int updateFree(FreeVO vo);

	public int deleteFree(int boNo);

	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVo);

	public List<FreeVO> selectFree2(PaginationInfoVO<FreeVO> pagingVo);

	public List<FreeVO> alltFreeList();

}
