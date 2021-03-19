<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>

<%
   int result = (int)request.getAttribute("res");

   if(result>0){
%>
<div class="row">
   <div class="col-md-10 offset-md-1">
      <h3 class="text-success">비밀번호가 일치합니다. 비밀번호 변경이 가능합니다.</h3>
      <button onclick="checkPwd()">확인</button>
   </div>
</div>
<%
}else{
%>
<div class="row">
   <div class="col-md-10 offset-md-1">
      <h3 class="text-danger">
         비밀번호가 일치 하지 않습니다.
      </h3>
      <a href="javascript:history.back()" class="btn btn-danger">다시 입력하기</a>
   </div>
</div>
<%
   }
%>


<script type="text/javascript">
   var checkPwd = function(){
      opener.document.meF.mypwd.readOnly = false;
      opener.document.meF.remypwd.readOnly = false;

      self.close();
   }
</script>