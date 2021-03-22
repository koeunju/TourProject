<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/top.jsp" />

<script type="text/javascript">
function ready(){
	alert('준비중에 있습니다.');
}


</script>
<!-- 메뉴사이드바 -->
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
		<h1 class="text-bold" id="font1">MyPage</h1>
		<br>
		<!-- 내상태  -->

		<h5 class="text-right font-weight-bold" id="font1">내 상태</h5>
		<c:if test="${loginUser.stat==1}">
		<h6 class="text-right font-weight-bold text-success">활동회원</h6>
		</c:if>
		<c:if test="${loginUser.stat==3}">
		<h6 class="text-right font-weight-bold text-info">휴먼회원</h6>
		</c:if>
		<c:if test="${loginUser.stat==4}">
		<h6 class="text-right font-weight-bold text-danger">탈퇴회원</h6>
		</c:if>
		<c:if test="${loginUser.stat==10}">
		<h6 class="text-right font-weight-bold text-primary">관리자</h6>
		</c:if>
		<!-- 내정보 -->
		<table class="table table-hover" id="mypageT">
			<tr>
				<td rowspan="7" style="width: 30%; padding: 10px;">
				<img src="./image/ready.png"	style="width: 100%;  margin: 20px; border: 1px solid gray"><br>
					<br> 사진</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${loginUser.name }</td>
				<th>가입일</th>
				<td>${loginUser.indate }</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>${loginUser.id }</td>
				<th>이메일</th>
				<td>${loginUser.email }</td>
			</tr>
			<tr>
				<th colspan="2">내 연락처</th>
				<td colspan="2">${loginUser.tel }</td>
			</tr>
			<tr>
				<th colspan="2">내포인트</th>
				<td colspan="2">${loginUser.point }POINT</td>
			</tr>

		</table>
		<div class="container text-right">
			<button class="btn btn-success" id="rewrite" onclick="location.href='mypageHEdit.do'">수정하기</button>
			<button class="btn btn-danger" id="userexit">탈퇴하기</button>
		</div>
		<!-- 버튼정렬div -->
	</div>
	<!-- 내정보 div -->
</div>

<jsp:include page="/foot_sub.jsp" />