<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <th width="20%">게시판 분류</th>
                <td width="30%" class="text-center">
                    <c:out value="#"/> <!-- 카테고리 분류 -->
                </td>
                <th width="20%">글쓴이</th>
                <td width="30%">
                    <c:out value="#"/> <!-- 카테고리 분류 -->
                </td>
            </tr>

            <tr>
                <td width="20%"><b>작성일</b></td>
                <td width="30%">
                    <fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd hh:mm:ss"/>
                </td>
                <td width="20%"><b>조회수</b></td>
                <td width="30%">
                    <c:out value="${board.binquiry}"/>
            </tr>
            <td width="20%"><b>첨부파일</b></td>
            <td colspan="3" class="text-left">
                &nbsp;
                <c:if test="#"><!-- 첨부파일 해야함@@@@@@@@@@@@@@@@ -->
                <a href="#" onclick="down()">
                    <c:out value="#"/>
                </a>
                <c:if test="#">
                <img src="board/Upload/#" class="img-thumnail"
                     width="100px">
                </c:if>
                </c:if>
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

        <button class="btn btn-primary"
                onclick="goEdit()">수정
        </button>
        <button class="btn btn-primary"
                onclick="goDel()">삭제
        </button>
        <button class="btn btn-primary"
                onclick="location.href='boardList.do'">목록
        </button>

        <%-- </c:if> --%>
    </div>
</div>

<form id="boardF" method="post">
    <input type="hidden" name="bnum" id="bnum"
           value="<c:out value="${board.bnum}"/>">
    <button id="btn" class="btn btn-info"> 글 삭제</button>
</form>

<!-- 함수 -------------------------------------- -->
<script type="text/javascript">
    function down() {
        $('#fileF').submit();
    }

    function goDel() {
        var yn = confirm('${board.idx}번 글을 정말 삭제할까요?');
        if (yn) {

            $('#boardF').prop('method', 'get');
            $('#boardF').prop('action', 'boardDelete.do');
        }
    }

    function goEdit() {

        $('#boardF').prop('method', 'post');
        $('#boardF').prop('action', 'boardEdit.do');

    }
</script>

<jsp:include page="/foot.jsp"/>