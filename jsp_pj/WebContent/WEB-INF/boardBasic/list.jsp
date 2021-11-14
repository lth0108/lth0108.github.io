<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록</title>
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
	<h2>게시판목록</h2>
	<table>
		<tr style="background-color: #dfdfdf">
			<td>제목</td>
			<td>작성자</td>
			<td>등록날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr style="background-color: #f0f0f0">
				<td style="width: 70%;"><a href="${path }/boardBasic_servlet/view.do?no=${dto.no }">○${dto.subject }</a>
				<input type="hidden" name="ref" value="${dto.ref }">
				</td>
				<td>${dto.writer }</td>
				<td>${dto.regi_date }</td>
				<td>${dto.hit }</td>
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="6" style="width:300px; height: 100px;">등록된 내용이 없습니다!</td>
			</tr>
		</c:if>
	</table>
	<button type="button" onClick="move('chuga.do', '');" style="float: right;">글쓰기</button>
</div>

<script>
	function move(value, value2) {
		location.href="${path}/boardBasic_servlet/" + value;
	}
</script>
</body>
</html>