<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<style type="text/css">
     <%@ include file = "./include/pj.css" %>
</style>
</head>
<body>
<div id="wrap">

    <div id="header">
        <%@ include file="./include/inc_login.jsp" %>
    </div>
    
<!-- 	    <div id="blank">
	    <br>
	    </div> -->
    
    <div id="left">
		<%@ include file="./include/inc_menu.jsp" %>
    </div>
</div>

<div id="contents">

	<h2>HOME</h2>
	
	<c:choose>
		<c:when test="${sessionScope.cookNo == null || sessionScope.cookNo == 0}">
			<table border="1" width="60%">
				<tr>
					<td align="center"><font style="font-size: 60px; font-weight: bold;">Here<br>We<br>Go !!!</font></td>
				</tr>
				<tr>
					<td align="center" style="padding: 10px; 0px;">
					
						<form name="loginForm" method="post" action="${path }/member_servlet/loginProc.do">
							아이디 : <input type="text" name="id" style="width: 100px;"> <br>
							비밀번호 : <input type="password" name="password" style="width: 100px;"><br>
							<button type="submit">로그인</button>
						</form>
						
					</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<h2>DashBoard</h2>
			
			<table border="1px; black;">
				<tr>
					<td align="center" style="width: 300px; height: 200px;">&nbsp;</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>