<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록수정</title>
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

	<h2>방명록수정</h2>
	
	<form name="sujungForm">
	<input type="hidden" name="no" value="${dto.no }">
	
	<div style="text-align: right; width: 70%;">
	<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
	</div><br>
	
	<div style="text-align: right; width: 70%;">${dto.regi_date }</div>
	이름<br>
	<input type="text" name="name" id="name" style="width: 70%; height: 40px;" value="${dto.name }"><br><br>
	이메일<br>
	<input type="email" name="email" style="width: 70%; height: 40px;" value="${dto.email }"><br><br>
	비밀번호<br>
	<input type="password" name="password" style="width: 70%; height: 40px;"><br><br>
	내용<br>
	<textarea name="content" id="content" style="width: 70%; height: 200px;">${dto.content }</textarea><br><br>
	
	<div style="text-align: right; width: 70%;">
	<button type="button" onClick="sujung()">방명록수정</button>
	</div>
	</form>
</div>

<script>


	function list() {
		location.href="${path }/guestBook_servlet/list.do";
	}
	
	function sujung() {
		if(sujungForm.name.value == '') {
			alert("이름을 입력하지 않았습니다!");
			sujungForm.name.focus();
			return;
		}
		
		if(sujungForm.email.value == '') {
			alert("이메일을 입력하지 않았습니다!");
			sujungForm.email.focus();
			return;
		} 
		
		if(sujungForm.content.value == '') {
			alert("내용을 입력하지 않았습니다!");
			sujungForm.content.focus();
			return;
		}
		
		if(sujungForm.password.value == '') {
			alert("비밀번호를 입력해주세요!");
			sujungForm.password.focus();
			return;
		}
		
		if(confirm("수정하시겠습니까?")) {
			sujungForm.action="${path }/guestBook_servlet/sujungProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>