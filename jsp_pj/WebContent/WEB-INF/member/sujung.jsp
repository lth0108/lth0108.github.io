<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
</style>
</head>
<body>
<div id="wrap">

    <div id="header">
        <%@ include file="../include/inc_login.jsp" %>
    </div>
    
    <div id="blank">
    <br>
    </div>
    
    <div id="left">
		<%@ include file="../include/inc_menu.jsp" %>
    </div>
    
</div>
	
<div id="contents">

	<h2>회원수정</h2>
	
	<form name="sujungForm">
		<input type="hidden" name="no" value="${dto.no }">
		
		<div style="text-align: right; width: 70%;">
		<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
		</div>
		
		아이디<br>
		<input type="text" name="id" id="id" style="width: 70%; height: 40px;" value="${dto.id }" readonly><br><br>
		이름<br>
		<input type="text" name="name" style="width: 70%; height: 40px;" value="${dto.name }"><br><br>
		연락처<br>
		<input type="text" name="phone1" maxlength="3" style="width: 22%; height: 40px;">-
		<input type="text" name="phone2" maxlength="4" style="width: 22%; height: 40px;">-
		<input type="text" name="phone3" maxlength="4" style="width: 22%; height: 40px;"><br><br>
		이메일<br>
		<input type="email" name="email" style="width: 70%; height: 40px;" value="${dto.email }"><br><br>
		주소<br>
		<input type="text" name="addr" style="width: 70%; height: 40px;" value="${dto.addr }"><br><br>
		등록일<br>
		<input type="text" name="regi_date" style="width: 70%; height: 40px;" value="${dto.regi_date }" readonly><br><br><br>
		
		<button type="button" onClick="sujung()" style="width: 70%; height: 40px;">회원수정</button><br><br>
		
	</form>
</div>

<script>
	function list() {
		location.href="${path }/member_servlet/list.do";
	}
	
	function sujung() {
		if(confirm("수정하시겠습니까?")) {
			sujungForm.action="${path }/member_servlet/sujungProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>