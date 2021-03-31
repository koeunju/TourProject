<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top"/>
<script>
    function goWrite(contentId) {
        location.href="/review/write?contentId=" + contentId;
    }
</script>
<input type="hidden" name="contentId" value="${contentId}">
<input type="hidden" name="idx" value="${idx}">
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
                <table  class="table table-hover">

                </table>
                별점 : ${rv.rstar}
                리뷰 내용 : ${rv.rcontent}
                <c:if test="${rv.rfile1 ne null or not empty rv.rfile1}">
                    <img src="../review/upload/${rv.rfile1}" class="rounded img-fluid"
                         style="height: 100px">
                </c:if>
                <c:if test="${rv.rfile2 ne null or not empty rv.rfile2}">
                    <img src="../review/upload/${rv.rfile2}" class="rounded img-fluid"
                         style="height: 100px">
                </c:if>
                <c:if test="${rv.rfile3 ne null or not empty rv.rfile3}">
                    <img src="../review/upload/${rv.rfile3}" class="rounded img-fluid"
                         style="height: 100px">
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    ${pageNavi}
</div>

<c:import url="/foot"/>

