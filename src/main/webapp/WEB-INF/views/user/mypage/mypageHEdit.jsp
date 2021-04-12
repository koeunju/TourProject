<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top_sub" />

<script type="text/javascript">
    function ready() {
        alert('준비중에 있습니다.');
    }

    //비밀번호 변경 체크
    function checkPwd(idx){
        let str = '<input type="password" id="repwd" name="repwd" class="form-control" onchange="checkp(${user.idx},this.value)" placeholder="현재 비밀번호를 입력해주세요">';
        $('#msgPwd').html(str);
        document.meF.pwd.readOnly = true;
        document.meF.remypwd.readOnly = true;
        $('#remypwd').val("");
        $('#pwd').val("");
        
    }
    let cls='';
    function checkp(idx,repwd){
        $.ajax({
            type:'get',
            url:'/user/pwdcheck?idx='+idx+"&pwd="+repwd,
            dataType:'json',
            cache:false,
        }).done(function(res){
            let n = parseInt(res.check);
            
            if(n>0){
            	cls='';
                cls='text-primary';
                $('#pwdstate').val(1);
                document.meF.pwd.readOnly = false;
                document.meF.remypwd.readOnly = false;
            }else{
            	cls='';
                cls='text-danger'
                $('#pwdstate').val("");
            }
            $('#msgPwd').removeClass();
            $('#msgPwd').text(res.okPwd).addClass(cls);


        }).fail(function(err){
            alert('error: '+err.status);

        })
    }

    $(function () {
        $('#rewrite').on('click', function (e) {
            e.preventDefault();
            var $nick = $('#nick');
            var $pwd = $('#pwd');
            var $pwd2 = $('#remypwd');
            var $tel = $('#tel');
            var $email = $('#email');
			
            if(cls == 'text-primary'){
            	if(!pwd == null){
            		 alert('수정할 비밀번호를 입력해주세요');
                     $pwd.focus();
                     return;
            	}
            	if ($pwd.val() != $pwd2.val()) {
                    alert('비밀번호가 일치하지 않습니다.');
                    $pwd2.focus();
                    return;
                }
            	if (!$pwd2.val()) {
                    alert('비밀번호 재확인 해주세요');
                    $pwd2.focus();
                    return;
                }
            }
            if (!$nick.val()) {
                alert('닉네임을 입력하세요');
                $nick.focus();
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
        <form name="meF" id="meF" action="edit" method="POST" enctype="multipart/form-data">

            <input type="hidden" id="idx" name="idx" value="${user.idx }">
            <table class="table table-hover" id="mypageT">
                <tr>
                    <td rowspan="7" style="width: 30%; padding: 10px;">
                    사진<br><img src="../user/upload/${user.image }"
                            style="width: 100%; j margin: 20px; border: 1px solid gray"><br>
                            <input type="file" name="mimage"
                                                      id="image" placeholder="Select IMAGE" class="form-control">
                        </td>
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
                    <td>${user.email }
                        <input type="hidden" id="email" name="email" value="${user.email }"></td>
                </tr>

                <tr>
                    <th colspan="2">연락처</th>
                    <td colspan="2"><input type="text" value="${user.tel }"
                                           name="tel" id="tel" class="form-control"></td>
                </tr>

                <tr>
                    <th colspan="2">비밀번호 변경여부</th>
                    <td colspan="2">

                        <button type="button" class="btn btn-primary" id="checkmypwd"
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
            <input type="text" name="adminCheck" value="${adminCheck}">
        </form>
        <!-- 버튼정렬div -->
    </div>
    <!-- 내정보 div -->
</div>

<c:import url="/foot_sub" />