package kr.or.ddit.dao;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {

	public int signUp(MemberVO member);

	public MemberVO signIn(MemberVO member);

}
