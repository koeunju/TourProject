<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"/>

<div>
    <form action="">
        방문목적
        <input type="checkbox" name="chkTheme" id="">식도락
        <input type="checkbox" name="chkTheme" id="">레저
        <input type="checkbox" name="chkTheme" id="">드라이브<br>
        <input type="checkbox" name="chkTheme" id="">휴식/힐링
        <input type="checkbox" name="chkTheme" id="">체험/학습
        <input type="checkbox" name="chkTheme" id="">역사/문화
        <input type="checkbox" name="chkTheme" id="">자연/경치
        <br>
        동반유형
        <input type="radio" name="rdCompanion" id="">혼자
        <input type="radio" name="rdCompanion" id="">친구
        <input type="radio" name="rdCompanion" id="">가족
        <input type="radio" name="rdCompanion" id="">연인
        <br>
        지역 <input type="text">
        <button>검색</button>
    </form>
</div>


<jsp:include page="/foot.jsp"/>