<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
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

	<h2>상품등록</h2>
	
	<form name="chugaForm">
		<table>
			<tr>
				<td align="center" style="background-color: #dfdfdf;">상품명</td>
				<td style="background-color: #f0f0f0"><input type="text" name="name"></td>
			</tr>
			<tr>
				<td align="center" style="background-color: #dfdfdf;">상품가격</td>
				<td style="background-color: #f0f0f0"><input type="number" name="price"></td>
			</tr>
			<tr>
				<td align="center" style="background-color: #dfdfdf;">상품설명</td>
				<td style="background-color: #f0f0f0">
					<textarea name="description" id="description"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" style="background-color: #dfdfdf;">이미지</td>
				<td style="background-color: #f0f0f0">
				대표이미지<br>
				<input type="file" name="0" class="files"><br><br>
				상세이미지<br>
				<input type="file" name="1" class="files"><br>
				<input type="file" name="2" class="files"><br>
				<input type="hidden" name="upload_counter" id="upload_counter">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" onClick="list()">목록으로</button>
					<button type="button" onClick="chuga()">상품등록</button>
				</td>
			</tr>
		</table>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/product_servlet/list.do";
	}
	
	function chuga() {
		if(chugaForm.name.value == '') {
			alert("상품명을 입력해주세요!")
			chugaForm.name.focus();
			return;
		}
		
		if(chugaForm.price.value == '') {
			alert("가격을 입력해주세요!")
			chugaForm.price.focus();
			return;
		}
		
		if(chugaForm.description.value == '') {
			alert("상품설명을 입력해주세요!")
			chugaForm.description.focus();
			return;
		}
		
		if(confirm("상품을 등록하시겠습니까?")) {
			document.chugaForm.upload_counter.value = document.getElementsByClassName("files").length;

			chugaForm.enctype = "multipart/form-data";
			chugaForm.method="post";
			chugaForm.action="${path }/product_servlet/chugaProc.do";
			chugaForm.submit();
		}
	}
</script>
</body>
</html>
