<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/top.jsp"/>

<script src="./js/tourKeyword.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411"></script>

<div id="warp">
    <form>
        <label>방문목적</label>
        <input type="text" name="keyword" id="keyword" placeholder="키워드를 입력하세요" class="search">
        <button class="btn btn-success" id="btnKeyword">검색</button>
    </form>
</div>
<div id="openAPI">

</div>

<jsp:include page="/foot.jsp"/>

