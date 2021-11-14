<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsonFile</title>
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
	
	<table border="0" width="80%" align="center">
		<tr>
			<td><a href="${path }/chart_servlet/piechart.do">piechart</a></td>
			<td><a href="${path }/chart_servlet/piechart3d.do">piechart3d</a></td>
			<td><a href="${path }/chart_servlet/combochart.do">combochart</a></td>
			<td><a href="${path }/chart_servlet/linechart.do">linechart</a></td>
			<td><a href="${path }/chart_servlet/jsonFile.do"><b>jsonFile</b></a></td>
		</tr>
	</table>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
		function drawChart() {
		
			var data = new google.visualization.DataTable(${chart_jsonData });
		
			var options = {
			  title : '${chart_subject }',
			  width : 900,
			  height : 500
			};
			
			var chart = new google.visualization.${chart_type }(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
	
	<div id="chart_div"></div>
	
	<a style="float: right;" href="#" onClick="move();">목록으로</a>
	
</div>

<script>
function move() {
	location.href = "${path }/chart_servlet/index.do";
}
</script>
</body>
</html>
