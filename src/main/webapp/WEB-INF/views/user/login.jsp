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
      if (!$('#pwd').val()) {
         alert('비밀번호를 입력하세요');
         $('#pwd').focus();
         return false;
      }
      return true;
   }
</script>
<%
   //쿠키 꺼내와서 사용자아이디가 저장되어 있으면 아이디 입력폼에 출력해주기
   Cookie[] cks = request.getCookies();
   String uid = "";
   boolean flag = false;
   if (cks != null) {
      for (Cookie ck : cks) {
         String key = ck.getName();
         if (key.equals("uid")) {
            uid = ck.getValue();
            flag = true;
            break;
         }
      }
   }
%>

<div class="col-sm-8 text-center">
<div class="in" style=" display: inline-block; margin-left: 45%;">
   <div class="card align-middle"
        style="width: 30rem; border-radius: 20px;">
      <div class="card-title" style="margin-top: 30px;">
         <h2 class="card-title text-center" style="color: #113366;">로그인
            페이지</h2>
      </div>
      <div class="card-body">
         <form name="loginF" action="login" method="post"
               onsubmit="return send()">
            <h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
            <label for="inputEmail" class="sr-only">Your ID</label> <input
                 type="text" name="id" id="id" value="<%=uid%>"
                 class="form-control" placeholder="아이디를 입력하세요" required autofocus><BR>
            <label for="inputPassword" class="sr-only">Password</label> <input
                 type="password" name="pwd" id="pwd" class="form-control"
                 placeholder="비밀번호를 입력하세요" required><br>
            <div class="checkbox">
               <label for="saveId"> <input type="checkbox" name="saveId"
                                           id="saveId" <%=(flag) ? "checked" : ""%>>아이디 저장
               </label>
            </div>
            <div>
               <a href="${pageContext.request.contextPath}/user/userSearch">&nbsp; 아이디</a>
               /<a href="${pageContext.request.contextPath}/user/pwdSearch">비밀번호 찾기</a>
            </div>
            <button class="btn btn-lg btn-primary btn-block">로그인</button>
         </form>

      </div>
   </div>
</div>
</div>

<c:import url="/foot" />