package kr.or.ddit.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDAOImpl implements IMemberDAO {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int signUp(MemberVO member) {
		
		return sqlSession.insert("Member.signUp", member);
	}

	@Override
	public MemberVO signIn(MemberVO member) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("Member.singIn",member);
	}

}
