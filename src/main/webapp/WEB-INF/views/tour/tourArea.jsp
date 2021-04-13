<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>
<style>
    ul {
        list-style: none;
    }
</style>
<script>
    let contextPath = "${pageContext.request.contextPath}";
</script>

<script src="../js/tourArea.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411"></script>
<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">
<div class="container text-center" id="warp">
    <form action="areaList" method="get">
        <ul>
            <li>
                <label>여행지</label>
                <input type="text" name="areaCode" id="areaCode" value="1" readonly>
                <input type="text" name="sigunguCode" id="sigunguCode" value="1" readonly>
                <button class="btn btn-success">선택</button>
            </li>
        </ul>
        <ul>
            <li>
                <input type="radio" name="contentTypeId" id="contentTypeId" value="12"> 관광지
                <input type="radio" name="contentTypeId" id="contentTypeId" value="14"> 문화시설
            </li>
        </ul>
        <ul>
            <li>
                <input type="text" name="cat1" id="cat1" value="A02"> 문화시설
                <input type="text" name="cat2" id="ca2" value="A0206"> 문화시설
            </li>
        </ul>






        <button class="btn btn-success" id="btnArea">검색</button>
    </form>
</div>
</div>
<div id="openAPI">

</div>

<c:import url="/foot"/>
