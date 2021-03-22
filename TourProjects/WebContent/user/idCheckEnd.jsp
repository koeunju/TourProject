<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="user.model.UserDAOMyBatis.*"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%
   int result = (int)request.getAttribute("isUse");
   String id=request.getParameter("id");
   
   if(result>0){
   %>
<div class="row">
   <div class="col-md-10 offset-md-1">
      <h3>[<span style="font-weight: bold;color:red"><%=id%></span>]
         는 이미 사용중 입니다.</h3>
      <button class="btn btn-warning" onclick="history.back()"
      >뒤로가기</button>
   </div>
</div>
   <%
   }else{
   %>
<div class="row">
   <div class="col-md-10 offset-md-1">
      <h3>
         ID로 [<span style="font-weight: bold; color: red"><%=id%></span>]을
         사용할 수 있습니다.
      </h3>
      <button class="btn btn-warning"
              onclick="setId('<%=id%>')">사용하기</button>
   </div>
</div>
   <% 
   }
%>


<script type="text/javascript">
   var setId = function(id){

      opener.document.joinF.id.value = id;
      
      self.close();
   }
</script>