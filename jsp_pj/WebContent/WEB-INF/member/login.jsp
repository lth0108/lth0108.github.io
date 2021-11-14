<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
</style>
</head>
<body>
<div id="wrap">

    <div id="header">
        <%@ include file="../include/inc_login.jsp" %>
    </div>
    
	<div id="blank"><br></div>

    <div id="left">
		<%@ include file="../include/inc_menu.jsp" %>
    </div>
</div>

<div id="contents">

	<h2>로그인</h2>
	<form name="loginForm">
	<input type="text" name="id" id="id" style="width: 70%; height: 40px;" placeholder="아이디"><br><br>
	<input type="password" name="password" id="password" style="width: 70%; height: 40px;" placeholder="비밀번호"><br><br>
	<button type="button" onClick="login()" style="width: 70%; height: 40px;">로그인</button>
	</form>
</div>

<script>
	function login() {
		if($("#id").val().trim() == "") {
			alert("아이디를 입력해주세요!")
			$("#id").val("");
			$("#id").focus();
			return;
		}
	
		if($("#password").val().trim() == "") {
			alert("비밀번호를 입력해주세요!")
			$("#password").val("");
			$("#password").focus();
			return;
		}
		
		if(confirm("로그인 하시겠습니까?")) {
			loginForm.method="post";
			loginForm.action="${path }/member_servlet/loginProc.do";
			loginForm.submit();
		}
	}
	
</script>

</body>
</html>