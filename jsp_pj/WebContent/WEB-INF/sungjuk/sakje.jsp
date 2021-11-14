<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적삭제</title>
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
	
	<h2>성적삭제</h2>
	
	<form name="sakjeForm">
	<input type="hidden" name="no" value="${dto.no }">
	
		<button type="button" onClick="list();" style="float:right; ">목록으로</button><br><br>
	
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
		</table><br><br>
		<button type="button" onClick="sakje()" style="width: 100%; height: 40px;">성적삭제</button>
	
	</form>
	
	<script>
		function list() {
			location.href="${path }/sungjuk_servlet/list.do";
		}
		
		function sakje() {
			if(confirm("삭제하시겠습니까?")) {
				sakjeForm.action="${path }/sungjuk_servlet/sakjeProc.do";
				sakjeForm.method="post";
				sakjeForm.submit();
			}
		}
	</script>
</div>

</body>
</html>