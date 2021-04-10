<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top_sub" />
<c:import url="/user/mypageMenubar" />
<script type="text/javascript" src="../js/toursave.js">

</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411"></script>
<!-- 메뉴사이드바 -->

<!-- 내정보 -->
<div class="container">
	<div class="m-5 p-3 text-center"
		style="border: 1px solid gray; border-radius: 15px" id="font2">
		<h1 class="text-bold" id="font1">찜한 여행지</h1>
		<br>


		<!--찜한 여행지 출력 -->
		<table class="table table-hover" id="mypageT">
			<tr>
				<th>구분번호</th>
				<th>찜한 날짜</th>
				<th>찜한 여행지 이름</th>
				<th>찜 해제</th>
			</tr>
			<c:if test="${mytour eq null or empty mytour }">
				<tr>
					<td colspan="4">찜 한 여행지 정보가 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${mytour ne null and not empty mytour }">
				<c:forEach var="mytour" items="${mytour }" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${mytour.sdate }</td>
						<td><a href="#" onclick="Detail(${mytour.contentId})">
								${mytour.contentId }</a></td>
						<td><a href="#">찜 해제</a></td>
					</tr>
					<tr><td colspan="4"><div id="mySaveTour" style="display:none"></div></td></tr>
				</c:forEach>
			</c:if>
		</table>
		


		<!-- 버튼정렬div -->
	</div>
	<!-- 내정보 div -->
</div>

<c:import url="/foot_sub" />