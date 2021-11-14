<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적수정</title>
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
	
	<h2>성적수정</h2>

	<form name="sujungForm">
		<input type="hidden" name="no" value="${dto.no }">
		
		<button type="button" onClick="list();" style="float:right; ">목록으로</button><br><br>
		
		<table style="text-align: center;">
			<tr style="background-color: #dfdfdf">
				<th colspan="2">아이디</th><th colspan="2">이름</th><th>시험명</th>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td colspan="2">${dto.id }</td><td colspan="2">${dto.name }</td><td>${dto.sihum_name }</td>
			</tr>
			<tr style="background-color: #dfdfdf">
				<th>국어</th><th>영어</th><th>수학</th><th>과학</th><th>역사</th>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td><input type="number" name="kor" value="${dto.kor }" style="width: 20%"></td>
				<td><input type="number" name="eng" value="${dto.eng }" style="width: 20%"></td>
				<td><input type="number" name="mat" value="${dto.mat }" style="width: 20%"></td>
				<td><input type="number" name="sci" value="${dto.sci }" style="width: 20%"></td>
				<td><input type="number" name="his" value="${dto.his }" style="width: 20%"></td>
			</tr>
			<tr style="background-color: #dfdfdf">
				<td><td><th>총점</th><th>평균</th><th>등급</th>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td><td><td>${dto.tot }</td><td>${dto.avg }</td><td>${dto.grade }</td>
			</tr>
			<tr style="background-color: #f0f0f0">
				<th colspan="5" style="text-align: right;">등록일</th>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td colspan="5" style="text-align: right;">${dto.regi_date }</td>
			</tr>
		</table><br>
		<button type="button" onClick="sujung()" style="width: 100%; height: 40px;">성적수정</button>
		
	</form>
</div>

<script>


	function list() {
		location.href="${path }/sungjuk_servlet/list.do";
	}
	
	function sujung() {
		if(sujungForm.kor.value == '') {
			alert("국어점수를 입력해주세요!")
			sujungForm.kor.focus();
			return;
		} else if(!(sujungForm.kor.value >= 0 && sujungForm.kor.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			sujungForm.kor.value = '';
			sujungForm.kor.focus();
			return;
		}
		
		if(sujungForm.eng.value == '') {
			alert("영어점수를 입력해주세요!")
			sujungForm.eng.focus();
			return;
		} else if(!(sujungForm.eng.value >= 0 && sujungForm.eng.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			sujungForm.eng.value = '';
			sujungForm.eng.focus();
			return;
		}
		
		if(sujungForm.mat.value == '') {
			alert("수학점수를 입력해주세요!")
			sujungForm.mat.focus();
			return;
		} else if(!(sujungForm.mat.value >= 0 && sujungForm.mat.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			sujungForm.mat.value = '';
			sujungForm.mat.focus();
			return;
		}
		
		if(sujungForm.sci.value == '') {
			alert("과학점수를 입력해주세요!")
			sujungForm.sci.focus();
			return;
		} else if(!(sujungForm.sci.value >= 0 && sujungForm.sci.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			sujungForm.sci.value = '';
			sujungForm.sci.focus();
			return;
		}
		
		if(sujungForm.his.value == '') {
			alert("역사점수를 입력해주세요!")
			sujungForm.his.focus();
			return;
		} else if(!(sujungForm.his.value >= 0 && sujungForm.his.value <= 100)) {
			alert("0~100 사이의 값을 입력하세요")
			sujungForm.his.value = '';
			sujungForm.his.focus();
			return;
		}
	
		if(confirm("수정하시겠습니까?")) {
			sujungForm.method="post";
			sujungForm.action="${path }/sungjuk_servlet/sujungProc.do";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>