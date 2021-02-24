<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/top.jsp"/>




<h1>회원가입</h1>
<form name="joinF" id="joinF" action="joinEnd.do" method="POST" >
   <table align="center">
      <tr>
         <td width="20%" class="m1"><b>이  름</b></td>
         <td width="80%" class="m2">
            <input type="text" name="name" id="name" placeholder="Name">
         </td>
      </tr>
      <tr>
         <td width="20%" class="m1"><b>아이디</b></td>
         <td width="80%" class="m2">
            <input type="text" name="id" id="id" 
            placeholder="User ID" readonly>
           <button type="button" onclick="openWin()">아이디 확인</button> 
          <br>
          <p style="font-size:12px;">* 아이디는 영문자,숫자,!,_로 4~8자 이내로 가능합니다.</p>
         </td>
      </tr>
      <tr>
         <td width="20%" class="m1"><b>비밀번호</b></td>
         <td width="80%" class="m2">
            <input type="password" name="pwd" id="pwd" placeholder="Password">            
         </td>
      </tr>
      <tr>
         <td width="20%" class="m1"><b>비밀번호 확인</b></td>
         <td width="80%" class="m2">
            <input type="password" name="pwd2" id="pwd2" 
            placeholder="Re Password">            
         </td>
      </tr>
      <tr>
         <td width="20%" class="m1"><b>연락처</b></td>
         <td width="80%" class="m2">
            <input type="text" name="tel" id="tel" maxlength="11" placeholder="Tel">
         </td>
      </tr>
      <tr>
         <td width="20%" class="m1"><b>이메일</b></td>
         <td width="80%" class="m2">
            <input type="text" name="email" id="email" placeholder="Email">
         </td>
      </tr>
      <tr>
         <td colspan="2" class="m1 text-center">
            <button  id="btnOk">회원가입</button>
            <button type="reset">다시쓰기</button>
         </td>
      </tr>
   </table>
</form>
<jsp:include page="/foot.jsp"/>


<script type="text/javascript">
   var win = null;
   function openWin(){
	   win = window.open("idCheck.do","idCheck",
			   "width=400, height=400, left=100, top=100");
   }
	 
$(function(){ 
	$('#btnOk').on('click',function(e){
		e.preventDefault();
		var $name = $('#name');
		var $id = $('#id');
		var $pwd = $('#pwd');
		var $pwd2 = $('#pwd2');
		var $tel = $('#tel');
		var $email = $('#email');
		
		if(!$name.val()){
			 alert('이름을 입력하세요');
	          $name.focus();
	          return;
		}
		if(!$id.val()){
			 alert('아이디를 입력하세요');
	          $id.focus();
	          return;
		}
		if(!$pwd.val()){
			 alert('비밀번호를 입력하세요');
	          $pwd.focus();
	          return;
		}
		if($pwd.val() != $pwd2.val()){
			 alert('비밀번호가 서로 달라요');
	          $pwd2.focus();
	          return;
		}
		if(!$tel.val()){
			 alert('전화번호를 입력하세요');
	          $tel.focus();
	          return;
		}
		if(!$email.val()){
			 alert('이메일을 입력하세요');
	          $email.focus();
	          return;
		}
		
		$('#joinF').submit();
	})
})
   
</script>