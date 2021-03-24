<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top" />

<%-- <%@ include file="/WEB-INF/views/user/userIdSearchModal.jsp" %>  --%>

	<div class="in" style=" display: inline-block; margin:0 auto;">
		<div class="card align-middle"
			style="width: 20rem; border-radius: 20px;">
			<div class="card-title" style="margin-top: 30px;">
				<h3 class="card-title text-center" style="color: #113366;">비밀번호 찾기
					</h3>
	           </div>
	           <div class="card-body">                   
					<label for="inputId" class="sr-only">아이디</label>
					 <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요." required>
				     <br>
					<label for="inputEmail" class="sr-only">이메일</label>
					 <input	type="text" class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" required>
					 <br>
					<div class="form-group">
					<button id="searchBtn" type="button" class="btn btn-primary btn-block">확인</button>
					<a class="btn btn-danger btn-block"	href="${pageContext.request.contextPath}">취소</a>
					</div>
			</div>
		</div>
	</div>

	
<c:import url="/foot" />