<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보</title>
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

	<h2>상품정보</h2>
					
	<input type="hidden" name="no" value="${dto.no }">
	<table style="align-content: center;">
		<tr align="center">
			<td style="background-color: #dfdfdf; width:30%;">상품명</td>
			<td style="background-color: #f0f0f0">${dto.name }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf">가격</td>
			<td style="background-color: #f0f0f0">${dto.price }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf">상품이미지</td>
			<td style="background-color: #f0f0f0">
				<c:choose>
				<c:when test="${img1 == '-' }">
					이미지 없음
				</c:when>
				<c:otherwise>
					<c:set var="img" value="${fn:split(dto.productImgInfo, '|') }"></c:set>
					<c:forEach var="str" items="${img }">
						<c:set var="array" value="${fn:split(str, '?') }"></c:set>
						<img src="${path }/attach/product_img/${array[1] }" width="50" height="50"><br>
					</c:forEach>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf">파일명</td>
			<td style="background-color: #f0f0f0">
				<c:set var="img1" value="${fn:split(dto.productImgInfo, '|') }"></c:set>
				<c:forEach var="str1" items="${img1 }">
					<c:set var="array1" value="${fn:split(str1, '?') }"></c:set>
					${array1[1] } <br>
				</c:forEach>
			</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf">상품설명</td>
			<td style="background-color: #f0f0f0">${dto.description }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf">등록일</td>
			<td style="background-color: #f0f0f0">${dto.regi_date }</td>
		</tr>
	</table>
	
	<div style="float: right;">
		<button type="button" onClick="move('list.do', '');">목록</button>
		<button type="button" onClick="move('chuga.do', '');">등록</button>
		<button type="button" onClick="move('sujung.do', '${dto.no }');">수정</button>
		<button type="button" onClick="move('sakje.do', '${dto.no }');">삭제</button>
	</div>
</div>

<script>
	function move(value1, value2) {
		location.href="${path }/product_servlet/" + value1 + "?no=" + value2;
	}
</script>

</body>
</html>