package kr.or.ddit;
//ServiceResult 인 enum은 처리를 담당하는 컨트롤러안에서
//서비스로 요청한 기능명의 결과 넘어오는 결과들인데,
//처리 성공여부 또한 처리여부에 따라서 OK,FAILED,EXIST,NOTEXIST등의 결과가 넘어온다
public enum ServiceResult {
	OK,FAILED,EXIST,NOTEXIST
	

}
