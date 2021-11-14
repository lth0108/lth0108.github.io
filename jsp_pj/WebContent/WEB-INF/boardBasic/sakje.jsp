<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글삭제</title>
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
	<h2>게시글삭제</h2>
	
	<form name="sakjeForm">
	<input type="hidden" name="no" value="${dto.no }">
	<input type="hidden" name="ref" value="${dto.ref }">
	
	<h3>${dto.subject }</h3>
	
	<b>${dto.writer }</b><br>
	${dto.regi_date } / 조회 ${dto.hit }
	<hr>
	<div style="width: 800px; height: 500px;">
		${dto.content }<br>
	</div>
	<hr>
	<div style="float: right;">
		<button type="button" onClick="list()">목록으로</button>
		<button type="button" onClick="sakje()">게시글삭제</button>
	</div>
	</form>
</div>
<script>
	function list() {
		location.href="${path }/boardBasic_servlet/list.do";
	}
	
	function sakje() {
		if(confirm("게시글을 삭제하시겠습니까?")) {
			sakjeForm.action="${path }/boardBasic_servlet/sakjeProc.do";
			sakjeForm.method="post";
			sakjeForm.submit();
		}
	}
</script>

</body>
</html>