package kr.or.ddit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;
@Repository
public class FreeDaoImpl implements IFreeDAO {
	
	@Inject
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertFree(FreeVO vo) {
		return sqlSession.insert("Free.insertFree",vo);
	}
	
	@Override
	public void incrementHit(int boNo) {
		sqlSession.update("Free.incrementHit",boNo);
	}
	
	@Override
	public FreeVO selectFree(int boNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Free.selectFree",boNo);
	}
	
	@Override
	public int updateFree(FreeVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("Free.updateFree",vo);
	}
	
	@Override
	public int deleteFree(int boNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("Free.deleteFree",boNo);
	}
	
	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Free.selectFreeCount",pagingVo);
	}
	
	@Override
	public List<FreeVO> selectFree2(PaginationInfoVO<FreeVO> pagingVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Free.selectFree2",pagingVo);
	}
	@Override
	public List<FreeVO> alltFreeList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Free.alltFreeList");
	}
}
