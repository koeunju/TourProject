<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top_sub" />
<script type="text/javascript">
	function ready(){
		alert('준비중에 있습니다.');
	}
</script>
<!-- 메뉴사이드바 -->
<c:import url="/user/mypageMenubar"/>
<!-- 내정보 -->
<div class="container">
	<div class="m-5 p-3 text-center"
		 style="border: 1px solid gray; border-radius: 15px" id="font2">
		<h3 class="text-bold" id="font1">나의 작성 글</h3>
		<br>
		<!-- 내가 쓴글 -->
		<table class="table table-hover text-center" id="mypageT">
			<tr class="table-dark  text-dark">
				<th style="width:20%">작성게시판</th>
				<th style="width:50%">제목</th>
				<th style="width:10%">조회수</th>
				<th style="width:20%">작성일</th>
			</tr>
			<c:forEach var="board" items="${board}">
				<tr>
					<td>
						<c:if test="${board.cg_num==0 }">
							<a class = "viewBoard" href="/board/view?bnum=${board.bnum }" style="color:dark;">여행지 추천</a>
						</c:if>
						<c:if test="${board.cg_num==1 }">
							<a class = "viewBoard" href="/board/view?bnum=${board.bnum }">자유게시판</a>
						</c:if>
						<c:if test="${board.cg_num==2}">
							<a class = "viewBoard" href="/board/view?bnum=${board.bnum }">고객센터</a>
						</c:if>
					</td>
					<td><a class = "viewBoard" href="/board/view?bnum=${board.bnum }">${board.btitle }</a></td>
					<td><a class = "viewBoard" href="/board/view?bnum=${board.bnum }">${board.binquiry }</a></td>
					<td><a class = "viewBoard" href="/board/view?bnum=${board.bnum }">${board.bdate }</a></td>
				</tr>
			</c:forEach>
		</table>
		        <div class="container">
                ${pageNavi}</div>
	</div>
	<!-- 내정보 div -->
</div>
<c:import url="/foot_sub" />