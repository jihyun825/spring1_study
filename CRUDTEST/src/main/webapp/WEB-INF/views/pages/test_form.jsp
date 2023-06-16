<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#micicon{
color : black;
width: 50px;
height: 50px;
}
</style>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/annyang/2.6.1/annyang.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<title>Insert title here</title>
</head>
<body>
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
<form method="post" action="/insertBoard" id="boardFrm" name="boardFrm" >
	 <div class="input-container">
		  <input type="text" class="form-control" name="botitle" placeholder="제목을 입력해주세요." value="${board.botitle }" id="title">
		  <i class="fa-solid fa-microphone" id="micicon"></i>
	</div>
	<textarea class="form-control" rows="20" id="content" name="bocontent">${board.bocontent }</textarea>
	   	<input type="hidden" id="start" value="시작">
		<input type="hidden" id="stop" value="멈춤">
</form>
</body>
<script type="text/javascript">
const speech = new SpeechRecognition();
$('#stop').on('click', function() {
  speech.stop();
});

speech.addEventListener('result', function(event) {
  const transcript = event.results[0][0].transcript;
  console.log(transcript);

  if (transcript.includes('제목에')) {
    var transtitle = newsentence(transcript);
    console.log(transtitle);
    $('#title').attr('placeholder', '');
    $('#title').val(transtitle);
  } else if (transcript.includes('내용')){
	  var transcontent = newsentence(transcript);
	    console.log(transcontent);
	    $('#content').val(transcontent);
	  }else if(transcript.includes('본문')) {
    var transcontent = newsentence(transcript);
    console.log(transcontent);
    $('#content').val(transcontent);
  }
  
  if (transcript.includes('로그아웃')) {
  	session.invalidate();
    }
  
});

$('#micicon').on('click', function() {
  speech.start();
});

document.addEventListener('keydown', function(event) {
	  if (event.shiftKey && event.which === 65) {
	    // `shift`와 `a`가 동시에 눌렸을 때 수행할 동작을 여기에 작성합니다.
		  speech.start();
	  }
	});

function newsentence(transcript) {
	  var keyword = '';

	  if (transcript.includes('제목에')) {
	    keyword = '제목에';
	  } else if (transcript.includes('내용에')) {
	    keyword = '내용에';
	  } else if (transcript.includes('내용의')) {
	    keyword = '내용의';
	  } else if (transcript.includes('내용이')) {
	    keyword = '내용이';
	  } else if (transcript.includes('본문에')) {
	    keyword = '본문에';
	  } else if (transcript.includes('본문')) {
	    keyword = '본문';
	  }

	  const index = transcript.indexOf(keyword);

	  if (index !== -1) {
	    const sentence = transcript.slice(index + keyword.length).trim();
	    return sentence;
	  }

	  return '';
	}
</script>
</html>