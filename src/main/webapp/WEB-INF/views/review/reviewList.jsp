<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top"/>
<script>
    function goWrite(contentId) {
        location.href="/review/write?contentId=" + contentId;
    }
</script>

<div class="">
    <input type="hidden" name="contentId" value="${contentId}">
    <input type="hidden" name="idx" value="${idx}">
    <button class="btn btn-primary" onclick="goWrite(${contentId})">리뷰 작성</button>
    <c:if test="${rList eq null or empty rList}">
        <div>
            <h3>리뷰가 없습니다</h3>
        </div>
    </c:if>

    <c:if test="${rList ne null or not empty rList}">
        <c:forEach var="rv" items="${rList}" varStatus="state">
            <div>
                <table  class="table table-hover">
                    <tr>
                        <th>닉네임 : ${rv.nick}</th>
                        <th>
                            <c:if test="${rv.rstar == 5}">
                            <img src="../review/star/2.png" style="width: 65px; height: 65px">
                            <img src="../review/star/2.png" style="width: 65px; height: 65px">
                            <img src="../review/star/2.png" style="width: 65px; height: 65px">
                            <img src="../review/star/2.png" style="width: 65px; height: 65px">
                            <img src="../review/star/2.png" style="width: 65px; height: 65px">
                            </c:if>
                            <c:if test="${rv.rstar == 4}">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                            </c:if>
                            <c:if test="${rv.rstar == 3}">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                            </c:if>
                            <c:if test="${rv.rstar == 2}">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                            </c:if>
                            <c:if test="${rv.rstar == 1}">
                                <img src="../review/star/2.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                                <img src="../review/star/1.png" style="width: 65px; height: 65px">
                            </c:if>
                        </th>
                    </tr>
                    <tr>
                        <td>내용 : ${rv.rcontent}</td>
                        <td>사진 :
                            <c:if test="${(rv.rfile1 eq null or empty rv.rfile1) and (rv.rfile2 eq null or empty rv.rfile2)
                            and (rv.rfile3 eq null or empty rv.rfile3)}">
                                등록된 사진이 없습니다
                            </c:if>
                            <c:if test="${rv.rfile1 ne null or not empty rv.rfile1}">
                            <img src="../review/upload/${rv.rfile1}" class="rounded img-fluid"
                                 style="width: 100px; height: 100px">
                            </c:if>
                            <c:if test="${rv.rfile2 ne null or not empty rv.rfile2}">
                                <img src="../review/upload/${rv.rfile2}" class="rounded img-fluid"
                                     style="width: 100px; height: 100px">
                            </c:if>
                            <c:if test="${rv.rfile3 ne null or not empty rv.rfile3}">
                                <img src="../review/upload/${rv.rfile3}" class="rounded img-fluid"
                                     style="width: 100px; height: 100px">
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </c:if>
    ${pageNavi}
</div>

<c:import url="/foot"/>

