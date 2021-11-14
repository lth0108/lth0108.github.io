<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록등록</title>
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
	
	<h2>방명록등록</h2>
	
	<form name="chugaForm">
		<div style="text-align: right; width: 70%;">
		<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
		</div>
		
		이름(*)<br>
		<input type="text" name="name" id="name" style="width: 70%; height: 40px;"><br><br>
		이메일<br>
		<input type="email" name="email" style="width: 70%; height: 40px;"><br><br>
		비밀번호(*)<br>
		<input type="password" name="password" style="width: 70%; height: 40px;"><br><br>
		비밀번호확인(*)<br>
		<input type="password" name="passwordChk" style="width: 70%; height: 40px;"><br><br>
		내용(*)<br>
		<textarea name="content" id="content" style="width: 70%; height: 200px;"></textarea><br>
		
		<button type="button" onClick="chuga()" style="width: 70%; height: 40px;">방명록등록</button>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/guestBook_servlet/list.do";
	}
	
	function chuga() {
		if($("#name").val().trim() == "") {
			alert("이름을 입력해주세요!")
			$("#name").val("");
			$("#name").focus();
			return;
		}
				
		if($("#email").val().trim() == "") {
			alert("이메일을 입력해주세요!")
			$("#email").val("");
			$("#email").focus();
			return;
		}
		
		if($("#password").val().trim() == "") {
			alert("비밀번호를 입력해주세요!")
			$("#password").val("");
			$("#password").focus();
			return;
		}
		
		if($("#passwordChk").val().trim() == "") {
			alert("비밀번호확인을 입력해주세요!")
			$("#passwordChk").val("");
			$("#passwordChk").focus();
			return;
		}
		
		if($("#content").val().trim() == "") {
			alert("내용을 입력해주세요!")
			$("#content").val("");
			$("#content").focus();
			return;
		}
		
		if($("#password").val().trim() != $("#passwordChk").val().trim()) {
			alert("비밀번호가 다릅니다!");
			$("#password").val("");
			$("#passwordChk").val("");
			$("#password").focus();
			return;
		}
		
		if(confirm("상품을 등록하시겠습니까?")) {
			chugaForm.method="post";
			chugaForm.action="${path }/guestBook_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
