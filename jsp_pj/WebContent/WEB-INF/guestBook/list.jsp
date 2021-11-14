<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 목록</title>
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

	<h2>방명록 목록</h2>
	
	<table>
		<c:forEach var="dto" items="${list }">
			<tr style="padding-bottom: 20px;">
				<td>
					<table width="100%">
						<tr style="background-color: #dfdfdf">
							<td style="padding: 0px 20px 0px 0px;">순번 : ${dto.no }</td>
							<td style="padding: 0px 20px 0px 20px;">이름 : <a href="${path }/guestBook_servlet/view.do?no=${dto.no }">${dto.name }</a></td>
							<td style="padding: 0px 20px 0px 0px;">등록일 : ${dto.regi_date }</td>
						</tr>
						<tr style="background-color: #f0f0f0">
							<td colspan="3">${dto.content }</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
	
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="6" style="width:300px; height: 100px;">등록된 </td>
			</tr>
		</c:if>
	
	<button type="button" onClick="move();" style="float: right;">방명록등록</button>
	
<script>
	function move() {
		location.href = "${path }/guestBook_servlet/chuga.do";
	}
</script>
</div>
</body>
</html>