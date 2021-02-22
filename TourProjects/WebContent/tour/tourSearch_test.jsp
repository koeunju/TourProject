<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="/top.jsp"/>

<script src="/js/tour.js"></script>

<%-- 스프링으로 전환시 사용 예정 --%>

<div>
    <form name="tourF" id="tourF" action="tourList.do" method="get">
        <div>
            <label>방문목적</label>
            <div>
                <input type="checkbox" name="chkTheme" value="eat">식도락
                <input type="checkbox" name="chkTheme" value="sport">레저
                <input type="checkbox" name="chkTheme" value="driv">드라이브<br>
                <input type="checkbox" name="chkTheme" value="healing">휴식/힐링
                <input type="checkbox" name="chkTheme" value="experience">체험/학습
                <input type="checkbox" name="chkTheme" value="history">역사/문화
                <input type="checkbox" name="chkTheme" value="nature">자연/경치
            </div>
        </div>
        <br>
        <div>
            <label>동반유형</label>
            <div>
                <input type="radio" name="rdCompanion" value="solo">혼자
                <input type="radio" name="rdCompanion" value="friend">친구
                <input type="radio" name="rdCompanion" value="family">가족
                <input type="radio" name="rdCompanion" value="lover">연인
            </div>
        </div>
        <br>
        지역 <input type="text">
        <button>검색</button>
    </form>
</div>


<jsp:include page="/foot.jsp"/>