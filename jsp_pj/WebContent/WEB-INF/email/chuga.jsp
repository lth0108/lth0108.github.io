<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>회원등록</h2>

<form name="chugaForm">
	<table border="1">
		<tr>
			<td>발신자 이름</td>
			<td><input type="text" name="fromName" id="fromName"></td>
		</tr>
		<tr>
			<td>발신자 이메일</td>
			<td><input type="text" name="fromEmail" id="fromEmail"></td>
		</tr>
		<tr>
			<td>수신자 이메일</td>
			<td><input type="text" name="toEmail" id="toEmail"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" id="subject" value="${dto.subject }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" id="content" style="width: 300px; height: 100px;"" wrap="hard"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" id="btnSave">발송하기</button>
			</td>
		</tr>
	</table>
</form>

<script>
	$(document).ready(function() {
		$("#fromName").focus();
		
		$("#btnSave").click(function() {
			if(confirm('발송하시겠습니까?')) {
				chugaForm.method = "post";
				chugaForm.action = "${path}/email_servlet/chugaProc.do";
				chugaForm.submit();
			}
		});
	});
</script>
</body>
</html>
