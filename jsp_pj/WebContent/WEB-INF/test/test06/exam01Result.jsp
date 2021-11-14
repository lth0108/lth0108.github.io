<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test06</title>
</head>
<body>

<%@ include file="../../include/inc_menu.jsp" %>

<h2>test06Result</h2>

<h2>상품분류 : ${productBunryu }</h2>
<h2>상품이름 : ${productName }</h2>
<h2>상품가격 : ${productPrice }</h2>
<h2>할인률 : ${productSalePercent }</h2>
<h2>할인금액 : ${productSaleMoney }</h2>
<h2>제조자 : ${productCompany }</h2>



<hr>

</body>
</html>