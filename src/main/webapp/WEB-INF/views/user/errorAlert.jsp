<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
   response.setStatus(200); // IE
%>

<script>
   <%-- alert('<%=exception.getMessage()%>'); --%>
   alert('${pageContext.exception.message}');
   location.href="${pageContext.request.contextPath}/login";
</script>