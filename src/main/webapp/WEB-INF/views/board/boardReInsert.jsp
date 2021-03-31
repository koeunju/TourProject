<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/top" />


<form name="boardF" id="boardF" action="insert" method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="mode" value="reInsert">
    <input type="hidden" name="bnum" value="${bnum}">
    <input type="hidden" name="cg_num" value="${cg_num}">
    <input type="hidden" name="idx" id="idx" value="${idx}" >

    <table class="table table-bordered">
        <tr>
            <th sdtyle="width: 20%">제목</th>
            <td style="width: 80%"><input type="text" name="btitle"
                                          id="btitle" placeholder="btitle"
                                          value="[RE]${btitle}" class="form-control"></td>
        </tr>
        <tr>
            <th style="width: 20%">글내용</th>
            <td style="width: 80%"><textarea rows="10" cols="50"
                                             name="bcontent" id="bcontent" placeholder="Content"
                                             class="form-control"></textarea></td>
        </tr>
        <tr>
            <th style="width: 20%">첨부파일</th>
            <td style="width: 80%"><input type="file" name="mfilename"
                                          id="filename" placeholder="Attach File" class="form-control">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-center">
                <button class="btn btn-success" id="btnWrite">글쓰기</button>
                <button type="reset" class="btn btn-success" id="btnReset">다시쓰기</button>

            </td>
        </tr>
    </table>

</form>

<div class="container">
    <h6 class='text-right'>
        <button class="btn btn-success" style="width: 100px;" name="btnList"
                id="btnList" onclick="history.back()">본문 보기</button>
        <button class="btn btn-success" style="width: 100px;" name="btnList"
                id="btnList" onclick="goList()">글 목록</button>
    </h6>

</div>


<c:import url="/foot" />
<script type="text/javascript">
    $(function() {
        $('#btnWrite').on('click', function(e) {
            e.preventDefault();
            var $btitle = $('#btitle');

            if (!$btitle.val()) {
                alert('제목를 입력하세요');
                $btitle.focus();
                return;
            }
            $('#boardF').submit();

        })
    })
    function goList() {
        location.href = '/board/list?cg_num=' + ${cg_num}
    }
</script>

