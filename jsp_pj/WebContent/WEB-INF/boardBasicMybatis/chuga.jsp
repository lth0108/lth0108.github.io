<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>게시판등록(Mybatis)</h2>

<form name="chugaForm">
	<input type="hidden" name="no" value="${dto.no }">
	<table border="1">
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea style="width: 100%; height: 100%;" name="content"></textarea>
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="email" name="email"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="passwordChk"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onClick="list()">목록으로</button>
				<button type="button" onClick="chuga()">게시글등록</button>
			</td>
		</tr>
	</table>
</form>

<script>
	function list() {
		location.href="${path }/boardBasicMybatis_servlet/list.do";
	}
	
	function chuga() {
		if(chugaForm.subject.value == '') {
			alert("제목을 입력해주세요!")
			chugaForm.subject.focus();
			return;
		}
		
		if(chugaForm.content.value == '') {
			alert("내용을 입력해주세요!")
			chugaForm.content.focus();
			return;
		}
		
		if(chugaForm.email.value == '') {
			alert("이메일을 입력해주세요!")
			chugaForm.email.focus();
			return;
		}
		
		if(chugaForm.password.value == '') {
			alert("비밀번호를 입력해주세요!")
			chugaForm.password.focus();
			return;
		}
		
		if(chugaForm.passwordChk.value == '') {
			alert("비밀번호 확인을 입력해주세요!")
			chugaForm.passwordChk.focus();
			return;
		}
		
		if(chugaForm.passwordChk.value != chugaForm.password.value) {
			alert("비밀번호가 다릅니다!")
			chugaForm.password.value = "";
			chugaForm.passwordChk.value = "";
			chugaForm.password.focus();
			return;
		}
		
		if(confirm("게시글을 등록하시겠습니까?")) {
			chugaForm.method="post";
			chugaForm.action="${path }/boardBasicMybatis_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
