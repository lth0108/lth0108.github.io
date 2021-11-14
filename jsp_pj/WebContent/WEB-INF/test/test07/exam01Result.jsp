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

<h2>test07</h2>

<h2>test07Result</h2>

<h2>상품분류 : ${dto.productBunryu }</h2>
<h2>상품이름 : ${dto.productName }</h2>
<h2>상품가격 : ${dto.productPrice }</h2>
<h2>할인률 : ${dto.productSalePercent }</h2>
<h2>할인금액 : ${dto.productSaleMoney }</h2>
<h2>제조자 : ${dto.productCompany }</h2>


</body>
</html>