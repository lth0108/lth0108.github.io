<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style type="text/css">

     <%@ include file = "./include/pj.css" %>
     <%@ include file = "./include/home.css"%>
     <%@ include file = "./include/checkbox.css"%>

#about{
	background-color:#f5f5f5;
	height: 230%;
}
#projects{
	background-color:#dfe1d5;
	height: 620%;
}
#contact{
	background-color:#333135;
	color:white;
	height: 60%;
}
.skills {
	width: 200px; height: 200px; background-size: cover; display: inline-block;
}
.hover {
	color: white; background-color: black;  width: 200px; height: 200px; 
	opacity: 0.9; text-align: center; display:none;
}
     
</style>
</head>
<body>
<div id="background">

	<%@ include file="./include/inc_header.jsp" %>
	<img src="">
	
	<input type="checkbox" id="menuicon">
	<label for="menuicon">
		<span></span>
		<span></span>
		<span></span>
	</label>
	<div class="sidebar">
		<div style="float: left; width: 40%;">
			<div class="home" style="margin-top: 140px;">
				<a href="#background" class="a"><b>Home</b></a>
			</div>
			<div class="home">
				<a href="#about" class="a"><b>About</b></a>
			</div>
			<div class="home">
				<a href="#projects" class="a"><b>Projects</b></a>
			</div>
			<div class="home">
				<a href="#contact" class="a"><b>Contact</b></a>
			</div>
		</div>
	</div>
	
	
	<div class="demo1" id="demo1">
	개발자 이태한의 포트폴리오입니다!
	</div>
	<div class="demo1" style="display: none;" id="hide">
	<br>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<div id="about">
		<div style="font-size: 50px; padding-left: 650px; padding-top: 150px;">
			<b>안녕하세요. 저는 이태한입니다.<br>
			저는 사용자가 좀더 편리하고, 효율적으로 사용할 수<br>
			있는 프로그램을 만들고 싶은 개발자입니다.</b>
		</div>
		<div style="font-size: 30px; padding-left: 650px; padding-top: 100px;">
			새로운 걸 배우기를 갈망하고 더 깊이 알고 싶어 하는 욕구가 강한 사람입니다.<br>
			또한, 동료들과의 커뮤니케이션과 상호 피드백 및 리뷰를 좋아합니다.<br>
			코딩하는 것이 즐겁고 더 많은 사람들과 일하고 배우며 성장하고 싶습니다.
		</div>
		
		<div style="font-size: 50px; padding-left: 650px; padding-top: 100px;">
			<b>Overall Experiences</b>
		</div>
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>국비지원 교육 - 공공SW 기반 자바프레임워크 개발자 양성</b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			2021. 05. - 2021. 11<br>
			한국IT교육원에서 국비지원 교육 개발자 양성 프로그램을 통해 자바 문법,<br>
			DB, jsp, jquery, spring 등 백엔드 개발에 필요한 기본적인 지식을 습득하였습니다. 
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>코딩테스트를 통한 알고리즘 학습</b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			프로그래머스, Baekjoon Online Judge 등의 코딩 테스트 사이트를 통하여 <br>
			알고리즘을 더욱 효율적으로 구상하는 방법을 학습하였습니다.
		</div>
		
		<div style="font-size: 50px; padding-left: 650px; padding-top: 100px;">
			<b>Skills</b>
		</div>
		<div id="skill" style="font-size: 30px; padding-left: 700px; padding-top: 70px;">
		<div style="display: inline; padding-left:350px;"><b>Backend</b><br><br></div>
			<div class="skills" id="java" style="background-image: url('/jsp_pj/attach/img/java.png');">
				<div class="hover" id="hover_java"><br>Java<br>Familiar</div>
			</div>
			<div class="skills" id="spring" style="background-image: url('/jsp_pj/attach/img/spring.png');">
				<div class="hover" id="hover_spring"><br>Spring<br>tried</div>
			</div>
			<div class="skills" id="oracle" style="background-image: url('/jsp_pj/attach/img/oracle.jpg');">
				<div class="hover" id="hover_oracle"><br>Oracle DB<br>Familiar</div>
			</div>
			<div class="skills" id="mybatis" style="background-image: url('/jsp_pj/attach/img/mybatis.png');">
				<div class="hover" id="hover_mybatis"><br>Mybates<br>tried</div>
			</div>
			<br><br><br>
		<div style="display: inline; padding-left:350px;"><b>Frontend</b><br><br></div>
			<div class="skills" id="jquery" style="background-image: url('/jsp_pj/attach/img/jquery.png');">
				<div class="hover" id="hover_jquery"><br>JQUERY<br>tried</div>
			</div>
			<div class="skills" id="html" style="background-image: url('/jsp_pj/attach/img/html.png');">
				<div class="hover" id="hover_html"><br>HTML<br>tried</div>
			</div>
			<div class="skills" id="css" style="background-image: url('/jsp_pj/attach/img/css.png');">
				<div class="hover" id="hover_css"><br>CSS<br>tried</div>
			</div>
			<div class="skills" id="javascript" style="background-image: url('/jsp_pj/attach/img/javascript.png');">
				<div class="hover" id="hover_javascript"><br>JavaScript<br>tried</div>
			</div>
		</div>
	</div>
	
	<div id="projects">
		<div style="font-size: 50px; padding-left: 650px; padding-top: 100px;">
			<b>관리자 Dashboad</b>
		</div>
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>Description</b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			CRUD형식의 게시판, 유저 커스터마이징, 로그인, 관리자페이지, 회원관리, 상품관리, 검색 기능을 구현한 웹사이트입니다.<br>
			로그인 아이디에 따른 회원관리 권한을 구분하여 아이디에 따라 다른 권한을 부여하였고 상품관리에서 상품 이미지 파일을<br>
			업로드 할때 파일의 형식을 검사하여 이미지 파일만이 업로드 되도록 하였습니다.<br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>Tech Stack</b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			Javascript, Jquery, Oracle DB, HTML, CSS
		</div>
		<br><br><br>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>회원등록, 수정, 삭제 페이지<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			회원등록과 로그인 기능을 가진 페이지입니다.<br>
			<img src="/jsp_pj/attach/img/gif/member_page1.gif" style="width: 80%; height: 80%;"><br><br>
			
			user 계정은 회원등록, 수정을 사용할 수 있고, 그 외의 계정은 본인의 회원정보 수정까지만 사용가능하도록,<br>
			admin 계정으로는 등록, 수정, 삭제 모든 기능이 사용가능한 회원관리 페이지입니다.<br>
			<img src="/jsp_pj/attach/img/gif/member_page2.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>성정등록, 수정, 삭제 페이지<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			등록한 회원의 아이디를 선택하여 성적을 등록, 수정, 삭제, 검색하는 페이지입니다.<br>
			<img src="/jsp_pj/attach/img/gif/sungjuk_page.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>메모 등록, 수정, 삭제 페이지입니다.<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			메모를 등록한 후 jquery를 사용하여 등록버튼이 수정, 삭제로 바뀌도록 만들었습니다.<br>
			<img src="/jsp_pj/attach/img/gif/memo_page.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>상품 등록, 장바구니 관리, 구매 페이지입니다.<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			<img src="/jsp_pj/attach/img/gif/shop_page.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>게시글, 댓글을 등록하는 게시글 페이지입니다.<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			<img src="/jsp_pj/attach/img/gif/board_page.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
		
		<div style="font-size: 30px; padding-left: 650px; padding-top: 70px;">
			<b>장바구니에 등록된 상품별 차트입니다.<br></b>
		</div>
		<div style="font-size: 20px; padding-left: 650px;">
			<img src="/jsp_pj/attach/img/gif/chart_page.gif" style="width: 80%; height: 80%;"><br><br>
		</div>
	
	</div>
	
	<div id="contact">
		<div style="font-size: 50px; padding-left: 650px; padding-top: 100px;">
			<b>Contact</b>
		</div>
		<div style="font-size: 30px; padding-top: 70px; text-align: center;">
			<b>E-mail : taehan0108@gmail.com</b><br><br>
			<a href="https://github.com/lth0108/lth0108.github.io"><img src="/jsp_pj/attach/img/git.png" style="width: 100px; height: 100px;"><br><br></a>
		</div>
	</div>
</div>

<script>
<%@ include file = "./include/hover.jsp"%>

$(document).ready(function() {	//이 문서의 로딩이 끝나고 나서
	$("#menuicon").click(function () {
		if($("#menuicon").prop("checked")) {
			$("#demo1").hide();
			$("#hide").show();
		} else {
			$("#demo1").show();
			$("#hide").hide();
		}
	});
});
</script>

</body>
</html>