<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top_sub.jsp" />

<script type="text/javascript">
function ready(){
	alert('준비중에 있습니다.');
}

</script><!-- 메뉴사이드바 -->
<nav class="navbar navbar-expand-sm bg-white navbar-white" id="font1">
	<a class="navbar-brand" href="#">My Page</a>
	<button class="navbar-toggler " type="button" data-toggle="collapse"
		data-target="#mypageNavbar">

		<i class="fas fa-bars"></i>
	</button>
	<div class="collapse navbar-collapse" id="mypageNavbar">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mypageHome.do">내정보확인</a></li>
			<li class="nav-item"><a class="nav-link" href="#" onclick="ready()">찜한여행지</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/mypageWrite.do">내가 쓴 글</a></li>

		</ul>
	</div>
</nav>
<!-- 내정보 -->
<div class="container">
	<div class="m-5 p-3 text-center"
		style="border: 1px solid gray; border-radius: 15px" id="font2">
		<h1 class="text-bold" id="font1">MyWrite</h1>
		<br>

		<!-- 내가 쓴글 -->
		<table class="table table-hover text-center" id="mypageT">
			<tr class="table-dark  text-dark">
				<th style="width:20%">작성게시판</th>
				<th style="width:50%">제목</th>
				<th style="width:10%">조회수</th>
				<th style="width:20%">작성일</th>
			</tr>
			<tr>
			<td><a>a</a></td>
			<td><a>a</a></td>
			<td><a>a</a></td>
			<td><a>a</a></td>
			</tr>
		</table>
	</div>
	<!-- 내정보 div -->
</div>

<jsp:include page="/foot_sub.jsp" />