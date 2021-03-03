<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>

<script>
   function send(){
      if(!$('#id').val()){
         alert('아이디를 입력하세요');
         $('#id').focus();
         return false;
      }
      if(!$('#pwd').val()){
         alert('비밀번호를 입력하세요');
         $('#pwd').focus();
         return false;
      }
      return true;
   }
</script>
<% 
   //쿠키 꺼내와서 사용자아이디가 저장되어 있으면 아이디 입력폼에 출력해주기
   Cookie[] cks =  request.getCookies();
   String uid="";
   boolean flag=false;
   if(cks!=null){
      for(Cookie ck:cks){
         String key = ck.getName();
         if(key.equals("uid")){
            uid = ck.getValue();
            flag =true;
            break;
         }
      }
   }
%>


<h1>로그인</h1>
<div class="wrap">
   <form name="loginF" action="loginEnd.do" method="post" onsubmit="return send()">
      <table id="loginTable" align="center">
         <tr>
            <td><b>아이디</b></td>
            <td><input type="text" class="a"
             name="id" id="id" value="<%=uid%>"   placeholder="User ID">
            </td>
         </tr>      
         <tr>
            <td><b>비밀번호</b></td>
            <td>
               <input type="password" name="pwd" id="pwd"  class="a"
                placeholder="Password">
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <label for="saveId">
                  <input type="checkbox" name="saveId"
                   id="saveId" <%=(flag)?"checked":"" %> >아이디 저장
               </label>
               <button>로그인</button>
            </td>
         </tr>
      </table>   
   </form>
</div>

<jsp:include page="/foot.jsp"/>