<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록(Mybatis)</title>
</head>
<body>

<%@ include file="../include/inc_menu.jsp" %>

<h2>게시판목록(Mybatis)</h2>

<div style="text-align: right;">
		${totalRecord }개 검색됨
</div>
	
<table border="1">
	<tr>
		<td>제목</td>
		<td>작성자</td>
		<td>이메일</td>
		<td>조회수</td>
		<td>ref</td>
		<td>re_step</td>
		<td>re_level</td>
		<td>등록날짜</td>
	</tr>
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>
				<c:forEach var="i" begin="2" end="${dto.re_step }" step="1">
					&nbsp;
				</c:forEach>
				<c:if test="${dto.re_step >= 2}">[re]</c:if>
			<a href="${path }/boardBasicMybatis_servlet/view.do?no=${dto.no }">${dto.subject }</a>
			<input type="hidden" name="ref" value="${dto.ref }">
			</td>
			<td>${dto.writer }</td>
			<td>${dto.email }</td>
			<td>${dto.hit }</td>
			<td>${dto.ref }</td>
			<td>${dto.re_step }</td>
			<td>${dto.re_level }</td>
			<td>${dto.regi_date }</td>
		</tr>
	</c:forEach>
	
	<c:if test="${fn:length(list) == 0 }">
		<tr>
			<td colspan="6" style="width:300px; height: 100px;">등록된 내용이 없습니다!</td>
		</tr>
	</c:if>
</table>
|
<a href="${path }/boardBasicMybatis_servlet/chuga.do?no="${dto.no }>글쓰기</a>
|

<form name="searchForm" style="float: right;">
	<input type="hidden" name="pageNumber" value="${pageNumber }">
	<select name="search_option" id="search_option"  onchange="myFunction(this.value)">
		<option value=""> -- 선택 --</option>
		<option value="subject" >제목</option>
		<option value="writer">작성자</option>
	</select>
	
	<input type="text" name="search_data" style="width: 100px;">
	<button type="button" onClick="move();">검색</button>
</form> 
<script>
function move() {
	if(alert('검색?'))
	
	searchForm.method = "post";
	searchForm.action = "${path }/boardBasic_servlet/list.do";
	searchForm.submit();
}
</script>
</body>
</html>