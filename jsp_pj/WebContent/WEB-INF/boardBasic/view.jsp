<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판뷰</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">
     <%@ include file = "../include/pj.css" %>
</style>
</head>
<body>
<script>


</script>

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
	<h2>게시판뷰</h2><br>
	
		<h2>${dto.subject }</h2>
		
		<div style="float: right;">
		<button onClick="move('sujung.do', '${dto.no }');">수정</button>
		<button onClick="move('sakje.do', '${dto.no }');">삭제</button>
		</div>
		
		<b>${dto.writer }</b><br>
		${dto.regi_date } / 조회 ${dto.hit }
		<hr>
		<div style="width: 800px; height: 500px;">
			${dto.content }<br>
			
		</div>
	
	<hr>
	<h3>댓글 내용</h3><br>
	<c:if test="${fn:length(list) == 0 }">
		등록된 댓글이 없습니다!
	</c:if>
	<form name="replyForm">
	<input type="hidden" name="no" value="${dto.no }">
	<c:set var="i" value="${dto.no }"></c:set>
	<c:forEach var="dto" items="${list }" varStatus="status">
	
		<c:if test="${dto.re_step >= 3 }">
			<c:set var="re" value="[re]"></c:set>
		</c:if>
		<c:forEach var="j" begin="3" end="${dto.re_step }" step="1">
			&nbsp;&nbsp;
		</c:forEach>
		
		<table style="display: inline;">
			<tr>
				<td>${re } 작성자 : ${dto.writer } / 이메일 : ${dto.email } / 등록일 : ${dto.regi_date }</td>
			</tr>
			<tr>
				<td>
					내용 : ${dto.content }<br>
					<button type="button" onClick="replymove('${i }', '${dto.no }');">${dto.writer }에 대한 답글달기</button><br><br>
			   
			    <c:if test="${dto.no == no_reply}">
				    <div id="replytextarea" style="background-color: #f0f0f0;">
				    	<input type="hidden" value="${dto.no }" name="no_reply"><br>
						<textarea style="width: 90%;, height: 100%;" name="reply_comment_text" id="reply_comment_text" autofocus></textarea><br>
						이메일 : <input type="email" name="reply_email">
						<button type="button" onClick="cancle();">댓글취소</button>
						<button type="button" onClick="reply_comment();">답글달기</button>
					</div>
			    </c:if>
				</td>
			</tr>
		</table><br>
		<hr>
		<c:set var="b" value="${b = a + 1 }"></c:set>
	</c:forEach>
	
	</form>
	
	<form name="commentForm">		
		<input type="hidden" name="no" value="${dto.no }">
		
		<div style="background-color: #f0f0f0">
			<table id="comment_reply">
				<tr>
					<td colspan="2">
						댓글을 달아주세요!<br>
						<textarea style="width: 100%;, height: 100%;" name="comment"></textarea><br>
						이메일 : <input type="email" name="email">
					</td>
				</tr>
			</table><br>
			<div style="float: right;">
				<button type="button" onClick="move('chuga.do', '');">글쓰기</button>
				<button type="button" onClick="comment1();">댓글달기</button>
			</div>
		</div>
		<br><br><br>
	</form>
	
	

</div>
<script>

	function replymove(value1, value2) {
		location.href = "${path }/boardBasic_servlet/view.do?no=" + value1 + "&no_reply=" + value2;
		
		$("#replytextarea").show();
	}
	
	function cancle() {
		$("#replytextarea").hide();
	}
	
	/* value =""

		iunput value

		<div id="test">
		<input name="title" value="aaa"/>
		</div>

		$("#test").html("<input type='ccc'>");

		<div id="test">
		<input type=''>
		</div>

		$("#test").append("<input type=''>");


		<div id="test">
		bbb
		</div> */
	
	
	function move(value1, value2) {
		location.href="${path }/boardBasic_servlet/" + value1 + "?no=" + value2;
	}
	
	
	function comment1() {
 		if(commentForm.comment.value == '') {
			alert("댓글을 입력해주세요!")
			commentForm.comment.focus();
			return;
		} 
 		
 		if(commentForm.email.value == '') {
			alert("이메일을 입력해주세요!")
			commentForm.email.focus();
			return;
		}
		
		if(confirm("댓글을 등록하시겠습니까?")) {
			commentForm.method="post";
			commentForm.action="${path }/boardBasic_servlet/commentProc.do";
			commentForm.submit();
		}
	}
	
	function reply_comment() {
 		if(replyForm.reply_comment_text.value == '') {
			alert("답글을 입력해주세요!")
			replyForm.reply_comment_text.focus();
			return;			
		}
		
		if(replyForm.reply_email.value == '') {
			alert("이메일을 입력해주세요!")
			replyForm.reply_email.focus();
			return;
		}
		
		if(confirm("답글을 등록하시겠습니까?")) {
			replyForm.method="post";
			replyForm.action="${path }/boardBasic_servlet/commentProc.do";
			replyForm.submit();
		}
		
	}
</script>

</body>
</html>