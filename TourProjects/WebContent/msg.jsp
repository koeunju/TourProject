<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 
	String msg=(String)request.getAttribute("message");
	String loc=(String)request.getAttribute("loc");
	//request, session, application에 저장한 값들을 jsp에서는 el표현식을 이용해
	//출력할 수 있다.
	${key} key에 해당하는 value값을 출력하란 의미
--%>    
    
<script>
	alert('${message}');
	location.href='${loc}'
</script>