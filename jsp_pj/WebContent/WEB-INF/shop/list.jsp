<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop</title>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
</style>
</head>
<body>

<div id="wrap">

    <div id="header">
        <%@ include file="../include/inc_login.jsp" %>
    </div>
    
    <div id="blank"><br></div>
    
    <div id="left">
		<%@ include file="../include/inc_menu.jsp" %>
    </div>
</div>

<div id="contents">

	<h2>쇼핑몰 목록</h2>
	<table>
		<tr>
			<c:forEach var="dto" items="${list_product }">
			<c:set var="img1" value="${fn:split(dto.productImgInfo,'|')[0]}"></c:set>
			<c:set var="img1_file" value="${fn:split(img1,'?')[1]}"></c:set>
			<c:choose>
				<c:when test="${dto.rownum % 3 == 0}">
					<tr><td>
						<table>
							<tr><td style="background: #eee;">
								<c:choose>
									<c:when test="${img1 == '-' }">
										<a href="${path }/shop_servlet/view.do?no=${dto.no }">이미지 없음</a>
									</c:when>
									<c:otherwise>
										<a href="${path }/shop_servlet/view.do?no=${dto.no }"><img src="${path }/attach/product_img/${img1_file }" width="300" height="250"></a>
									</c:otherwise>
								</c:choose>
							</td></tr>
							<tr><td><a href="${path }/shop_servlet/view.do?no=${dto.no }">${dto.name }</a></td></tr>
							<tr><td>${dto.price }원</td></tr>
						</table>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<table>
							<tr><td style="background: #eee;">
								<c:choose>
									<c:when test="${img1 == '-' }">
										<a href="${path }/shop_servlet/view.do?no=${dto.no }">이미지 없음</a>
									</c:when>
									<c:otherwise>
										<a href="${path }/shop_servlet/view.do?no=${dto.no }"><img src="${path }/attach/product_img/${img1_file }" width="300" height="250"></a>
									</c:otherwise>
								</c:choose>
							</td></tr>
							<tr><td><a href="${path }/shop_servlet/view.do?no=${dto.no }">${dto.name }</a></td></tr>
							<tr><td>${dto.description }</td></tr>
							<tr><td>${dto.price }원</td></tr>
						</table>
					</td>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</tr>
	</table>
	
	<br>
<!-- 	<button type="button" onClick="list()">상품목록</button>
	<button type="button" onClick="market()">장바구니</button> -->
</div>


<script>
	function list() {
		location.href = "${path}/shop_servlet/list.do"
	}
	
	function market() {
		location.href = "${path}/shop_servlet/market.do"
	}
</script>

</body>
</html>