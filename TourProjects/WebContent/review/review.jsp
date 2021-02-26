<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<jsp:include page="/top.jsp"/>

<script src="./js/reviewInput.js"></script>

<form name="reviewF" id="reviewF" action="reviewEnd.do" method="post" enctype="multipart/form-data">
    <table class="table table-bordered">
        <tr>
            <th style="width:20%">별점</th>
            <td style="width:80%">
                <input type="text" name="star"
                       id="star" placeholder="별점" class="form-control">
            </td>
        </tr>
        <tr>
            <th style="width:20%">리뷰내용</th>
            <td style="width:80%">
               <textarea rows="10" cols="50" name="content"
                         id="content" placeholder="Content" class="form-control"></textarea>
            </td>
        </tr>
        <tr>
            <th style="width:20%">첨부파일</th>
            <td style="width:80%">
                <input type="file"
                       name="filename" id="filename"
                       placeholder="Attach File" class="form-control">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-center">
                <button class="btn btn-success" id="btnWrite">글쓰기</button>
                <button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
            </td>
        </tr>
    </table>
</form>

<jsp:include page="/foot.jsp"/>
