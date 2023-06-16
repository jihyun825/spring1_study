package kr.or.ddit.main.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

public interface IMainDAO {

	public List<BoardVO> selectList();

	public List<NoticeVO> selectNoticeList();

	public List<FreeVO> selectFreelist();

	public List<NoticeVO> allNoticeList();

	public List<BoardVO> alltBoardList();

	public List<FreeVO> alltFreeList();

}
