<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글수정</title>
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
	<h2>게시글수정</h2>
	
	<form name="sujungForm">
	<input type="hidden" name="no" value="${dto.no }">
	<input type="hidden" name="ref" value="${dto.ref }">
	
	<table>
			<tr>
				<td><input type="text" name="subject" value="${dto.subject }" style="width: 100%; height: 25px;"></td>
			</tr>
			<tr>
				<td>
					<textarea style="width: 100%; height: 300px;" name="content">${dto.content }</textarea>
				</td>
			</tr>
			<tr>
				<td><input type="email" name="email" value="${dto.email }" style="width: 100%; height: 25px;"></td>
			</tr>
			<tr>
				<td>
					<input type="password" name="password" placeholder="비밀번호를 입력하세요!">
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<button type="button" onClick="list()">목록으로</button>
					<button type="button" onClick="sujung()">게시글수정</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<script>
	function list() {
		location.href="${path }/boardBasic_servlet/list.do";
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
		
		if(sujungForm.password.value == '') {
			alert("비밀번호를 입력하지 않았습니다!");
			sujungForm.password.focus();
			return;
		} 
		
		if(confirm("수정하시겠습니까?")) {
			sujungForm.action="${path }/boardBasic_servlet/sujungProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>