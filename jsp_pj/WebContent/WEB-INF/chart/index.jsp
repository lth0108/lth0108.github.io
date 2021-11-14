<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chart</title>
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
	
	<h2>Chart</h2>
	
	<table style="text-align: center">
		<tr>
			<td>
				<a href="${path }/chart_servlet/piechart.do">
				<img src="${path }/attach/img/piechart1.png" style="width: 45%; height: 250px;"><br>
				piechart<br>
				</a>
			</td>
			<td>
				<a href="${path }/chart_servlet/piechart3d.do">
				<img src="${path }/attach/img/piechart3d.png" style="width: 45%; height: 250px;"><br>
				piechart3d<br>
				</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="${path }/chart_servlet/combochart.do">
				<img src="${path }/attach/img/combochart.png" style="width: 45%; height: 250px;"><br>
				combochart<br>
				</a>
			</td>
			<td>
				<a href="${path }/chart_servlet/linechart.do">
				<img src="${path }/attach/img/linechart.png" style="width: 45%; height: 250px;"><br>
				linechart<br>
			</a>
			</td>
		</tr>
		<tr>
			<td colspan="2"><a href="${path }/chart_servlet/jsonFile.do"><br><br><br><br>jsonFile</a></td>
		</tr>
	</table>
</div>
</body>
</html>