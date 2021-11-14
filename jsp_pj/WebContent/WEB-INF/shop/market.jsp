<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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

	<h2>장바구니</h2>
	
	<div style="text-align: right;">
		${totalRecord }개의 상품이 담겨있습니다.
	</div>
	
	<form name="chkForm">
	<table>
		<tr style="background-color: #dfdfdf;">
			<td><input type="checkbox" name="checkAll" id="checkAll"></td>
			<td>상품이미지</td>
			<td>주문수량</td>
			<td>회원명(회원번호)</td>
			<td>상품명(상품번호)</td>
			<td>상품 가격</td>
			<td>총 가격</td>
			<td>등록일</td>
			<td>비고</td>
		</tr>
		
	<c:set var="i" value="0"></c:set>
	<c:set var="hap_money" value="0"></c:set>
	
	<c:forEach var="dto" items="${list_shop }">
		<c:set var="img1" value="${fn:split(dto.productImgInfo,'|')[0]}"></c:set>
		<c:set var="img1_file" value="${fn:split(img1,'?')[1]}"></c:set>
		<tr style="background-color: #f0f0f0;">
	 		<%-- <td><input type="checkbox" name="chk_${i }" id="chk_${i }" value="${dto.no}">${dto.no}</td> --%>
			<td>
				<input type="checkbox" name="chk" id="chk" id="chk_${i }" value="${dto.no}-${dto.totPrice}">
			</td>
			<td>
				<c:choose>
					<c:when test="${img1 == '-' }">
						이미지 없음
					</c:when>
					<c:otherwise>
						<a href="#" onClick="move('view','${dto.productNo}');"><img src="${path }/attach/product_img/${img1_file }" width="70" height="70"></a>
					</c:otherwise>
				</c:choose>
			</td>
			<td>${dto.amount }</td>
			<td>${dto.memberName }(${dto.memberNo })</td>
			<td><a href="#" onClick="move('view','${dto.productNo}');">${dto.productName }(${dto.productNo })</a></td>
			<td>${dto.price }</td>
			<td>${dto.totPrice }</td>
			<td>${dto.regi_date }</td>
			<td>
				<a href="#" onClick="move('sakje', '${dto.no}')">[삭제]Get</a>
				<a href="#" onClick="shopClearOneForm('${dto.no}')">[삭제]Post</a>
			</td>
		</tr>
		<c:set var="i" value="${i = i + 1 }"></c:set>
		<c:set var="hap_money" value="${hap_money = hap_money + dto.totPrice }"></c:set>
	</c:forEach>
		<tr align="right" style="background-color: #f0f0f0;">
			<td colspan="10">총 합계 금액 : ${hap_money } 원</td>
		</tr>
		<tr align="right" style="height: 50px;">
			<td colspan="10">
				<button type="button" onClick="cartClear();">장바구니 비우기</button>
				<!-- <button type="button" onClick="cartClearjs();">장바구니 비우기(js)</button> -->
				<button type="button" onClick="move('list','');">쇼핑하기</button>
				<button type="button" onClick="buy();">주문하기</button>
			</td>
		</tr>
	</table>
	
	<input type="hidden" name="total_counter" value=${i }>
	</form>
</div>

<form name="imsiForm">
<input type="hidden" name="no" id="no">
<input type="hidden" name="chk_no" id="chk_no">
</form>

<script>
function move(value1, value2) {
	location.href="${path }/shop_servlet/" + value1 + ".do?" + "no=" + value2;
}

//삭제 : post방식
function shopClearOneForm(value1) {
	$("#no").val(value1);
	
	if(confirm('선택한 상품을 삭제하시겠습니까?')) {
	 	document.imsiForm.method = "post";
		document.imsiForm.action = "${path}/shop_servlet/sakje.do";
		document.imsiForm.submit();
	}
}

//전체 선택
$(document).ready(function() {	//이 문서의 로딩이 끝나고 나서
	$("#checkAll").click(function () {
		if($("#checkAll").prop("checked")) {
			$("input[name=chk]").prop("checked", true);
		} else {
			$("input[name=chk]").prop("checked", false);
		}
	});
});


//장바구니 구매
function buy() {
	if($("input:checkbox[name=chk]:checked").length == 0) {
		alert('구매할 상품을 선택하세요!');
		return;
	}
	
	if(confirm('선택한 상품들을 구매하시겠습니까?')) {
	 	document.chkForm.method = "post";
		document.chkForm.action = "${path}/shop_servlet/buyProc.do";
		document.chkForm.submit();
	}
}


//장바구니 비우기 : 배열처리
function cartClear() {
	if($("input:checkbox[name=chk]:checked").length == 0) {
		alert('삭제할 상품을 선택하세요!');
		return;
	}

	
	if(confirm('선택한 상품들을 삭제하시겠습니까?')) {
	 	document.chkForm.method = "post";
		document.chkForm.action = "${path}/shop_servlet/cartClear.do";
		document.chkForm.submit();
	}
}

//장바구니 비우기 : 자바스크립트
function cartClearjs() {
	var chk_no = "";
	$('input[name="chk"]:checked').each(function (index) {
		if(index != 0) {
			chk_no += ',';
		}
		chk_no += $(this).val();
	});
	
	if(chk_no == '') {
		alert('상품을 선택하세요.');
		$("#chk_no").val("");
		return;
	}
	
	if(confirm('장바구니를 비우겠습니까?')) {
		$("#chk_no").val(chk_no);
		
		imsiForm.method = "post";
		imsiForm.action = "${path}/shop_servlet/clearProc.do"
		imsiForm.submit();
	}
}

</script>

</body>
</html>