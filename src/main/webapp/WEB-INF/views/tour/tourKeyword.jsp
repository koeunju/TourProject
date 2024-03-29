<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>
<style>
    .carousel-control-prev-icon, .carousel-control-next-icon {
        height: 100px;
        width: 100px;
        outline: black;
        background-color: rgba(0, 0, 0, 0.3);
        background-size: 100%, 100%;
        border-radius: 50%;
        border: 1px solid black;
    }
</style>
<script>
    let contextPath = "${pageContext.request.contextPath}";
</script>

<script src="../js/tourKeyword.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411"></script>
<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">
<div class="container text-center" id="warp">
    <form action="keywordList" method="get">
        <label>방문목적</label>
        <input type="text" name="keyword" id="keyword" placeholder="키워드를 입력하세요" class="search">
        <button class="btn btn-success" id="btnKeyword">검색</button>
    </form>
</div>
</div>
<div id="openAPI">

</div>

<c:import url="/foot"/>
