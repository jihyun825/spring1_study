<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<!DOCTYPE html>
<html>

<head>
<style type="text/css">
#micicon{
color : black;
width: 50px;
height: 50px;
}

#micicon:hover {
	cursor: pointer;
	color : red;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
  <title>
    대덕인재개발원 CRUD 연습
  </title>
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <!-- Nucleo Icons -->
  <link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <!-- CSS Files -->
  <link id="pagestyle" href="../assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
<script src="../../resources/ckeditor/ckeditor.js"></script>  
</head>
<c:if test="${fail eq 'f' }">
	<script type="text/javascript">
		alert("수정에 실패했습니다. 다시시도해주세요");
	</script>
</c:if>
<c:if test="${member eq null }">
	<c:redirect url="/"/>
</c:if>
<c:set value="등록" var = "name"/>
<c:if test="${modify eq 'm' }">
	<c:set value="수정" var="name"/>
</c:if>
<body class="g-sidenav-show  bg-gray-200">
  <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="" target="_blank">
        <img src="../assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
        <span class="ms-1 font-weight-bold text-white">대덕인재개발원</span>
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-white active bg-gradient-info" href="../pages/tables.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>
            <span class="nav-link-text ms-1">공지사항</span>
          </a>
        </li>
      </ul>
    </div>
  </aside>
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <!-- Navbar -->
    <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true">
      <div class="container-fluid py-1 px-3">
        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
          <div class="ms-md-auto pe-md-3 d-flex align-items-center">
          </div>
          <ul class="navbar-nav  justify-content-end">
           <c:if test="${sessionScope.member eq null}">
            <li class="nav-item d-flex align-items-center">
              <a href="/" class="nav-link text-body font-weight-bold px-0">
                <i class="fa fa-user me-sm-1"></i>
                <span class="d-sm-inline d-none">로그인</span>
              </a>
            </li>
            </c:if>
			<li class="nav-item d-flex align-items-center">　</li>
			<li class="nav-item">
			  <div class="d-flex align-items-center justify-content-between">
				<div class="avatar-group mt-2 avatar avatar-xs rounded-circle">
				  <i class="fa-solid fa-microphone" id="micicon"></i>				</div>
			  </div>
			</li>
			<li class="nav-item d-flex align-items-center">　</li>
			<li class="nav-item d-flex align-items-center">
				<div class="d-flex flex-column justify-content-center">
				  <h6 class="mb-0 text-sm">${member.mem_name}</h6>
				  <p class="text-xs text-secondary mb-0">${member.mem_email }</p>
				</div>
			</li>
			<li class="nav-item d-flex align-items-center">　</li>
			<c:if test="${sessionScope.member ne null}">
			<li class="nav-item d-flex align-items-center">
              <a href="logout()" class="nav-link text-body font-weight-bold px-0">
                <i class="fa fa-user me-sm-1"></i>
                <span class="d-sm-inline d-none">로그아웃</span>
              </a>
            </li>
            </c:if>
			<li class="nav-item d-flex align-items-center">　</li>
			<li class="nav-item d-flex align-items-center">
              <a href="" class="nav-link text-body font-weight-bold px-0">
                <i class="fa fa-user me-sm-1"></i>
                <span class="d-sm-inline d-none">마이페이지</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- End Navbar -->
    <div class="container-fluid px-2 px-md-4">
      <div class="page-header min-height-300 border-radius-xl mt-4" style="background-image: url('https://images.unsplash.com/photo-1531512073830-ba890ca4eba2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1920&q=80');">
        <span class="mask  bg-gradient-secondary opacity-6"></span>
      </div>
      <div class="card card-body mx-3 mx-md-4 mt-n6">
        <form action="/insertBoard" method="post" id="boardFrm" name="boardFrm">
        <div class="row gx-4 mb-2">
		  <div class="col-md-12">
			<div class="input-group input-group-outline mb-4">
			  <label class="form-label">제목을 입력해주세요.</label>
			  <input type="hidden" value="${member.mem_id }" name="mem_id" id="mem_id">
			  <input type="hidden" value="${board.bono }" name="bono" id="bonos">
			  <input type="text" class="form-control" name="botitle" value="${board.botitle }" id="title">
			</div>
		  </div>
		  <div class="col-md-12">
		    <div class="input-group input-group-outline mb-4">
			  <textarea class="form-control" rows="20" id="content" name="bocontent">${board.bocontent }</textarea>
		    </div>
		  </div>
		  <div class="col-md-12">　</div>
		  <div class="col-md-12">
		  	<input type="button" value="${name }" class="btn btn-primary" id="insert" > 
		    <c:if test="${modify eq 'm' }">
		    <a href="/boardDetail/${board.bono}">
		    <button type="button" class="btn btn-danger" id="cancle">취소</button>
		    </a>
		    </c:if>
		    <c:if test="${modify ne 'm' }">
		    <button type="button" class="btn btn-danger" id="cancle">취소</button>
		    </c:if>
		    <button type="button" class="btn btn-info" id="golist">목록</button>
		  </div>
        </div>
        </form>
      </div>
    </div>
  </main>
  <div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
      <i class="fa-solid fa-microphone" id="micicon"></i>
    </a>
        <!-- End Toggle Button -->
      </div>
      <hr class="horizontal dark my-1">
      <div class="card-body pt-sm-3 pt-0">
        <!-- Sidebar Backgrounds -->
        <div>
          <h6 class="mb-0">Sidebar Colors</h6>
        </div>
        <a href="javascript:void(0)" class="switch-trigger background-color">
          <div class="badge-colors my-2 text-start">
            <span class="badge filter bg-gradient-primary active" data-color="primary" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-success" data-color="success" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-warning" data-color="warning" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-danger" data-color="danger" onclick="sidebarColor(this)"></span>
          </div>
        </a>
        <hr class="horizontal dark my-3">
        <div class="mt-2 d-flex">
          <h6 class="mb-0">Light / Dark</h6>
          <div class="form-check form-switch ps-0 ms-auto my-auto">
            <input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version" onclick="darkMode(this)">
          </div>
        </div>
        <hr class="horizontal dark my-sm-4">
      </div>
    </div>
  </div>
    <input type="hidden" id="start" value="시작">
	<input type="hidden" id="stop" value="멈춤">
  
  <!--   Core JS Files   -->
  <script src="../assets/js/core/popper.min.js"></script>
  <script src="../assets/js/core/bootstrap.min.js"></script>
  <script src="../assets/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="../assets/js/plugins/smooth-scrollbar.min.js"></script>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<script>
