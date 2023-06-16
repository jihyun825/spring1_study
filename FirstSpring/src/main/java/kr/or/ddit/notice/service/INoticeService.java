package kr.or.ddit.notice.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeService {

	public ServiceResult insertNotice(NoticeVO vo);

	public NoticeVO selectNotice(int boNo);

	public ServiceResult updateNotice(NoticeVO vo);

	public ServiceResult deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> selectNotice2(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> alltNoticeList();

}
