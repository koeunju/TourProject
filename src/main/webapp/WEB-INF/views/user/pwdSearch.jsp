<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/top" />

<script>
	function send() {
		if (!$('#id').val()) {
			alert('아이디를 입력하세요');
			$('#id').focus();
			return false;
		}
		if (!$('#email').val()) {
			alert('이메일을 입력 하세요');
			$('#email').focus();
			return false;
		}
		return true;
	}
</script>


	<div class="in" style=" display: inline-block; margin:0 auto;">
		<div class="card align-middle"
			style="width: 20rem; border-radius: 20px;">
			<div class="card-title" style="margin-top: 30px;">
				<h3 class="card-title text-center" style="color: #113366;">비밀번호 찾기
					</h3>
	           </div>
	           <div class="card-body">     
	           	<form name="pwdSearch" id="pwdSearch" action="pwdSearchEnd" method="GET"
	           	onsubmit="return send()">              
					<label for="id" class="sr-only">아이디</label>
					 <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요." required>
				     <br>
					<label for="email" class="sr-only">이메일</label>
					 <input	type="text" class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" required>
					 <br>
					<div class="form-group">
					<button class="btn btn-primary btn-block">확인</button>
					<a class="btn btn-danger btn-block"	href="${pageContext.request.contextPath}/user/login">취소</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	
<c:import url="/foot" />