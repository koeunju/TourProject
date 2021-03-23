<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top_sub" />

<script type="text/javascript">
function ready(){
	alert('준비중에 있습니다.');
}

</script>
<!-- 메뉴사이드바 -->
<jsp:include page="menubar.jsp"/>
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
			<c:forEach var="board" items="${board }">
			<tr>
			<td><a>${board.cg_num }</a></td>
			<td><a>${board.btitle }</a></td>
			<td><a>a</a></td>
			<td><a>a</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 내정보 div -->
</div>

<c:import url="/foot_sub" />