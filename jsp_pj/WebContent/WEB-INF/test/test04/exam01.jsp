<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="../../include/inc_menu.jsp" %>

<h2>test04</h2>

<form name="form" method="post" action="${path }/test_servlet/test04Proc.do">
이름 : <input type="text" name="name"> <br>
주민번호 : <input type="text" name="jumin"> <br>
<button type="submit">확인</button>
</form>

</body>
</html>