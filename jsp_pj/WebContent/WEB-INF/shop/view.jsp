<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
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
	<div style="float: right;">
		<button type="button" onClick="move('list');">쇼핑하기</button>
		<button type="button" onClick="move('market');">장바구니</button>
	</div>
<form name="form">
	<h2>상품정보</h2>
	
	<div style="width: 50%; float: left;">
	<c:set var="img1" value="${fn:split(dto_product.productImgInfo,'|')[0]}"></c:set>
			<c:set var="img1_file" value="${fn:split(img1,'?')[1]}"></c:set>
		<c:choose>
			<c:when test="${img1 == '-' }">
				이미지 없음
			</c:when>
			<c:otherwise>
				<img src="${path }/attach/product_img/${img1_file }" width="400px;" height="400px;">
			</c:otherwise>
		</c:choose>
	</div>
	
	<div style="width:50%; float: left;">
		<h3>${dto_product.name }</h3><hr>
		<table>
			<tr>
				<td><b>상품요약정보</b></td>
				<td>${dto_product.description }</td>
			</tr>
			<tr>
				<td><b>판매가</b></td>
				<td>${dto_product.price }</td>
			</tr>
		</table>
		<hr>
		(최소주문수량 1개이상 / 최대주문수량 20개이하)<br>
		<input type=hidden name="productNo" value="${dto_product.no }">
			<table>
				<tr>
					<td>
						수량 : <input type="hidden" name="sell_price" value="${dto_product.price }">
						<input type="text" name="amount" value="1" size="3" onchange="change();" readonly>
						<input type="button" value=" + " onclick="add();"><input type="button" value=" - " onclick="del();"><br>
					</td>
				</tr>
				<tr>
					<td>
						금액 : <input type="text" name="sum" size="11" readonly value="${dto_product.price }">원
					</td>
				</tr>
			</table>
			<br>
			<button type="button" onClick="productBuy();" style="width: 180px; height: 50px;">바로구매</button>
			<button type="button" onClick="marketIn();" style="width: 180px; height: 50px;">장바구니담기</button>
	</div>
</form>	
<script>
var sell_price;
var amount;

function init () {
	sell_price = document.form.sell_price.value;
	amount = document.form.amount.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.amount;
	sum = document.form.sum;
	if(hm.value < 20) {
		hm.value ++ ;

		sum.value = parseInt(hm.value) * ${dto_product.price};
	}
}

function del () {
	hm = document.form.amount;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * ${dto_product.price};
		}
}

function change () {
	hm = document.form.amount;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
}  
</script>
	
</div>
<script>
	function marketIn() {
/* 		if(marketForm.amount.value == '') {
			alert("상품 수량을 입력하세요!")
			return;
		} */
		
		form.method="post";
		form.action="${path }/shop_servlet/marketChuga.do";
		form.submit();
	}
	
	function productBuy() {
		form.method="post";
		form.action="${path }/shop_servlet/buyProc.do";
		form.submit();
	}
	
	function move(value1) {
		location.href="${path }/shop_servlet/"+ value1 +".do";
	}
</script>

</body>
</html>