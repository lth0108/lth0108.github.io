<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
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
    
    <div id="blank"><br></div>
    
    <div id="left">
		<%@ include file="../include/inc_menu.jsp" %>
    </div>
    
</div>
	
<div id="contents">

	<h2>상품목록</h2>
	
	<div style="text-align: right;">
			${totalRecord }개 검색됨
	</div>
	
<form name="chkForm">
	<table>
			<tr style="background-color: #dfdfdf">
			<td><input type="checkbox" name="checkAll" id="checkAll"></td>
			<td>상품이미지</td>
			<td>상품이름</td>
			<td>상품가격</td>
			<td>등록일</td>
		</tr>
		<c:forEach var="dto" items="${list }">
		<c:set var="img1" value="${fn:split(dto.productImgInfo,'|')[0]}"></c:set>
		<c:set var="img1_file" value="${fn:split(img1,'?')[1]}"></c:set>
			<tr style="background-color: #f0f0f0">
				<td>
					<input type="checkbox" name="chk" id="chk" id="chk_${i }" value="${dto.no}-${dto.price}">
				</td>
				<td>
					<c:choose>
						<c:when test="${img1_file == '-' }">
							이미지 없음
						</c:when>
						<c:otherwise>
							<img src="${path }/attach/product_img/${img1_file}" width="50" height="50">
						</c:otherwise>
					</c:choose>
				</td>
				<td><a href="${path }/product_servlet/view.do?no=${dto.no }">${dto.name }</a></td>
				<td>${dto.price }</td>
				<td>${dto.regi_date }</td>
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="6" style="width:300px; height: 100px;">등록된 </td>
			</tr>
		</c:if>
	</table>
	<button type="button" onClick="cartClear();" style="float: right;">상품삭제</button>
	<button type="button" onClick="move();" style="float: right;">상품등록</button>
</form>
<br><br>
<form name="searchForm" style="float: right;">
		<input type="hidden" name="pageNumber" value="${pageNumber }">
	<select name="search_option" id="search_option"  onchange="myFunction(this.value)">
		<option value=""> -- 선택 --</option>
		<option value="name" >상품이름</option>
	</select>
	
	<input type="text" name="search_data" style="width: 100px;">
	<button type="button" onClick="search();">검색</button>
</form> 
	

<script>
	function search() {
		if(alert('검색?'))
		
		searchForm.method = "post";
		searchForm.action = "${path }/product_servlet/list.do";
		searchForm.submit();
	}
	
	function move() {
		location.href = "${path }/product_servlet/chuga.do/chuga.do";
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
	
	function cartClear() {
		if($("input:checkbox[name=chk]:checked").length == 0) {
			alert('삭제할 상품을 선택하세요!');
			return;
		}
		
		if(confirm('선택한 상품들을 삭제하시겠습니까?')) {
		 	document.chkForm.method = "post";
			document.chkForm.action = "${path}/product_servlet/cartClear.do";
			document.chkForm.submit();
		}
	}
</script>
</div>

</body>
</html>