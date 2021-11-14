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

<h2>test08Result</h2>

<h2>상품분류 : ${map.productBunryu }</h2>
<h2>상품이름 : ${map.productName }</h2>
<h2>상품가격 : ${map.productPrice }</h2>
<h2>할인률 : ${map.productSalePercent }</h2>
<h2>할인금액 : ${map.productSaleMoney }</h2>
<h2>제조자 : ${map.productCompany }</h2>


</body>
</html>