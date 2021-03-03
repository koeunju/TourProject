<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/top.jsp"/>

<nav class="navbar navbar-expand-md navbar-white bg-white text-white" id="font1">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/userList.do">유저관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/pointshopList.do">포인트 샵 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/purchase.do">구매 정보 관리</a>
            </li>
        </ul>
    </div>
</nav>

<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">
    <!-- top끝 -->


    <c:set var="myctx" value="${pageContext.request.contextPath}"/>
    <!--컨텍스트명을 myctx변수에 할당  -->
    <div class="container" style="margin-top:20px">
        <div class="row">

            <div class="col-sm-12 text-center">
                <h1 class="text-center m-3">::User List::</h1>

                <!-- <table class="table table-striped table-hover" id="userList">
                <tr class="table-secondary">
                    <th width="20%">회원번호 </th>
                    <th width="20%">이름 </th>
                    <th width="20%">가입일 </th>
                    <th width="20%">상태변경 </th>
                    <th width="20%">회원탈퇴 </th>
                </tr> -->
                <table class="table table-striped m-4" id="bbs">
                    <thead>
                    <tr class="table-secondary">
                        <th data-sort="int">회원번호</th>
                        <th data-sort="string">회원이름</th>
                        <th data-sort="date">가입일</th>
                        <th data-sort="int">상태변경</th>
                        <th>회원수정</th>
                        <th>회원탈퇴</th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- 데이터  -->


                    <c:if test="${userList eq null or empty userList}">

                        <tr>
                            <td colspan="5">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:if test="${userList ne null and not empty userList}">
                        <c:forEach var="user" items="${userList}">
                            <tr class="record">

                                <td>
                                    <c:out value="${user.idx}"/>
                                </td>
                                <td>
                                    <c:out value="${user.name}"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${user.indate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>
                                    <c:out value="${user.stat}"/>
                                </td>
                                <td>
                                    <a href="userEdit.do?idx=${user.idx}">회원수정</a>
                                </td>
                                <td>
                                    <a href="userDelete.do?idx=${user.idx}">회원탈퇴</a>
                                </td>

                                <!--  -->
                                    <%-- 	<td>${user.idx }</td>
                                        <td>${user.name}</td>
                                        <td><fmt:formatDate value="${user.indate}" pattern="yyyy-MM-dd"/></td>
                                        <td>${user.stat}</td>
                                        <td></td> --%>

                                <!--  -->

                            </tr>
                        </c:forEach>
                    </c:if>

                    <tr>
                        <td colspan="3" class="text-center">
                            <ul class="pagination justify-content-center">
                                <c:forEach var="i" begin="1" end="${pageCount}">
                                    <%-- [ ${i} ] --%>
                                    <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
                                        <a class="page-link"
                                           href="userList.do?cpage=${i}">
                                                ${i}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td colspan="2">
                            <span class="text-primary">총유저: <c:out value="${totalCount}"/></span>
                        </td>
                    </tr>
                    <!-- 유저 검색 창 -->


                    <!--  -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/foot.jsp"/>