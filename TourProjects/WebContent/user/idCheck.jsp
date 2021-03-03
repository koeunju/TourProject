<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>

<div>
   <div>
      <form name="idf" action="idCheckEnd.do" method="POST">
         <label for="id">아이디</label>
         <input type="text" autofocus="autofocus" name="id" id="id" placeholder="User ID">
         <button type="button" onclick="id_check()">확 인</button>
      </form>
   </div>
</div>

<script type="text/javascript">
   var id_check= function(){
      if(!idf.id.value){
         alert('아이디를 입력하세요');
         idf.id.focus();
         return;
      }
      var pattern=/^[a-zA-Z]{1}[\w_!]{3,7}$/;
      var b = pattern.test(idf.id.value);
      if(!b){
         alert('아이디 형식에 맞지 않아요(영문자,숫자,!,_로 4~8자 이내)');
         idf.id.select();
         return;
      }
      idf.submit();
   }
</script>

