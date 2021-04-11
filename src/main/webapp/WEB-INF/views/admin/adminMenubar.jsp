<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-white navbar-white" id="font1">
    <a class="navbar-brand" href="#">My Page</a>
    <button class="navbar-toggler " type="button" data-toggle="collapse"
            data-target="#mypageNavbar">

        <i class="fas fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="mypageNavbar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/userList">유저관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/shopList">포인트 샵 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/orderList">구매 정보 관리</a>
            </li>
        </ul>
    </div>
</nav>