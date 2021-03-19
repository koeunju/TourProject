<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-sm bg-white navbar-white" id="font1">
    <a class="navbar-brand" href="#">My Page</a>
    <button class="navbar-toggler " type="button" data-toggle="collapse"
            data-target="#mypageNavbar">

        <i class="fas fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="mypageNavbar">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/mypageHome.do?idx=${loginUser.idx}">내정보확인</a></li>
            <li class="nav-item"><a class="nav-link" href="#"
                                    onclick="ready()">찜한여행지</a></li>
            <li class="nav-item"><a class="nav-link"
                                    href="${pageContext.request.contextPath}/mypageWrite.do">내가 쓴 글</a></li>

        </ul>
    </div>
</nav>