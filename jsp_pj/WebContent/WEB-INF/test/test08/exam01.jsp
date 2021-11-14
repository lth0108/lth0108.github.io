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

<h2>test08</h2>

<form name="form" action="${path }/test_servlet/test08Proc.do" method="post">
상품분류 <input type="text" name="productBunryu">  <br>
상품이름 <input type="text" name="productName">  <br>
상품가격 <input type="text" name="productPrice">  <br>
할인률 <input type="text" name="productSalePercent">  <br>
할인금액 <input type="text" name="productSaleMoney">  <br>
제조자  <input type="text" name="productCompany"> <br>
<button type="submit">확인</button>
</form>


</body>
</html>