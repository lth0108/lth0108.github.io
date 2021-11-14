<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test11</title>
</head>
<body>

<%@ include file="../../include/inc_menu.jsp" %>

<h2>test11</h2>

<form name="chugaForm">
이름 : <input type="text" name="name" id="name"><br>
연락처 : <input type="text" name="phone" id="phone"><br>
사진1 : <input type="file" name="0" class="files"><br><br>
사진2 : <input type="file" name="1" class="files"><br><br>
사진3 : <input type="file" name="2" class="files"><br><br>

<input type="text">
<button type="button" onClick="chuga();">확인</button>
</form>

<script>
	function chuga() {
		if(confirm("등록하시겠습니까?")) {
			document.chugaForm.upload_counter.value = document.getElementsByClassName("files").length;

			chugaForm.enctype = "multipart/form-data";
			chugaForm.action = "${path }/test_servlet/test11Proc.do";
			chugaForm.method = "post";
			chugaForm.submit();
		}
	}
</script>

</body>
</html>