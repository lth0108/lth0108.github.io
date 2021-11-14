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
			<td><a href="${path }/chart_servlet/combochart.do"><b>combochart</b></a></td>
			<td><a href="${path }/chart_servlet/linechart.do">linechart</a></td>
			<td><a href="${path }/chart_servlet/jsonFile.do">jsonFile</a></td>
		</tr>
	</table>
	
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawVisualization);
	
		function drawVisualization() {
			// Some raw data (not necessarily accurate)
			var data = google.visualization.arrayToDataTable([
			  ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
			  ['2004/05',  165,      938,         522,             998,           450,      614.6],
			  ['2005/06',  135,      1120,        599,             1268,          288,      682],
			  ['2006/07',  157,      1167,        587,             807,           397,      623],
			  ['2007/08',  139,      1110,        615,             968,           215,      609.4],
			  ['2008/09',  136,      691,         629,             1026,          366,      569.6]
			]);
			
			var options = {
			  title : 'Monthly Coffee Production by Country',
			  vAxis: {title: 'Cups'},
			  hAxis: {title: 'Month'},
			  seriesType: 'bars',
			  series: {5: {type: 'line'}}
			};
			
			var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
	<div id="chart_div" style="width: 900px; height: 500px;"></div>
	
	<a style="float: right;" href="#" onClick="move();">목록으로</a>
</div>

<script>
function move() {
	location.href = "${path }/chart_servlet/index.do";
}
</script>

</body>
</html>
