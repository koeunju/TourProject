<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>

<div>
   <div>
      <form name="pwdf" action="pwdCheckEnd.do" method="POST">
         <label for="id">패스워드를 입력하세요</label>
         <input type="hidden" id="getidx" name="getidx" value=${idx }>
         <input type="text" autofocus="autofocus" name="pwd" id="pwd" placeholder="USER PASSWARD">
         <button type="button" onclick="pwd_check()">확 인</button>
      </form>
   </div>
</div>


<script type="text/javascript">
   var pwd_check= function(){
      if(!pwdf.pwd.value){
         alert('현재 비밀번호를 입력하세요');
         pwdf.pwd.focus();
         return;
      }
      pwdf.submit();
   }
</script>



