<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
$(window).scroll(function(){ 
	var position = $(window).scrollTop()+35;
	$(window).scroll(function( ){
		var position = $(window).scrollTop()+35;
		$( "#left" ).stop().animate({top:position+"px"}, 800);
	});
});
</script>

<%@ include file="../include/inc_header.jsp" %>

<nav class="sidebar">
	<ul class="myMenu">
		<li><a href="${path }/">HOME</a></li>
		<li class="menu_member">
			<a href="${path }/member_servlet/list.do">회원관리</a>
			<ul class="menu_member_s submenu">
				<li><a href="${path }/member_servlet/list.do">회원목록</a></li>
				<li><a href="${path }/member_servlet/chuga.do">회원등록</a></li>
				<li><a href="${path }/member_servlet/login.do">로그인</a></li>
			</ul>
		</li>
		<li>
			<a href="${path }/sungjuk_servlet/list.do">성적관리</a>
			<ul class="menu_sujung submenu">
				<li><a href="${path }/sungjuk_servlet/list.do">성적목록</a></li>
				<li><a href="${path }/sungjuk_servlet/chuga.do">성적등록</a></li>
			</ul>
		</li>
		<li><a href="${path }/memo_servlet/list.do">메모관리</a></li>
		<li>
			<a href="${path }/guestBook_servlet/list.do">방명록</a>
			<ul class="menu_guestBook submenu">
				<li><a href="${path }/guestBook_servlet/list.do">방명록목록</a></li>
				<li><a href="${path }/guestBook_servlet/chuga.do">방명록등록</a></li>
			</ul>
		</li>
		<%-- <li><a href="${path }/survey_servlet/list.do">설문관리</a></li> --%>
		<li>
			<a href="${path }/product_servlet/list.do">상품관리</a>
			<ul class="menu_product submenu">
				<li><a href="${path }/product_servlet/list.do">상품목록</a></li>
				<li><a href="${path }/product_servlet/chuga.do">상품등록</a></li>
			</ul>
		</li>
		<li>
			<a href="${path }/shop_servlet/list.do">SHOP</a>
			<ul class="menu_shop submenu">
				<li><a href="${path }/shop_servlet/list.do">쇼핑하기</a></li>
				<li><a href="${path }/shop_servlet/market.do">장바구니</a></li>
			</ul>
		</li>
		<li>
			<a href="${path }/boardBasic_servlet/list.do">게시판</a>
			<ul class="menu_boardBasic submenu">
				<li><a href="${path }/boardBasic_servlet/list.do">게시판목록</a></li>
				<li><a href="${path }/boardBasic_servlet/chuga.do">글쓰기</a></li>
			</ul>
		</li>
		<li>
			<a href="${path }/chart_servlet/index.do">Chart</a>
			<ul class="menu_chart submenu">
				<li><a href="${path }/chart_servlet/index.do">차트목록</a></li>
				<li><a href="${path }/chart_servlet/piechart.do">PieChart</a></li>
				<li><a href="${path }/chart_servlet/piechart3d.do">PieChart3D</a></li>
				<li><a href="${path }/chart_servlet/combochart.do">ComboChart</a></li>
				<li><a href="${path }/chart_servlet/linechart.do">LineChart</a></li>
				<li><a href="${path }/chart_servlet/jsonFile.do">JsonFile</a></li>
			</ul>
		</li>
	</ul>
</nav>
