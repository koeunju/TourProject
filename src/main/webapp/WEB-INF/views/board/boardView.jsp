<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/top.jsp"/>

<div class="container" style="margin-top:30px">
    <div id="wrap" align="center">
        <h3>게시글 상세보기</h3>

        <%-- <c:if test="${board eq null}"> <!-- arrLaist가 아니라서 null만 체크 -->
             <h2 class="text-danger m-5">${param.idx}번 게시글이 없습니다.</h2>
         </c:if>
        <c:if test="${board ne null}"> --%>
        <table class="table table-bordered">
            <tr>
                <th width="25%">게시물 유형</th>
                <td width="30%" class="text-center">
                    <c:out value="${category.cg_name}"/> <!-- 카테고리 분류 -->
                </td>
                <th width="25%">글쓴이</th>
                <td width="30%">
                    <c:out value="${board.idx}"/> <!-- 카테고리 분류 -->
                </td>
            </tr>

            <tr>
                <td width="25%"><b>작성일</b></td>
                <td width="30%">
                    <fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd hh:mm:ss"/>
                </td>
                <td width="20%"><b>조회수</b></td>
                <td width="30%">
                    <c:out value="${board.binquiry}"/>
            </tr>
            <tr>
                <td width="20%"><b>첨부파일</b></td>
                <td colspan="3">

                    <img src="board/Upload/${board.bupload1}"
                         class="img-fluid img-thumbnail" style="width: 32%;">

                    <img src="board/Upload/${board.bupload2}"
                         class="img-fluid img-thumbnail" style="width: 32%;">

                    <img src="board/Upload/${board.bupload3}"
                         class="img-fluid img-thumbnail" style="width: 32%;">

                </td>
            <tr style="border:0">

            </tr>


            <tr>
                <td width="20%"><b>제목</b></td>
                <td colspan="3">
                    <c:out value="${board.btitle}"/>
            </tr>

            <tr>
                <td width="20%"><b>글내용</b></td>
                <td colspan="3">
                    <pre><c:out value="${board.bcontent}"/></pre>
                </td>
            </tr>


        </table>

        <br>
        <br>
        <form id="boardF" method="post">
            <input type="hidden" name="bnum" id="bnum"
                   value="<c:out value="${board.bnum}"/>">
            <button class="btn btn-primary" onclick="goEdit()">수정</button>
            <button class="btn btn-primary" onclick="goDel()">삭제</button>
            <button class="btn btn-primary" onclick="goList()">목록</button>
        </form>


    </div>
</div>


<!-- 함수 -------------------------------------- -->
<script type="text/javascript">
    function down() {
        $('#fileF').submit();
    }

    function goDel() {
        var yn = confirm('${board.bnum}번 글을 정말 삭제할까요?');
        if (yn) {

            $('#boardF').prop('method', 'post');
            $('#boardF').prop('action', 'boardDelete.do');
        }
    }

    function goEdit() {

        $('#boardF').prop('method', 'post');
        $('#boardF').prop('action', 'boardEdit.do');

    }

    function goList() {

        $('#boardF').prop('method', 'post');
        $('#boardF').prop('action', 'boardList.do');

    }
</script>

<jsp:include page="/foot.jsp"/>