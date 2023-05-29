package kr.or.ddit.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

@Repository
public class BoardDaoImpl implements IBoardDAO {
	
	@Inject
	SqlSessionTemplate session;

	@Override
	public List<BoardVO> boardList() {
		// TODO Auto-generated method stub
		return session.selectList("Board.boardList");
	}
	@Override
	public BoardVO detailBoard(int bono) {
		// TODO Auto-generated method stub
		return session.selectOne("Board.detailBoard",bono);
	}
	@Override
	public int updateHit(int bono) {
		
		return session.update("Board.updateHit",bono);
	}
	@Override
	public int boardDel(int bono) {
		// TODO Auto-generated method stub
		return session.delete("Board.boardDel",bono);
	}
	@Override
	public int insertBoard(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return session.insert("Board.insertBoard",map);
	}
	@Override
	public BoardVO selectBoard(int bono) {
		return session.selectOne("Board.selectBoard",bono);
	}
	@Override
	public int updateBoard(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return session.update("Board.updateBoard",map);
	}
}
