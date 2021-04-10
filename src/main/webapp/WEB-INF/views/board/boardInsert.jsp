<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top_sub" />

<div class="container" style="margin-top: 30px">
    <div class="row">

        <div class="col-sm-12 text-center">
            <h1 class="text-center">자유게시판</h1>

            <form name="boardF" id="boardF" action="insert" method="post"
                  enctype="multipart/form-data">

                <input type="hidden" name="mode" value="insert">
                <input type="hidden" name="idx" value="${loginUser.idx}">
                <table class="table table-bordered">
                    <tr>
                        <th style="width: 20%">게시물 유형</th>
                        <td style="width: 80%">
                            <div class="col-md-12">

                                <select name="cg_num" id="cg_num" class="form-control">
                                    <option value="99">::게시물 유형::</option>
                                    <option value="1">자유게시판</option>
                                    <option value="0">이달의 여행지</option>
                                    <!-- <option value="2">고객센터</option> -->
                                </select>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 20%">제목</th>
                        <td style="width: 80%"><input type="text" name="btitle"
                                                      id="btitle" placeholder="" class="form-control"></td>
                    </tr>
                    <tr>
                        <th style="width: 20%">첨부파일</th>
                        <td style="width: 80%"><input type="file" name="mfilename"
                                                      id="filename" placeholder="Attach File" class="form-control">
                        </td>
                    </tr>

                    <tr>
                        <th style="width: 20%">글내용</th>
                        <td style="width: 80%"><textarea rows="10" cols="50"
                                                         name="bcontent" id="bcontent" placeholder=""
                                                         class="form-control"></textarea></td>
                    </tr>

                    <tr>
                        <td colspan="2" class="text-right">
                            <button class="btn btn-success" id="btnWrite">글쓰기</button>
                            <button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>

                        </td>
                    </tr>
                </table>
            </form>
            <div class="container">
                <h6 class='text-right'>
                    <button class="btn btn-success" style="width: 100px;"
                            name="btnList" id="btnList" onclick="history.back()">목록</button>
                </h6>

            </div>

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
                return false;
            }
            $('#boardF').submit();
        })
        /*  $('#btnList').on('click', function(e){
             history.back();
         }) */
    })
</script>

<c:import url="/foot_sub" />