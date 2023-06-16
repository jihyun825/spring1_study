<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" />
<title>자유게시판 목록</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">자유 게시판 목록</h1>
		</div>
	</div>
	<div class="container">
		<form class="input-group input-group-sm" method="post" id="searchForm" style="width: 440px;">
			<input type="hidden" name="page" id="page"/>
				<select class="form-control" name="searchType">
				<option value="wrti" <c:if test="${searchType == 'wrti' }"><c:out value="selected"/></c:if>>제목+작성자</option>
					<option value="title" <c:if test="${searchType == 'title' }"><c:out value="selected"/></c:if>>제목</option>
					<option value="writer" <c:if test="${searchType == 'writer' }"><c:out value="selected"/></c:if>>작성자</option>
				</select>
				<input type="text" name="searchWord" class="form-control float-right" value="${searchWord }" placeholder="Search">
				<div class="input-group-append">
					<button type="submit" class="btn btn-default">
						<i class="fas fa-search"></i>검색
					</button>
				</div>
				</form>
			<div>
				<div class="text-right">
					<span>전체${fn:length(alltFreeList)} 건	</span>
				</div>
			</div>
			<div style="padding-top: 50px">
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>작성자</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set value="${pagingVo.dataList }" var="freeList"/>
						<c:choose>
							<c:when test="${empty freeList }">
								<tr>
									<td colspan="5">조회하신 게시글이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items ="${freeList }" var="free">
								<tr class="free">
									<td class="boNo">${free.boNo }</td>
									<td>
										<a href="/free/detail.do?boNo=${free.boNo}">
											${free.boTitle }
										</a>
									</td>
									<td>${free.boWriter }</td>
									<td>${free.boDate }</td>
									<td>${free.boHit }</td>
								</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div align="left">
				<a href="/free/form.do" onclick="checkForm(); return false;" class="btn btn-primary">&laquo;글쓰기</a>
			</div>
			<div class="card-footer clearfix" id="pagingArea">
				${pagingVo.pagingHTML }
			</div>
		<hr>
	</div>
</body>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	var searchForm = $("#searchForm");
	var pagingArea =$("#pagingArea");

	pagingArea.on("click","a", function(){
		event.preventDefault();
		var pageNo = $(this).data("page");
		searchForm.find("#page").val(pageNo);
		searchForm.submit();
	});
	
	$(document).on('click','.free',function(){
	      var boNo = $(this).find('.boNo').text();
	      location.href = "/free/detail.do?boNo="+boNo;
	   })
	
});
</script>
</html>





