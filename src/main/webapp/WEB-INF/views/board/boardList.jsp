<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top" />


<div id="wrap" align="center">
    <c:if test="${cg_num==1 }">
        <h2>자유게시판</h2>
    </c:if>
    <c:if test="${cg_num==2 }">
        <h2>고객센터</h2>
    </c:if>
    <!-- 검색폼 -->
    <div class="col-md-8">
        <form name="findF" id="findF" action="list" class="form-inline">
            <select name="findType" id="findType" class="form-control m-3">
                <option value="0">::검색 유형::</option>
                <option value="1">제 목</option>
                <option value="2">작성자</option>
                <option value="3">글내용</option>
            </select> <input type="text" name="findKeyword" id="findKeyword"
                             placeholder="검색어를 입력하세요" class="form-control m-3" required>
            <button class="btn btn-primary">검 색</button>
        </form>
    </div>

    <!-- 분류 -->
    <table class="table table-striped table-hover " id="bbs">
        <tr class="table-secondary">
            <th width="10%">글번호</th>
            <th width="30%">제 목</th>
            <th width="20%">글쓴이</th>
            <th width="20%">날 짜</th>
            <th width="10%">조회수</th>
            <th width="10%">추천수</th>
        </tr>

        <!-- 데이터넣기 -->
        <c:if test="${boardList eq null or empty boardList}">
            <tr>
                <td colspan="5">데이터가 없습니다.</td>
            </tr>
        </c:if>
        <c:if test="${boardList ne null and not empty boardList}">
            <c:forEach var="board" items="${boardList}">

                <!-- 데이터넣기 -->
                <tr>
                    <td><c:out value="${board.bnum}" /></td>
                    <td style="text-align: left; padding-left: 10px"><a
                            href="view?bnum=<c:out value="${board.bnum}"/>"> <c:if
                            test="${board.newImg < 1 }">
                        <span class="badge badge-success">New</span>
                    </c:if> <!-- 답변 레벨에 따른 들여쓰기 --> <c:forEach var="k" begin="0"
                                                               end="${board.lev}">
                        &nbsp;&nbsp;&nbsp;
                    </c:forEach> <c:if test="${board.lev>0}">
                        <img src="../image/re.png" style="width:5%">
                    </c:if> <!-- 글머리(제목 앞에 카테고리 넣어보기) -->
                        <c:if test="${board.cg_num==0 }">
                            <label>[여행지 추천]</label>
                        </c:if> <c:if test="${board.cg_num==1 }">
                            <label>[자유게시판]</label>
                        </c:if> <c:if test="${board.cg_num==2 }">
                            <label>[고객센터]</label>
                        </c:if>
                        <c:out value="${board.btitle}" />

                    </a> <c:if test="${board.filesize > 0 }">
                        <img src="../image/file.png" width="26px">
                    </c:if></td>
                    <td><c:out value="${board.nick }" /></td>
                    <td><fmt:formatDate value="${board.bdate}"
                                        pattern="yyyy-MM-dd" /></td>
                    <td><c:out value="${board.binquiry}" /></td>
                    <td><c:out value="아직안함" /></td>
                </tr>
            </c:forEach>
        </c:if>

        <!-- 페이징 시작 -->
        <tr>
            <td colspan="10" class="text-center col-md-10">${pageNavi}</td>
            <%-- <td colspan="2"><span class="text-primary">총게시글수: <c:out
                        value="${paging.totalCount}" />개
            </span> <br> <span class="text-danger">${paging.cpage}</span> / <span>${paging.pageCount}</span>
            </td> --%>
        </tr>
    </table>

    <!-- 함수 -->
    <script type="text/javascript">
        $(function() {
            $('#findF').on('submit', function() {
                var $type = $('#findType');
                var $keyword = $('#findKeyword');
                if ($type.val() == 0) {
                    alert('검색 유형을 선택하세요');
                    $type.focus();
                    return false;
                }
                if (!$keyword.val()) {
                    alert('검색어를 입력하세요');
                    $keyword.focus();
                    return false;
                }
                return true;
            })
        })
    </script>



</div>

<tr>
    <td>
        <c:if test="${cg_num==1 }">
            <button class="btn btn-primary" onclick="location.href='insert'">글쓰기</button>
        </c:if>
        <c:if test="${cg_num==2 }">
            <button class="btn btn-primary" onclick="location.href='insert2'">글쓰기</button>
        </c:if>
    </td>
</tr>


<c:import url="/foot" />