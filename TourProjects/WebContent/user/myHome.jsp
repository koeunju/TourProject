<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp" />
<div class="container">
    <h1 class="text-bold" id="font1">MyPage</h1>
    <br>
    <!-- 내상태  -->

    <h5 class="text-right font-weight-bold" id = "font1">내 상태</h5>
    <h6  class="text-right font-weight-bold text-primary">활동회원</h6>
    테스트중입니다.

    <!-- 내정보 -->
    <table class="table table-hover" id="mypageT">
        <tr>
            <td rowspan="6">사진
            </td>
            <th>이름</th>
            <td>aaa</td>
            <th>가입일</th>
            <td>YY-MM-DD</td>
        </tr>
        <tr>
            <th>아이디</th>
            <td>aaa</td>
            <th>이메일</th>
            <td>aaa@aaa</td>
        </tr>
        <tr>
            <th colspan = "2">내포인트</th>
            <td colspan ="2">000POINT</td>
        </tr>
    </table>
</div>
<jsp:include page="/foot.jsp" />

