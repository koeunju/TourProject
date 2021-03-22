<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/top.jsp" />

<script src="./js/join.js"></script>


<div class="in" style="display: inline-block; margin: 0 auto;">
	<div class="card align-middle"
		style="width: 50rem; border-radius: 20px;">
		<div class="card-title" style="margin-top: 30px;">
			<h2 class="card-title text-center" style="color: #113366;">회원가입</h2>
		</div>
		<div class="card-body">
			<form name="joinF" id="joinF" action="joinEnd.do" method="POST">
				<h5 class="form-signin-heading">양식에 맞춰 입력해 주세요</h5>
				<h6 class="text-danger">* 아이디는 영문자,숫자,!,_로 4~8자 이내로 가능합니다.</h6>
				<label for="inputName" class="sr-only">*이름</label> <input
					type="text" name="name" id="name" class="form-control"
					placeholder="NAME" required autofocus><BR> <label
					for="inputId" class="sr-only">*아이디</label> <input type="text"
					name="id" id="id"  placeholder="User ID" readonly class="form-control">
				<button type="button" onclick="openWin()" class="btn btn-outline-primary" >아이디 확인</button>
				<br>
				
				<br> <label for="inputPwd" class="sr-only">*비밀번호</label> <input
					type="password" name="pwd" id="pwd" class="form-control"
					placeholder="Password" required autofocus><br> <label
					for="inputCheckPwd" class="sr-only">*비밀번호확인</label> <input
					type="password" name="pwd2" id="pwd2" class="form-control"
					placeholder="RE Password" required autofocus><br> <label
					for="inputTel" class="sr-only">*연락처</label> <input type="text"
					name="tel" id="tel" maxlength="11" class="form-control"
					placeholder="Tel" required autofocus><BR> <label
					for="inputEmil" class="sr-only">*연락처</label> <input type="text"
					name="email" id="email" class="form-control" placeholder="email"
					required autofocus><BR>

				<div>
				<button id="btnOk" class="btn btn-success">회원가입</button>
				<button type="reset" class="btn btn-warning">다시쓰기</button>
				</div>
			</form>

		</div>
	</div>
</div>



<jsp:include page="/foot.jsp" />
