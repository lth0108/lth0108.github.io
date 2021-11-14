<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록정보</title>
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

	<h2>방명록정보</h2>
	
	<input type="hidden" name="no" value="${dto.no }">
	
	<div style="text-align: right; width: 70%;">${dto.regi_date }</div>
	이름<br>
	<input type="text" name="name" id="name" style="width: 70%; height: 40px;" value="${dto.name }" readonly><br><br>
	이메일<br>
	<input type="email" name="email" style="width: 70%; height: 40px;" value="${dto.email }" readonly><br><br>
	내용<br>
	<textarea name="content" id="content" style="width: 70%; height: 200px;" readonly>${dto.content }</textarea><br>
	
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
		location.href="${path }/guestBook_servlet/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>