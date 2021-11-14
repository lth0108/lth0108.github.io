<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test12</title>
</head>
<body>

<%@ include file="../../include/inc_menu.jsp" %>

<h2>test12</h2>

<c:set var="k" value="${k = 0 }"></c:set>
<c:forEach var="aaa" items="${list }">
	<input type="text" name="box_${k }" id="box_${k }" value="${aaa }" style="width: 400px;">
	${aaa } &nbsp;
	<a href="#" onClick="sakje('box_${k }');">[삭제]</a>
	<br>
	<c:set var="k" value="${k = k + 1 }"></c:set>
</c:forEach>

<hr>

<form name="imsiForm">
<input type="text" name="filePath" value="" style="width: 400px;">
</form>


<script>
	function sakje(value1) {
		var imsi = document.getElementById(value1).value;
		imsiForm.filePath.value = imsi;
		
		if(confirm("삭제하시겠습니까?")) {
			imsiForm.method = "post";
			imsiForm.action = "${path}/test_servlet/test12Proc.do";
			imsiForm.submit();
		}

	}	
</script>

</body>
</html>