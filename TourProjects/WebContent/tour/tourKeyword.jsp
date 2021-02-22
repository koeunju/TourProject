<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="searchResult" value="${requestScope[searchKeyword]}"/>
<jsp:include page="/top.jsp"/>

<script src="./js/tourKeyword.js"></script>

<div id="warp">
    <form name="tourF" id="tourF" action="tourList.do" method="get">
        <label>방문목적</label>
        <input type="text" name="keyword" id="keyword" placeholder="키워드를 입력하세요" class="search">
        <button id="btnKeyword">검색</button>
    </form>
</div>
<div id="openAPI">

</div>

<jsp:include page="/foot.jsp"/>



<%-- Ajax
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="searchResult" value="${requestScope[searchKeyword]}"/>
<jsp:include page="/top.jsp"/>

<script>
    $(function () {
        $('#btnKeyword').click((e) => {
            e.preventDefault();
            var keyword = $('#keyword').val();
            if (!keyword) {
                alert('키워드를 입력하세요');
                $('#keyword').focus();
                return;
            }
            send(keyword);
        })
    })

    function send(keyword) {
        $.ajax({
            type: 'get',
            url: "tourSearch.do",
            dataType: 'json',
            cache: false
        }).done(function (data) {
            alert(data)
            showList(data.item, keyword);
        }).fail(function (err) {
            alert('error: ' + err.status)
            console.dir(err)
        })
    }

    function showList(items, keyword) {
        var str = "<h2>" + keyword + "</h2>"
        str += "<div class='pagination' style='text-align: center'></div>"
        str += "<table border='1' id='tourTable'>"
        str += "<tr>"
        $.each(items, function (i, tour) {
            str += "<td width='20%'>";
            str += "<h4>" + tour + "</h4>";
            str += "</td>";
            str += "</tr>"
            str += "</table>"
            $('#openAPI').html(str)
        })
    }
</script>

<div id="warp">
    <form><!-- name="tourF" id="tourF" action="tourList.do" method="get">-->
        <label>방문목적</label>
        <input type="text" name="keyword" id="keyword" placeholder="키워드를 입력하세요" class="search">
        <button id="btnKeyword">검색</button>
    </form>
</div>
<div id="openAPI">

</div>

<jsp:include page="/foot.jsp"/>

--%>