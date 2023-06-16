package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public int insertNotice(NoticeVO vo);

	public void incrementHit(int boNo);

	public NoticeVO selectNotice(int boNo);

	public int updateNotice(NoticeVO vo);

	public int deleteNotice(int boNo);

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> selectNotice2(PaginationInfoVO<NoticeVO> pagingVo);

	public List<NoticeVO> alltNoticeList();



}
