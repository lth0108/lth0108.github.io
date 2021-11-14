<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판등록(Mybatis)</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>게시판등록(Mybatis)</h2>
<table border="1" style="align-content: center;">
	<tr align="center">
		<td>제목</td>
		<td>${dto.subject }</td>
	</tr>
	<tr align="center">
		<td>작성자</td>
		<td>${dto.writer }</td>
	</tr>
	<tr align="center">
		<td>조회수</td>
		<td>${dto.hit }</td>
	</tr>
	<tr align="center">
		<td>내용</td>
		<td>${dto.content }</td>
	</tr>
<tr align="center">
		<td>이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr align="center">
		<td>등록일</td>
		<td>${dto.regi_date }</td>
	</tr>
</table>
	
<form name="replyForm">
<input type="hidden" name="no" value="${dto.no }">
	<c:forEach var="dto" items="${list }" varStatus="status">
		<c:forEach var="i" begin="2" end="${dto.re_step }" step="1">
		<table style="display: inline;">
				<tr>
					<td>&nbsp;&nbsp;[re]</td>
				</tr>
			</table>
		</c:forEach>
	</c:forEach>
</form>

<form name="commentForm">		
	<input type="hidden" name="no" value="${dto.no }">
</form>
|
<a href="#" onClick="move('list.do', '');">목록</a>
|
<a href="#" onClick="move('chuga.do', '');">글쓰기</a>
|
<a href="#" onClick="move('chuga.do', '${dto.no }');">댓글쓰기</a>
|
<a href="#" onClick="move('sujung.do', '${dto.no }');">수정</a>
|
<a href="#" onClick="move('sakje.do', '${dto.no }');">삭제</a>
|


<script>
	function move(value1, value2) {
		location.href="${path }/boardBasicMybatis_servlet/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>