const speech = new webkitSpeechRecognition();
  var win = navigator.platform.indexOf('Win') > -1;
  if (win && document.querySelector('#sidenav-scrollbar')) {
    var options = {
      damping: 0.5
    };
    Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
  }
  $(function() {
    $('#insert').on('click', function() {
      var title = $('#title').val();
      var content = $('#content').val();
      var mem_id = $('#getId').val();
     var boardFrm = $('#boardFrm');
     console.log($(this).val());
     if(title == ""){
    	 alert("제목을 입력해주세요");
    	 $('#title').focus();
    	 return false;
    	 
     }
     if(content == ""){
    	 alert("내용을 입력해주세요");
    	 $('#content').focus();
    	 return false;
     }
     
     if($(this).val()=="수정"){
    	 console.log($(this).val());
			$('#boardFrm').attr("action","/update.do");
		}
     
      boardFrm.submit();
    });
    $('#cancle').on('click', function() {
    	location.href= "/list.do"
    	
    });
    $('#golist').on('click', function() {
    	location.href= "/list.do"
    	
    })
 	  document.getElementById('stop').addEventListener('click', function() {
  	    speech.stop();
  	  });

  	  speech.addEventListener('result', function(event) {
  	    const transcript = event.results[0][0].transcript;
  	    console.log("원문"+ transcript);
  	    
	    if (transcript.includes('로그아웃')) {
	    	session.invalidate();
  	    }
	    if (transcript.includes('목록')) {
  	      location.href = "/list.do";
  	    }
  	    if (transcript.includes('제목')) {
  	    	var transtitle = newsentence(transcript);
  	    	console.log("가공문" + transtitle);
  	    	$('#title').attr('placeholder','');
  	    	$('#title').val(transtitle);
  	    }
  	    
  	  if (transcript.includes('내용')) {
  		var transcontent = newsentence(transcript);
  		console.log("가공문" +transcontent);
	      $('#content').val(transcontent);
	    }
  	  
  	  if (transcript.includes('등록')) {
    		console.log(transcontent);
    		boardFrm.submit();
  	    }
  	  });

  	  document.getElementById('micicon').addEventListener('click', function() {
  	    speech.start();
  	  });
  	  
  	  
  });
  document.addEventListener('keydown', function(event) {
	  if (event.shiftKey && event.which === 65) {
	    // `shift`와 `a`가 동시에 눌렸을 때 수행할 동작을 여기에 작성합니다.
		  speech.start();
	  }
	});
  
  function newsentence(transcript) {
	  var keyword = '';
	  if(transcript.includes('제목에')){
		  keyword = '제목에';
	  }else if(transcript.includes('내용에')){
		  keyword = '내용에';
	  }
	  const index = transcript.indexOf(keyword);
	  
	  if (index !== -1) {
	    const sentence = transcript.slice(index + keyword.length).trim();
	    return sentence;
	  }
	  
	  return '';
	}
</script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="../assets/js/material-dashboard.min.js?v=3.0.4"></script>
</body>

</html>