<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
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
	
	<h2>회원정보</h2>
	
	<input type="hidden" name="no" value="${dto.no }">
	
	아이디<br>
	<input type="text" name="id" id="id" style="width: 70%; height: 40px;" value="${dto.id }" readonly><br><br>
	이름<br>
	<input type="text" name="name" style="width: 70%; height: 40px;" value="${dto.name }" readonly><br><br>
	연락처<br>
	<input type="text" name="phone" style="width: 70%; height: 40px;" value="${dto.phone }" readonly><br><br>
	이메일<br>
	<input type="email" name="email" style="width: 70%; height: 40px;" value="${dto.email }" readonly><br><br>
	주소<br>
	<input type="text" name="addr" style="width: 70%; height: 40px;" value="${dto.addr }" readonly><br><br>
	등록일<br>
	<input type="text" name="regi_date" style="width: 70%; height: 40px;" value="${dto.regi_date }" readonly><br><br>
	
<%--	
 	|
	<a href="${path }/member_servlet/list.do">목록</a>
	|
	<a href="${path }/member_servlet/chuga.do">등록</a>
	|
	<a href="${path }/member_servlet/sujung.do?no=${dto.no }">수정</a>
	|
	<a href="${path }/member_servlet/sakje.do?no=${dto.no }">삭제</a>
	| 
--%>
	<div style="text-align: right; width: 70%;">
	|
	<a href="#" onClick="move('list.do', '');">목록</a>
	|
	<a href="#" onClick="move('chuga.do', '');">등록</a>
	|
	<a href="#" onClick="move('sujung.do', '${dto.no }');">수정</a>
	|
	<a href="#" onClick="move('sakje.do', '${dto.no }');">삭제</a>
	|
	</div>
</div>

<script>
	function move(value1, value2) {
		location.href="${path }/member_servlet/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>