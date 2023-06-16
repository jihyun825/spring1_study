<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
	
<title>공지게시판 상세보기</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 상세보기 / 수정 / 삭제</h3>
		</div>
	</div>

	<div class="container">
		
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					${notice.boTitle }
					<input type = "hidden" name="subject" class="form-control"	value="${notice.boTitle }" >
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					${notice.boContent }
					
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10 ">
				<div class="card-footer">
					<button type="button" class="btn btn-primary" id="listBtn">목록</button>
					<button type="button" class="btn btn-info" id="updateBtn">수정</button>
					<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
					</div>
				</div>
			</div>
			

		<hr>
	</div>
		<form action="/notice/update.do" method="get" id="nFrm">
			<input type="hidden" name="boNo" value="${notice.boNo }">
	</form>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	var listBtn = $("#listBtn");	
	var updateBtn = $("#updateBtn");	
	var delBtn = $("#delBtn");	
	var nFrm = $("#nFrm");
	
	listBtn.on("click",function(){
		location.href= "/notice/list.do"	;
	});
	
	updateBtn.on("click",function(){
		nFrm.submit();
		
	})
	
	
	
	delBtn.on("click",function(){
			if(confirm("정말 삭제하시겠습니까?")){
				//삭제처리
				
				nFrm.attr("method","post");
				nFrm.attr("action","/notice/delete.do");
				nFrm.submit();
				
			}else{
				//삭제 취소		
					
				nFrm.reset();
			}
		
	});
	
})
</script>
</html>


