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

<form name="name" method="post" action="${path }/test_servlet/test09Proc.do">
이름 : <input type="text" name="name"> <br>
국어 : <input type="number" name="kor"> <br>
영어 : <input type="number" name="eng"> <br>
수학 : <input type="number" name="mat"> <br>
과학 : <input type="number" name="sci"> <br>
역사 : <input type="number" name="his"> <br>
<button type="submit">확인</button> <br>
</form>
</body>
</html>