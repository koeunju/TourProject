<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top" />




<script src="../js/point.js"></script>

<!-- 메뉴사이드바 -->

<!-- 내정보 -->
<div class="container">
	<div class="m-5 p-3 text-center"
		style="border: 1px solid gray; border-radius: 15px" id="font2">
		<h1 class="text-bold" id="font1">MyPage</h1>
		<br>
		<!-- 내상태  -->
		<h5 class="text-right font-weight-bold" id="font1">내 상태</h5>
		<c:if test="${user.stat==1}">
			<h6 class="text-right font-weight-bold text-success">활동회원</h6>
		</c:if>
		<c:if test="${user.stat==3}">
			<h6 class="text-right font-weight-bold text-info">휴먼회원</h6>
		</c:if>
		<c:if test="${user.stat==4}">
			<h6 class="text-right font-weight-bold text-danger">탈퇴회원</h6>
		</c:if>
		<c:if test="${user.stat==9}">
			<h6 class="text-right font-weight-bold text-primary">관리자</h6>
		</c:if>
		<!-- 내정보 -->
		<table class="table table-hover" id="mypageT">
			<tr>
			 <td rowspan="7" style="width: 30%; padding: 10px;">
                    사진<br><img src="../user/upload/${user.image }"
                            style="width: 100%; j margin: 20px; border: 1px solid gray"><br>
					<br> 사진</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${user.nick }</td>
				<th>가입일</th>
				<td>${user.indate }</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>${user.id }</td>
				<th>이메일</th>
				<td>${user.email }</td>
			</tr>
			<tr>
				<th colspan="2">내 연락처</th>
				<td colspan="2">${user.tel }</td>
			</tr>
			<tr>
				<th colspan="2">내포인트</th>
				<td colspan="2">${mytotalpoint }POINT
				<a id = "more" href="javascript:morePoint()" style="display:Inline"><label class="badge badge-warning">더보기▼</label> </a>
				<a id = "close" href="javascript:closePoint()" style="display:none"><label  class="badge badge-warning">닫기▼</label> </a>
					<div id="pointView" style="display:none">
						<table class="table" >
							<tr>
								<th>구분번호</th>
								<th>포인트 적립</th>
								<th>포인트 사용</th>
								<th>적립/사용 내역</th>
								<th>적립/사용 일</th>
								<th>총 포인트</th>
							</tr>
							<!-- 포인트 적립내역 출력 -->
							<c:if test="${mypoint  eq null or empty mypoint  }">
								<tr>
									<td>적립된 포인트가 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${mypoint ne null and not empty mypoint }">
								<c:forEach var="mypoint" items="${mypoint }" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td><c:if
												test="${mypoint.pcontent eq 400 or empty mypoint.pcontent}">
                            -
                        </c:if> <c:if test="${mypoint.pcontent ne 400 }">
												<label class="text-primary">${mypoint.psavePoint }</label>
											</c:if></td>

										<td><c:if test="${mypoint.pcontent ne 400}">
                            -
                        </c:if> <c:if test="${mypoint.pcontent eq 400 }">
												<label class="text-danger">${mypoint.psavePoint }</label>
											</c:if></td>
										<td><c:if test="${mypoint.pcontent==100 }">
												<label>회원가입</label>
											</c:if> <c:if test="${mypoint.pcontent==200 }">
												<label>게시판 글 작성</label>
											</c:if> <c:if test="${mypoint.pcontent==300 }">
												<label>리뷰작성</label>
											</c:if> <c:if test="${mypoint.pcontent==400 }">
												<label>포인트샵 사용</label>
											</c:if> <c:if test="${mypoint.pcontent==500 }">
												<label>이달의 여행지 선정</label>
											</c:if></td>

										<td>${mypoint.pdate }</td>
										<td>${mypoint.ptotalPoint }</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
						<lable>상세 포인트 확인 내역을 확인하시려면 <a href="/user/mypoint?idx=${user.idx }" id = "morePoint">이곳을</a> 클릭해 주세요.<br>사용목록이 30개가 넘으면 자동 삭제 됩니다.</lable>
					</div>
				</td>
			</tr>
		</table>
		<div class="container text-right">
			<c:if test="${user.stat ne 9}">
				<button class="btn btn-success" id="rewrite"
					onclick="location.href='/user/edit?idx=${user.idx}'">수정하기</button>
				<button class="btn btn-danger" id="userexit"
					onclick="leave(${user.idx})">탈퇴하기</button>
			</c:if>
			<c:if test="${user.stat eq 9 }">
				<label class="text-success" style="font-style: bold" id="font1">관리자는
					삭제 및 탈퇴 처리가 불가능 합니다.</label>
			</c:if>
		</div>
		<!-- 버튼정렬div -->
	</div>
	<!-- 내정보 div -->
</div>
<!-- 삭제  form---------------- -->
<form name="pf" id="pf" method="post">
	<input type="hidden" name="idx" id="idx">
</form>
<!-- -------------------------------- -->
<script type="text/javascript">
	function leave(num){
		var n = $('#idx').val(num);
		$('#pf').attr('action','/user/del')
		$('#pf').submit();
	}
</script>
<c:import url="/foot" />