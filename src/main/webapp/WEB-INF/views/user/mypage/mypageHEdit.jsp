<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top_sub" />

<script type="text/javascript">
    function ready() {
        alert('준비중에 있습니다.');
    }

    //비밀번호 변경 체크
    function checkPwd(idx){
        let str = '<input type="text" id="repwd" name="repwd" class="form-control" onchange="checkp(${user.idx},this.value)" placeholder="현재 비밀번호를 입력해주세요">';
        $('#msgPwd').html(str);
        $('#checkmypwd').hide();
    }

    function checkp(idx,repwd){
        $.ajax({
            type:'get',
            url:'/user/pwdcheck?idx='+idx+"&pwd="+repwd,
            dataType:'json',
            cache:false,
        }).done(function(res){
            let n = parseInt(res.check);
            let cls='';
            if(n>0){
                cls='text-primary';
                $('#pwdstate').val(1);
            }else{
                cls='text-danger'
                $('#pwdstate').val("");
            }
            $('#msgPwd').text(res.okPwd).addClass(cls);
            document.meF.pwd.readOnly = false;
            document.meF.remypwd.readOnly = false;

        }).fail(function(err){
            alert('error: '+err.status);

        })
    }

    $(function () {
        $('#rewrite').on('click', function (e) {
            e.preventDefault();
            var $name = $('#nick');
            var $pwd = $('#pwd');
            var $pwd2 = $('#remypwd');
            var $tel = $('#tel');
            var $email = $('#email');

            if (!$name.val()) {
                alert('닉네임을 입력하세요');
                $name.focus();
                return;
            }
            if (!$pwd.val()) {
                alert('비밀번호를 입력하세요');
                $pwd.focus();
                return;
            }
            if ($pwd.val() != $pwd2.val()) {
                alert('비밀번호가 서로 달라요');
                $pwd2.focus();
                return;
            }
            if (!$tel.val()) {
                alert('전화번호를 입력하세요');
                $tel.focus();
                return;
            }

            $('#meF').submit();
        })
    })
</script>

<!-- 메뉴사이드바 -->
<c:import url="/user/mypageMenubar" />

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
        <form name="meF" id="meF" action="edit" method="POST">

            <input type="hidden" id="idx" name="idx" value="${user.idx }">
            <table class="table table-hover" id="mypageT">
                <tr>
                    <td rowspan="7" style="width: 30%; padding: 10px;"><img
                            src="../image/noimage.png"
                            style="width: 100%; j margin: 20px; border: 1px solid gray"><br>
                        <br> 사진</td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" value="${user.nick}" name="nick"
                               id="nick" class="form-control"></td>
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
                    <th colspan="2">연락처</th>
                    <td colspan="2"><input type="text" value="${user.tel }"
                                           name="tel" id="tel" class="form-control"></td>
                </tr>

                <tr>
                    <th colspan="2">비밀번호 변경여부</th>
                    <td colspan="2">

                        <button type="button" id="checkmypwd"
                                onclick="checkPwd(${user.idx })">비밀번호 변경하기</button> <input
                            type="hidden" name="pwdState" id="pwdState">
                        <div id="msgPwd"></div>
                    </td>
                </tr>
                <tr>
                    <th colspan="2">비밀번호 입력</th>
                    <td colspan="2"><input type="password" name="pwd" id="pwd"
                                           class="form-control" readonly></td>
                </tr>
                <tr>
                    <th colspan="2">비밀번호 재 입력</th>
                    <td colspan="2"><input type="password" name="remypwd"
                                           id="remypwd" class="form-control" readonly></td>
                </tr>
                <tr>
                    <th colspan="2">내 상태</th>
                    <td colspan="2">
                        <div class="radio">
                            <c:if test="${user.stat==1 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1" checked>활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3">휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input
                                        type="radio" id="stat" name="stat" value="4" disabled>탈퇴회원
                                </label>
                            </c:if>

                            <c:if test="${user.stat==3 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1">활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3" checked>휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input
                                        type="radio" id="stat" name="stat" value="4" disabled>탈퇴회원
                                </label>

                            </c:if>

                            <c:if test="${user.stat==4 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1" disabled>활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3" disabled>휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input
                                        type="radio" id="stat" name="stat" value="4" disabled checked>탈퇴회원
                                </label>
                                <h6 class="text-danger" id="statMsg">탈퇴 처리를 해제하려면 고객센터에 문의
                                    해 주세요</h6>
                            </c:if>

                            <c:if test="${user.stat==9 }">
                                <label class="radio-inline text-warning">당신은 관리자 입니다. </label>

                            </c:if>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="container text-right">
                <input type="hidden" id="res" name="res">
                <button class="btn btn-success" id="rewrite" name="rewirte">수정하기</button>
                <button type="reset" class="btn btn-info" id="resetbtn">다시쓰기</button>
            </div>
        </form>
        <!-- 버튼정렬div -->
    </div>
    <!-- 내정보 div -->
</div>

<c:import url="/foot_sub" />