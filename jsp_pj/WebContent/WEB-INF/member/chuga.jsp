<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
</style>
<script>
/* $(function() {
		alert('추가페이지');
		$("#id").focus();
		$("#id").val('asd');		
	}); */
</script>

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
	<h2>회원등록</h2>
	
	<form name="chugaForm">
		<div style="text-align: right; width: 70%;">
		<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
		</div>
		
		아이디<br>
		<input type="text" name="id" id="id" style="width: 70%; height: 40px;"><br><br>
		비밀번호<br>
		<input type="password" name="password" style="width: 70%; height: 40px;"><br><br>
		비밀번호확인<br>
		<input type="password" name="passwordChk" style="width: 70%; height: 40px;"><br><br>
		이름<br>
		<input type="text" name="name" style="width: 70%; height: 40px;"><br><br>
		연락처<br>
		<input type="text" name="phone1" maxlength="3" style="width: 22%; height: 40px;">-
		<input type="text" name="phone2" maxlength="4" style="width: 22%; height: 40px;">-
		<input type="text" name="phone3" maxlength="4" style="width: 22%; height: 40px;"><br><br>
		이메일<br>
		<input type="text" name="email" style="width: 70%; height: 40px;"><br><br>
		주소<br>
		<input type="text" name="address" style="width: 70%; height: 40px;"><br><br><br>	
			
		<button type="button" onClick="chuga()" style="width: 70%; height: 40px;">회원등록</button>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/member_servlet/list.do";
	}
	
	function chuga() {
		if(chugaForm.id.value == '') {
			alert("아이디를 입력해주세요!")
			chugaForm.id.focus();
			return;
		}
		
		if(chugaForm.password.value == '') {
			alert("비밀번호를 입력해주세요!")
			chugaForm.password.focus();
			return;
		}
		
		if(chugaForm.passwordChk.value == '') {
			alert("비밀번호확인을 입력해주세요!")
			chugaForm.passwordChk.focus();
			return;
		}
		
		if(chugaForm.name.value == '') {
			alert("이름을 입력해주세요!")
			chugaForm.name.focus();
			return;
		}
		
		if(chugaForm.password.value != chugaForm.passwordChk.value) {
			alert("입력하신 비밀번호가 다릅니다!")
			chugaForm.password.value='';
			chugaForm.passwordChk.value='';
			chugaForm.password.focus();
			return;
		}

		if(confirm("회원등록을 하시겠습니까?")) {
			chugaForm.method="post";
			chugaForm.action="${path }/member_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
