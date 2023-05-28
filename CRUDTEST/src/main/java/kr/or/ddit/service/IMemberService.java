package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	
	public ServiceResult signUp(MemberVO member);

	public MemberVO singIn(MemberVO member);


}
