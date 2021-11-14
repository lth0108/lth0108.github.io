<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적정보</title>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
td { width: 60px; }
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
	
	<h2>성적정보</h2>
	<input type="hidden" name="no" value="${dto.no }">
	
	<table style="text-align: center;">
		<tr style="background-color: #dfdfdf">
			<th colspan="2">아이디</th><th colspan="2">이름</th><th>시험명</th>
		</tr>
		<tr style="background-color: #f0f0f0">
			<td colspan="2">${dto.id }</td><td colspan="2">${dto.name }</td><td>${dto.sihum_name }</td>
		</tr>
		<tr style="background-color: #dfdfdf">
			<th>국어</th><th>영어</th><th>수학</th><th>과학</th><th>역사</th>
		</tr>
		<tr style="background-color: #f0f0f0">
			<td>${dto.kor }</td><td>${dto.eng }</td><td>${dto.mat }</td><td>${dto.sci }</td><td>${dto.his }</td>
		</tr>
		<tr style="background-color: #dfdfdf">
			<td><td><th>총점</th><th>평균</th><th>등급</th>
		</tr>
		<tr style="background-color: #f0f0f0">
			<td><td><td>${dto.tot }</td><td>${dto.avg }</td><td>${dto.grade }</td>
		</tr>
		<tr style="background-color: #f0f0f0">
			<th colspan="5" style="text-align: right;">등록일</th>
		</tr>
		<tr style="background-color: #f0f0f0">
			<td colspan="5" style="text-align: right;">${dto.regi_date }</td>
		</tr>
	</table>
	
	<div style="text-align: right; width: 100%;">
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
	<br><br>
</div>

<script>
	function move(value1, value2) {
		location.href="${path }/sungjuk_servlet/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>