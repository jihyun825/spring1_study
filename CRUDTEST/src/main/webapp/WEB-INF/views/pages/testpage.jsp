<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/webkitSpeechRecognition/0.1.0/webkitSpeechRecognition.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/annyang/2.6.1/annyang.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<title>Insert title here</title>
</head>
<body>
<i class="fa-solid fa-microphone" id="micicon"></i>
<button id="stopButton">음성 인식 종료</button>
</body>
<script type="text/javascript">
var recognition = new webkitSpeechRecognition(); // Chrome 브라우저를 사용하는 경우
recognition.lang = "ko-KR";
//음성 인식 명령어와 처리 함수 정의
var commands = {
  '안녕': function() {
    console.log('안녕하세요!');
  },
  // 다른 음성 인식 명령어와 처리 함수도 추가 가능
};

// 음성 인식 명령어 등록
annyang.addCommands(commands);

$('#micicon').on('click', function() {
  annyang.start();
});

$('#stopButton').on('click', function() {
  annyang.abort();
});

// 음성 인식 시작
</script>
</html>
