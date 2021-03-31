<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="myctx" value="${pageContext.request.contextPath}"/>

<c:import url="/top_sub" />


<div class="container" style="margin-top: 30px">
    <div class="row">

        <div class="col-sm-12 text-center">
            <h1 class="text-center">게시물 작성</h1>

            <form name="boardF" id="boardF" action="insert" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="mode" value="edit">

                <table class="table table-bordered">
                    <tr>
                        <th style="width: 20%">게시물 유형</th>
                        <td width="30%" class="text-center">
                            <input type="hidden" id="cg_num" name = "cg_num" value="${board.cg_num }">
                            <c:if test="${board.cg_num==0 }">
                                <b>여행지 추천</b>
                            </c:if> <c:if test="${board.cg_num==1 }">
                            <b>자유게시판</b>
                        </c:if> <c:if test="${board.cg_num==2 }">
                            <b>고객센터</b>
                        </c:if></td>
                    </tr>
                    <tr>
                        <th style="width: 20%">글번호</th>
                        <td style="width: 80%"><input type="text" name="bnum"
                                                      value="<c:out value="${board.bnum}"/>" id="bnum"
                                                      placeholder="bnum" class="form-control" readonly></td>
                    </tr>
                    <tr>
                        <th style="width: 20%">제목</th>
                        <td style="width: 80%"><input type="text" name="btitle"
                                                      id="btitle" placeholder="" class="form-control"
                                                      value="<c:out value='${board.btitle}'/>"></td>
                    </tr>
                    <%--    <tr>
                        <th style="width:20%">글쓴이</th>
                        <td style="width:80%">ㅁㄴㅇ
                           <input type="text" name="name"
                            value="<c:out value="${board.idx}"/>"
                            id="name" placeholder="Name" class="form-control">
                        </td>
                    </tr> --%>
                    <tr>
                        <th style="width: 20%">첨부파일</th>
                        <td style="width: 80%"><c:out value="${board.originFilename}" />
                            [ <c:out value="${board.filesize}" /> bytes]<br> <input
                                    type="text" name="old_filename"
                                    value="<c:out value='${board.filename}'/>"> <input
                                    type="file" name="mfilename" id="filename"
                                    placeholder="Attach File" class="form-control"></td>
                    </tr>

                    <tr>
                        <th style="width: 20%">글내용</th>
                        <td style="width: 80%"><textarea rows="10" cols="50"
                                                         name="bcontent" id="bcontent" placeholder=""
                                                         class="form-control"><c:out value="${board.bcontent}" /></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            <button type="button" class="btn btn-primary" >수 정</button>
                            <button type="reset" class="btn btn-primary" >다시쓰기</button>
                            <button type="button" class="btn btn-primary"
                                    onclick="history.back()">돌아가기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $('#btnWrite').on('click', function(e) {
            e.preventDefault();
            var $btitle = $('#btitle');
            var $bcontent = $('#bcontent');

            if (!$btitle.val()) {
                alert('제목을 입력하세요');
                $btitle.focus();
                return;
            }
            if (!$bcontent.val()) {
                alert('내용을 입력하세요');
                $bcontent.focus();
                return;
            }
            var $cg = $('#cg_num')
            if ($cg.val() == 99) {
                alert('선택할수 없는 카테고리입니다. 다시 선택해주세요')
                $('#cg_num').focus();
                return;
            }

            $('#boardF').submit();
        })
    })
</script>

<c:import url="/foot_sub" />