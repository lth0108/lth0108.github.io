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

<h2>성적처리 : test10</h2>

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
${map.name } &nbsp; &nbsp;
${map.kor } &nbsp; &nbsp;
${map.eng } &nbsp; &nbsp;
${map.mat } &nbsp; &nbsp;
${map.sci } &nbsp; &nbsp;
${map.his } &nbsp; &nbsp;
${map.tot } &nbsp; &nbsp;
${map.avg } &nbsp; &nbsp;
${map.grade } &nbsp; &nbsp;

<br>
==========================================<br>
${map.dto.name } &nbsp; &nbsp;
${map.dto.kor } &nbsp; &nbsp;
${map.dto.eng } &nbsp; &nbsp;
${map.dto.mat } &nbsp; &nbsp;
${map.dto.sci } &nbsp; &nbsp;
${map.dto.his } &nbsp; &nbsp;
${map.dto.tot } &nbsp; &nbsp;
${map.dto.avg } &nbsp; &nbsp;
${map.dto.grade } &nbsp; &nbsp;

</body>
</html>