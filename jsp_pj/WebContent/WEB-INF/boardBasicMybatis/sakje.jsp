<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판삭제(Mybatis)</title>
</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>게시판삭제(Mybatis)</h2>

<form name="sakjeForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="ref" value="${dto.ref }">
<table border="1">
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
		<td>비밀번호</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr align="center">
		<td>등록일</td>
		<td>${dto.regi_date }</td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<button type="button" onClick="list()">목록으로</button>
			<button type="button" onClick="sakje()">게시글삭제</button>
		</td>
	</tr>
</table>
</form>

<script>
	function list() {
		location.href="${path }/boardBasicMybatis_servlet/list.do";
	}
	
	function sakje() {
		if(confirm("게시글을 삭제하시겠습니까?")) {
			sakjeForm.action="${path }/boardBasicMybatis_servlet/sakjeProc.do";
			sakjeForm.method="post";
			sakjeForm.submit();
		}
	}
</script>

</body>
</html>