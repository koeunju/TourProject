<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top" />

<%@ include file="/WEB-INF/views/user/userIdSearchModal.jsp" %> 
<script>
$(document).ready(function() {
	// 1. 모달창 히든 불러오기
	$('#searchBtn').click(function() {
		$('#background_modal').modal('show');
	});
	// 2. 모달창 닫기 버튼
	$('.close').on('click', function() {
		$('#background_modal').hide();
	});
	// 3. 모달창 윈도우 클릭 시 닫기
	$(window).on('click', function() {
		if (event.target == $('#background_modal').get(0)) {
            $('#background_modal').hide();
         }
	});
});


var idV = "";
// 닉네임 값 받고 출력하는 ajax
var idSearch_click = function(){
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/userSearch?inputNick="
				+$('#inputNick').val()+"&inputEmail="+$('#inputEmail').val(),
		success:function(data){
			if(data == 0){
				$('#id_value').text("일치하는 아이디가 없습니다.");	
			} else {
				$('#id_value').text(data);
				// 아이디값 별도로 저장
				idV = data;
			}
		}
	});
}
</script>

	<div class="in" style=" display: inline-block; margin:0 auto;">
		<div class="card align-middle"
			style="width: 20rem; border-radius: 20px;">
			<div class="card-title" style="margin-top: 30px;">
				<h3 class="card-title text-center" style="color: #113366;">아이디 찾기
					</h3>
	           </div>
	           <div class="card-body">                
					<label for="inputNick" class="sr-only">닉네임</label>
					 <input type="text" class="form-control" name="inputNick" id="inputNick" placeholder="닉네임을 입력하세요." required>
				     <br>
					<label for="inputEmail" class="sr-only">이메일</label>
					 <input	type="text" class="form-control" name="inputEmail" id="inputEmail" placeholder="이메일을 입력하세요" required>
					 <br>
					<div class="form-group">
					<button id="searchBtn" type="button" onclick="idSearch_click()" class="btn btn-primary btn-block">확인</button>
					<a class="btn btn-danger btn-block"	href="${pageContext.request.contextPath}">취소</a>
					</div>
			</div>
		</div>
	</div>

	
<c:import url="/foot" />