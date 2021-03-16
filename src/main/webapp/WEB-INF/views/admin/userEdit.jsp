<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/top" />

<script type="text/javascript">
    function ready() {
        alert('준비중에 있습니다.');
    }

    function openWin(idx) {
        var win = window.open("pwdCheck.do?idx=" + idx, "pwdCheck", "width=400, height=400, left=100, top=100");
    }

    $(function () {
        $('#rewrite').on('click', function (e) {
            e.preventDefault();
            var $name = $('#myname');
            var $pwd = $('#mypwd');
            var $pwd2 = $('#remypwd');
            var $tel = $('#mytel');
            var $email = $('#myemail');
            var $point = ${'#point'};


            if (!$name.val()) {
                alert('이름을 입력하세요');
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
            if (!$email.val()) {
                alert('이메일을 입력하세요');
                $email.focus();
                return;
            }
            if (!$point.val()) {
                alert('포인트를 입력하세요');
                $point.focus();
                return;
            }
            $('#meF').submit();
        })
    })
</script>

<nav class="navbar navbar-expand-md navbar-white bg-white text-white" id="font1">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/userList.do">유저관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/pointshopList.do">포인트 샵 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/purchase.do">구매 정보 관리</a>
            </li>
        </ul>
    </div>
</nav>


<!-- 내정보 -->
<div class="container">
    <div class="m-5 p-3 text-center"
         style="border: 1px solid gray; border-radius: 15px" id="font2">
        <h1 class="text-bold" id="font1">유저정보 수정</h1>
        <br>
        <form name="meF" id="meF" action="userEditEnd.do" method="POST">
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

                <input type="hidden" id="idx" name="idx" readonly value="${user.idx }">
                <table class="table table-hover text-left" id="mypageT">
                    <tr>
                        <td rowspan="7" style="width: 30%; padding: 10px;"><img
                                src="./image/ready.png"
                                style="width: 100%; j margin: 20px; border: 1px solid gray"><br>
                            <br> 사진
                        </td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td>${user.id }</td>
                        <th>회원번호</th>
                        <td><input type="text" name="myidx" readonly
                                   id="myidx" class="form-control" value="${user.idx }"></td>

                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" value="${user.name }" name="myname"
                                   id="myname" class="form-control"></td>
                        <th>가입일</th>
                        <td>${user.indate }</td>
                    </tr>


                    <tr>
                        <th>연락처</th>
                        <td><input type="text" value="${user.tel }"
                                   name="mytel" id="mytel" class="form-control"></td>

                        <th>이메일</th>
                        <td><input type="text" value="${user.email }" name="myemail"
                                   id="myemail" class="form-control"></td>
                    </tr>


                    <tr>
                        <th colspan="2">비밀번호 입력</th>
                        <td colspan="2"><input type="password" name="mypwd"
                                               id="mypwd" class="form-control" value="${user.pwd }"></td>
                    </tr>

                    <tr>
                        <th colspan="2">내 상태</th>
                        <td colspan="2">
                            <div class="radio">
                                <c:if test="${user.stat==1 }">
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="1" checked>활동회원
                                    </label>
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="3">휴먼회원
                                    </label>
                                    <label class="radio-inline text-danger"> <input type="radio"
                                                                                    id="mystat" name="mystat" value="4">탈퇴회원
                                    </label>
                                </c:if>

                                <c:if test="${user.stat==3 }">
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="1">활동회원
                                    </label>
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="3" checked>휴먼회원
                                    </label>
                                    <label class="radio-inline text-danger"> <input type="radio"
                                                                                    id="mystat" name="mystat" value="4">탈퇴회원
                                    </label>

                                </c:if>

                                <c:if test="${user.stat==4 }">
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="1">활동회원
                                    </label>
                                    <label class="radio-inline"> <input type="radio"
                                                                        id="mystat" name="mystat" value="3">휴먼회원
                                    </label>
                                    <label class="radio-inline text-danger"> <input type="radio"
                                                                                    id="mystat" name="mystat" value="4"
                                                                                    checked>탈퇴회원
                                    </label>

                                </c:if>

                                <c:if test="${user.stat==9 }">
                                    <label class="radio-inline text-warning"> 당신은 관리자 입니다.
                                    </label>

                                </c:if>

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">포인트</th>
                        <td colspan="2"><input type="text" name="mypoint"
                                               id="mypoint" class="form-control" value="${user.point }"></td>
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

<c:import url="/foot" />

