<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적처리</title>
</head>
<body>

<%@ include file="../../include/inc_menu.jsp" %>

<h2>성적처리 : test09</h2>

이름 &nbsp; &nbsp;
국어 &nbsp;
영어 &nbsp;
수학 &nbsp;
과학 &nbsp;
역사 &nbsp;
총점 &nbsp;
평균 &nbsp;
등급 &nbsp; <br>
==========================================<br>
${dto.name } &nbsp; &nbsp;
${dto.kor } &nbsp; &nbsp;
${dto.eng } &nbsp; &nbsp;
${dto.mat } &nbsp; &nbsp;
${dto.sci } &nbsp; &nbsp;
${dto.his } &nbsp; &nbsp;
${dto.tot } &nbsp; &nbsp;
${dto.avg } &nbsp; &nbsp;
${dto.grade } &nbsp; &nbsp;

</body>
</html>