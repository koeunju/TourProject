<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="myctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/top.jsp"/>

<script src="./js/tour.js"></script>

<div>
    <form name="tourF" id="tourF" action="tourList.do" method="get">
        <label>방문목적</label>
        <select name="selTheme" id="selTheme">
            <option value="">테마를 선택해주세요</option>
        </select>
        <br>
        <label>지역</label>
        <select name="selLoc" id="selLoc">
            <option value="">지역을 선택해주세요</option>
        </select>
        <br>
        <button type="submit">검색</button>
    </form>
</div>


<jsp:include page="/foot.jsp"/>