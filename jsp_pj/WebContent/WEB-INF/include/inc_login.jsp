<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="./inc_header.jsp" %>

<p align="right" style="padding-right: 10%;">
<c:choose>
	<c:when test ="${sessionScope.cookNo == null || sessionScope.cookNo == 0}">
		<a href = "${path }/member_servlet/login.do">로그인</a>
	</c:when>
	<c:otherwise>
		<b>${cookName }님 반갑습니다..</b>
		<a href = "${path }/member_servlet/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
</p>
<br>
<hr>
    