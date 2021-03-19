<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/top"/>

<script>
    function checkId(uid) {
        let len = uid.length;
        $('#msgId').text('')
            .removeClass('text-danger')
            .removeClass('text-primary');
        if (len < 4 || len > 18) {
            $('#msgId').text('아이디는 4자 이상 18자 이내로 가능합니다')
                .addClass('text-danger');
            $('#id').select();
            return;
        }
        $.ajax({
            type: 'GET',
            url: 'idcheck?id=' + uid,
            dataType: 'json', // pom.xml에 json관련 라이브러리 등록해야 함
            cache: false,
        }).done(function (res) {
            let n = parseInt(res.isUse);
            let cls = ''
            if (n > 0) {
                cls = 'text-primary';
                $('#idstate').val(1);
            } else {
                cls = 'text-danger'
                $('#idstate').val("");
            }
            $('#msgId').text(res.idResult).addClass(cls)
        }).fail(function (err) {
            alert('error: ' + err.status);
        })
    }

    function check() {
        if (!joinF.idstate.value) {
            alert('입력한 아이디는 사용 불가능합니다.');
            f.id.select();
            return;
        }
        f.submit();
    }
</script>

<script src="../js/join.js"></script>

<div class="in" style="display: inline-block; margin: 0 auto;">
    <div class="card align-middle"
         style="width: 50rem; border-radius: 20px;">
        <div class="card-title" style="margin-top: 30px;">
            <h2 class="card-title text-center" style="color: #113366;">회원가입</h2>
        </div>
        <div class="card-body">
            <form name="joinF" id="joinF" action="join" method="POST">
                <h5 class="form-signin-heading">양식에 맞춰 입력해 주세요</h5>
                <label for="inputName" class="sr-only">*이름</label> <input
                    type="text" name="name" id="name" class="form-control"
                    placeholder="NAME" required autofocus><BR> <label
                    for="inputId" class="sr-only">*아이디</label>
                <input type="hidden" name="idstate" id="idstate">
                <input type="text" name="id" id="id" placeholder="User ID" class="form-control"
                       onchange="checkId(this.value)">
                <div id="msgId"></div>
                <!--아이디 사용 가능 여부를 보여줄 예정  -->
                <span class="text-danger">
				 	<form:errors path="id"/>
				 </span>
                <br> <label for="inputPwd" class="sr-only">*비밀번호</label> <input
                    type="password" name="pwd" id="pwd" class="form-control"
                    placeholder="Password" required autofocus><br> <label
                    for="inputCheckPwd" class="sr-only">*비밀번호확인</label> <input
                    type="password" name="pwd2" id="pwd2" class="form-control"
                    placeholder="RE Password" required autofocus><br> <label
                    for="inputTel" class="sr-only">*연락처</label> <input type="text"
                                                                       name="tel" id="tel" maxlength="11"
                                                                       class="form-control"
                                                                       placeholder="Tel" required autofocus><BR> <label
                    for="inputEmil" class="sr-only">*연락처</label> <input type="text"
                                                                        name="email" id="email" class="form-control"
                                                                        placeholder="email"
                                                                        required autofocus><BR>

                <div>
                    <button type="button" id="btnOk" class="btn btn-success" onclick="check()">회원가입</button>
                    <button type="reset" class="btn btn-warning">다시쓰기</button>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="/foot"/>