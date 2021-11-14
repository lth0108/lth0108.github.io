<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판수정(Mybatis)</title>
</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>게시판수정(Mybatis)</h2>

<form name="sujungForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="ref" value="${dto.ref }">
<table border="1">
	<tr align="center">
		<td>제목</td>
		<td><input type="text" name="subject" value="${dto.subject }"></td>
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
		<td>
			<textarea style="width: 100%; height: 100%;" name="content">${dto.content }</textarea>
		</td>
	</tr>
	<tr align="center">
		<td>이메일</td>
		<td><input type="email" name="email" value="${dto.email }"></td>
	</tr>
	<tr align="center">
		<td>등록일</td>
		<td>${dto.regi_date }</td>
	</tr>
	<tr align="center">
		<td>비밀번호</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<button type="button" onClick="list()">목록으로</button>
			<button type="button" onClick="sujung()">게시글수정</button>
		</td>
	</tr>
</table>
</form>

<script>
	function list() {
		location.href="${path }/boardBasicMybatis_servlet/list.do";
	}
	
	function sujung() {
		if(sujungForm.subject.value == '') {
			alert("제목을 입력하지 않았습니다!");
			sujungForm.subject.focus();
			return;
		}
		
		if(sujungForm.content.value == '') {
			alert("내용을 입력하지 않았습니다!");
			sujungForm.content.focus();
			return;
		}
		
		if(sujungForm.email.value == '') {
			alert("이메일을 입력하지 않았습니다!");
			sujungForm.email.focus();
			return;
		} 
		
		if(confirm("수정하시겠습니까?")) {
			sujungForm.action="${path }/boardBasicMybatis_servlet/sujungProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>