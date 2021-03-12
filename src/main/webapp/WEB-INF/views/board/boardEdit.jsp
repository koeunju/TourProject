<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="myctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="/top.jsp"/>


<div class="container" style="margin-top:30px">
    <div class="row">

        <div class="col-sm-12 text-center">
            <h1 class="text-center">Board Write</h1>
            <!-- 파일 업로드시. 메소드는 post, 인코딩타입은 multipart/form-data로 주어야함 -->
            <!-- boardWriteEnd.do로 간다고 해놓고 properties선언에서는 insert로 선언을 하니까 경로를 못찾음 -->
            <form name="boardF" id="boardF" action="boardEditEnd.do"
                  method="post" enctype="multipart/form-data">
                <table class="table table-bordered">

                    <tr>
                        <th style="width:20%">게시물 유형</th>
                        <td style="width:80%">
                            <div class="col-md-12">
                                <!-- <form name="CategoryF" id="CategoryF" action="boardCategory.do#bbs"
                                    class="form-inline"> -->
                                <select name="CategoryF" id="CategoryF" class="form-control">
                                    <option value="">::게시물 유형::</option>
                                    <option value="0">이달의 여행지</option>
                                    <option value="1">자유게시판</option>
                                    <option value="2">고객센터</option>
                                </select>
                                <!-- </form> -->
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">글번호</th>
                        <td style="width:80%">
                            <input type="text" name="bnum" value="<c:out value="${board.bnum}"/>"
                                   id="bnum" placeholder="bnum" class="form-control" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">제목</th>
                        <td style="width:80%">
                            <input type="text" name="btitle"
                                   id="btitle" placeholder="" class="form-control" value="${board.btitle }">
                        </td>
                    </tr>
                    <!--  <tr>
                        <th style="width:20%">글쓴이</th>
                        <td style="width:80%">
                           <input type="text" name="name"
                            id="name" placeholder="Name" class="form-control">
                        </td>
                     </tr> -->
                    <tr>
                        <th style="width:20%">첨부파일</th>
                        <td style="width:80%">
                            <img src="board/Upload/${board.bupload1}"
                                 class="img-thumbnail" style="width:32%"
                                 alt="이미지1">

                            <img src="board/Upload/${board.bupload2}"
                                 class="img-thumbnail" style="width:32%"
                                 alt="이미지2">

                            <img src="board/Upload/${board.bupload3}"
                                 class="img-thumbnail" style="width:32%"
                                 alt="이미지3">

                            <input type="file"
                                   name="bupload1" id="bupload1"
                                   placeholder="Attach File" class="form-control">
                            <input type="file"
                                   name="bupload2" id="bupload2"
                                   placeholder="Attach File" class="form-control">
                            <input type="file"
                                   name="bupload3" id="bupload3"
                                   placeholder="Attach File" class="form-control">
                        </td>
                    </tr>

                    <tr>
                        <th style="width:20%">글내용</th>
                        <td style="width:80%">
               <textarea rows="10" cols="50" name="bcontent"
                         id="bcontent" placeholder="" class="form-control">${board.bcontent}</textarea>
                        </td>
                    </tr>
                    <!--  <tr>
                        <th style="width:20%">비밀번호</th>
                        <td style="width:80%">
                           <input type="password"
                            name="pwd" id="pwd" placeholder="Password" class="form-control">
                        </td>
                     </tr> -->

                    <tr>
                        <td colspan="2" class="text-center">
                            <button class="btn btn-primary" id="btnWrite">수 정</button>
                            <button type="reset" class="btn btn-primary" id="btnReset">다시쓰기</button>
                            <button class="btn btn-primary" id="btnView" onclick="history.back()">돌아가기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $('#btnWrite').on('click', function (e) {
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


            $('#boardF').submit();
        })
    })

    /* 카테고리 선택 여부 함수 */
    $(function () {
        $('#CategoryF').on('submit', function () {
            if (!$('#CategoryF').val()) {
                alert('카테고리를 선택하세요');
                $('#CategoryF').focus();
                return false;
            }
            //유효성 체크...

            return true;
        })
    })
</script>

<jsp:include page="/foot.jsp"/>