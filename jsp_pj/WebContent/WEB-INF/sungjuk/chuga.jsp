<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적등록</title>
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
    
    <div id="blank">
    <br>
    </div>
    
    <div id="left">
		<%@ include file="../include/inc_menu.jsp" %>
    </div>
    
</div>
	
<div id="contents">
	
	<h2>성적등록</h2>
	
	<form name="chugaForm">
		<div style="text-align: right; width: 70%;">
		<button type="button" onClick="list()" style="float: right;">목록으로</button><br>
		</div>	
		
		아이디(이름)<br>
		<select name="id" style="width: 70%; height: 40px;">
			<c:forEach var="dto" items="${listM }">
				<option value="${dto.id }_${dto.name }">
					${dto.id }(${dto.name })</option>
			</c:forEach>
		</select><br><br>
		시험명<br>
		<input type="radio" name="sihum_name" value="middle" checked > 중간
		<input type="radio" name="sihum_name" value="final"> 기말<br><br>
		국어<br>
		<input type="number" name="kor" style="width: 70%; height: 40px;"><br><br>
		영어<br>
		<input type="number" name="eng" style="width: 70%; height: 40px;"><br><br>
		수학<br>
		<input type="number" name="mat" style="width: 70%; height: 40px;"><br><br>
		과학<br>
		<input type="number" name="sci" style="width: 70%; height: 40px;"><br><br>
		역사<br>
		<input type="number" name="his" style="width: 70%; height: 40px;"><br><br>
			
		<button type="button" onClick="chuga()" style="width: 70%; height: 40px;">성적등록</button>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/sungjuk_servlet/list.do";
	}
	
	function chuga() {
		if(chugaForm.id.value == '') {
			alert("아이디을 입력해주세요!")
			chugaForm.id.focus();
			return;
		}
		
		if(chugaForm.name.value == '') {
			alert("이름을 입력해주세요!")
			chugaForm.name.focus();
			return;
		}
		
		if(chugaForm.sihum_name.value == '') {
			alert("시험명을 입력해주세요!")
			chugaForm.sihum_name.focus();
			return;
		}
		
		if(chugaForm.kor.value == '') {
			alert("국어점수를 입력해주세요!")
			chugaForm.kor.focus();
			return;
		} else if(!(chugaForm.kor.value >= 0 && chugaForm.kor.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			chugaForm.kor.value = '';
			chugaForm.kor.focus();
			return;
		}
		
		if(chugaForm.eng.value == '') {
			alert("영어점수를 입력해주세요!")
			chugaForm.eng.focus();
			return;
		} else if(!(chugaForm.eng.value >= 0 && chugaForm.eng.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			chugaForm.eng.value = '';
			chugaForm.eng.focus();
			return;
		}
		
		if(chugaForm.mat.value == '') {
			alert("수학점수를 입력해주세요!")
			chugaForm.mat.focus();
			return;
		} else if(!(chugaForm.mat.value >= 0 && chugaForm.mat.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			chugaForm.mat.value = '';
			chugaForm.mat.focus();
			return;
		}
		
		if(chugaForm.sci.value == '') {
			alert("과학점수를 입력해주세요!")
			chugaForm.sci.focus();
			return;
		} else if(!(chugaForm.sci.value >= 0 && chugaForm.sci.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			chugaForm.sci.value = '';
			chugaForm.sci.focus();
			return;
		}
		
		if(chugaForm.his.value == '') {
			alert("역사점수를 입력해주세요!")
			chugaForm.his.focus();
			return;
		} else if(!(chugaForm.his.value >= 0 && chugaForm.his.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			chugaForm.his.value = '';
			chugaForm.his.focus();
			return;
		}
	

		if(confirm("성적등록을 하시겠습니까?")) {
			chugaForm.method="post";
			chugaForm.action="${path }/sungjuk_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
