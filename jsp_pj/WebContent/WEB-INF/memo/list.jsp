<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모목록</title>
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
	
	<h2>메모관리</h2>
	
	<form name="saveForm">
	<span id="gubun" style="display: none;"></span>
	<input type=hidden name="no" value="" id="no">
		<table>
			<tr style="background-color: #dfdfdf">
				<td>작성자</td>
				<td><input type="text" name="writer" id="writer" style="width: 95%;" ></td>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td>내용</td>
				<td><textarea name="content" id="content" style="width: 95%; height: 100px;" ></textarea></td>
			</tr>
			<tr style="background-color: #f0f0f0">
				<td colspan="2" align="center">
					<button type="button" name="btnSave" id="btnSave" onClick="submitProc()">메모등록</button>
					<button type="button" id="btnClear" style="display: none;" onClick="clearForm()">초기화</button>
				</td>
			</tr>
		</table>
	</form>
	<br><br><hr>
	
	<h2>메모목록</h2>
	
	<div style="text-align: right;">
			${totalRecord }개 검색됨
	</div>
		
	<table>
		<tr style="background-color: #dfdfdf">
			<td style="120px;">순번</td>
			<td style="150px;">작성자</td>
			<td style="width: 450px;">내용</td>
			<td>등록일</td>
			<td style="width: 50px;">비고</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr style="background-color: #f0f0f0">
				<td>${dto.no }</td>
				<td>${dto.writer }</td>
				<td>${dto.content }</td>
				<td>${dto.regi_date }</td>
				<td>
					<a href="#" onClick="chage('${dto.no }', '${dto.writer }', '${dto.content }')">수정</a> / 
					<a href="#" onClick="erase('${dto.no }', '${dto.writer }', '${dto.content }')">삭제</a>
				</td>
			</tr>
		</c:forEach>
		
		<c:if test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="4" style="width:300px; height: 100px;">등록된 내용이 없습니다.</td>
			</tr>
		</c:if>
	</table>
	
	<form name="searchForm" style="float: right;">
 		<input type="hidden" name="pageNumber" value="${pageNumber }">
		<select name="search_option" id="search_option"  onchange="myFunction(this.value)">
			<option value=""> -- 선택 --</option>
			<option value="writer" >작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" name="search_data" style="width: 100px;">
		<button type="button" onClick="move();">검색</button>
	</form> 
	<br><br>
	
	<script>
	function move() {
		if(alert('검색?'))
		
		searchForm.method = "post";
		searchForm.action = "${path }/memo_servlet/list.do";
		searchForm.submit();
	}
	
		function chage(value1, value2, value3) {
	//		document.saveForm.no.value = value1;
			$("#gubun").text("M");
			$("#no").val(value1);
			$("#writer").val(value2);
			$("#content").val(value3);
			$("#btnSave").text("메모수정");
			
			$("#btnClear").show();
		}
		
		function erase(value1, value2, value3) {
	//		document.saveForm.no.value = value1;
			$("#gubun").text("D");
			$("#no").val(value1);
			$("#writer").val(value2);
			$("#content").val(value3);
			$("#btnSave").text("메모삭제");
			
			$("#btnClear").show();
		}
		
		function clearForm() {
			$("#gubun").text("");
			$("#no").val("");
			$("#writer").val("");
			$("#content").val("");
			$("#btnSave").text("메모등록");
			$("#btnClear").hide();
		}
		
		
		function submitProc() {
	/*		if(saveForm.writer.value == "") {
				alert("작성자를 입력하세요!")
				saveForm.writer.focus();
				return;
			}*/
			
			if($("#writer").val() == "") {
				alert("작성자를 입력하세요!")
				saveForm.writer.focus();
				return;
			}
			
			var value1 = $("#gubun").text();
			
			if(value1 == "M") {
				var url = "${path }/memo_servlet/sujungProc.do";
			} else if(value1 == "D") {
				var url = "${path }/memo_servlet/sakjeProc.do";
			}else {
				var url = "${path }/memo_servlet/chugaProc.do";
			}
			document.saveForm.method = "post";
			document.saveForm.action = url;
			document.saveForm.submit();
		}
	</script>
</div>

</body>
</html>