<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품삭제</title>
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
	<h2>상품삭제</h2>
	
	<form name="sujungForm">
	<input type="hidden" name="no" value="${dto.no }">
	<table style="align-content: center;">
		<tr align="center">
			<td style="background-color: #dfdfdf; width:30%;">상품명</td>
			<td style="background-color: #f0f0f0">${dto.name }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf; width:30%;">가격</td>
			<td style="background-color: #f0f0f0">${dto.price }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf; width:30%;">상품이미지</td>
			<td style="background-color: #f0f0f0">
				<c:choose>
				<c:when test="${img1_ori_file == '-' }">
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
			<td style="background-color: #dfdfdf; width:30%;">상품설명</td>
			<td style="background-color: #f0f0f0">${dto.description }</td>
		</tr>
		<tr align="center">
			<td style="background-color: #dfdfdf; width:30%;">이미지파일</td>
			<td style="background-color: #f0f0f0">
				<c:set var="img1" value="${fn:split(dto.productImgInfo, '|') }"></c:set>
				<c:forEach var="str1" items="${img1 }">
					<c:set var="array1" value="${fn:split(str1, '?') }"></c:set>
					${array1[1] } <br>
				</c:forEach>
			</td>
		</tr>
	</table>
	<div align="center">
		<button type="button" onClick="list()">목록으로</button>
		<button type="button" onClick="sujung()">상품삭제</button>
	</div>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/product_servlet/list.do";
	}
	
/*	function sakje() {
		if(confirm("파일을 삭제하시겠습니까?")) {
			sujungForm.action="${path }/product_servlet/sakjeProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}*/
	
	function sujung() {
		if(confirm("상품을 삭제하시겠습니까?")) {
			sujungForm.action="${path }/product_servlet/sakjeProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>