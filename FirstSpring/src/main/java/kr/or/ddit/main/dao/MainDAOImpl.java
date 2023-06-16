package kr.or.ddit.main.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;

@Repository
public class MainDAOImpl implements IMainDAO {
	@Inject
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BoardVO> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.selectBoardList");
	}
	
	@Override
	public List<NoticeVO> selectNoticeList() {
		// TODO Auto-generated method stub
		return  sqlSession.selectList("Main.selectNoticeList");
	}

	@Override
	public List<FreeVO> selectFreelist() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.selectFreeList");
	}

	@Override
	public List<NoticeVO> allNoticeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.allNoticeList");
	}

	@Override
	public List<BoardVO> alltBoardList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.alltBoardList");
	}

	@Override
	public List<FreeVO> alltFreeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Main.alltFreeList");
	}
}
