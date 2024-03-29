<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top" />

<script type="text/javascript">

 $(function(){
	 $('#edUser').on('click', function (e) {
         e.preventDefault();
         alert('test');
         var $nick = $('#nick');
         var $tel = $('#tel');
         var $email = $('#email');
         var $point = ${'#point'};
         var $stat = $('#stat');
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
         $('#adF').submit();
     })
 })
 
 
</script>


<!-- 내정보 -->
<div class="container">
    <div class="m-5 p-3 text-center"
         style="border: 1px solid gray; border-radius: 15px" id="font2">
        <h1 class="text-bold" id="font1">유저정보 수정</h1>
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
		<form id = "adF" name ="adF" action="userEdit" method="POST">
            <input type="hidden" name="idx" value="${user.idx}">
            <input type="hidden" name="adminCheck" value="${adminCheck}">
            <table class="table table-hover text-left" id="mypageT">
                <tr>
                      <td rowspan="7" style="width: 30%; padding: 10px;">
                    사진<br><img src="../user/upload/${user.image }" style="width: 100%; j margin: 20px; border: 1px solid gray">
                    </td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td>${user.id }</td>
                    <th>회원번호</th>
                    <td>${user.idx }</td>
                </tr>
                <tr>
                    <th>닉네임</th>
                    <td><input type="text" value="${user.nick }" name="nick"
                               id="nick" class="form-control"></td>
                    <th>가입일</th>
                    <td>${user.indate }</td>
                </tr>

                <tr>
                    <th>연락처</th>
                    <td><input type="text" value="${user.tel }"
                               name="tel" id="tel" class="form-control"></td>

                    <th>이메일</th>
                    <td><input type="text" value="${user.email }" name="email"
                               id="email" class="form-control"></td>
                </tr>

                <tr>
                    <th colspan="2">회원 상태</th>
                    <td colspan="2">
                        <div class="radio">
                            <c:if test="${user.stat==1 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1" checked>활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3">휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="4">탈퇴회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="9">관리자
                                </label>
                            </c:if>

                            <c:if test="${user.stat==3 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1">활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3" checked>휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="4">탈퇴회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="9">관리자
                                </label>

                            </c:if>

                            <c:if test="${user.stat==4 }">
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="1">활동회원
                                </label>
                                <label class="radio-inline"> <input type="radio"
                                                                    id="stat" name="stat" value="3">휴먼회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="4"
                                                                                checked>탈퇴회원
                                </label>
                                <label class="radio-inline text-danger"> <input type="radio"
                                                                                id="stat" name="stat" value="9">관리자
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
                    <td colspan="2">${mytotalpoint }</td>
                </tr>

            </table>

            <div class="container text-right">
               
                <button class="btn btn-success" id="edUser" name="edUser">수정하기</button>
            </div>

   </form>
        <!-- 버튼정렬div -->
    </div>
    <!-- 내정보 div -->
</div>

<c:import url="/foot" />