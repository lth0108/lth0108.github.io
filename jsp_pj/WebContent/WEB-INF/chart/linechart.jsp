<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pieChart</title>
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
			<td><a href="${path }/chart_servlet/linechart.do"><b>linechart</b></a></td>
			<td><a href="${path }/chart_servlet/jsonFile.do">jsonFile</a></td>
		</tr>
	</table>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
		  ['Year', 'Sales', 'Expenses'],
		  ['2004',  1000,      400],
		  ['2005',  1170,      460],
		  ['2006',  660,       1120],
		  ['2007',  1030,      540]
		]);
		
		var options = {
		  title: 'Company Performance',
		  curveType: 'function',
		  legend: { position: 'bottom' }
		};
		
		var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
		
		chart.draw(data, options);
	}
	</script>
	
	<div id="curve_chart" style="width: 900px; height: 500px"></div>
	
	<a style="float: right;" href="#" onClick="move();">목록으로</a>
	
</div>

<script>
function move() {
	location.href = "${path }/chart_servlet/index.do";
}
</script>
</body>
</html>
