<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>

<style type="text/css">
     <%@ include file = "../include/pj.css" %>
}
</style>
</head>
<body>
<script>
$(window).scroll(function(){ 
	var position = $(window).scrollTop()+35;
	$(window).scroll(function( ){
		var position = $(window).scrollTop()+35;
		$( "#floatMenu" ).stop().animate({top:position+"px"}, 800);
	});
});

</script>
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
    <h2>회원목록</h2>
    
    <div style="text-align: right;">
			${totalRecord }개 검색됨
	</div>
	
	<table>
		<tr style="background-color: #dfdfdf">
			<td>순번</td>
			<td>아이디</td>
			<td>이름</td>
			<td>연락처</td>
			<td>이메일</td>
			<td>등급</td>
			<td>등록일</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr style="background-color: #f0f0f0">
				<td>${dto.no }</td>
				<td><a href="${path }/member_servlet/view.do?no=${dto.no }">${dto.id }</a></td>
				<td>${dto.name }</td>
				<td>${dto.phone }</td>
				<td>${dto.email }</td>
				<td>${dto.grade }</td>
				<td>${dto.regi_date }</td>
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="6" style="width:300px; height: 100px;">등록된 </td>
			</tr>
		</c:if>
	</table>
	<br>
 	<form name="searchForm" style="float: right;">
 		<input type="hidden" name="pageNumber" value="${pageNumber }">
		<select name="search_option" id="search_option"  onchange="myFunction(this.value)">
			<option value=""> -- 선택 --</option>
			<option value="id" >아이디</option>
			<option value="name">이름</option>
		</select>
		
		<input type="text" name="search_data" style="width: 100px;">
		<button type="button" onClick="move();">검색</button>
	</form> 
	
	
		
</div>

<script>
function move() {
	if(alert('검색?'))
	
	searchForm.method = "post";
	searchForm.action = "${path }/member_servlet/list.do";
	searchForm.submit();
}

</script>

</body>
</html>