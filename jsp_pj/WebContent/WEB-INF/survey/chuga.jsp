<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 등록</title>
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

	<h2>설문조사 등록</h2>
	
	<form name="surveyForm">
	<table border="1">
		<tr>
			<td>question</td>
			<td>
				<input type="text" name="question">
			</td>
		</tr>
		<tr>
			<td>ans1</td>
			<td>
				<input type="text" name="question">
			</td>
		</tr>	
		<tr>
			<td>ans2</td>
			<td>
				<input type="text" name="question">
			</td>
		</tr>	
		<tr>
			<td>ans3</td>
			<td>
				<input type="text" name="question">
			</td>
		</tr>	
		<tr>
			<td>ans4</td>
			<td>
				<input type="text" name="question">
			</td>
		</tr>	
		<tr>
			<td>status</td>
			<td>
				<input type="radio" name="status" checked> 진행중
				<input type="radio" name="status"> 종료
			</td>
		</tr>	
		<tr>
			<td>start_date</td>
			<td>
			 	<select name="year1">
			 		<option>--선택안함--</option>
			 		<% for(int i=1950;i<2030;i++) { %>
			 		<option value="<%=i %>"><%=i%>년</option>
					<% } %>
			 	</select>
			 	<select name="month1">
			 		<option>--선택안함--</option>
			 		<% for(int i=1;i<12;i++) { %>
			 		<option value="<%=i %>"><%=i%>월</option>
					<% } %>
			 	</select>
			 	<select name="day1">
			 		<option>--선택안함--</option>
			 		<% for(int i=1;i<31;i++) { %>
			 		<option value="<%=i %>"><%=i%>일</option>
					<% } %>
			 	</select>
			</td>
		</tr>	
		<tr>
			<td>last_date</td>
			<td>
				<select name="year2">
			 		<option>--선택안함--</option>
			 		<% for(int i=2021;i<2022;i++) { %>
			 		<option value="<%=i %>"><%=i%>년</option>
					<% } %>
			 	</select>년
			 	<select name="month2">
			 		<option>--선택안함--</option>
			 		<% for(int i=1;i<12;i++) { %>
			 		<option value="<%=i %>"><%=i%>월</option>
					<% } %>
			 	</select>월
			 	<select name="day2">
			 		<option>--선택안함--</option>
			 		<% for(int i=1;i<31;i++) { %>
			 		<option value="<%=i %>"><%=i%>일</option>
					<% } %>
			 	</select>일
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onClick="list();">목록으로</button>
				<button type="button" onClick="save();">저장하기</button>
			</td>
		</tr>	
		
	</table>
	</form>
</div>

<script>
	function list() {
		location.href="${path }/survey/list.do";
	}
	

	function save() {
		if(confirm("저장하시겠습니까?")) {
			surveyForm.action = "${path }/survey/save.do";
			surveyForm.method = "post";
			surveyForm.submit();
		}
		
	}
</script>

</body>
</html>