<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
<script>
    function goWrite(contentId) {
        location.href="/review/write?contentId=" + contentId;
    }
</script>
<input type="text" name="contentId" value="${contentId}">
<input type="text" name="idx" value="${idx}">
<button class="btn btn-primary" onclick="goWrite(${contentId})">리뷰 작성</button>

<div>
    <c:if test="${rList eq null or empty rList}">
        <div class="col-md-3">
            <h3>리뷰가 없습니다</h3>
        </div>
    </c:if>

    <c:if test="${rList ne null or not empty rList}">
        <c:forEach var="rv" items="${rList}" varStatus="state">
            <div class="col-md-3">
                리뷰 내용 : ${rv.rcontent}
            </div>
        </c:forEach>
    </c:if>
	${pageNavi}
</div>
<c:import url="/foot"/>

