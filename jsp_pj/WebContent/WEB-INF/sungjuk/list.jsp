<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적목록</title>
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
	
	<h2>성적목록</h2>
	
	<div style="text-align: right;">
		${totalRecord }개 검색됨
	</div>
	
	<table>
		<tr style="background-color: #dfdfdf">
			<td>순번</td>
			<td>이름</td>
			<td>아이디</td>
			<td>시험명</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>과학</td>
			<td>역사</td>
			<td>총점</td>
			<td>평균</td>
			<td>등급</td>
			<td>등록일</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr style="background-color: #f0f0f0">
				<td><a href="${path }/sungjuk_servlet/view.do?no=${dto.no }">${dto.no }</a></td>
				<td>${dto.name }</td>
				<td>${dto.id }</td>
				<td>${dto.sihum_name }</td>
				<td>${dto.kor }</td>
				<td>${dto.eng }</td>
				<td>${dto.mat }</td>
				<td>${dto.sci }</td>
				<td>${dto.his }</td>
				<td>${dto.tot }</td>
				<td>${dto.avg }</td>
				<td>${dto.grade }</td>
				<td>${dto.regi_date }</td>
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="13" style="width:300px; height: 100px;">등록된 </td>
			</tr>
		</c:if>
	</table>
	
	<form name="searchForm" style="float: right;">
 		<input type="hidden" name="pageNumber" value="${pageNumber }">
		<select name="search_option" id="search_option"  onchange="myFunction(this.value)">
			<option value=""> -- 선택 --</option>
			<option value="id" >아이디</option>
			<option value="name">이름</option>
			<option value="sihum_name">시험명</option>
			<option value="grade">등급</option>
		</select>
		
		<input type="text" name="search_data" style="width: 100px;">
		<button type="button" onClick="move();">검색</button>
	</form> 
	
</div>

<script>
	function move() {
		if(alert('검색?'))
		
		searchForm.method = "post";
		searchForm.action = "${path }/sungjuk_servlet/list.do";
		searchForm.submit();
	}
</script>
</body>
</html>