<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판등록</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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
	
	<h2>게시판등록</h2>
	
	<form name="chugaForm">
		<table>
			<tr>
				<td><input type="text" name="subject" placeholder="제목을 입력하세요!" style="width: 100%; height: 25px;"></td>
			</tr>
			<tr>
				<td>
					<textarea style="width: 100%; height: 300px;" name="content" placeholder="내용을 입력하세요!"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="email" name="email" placeholder="이메일을 입력하세요!" style="width: 100%; height: 25px;"></td>
			</tr>
			<tr>
				<td><input type="password" name="password" placeholder="비밀번호를 입력하세요!"></td>
			</tr>
			<tr>
				<td><input type="password" name="passwordChk" placeholder="비밀번호확인을 입력하세요!"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<button type="button" onClick="list()">목록으로</button>
					<button type="button" onClick="chuga()">게시글등록</button>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/boardBasic_servlet/list.do";
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
			chugaForm.action="${path }/boardBasic_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
