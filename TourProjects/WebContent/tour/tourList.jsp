<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="searchResult" value="${requestScope[searchKeyword]}"/>

<jsp:include page="/top.jsp"/>

<table border="1">
    <tr>
        <th>제목</th>
    </tr>
<c:forEach items="${searchKeyword}" var="tour">
<tr>
    <td>${tour}</td>
</tr>
</c:forEach>

<%--    ${searchKeyword}--%>
</table>

<jsp:include page="/foot.jsp"/>