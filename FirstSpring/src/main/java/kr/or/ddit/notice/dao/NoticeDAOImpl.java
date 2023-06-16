package kr.or.ddit.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
@Repository
public class NoticeDAOImpl implements INoticeDAO{

	@Inject
	private SqlSessionTemplate sqlSession;
	@Override
	public int insertNotice(NoticeVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("Notice.insertNotice",vo);
	}
	@Override
	public void incrementHit(int boNo) {
		sqlSession.update("Notice.incrementHit",boNo);
		
	}
	@Override
	public NoticeVO selectNotice(int boNo) {
		
		return sqlSession.selectOne("Notice.selectNotice",boNo);
	}
	@Override
	public int updateNotice(NoticeVO vo) {
		return sqlSession.update("Notice.updateNotice",vo);
	}

	@Override
	public int deleteNotice(int boNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("Notice.deleteNotice",boNo);
	}
	
	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Notice.selectNoticeCount",pagingVo);
	}
	
	@Override
	public List<NoticeVO> selectNotice2(PaginationInfoVO<NoticeVO> pagingVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Notice.selectNoticeList2",pagingVo);
	}
	
	@Override
	public List<NoticeVO> alltNoticeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Notice.alltNoticeList");
	}
}
