<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/adminlte.min.css">
<title>공지게시판 등록</title>
</head>
<body class = "hold-transition sidebar-mini">
	<c:if test="${status eq 'u' }">
		<c:set value="수정" var="name"/>
	</c:if>	
	<c:if test="${status ne 'u' }">
		<c:set value="등록" var="name"/>
	</c:if>	
	<div class="jumbotron">
		<div class="container">
			<h3 class="display-3">공지게시판 ${name }</h3>
		</div>
	</div>
	<div class="container">
		<form name="newWrite" action="/notice/insert.do" class="form-horizontal" method="post" id="newWrite">
		<c:if test="${status eq 'u'}">
			<input type="hidden" name="boNo" value="${notice.boNo}"/>
		</c:if>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >제목</label>
				<div class="col-sm-5">
					<input id="boTitle" name="boTitle" type="text" class="form-control" placeholder="subject" value="${notice.boTitle }"">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label" >내용</label>
				<div class="col-sm-8">
					<textarea id="boContent" name="boContent" cols="50" rows="5" class="form-control" placeholder="content">${notice.boContent }</textarea>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-12">
									<!-- 
										등록시 버튼 그룹
										> 목록, 등록
										수정시 버튼 그룹
										>수정, 취소
									 -->
										<input type="button" value="${name}" id="formBtn" class="btn btn-primary float-right">
										<c:if test="${status eq 'u' }">
										<a href="/notice/detail.do?boNo=${notice.boNo }">
											<input type="button" value="취소" class="btn btn-danger float-right">
										</a>
										</c:if>
										<c:if test="${status ne 'u' }">
										<a href="/notice/list.do">
											<input type="button" value="목록" class="btn btn-success float-right">
										</a>
										</c:if>
									</div>
			</div>
		</form>
		<hr>
	</div>
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
</body>
<script type="text/javascript">
	$(function(){
			
		CKEDITOR.replace("boContent");
		CKEDITOR.config.allowedContent = true;
		
		var formBtn = $('#formBtn');
		
		formBtn.on('click',function(){
			
			var title= $('#boTitle').val();
			var content= $('#boContent').val();
			var content = CKEDITOR.instances.boContent.getData();
			
	      
	       var titleLength = getBytes(title);
	       var contentLength = getBytes(content);
	       
	       var maxTitle = 400;
	       var maxContent = 4000;
	       
	        if (titleLength >= maxTitle) {
	            alert("제목 입력 제한 길이를 추가했습니다. 다시 입력해 주세요.");
	           return false;
	        }
	        if (contentLength >= maxContent) {
	            alert("내용 입력 제한 길이를 추가했습니다. 다시 입력해 주세요.");
	           return false;
	        }
			
			if(title ==""){
				alert("제목을 입력해주세요!")
				$('#boTitle').focus();
				return false;
			}
			
			if(content ==""){
				alert("내용을 입력해주세요!")
				$('#boContent').focus();
				return false;
			}
			
			if($(this).val()=="수정"){
				$('#newWrite').attr("action","/notice/update.do");
			}
			
			//제목,내용을 누락하지않았을때
			
			$('#newWrite').submit();
			
		})
	
	})
function getBytes(str) {
     let character;
     let charBytes = 0;

     for (let i = 0; i < str.length; i += 1) {
       character = str.charAt(i);

       if (escape(character).length > 4) charBytes += 3;
       else charBytes += 1;
     }

     return charBytes;
}
</script>
</html>



