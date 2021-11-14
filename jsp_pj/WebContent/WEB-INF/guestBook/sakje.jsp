<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록삭제</title>
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
	
	<h2>방명록삭제</h2>
	
	<form name="sakjeForm">
	<input type="hidden" name="no" value="${dto.no }">
	
	<div style="text-align: right; width: 70%;">
	<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
	</div><br>
	
	<div style="text-align: right; width: 70%;">${dto.regi_date }</div>
	이름<br>
	<input type="text" name="name" id="name" style="width: 70%; height: 40px;" value="${dto.name }" readonly><br><br>
	이메일<br>
	<input type="email" name="email" style="width: 70%; height: 40px;" value="${dto.email }" readonly><br><br>
	비밀번호<br>
	<input type="password" name="password" style="width: 70%; height: 40px;"><br><br>
	내용<br>
	<textarea name="content" id="content" style="width: 70%; height: 200px;" readonly>${dto.content }</textarea><br><br>
	
	<div style="text-align: right; width: 70%;">
	<button type="button" onClick="sakje()">방명록삭제</button>
	</div>
	
	</form>
</div>
<script>
	function list() {
		location.href="${path }/guestBook_servlet/list.do";
	}
	
	function sakje() {
		if(sakjeForm.password.value == '') {
			alert("비밀번호를 입력해주세요!");
			sakjeForm.password.focus();
			return;
		}
		
		if(confirm("삭제하시겠습니까?")) {
			sakjeForm.action="${path }/guestBook_servlet/sakjeProc.do";
			sakjeForm.method="post";
			sakjeForm.submit();
		}
	}
</script>

</body>
</html>