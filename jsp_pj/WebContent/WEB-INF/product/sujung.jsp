<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
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

	<h2>상품수정</h2>
	
	<form name="sujungForm">
	<input type="hidden" name="no" value="${dto.no }">
	<table>
		<tr>
			<td align="center" style="background-color: #dfdfdf; width:30%; align-content: center;">상품명</td>
			<td style="background-color: #f0f0f0;"><input type="text" name="name" value="${dto.name }"></td>
		</tr>
		<tr>
			<td align="center" style="background-color: #dfdfdf;">가격</td>
			<td style="background-color: #f0f0f0;"><input type="number" name="price" value="${dto.price }"></td>
		</tr>
		<tr>
			<td align="center" style="background-color: #dfdfdf;">상품설명</td>
			<td style="background-color: #f0f0f0;">
				<textarea name="description" id="description" style="width: 200px; height: 100px;">${dto.description }</textarea>
			</td>	
		</tr>
		<tr>
			<td align="center" style="background-color: #dfdfdf;">상품이미지</td>
			<td style="background-color: #f0f0f0;">
				<c:choose>
				<c:when test="${img1_ori_file == '-' }">
					이미지 없음
				</c:when>
				<c:otherwise>
					<c:set var="img" value="${fn:split(dto.productImgInfo, '|') }"></c:set>
					<c:forEach var="str" items="${img }">
						<c:set var="array" value="${fn:split(str, '?') }"></c:set>
						<img src="${path }/attach/product_img/${array[1] }" width="50" height="50"><br>
						${array[1] }<br>
					 	<a href="#" onClick="img_sakje('${array[1] }');">[파일삭제]</a><br> 
					</c:forEach>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="background-color: #f0f0f0;">
				대표이미지<br>
				<input type="file" name="0" class="files"><br><br>
				상세이미지<br>
				<input type="file" name="1" class="files"><br>
				<input type="file" name="2" class="files"><br>
				<input type="hidden" name="upload_counter" id="upload_counter">
			</td>
		</tr>
		<tr align="center">
			<td align="center" style="background-color: #dfdfdf;">등록일</td>
			<td style="background-color: #f0f0f0;">${dto.regi_date }</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<button type="button" onClick="list()">목록으로</button>
				<button type="button" onClick="sujung()">상품수정</button>
			</td>
		</tr>
	</table>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/product_servlet/list.do";
	}
	
	function img_sakje(value1) {
		if(confirm("이미지를 삭제하시겠습니까?")) {
			sujungForm.action="${path }/product_servlet/img_sake.do?value1=" + value1;
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
	
	
	function sujung() {
		if(sujungForm.name.value == '') {
			alert("상품명을 입력하지 않았습니다!");
			sujungForm.name.focus();
			return;
		}
		
		if(sujungForm.price.value == '') {
			alert("가격을 입력하지 않았습니다!");
			sujungForm.price.focus();
			return;
		} 
		
		if(confirm("수정하시겠습니까?")) {
			document.sujungForm.upload_counter.value = document.getElementsByClassName("files").length;

			sujungForm.enctype = "multipart/form-data";
			sujungForm.action="${path }/product_servlet/sujungProc.do";
			sujungForm.method="post";
			sujungForm.submit();
		}
	}
</script>

</body>
</html>