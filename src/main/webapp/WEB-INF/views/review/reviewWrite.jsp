<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<c:import url="/top"/>

<script src="../js/reviewInput.js"></script>

<form name="reviewF" id="reviewF" action="write" method="post" enctype="multipart/form-data">
    <input type="hidden" name="contentId" value="${contentId}">
    <input type="hidden" name="idx" value="${idx}">
    <input type="text" name="rstar" id="rstar">
    <table class="table table-bordered">
        <tr>
            <th style="width:20%">별점</th>
            <td style="width:80%">
                <span>
                <img id="image1" onmouseover="show(1)" onclick="mark(1)" onmouseout="noshow(1)" src="../review/star/1.png" style="width: 15%">
                <img id="image2" onmouseover="show(2)" onclick="mark(2)" onmouseout="noshow(2)" src="../review/star/1.png" style="width: 15%">
                <img id="image3" onmouseover="show(3)" onclick="mark(3)" onmouseout="noshow(3)" src="../review/star/1.png" style="width: 15%">
                <img id="image4" onmouseover="show(4)" onclick="mark(4)" onmouseout="noshow(4)" src="../review/star/1.png" style="width: 15%">
                <img id="image5" onmouseover="show(5)" onclick="mark(5)" onmouseout="noshow(5)" src="../review/star/1.png" style="width: 15%">
                </span>
            </td>
        </tr>
        <tr>
            <th style="width:20%">리뷰내용</th>
            <td style="width:80%">
               <textarea rows="10" cols="50" name="rcontent"
                         id="content" placeholder="Content" class="form-control"></textarea>
            </td>
        </tr>
        <tr>
            <th style="width:20%">첨부파일</th>
            <td style="width:80%">
                <input type="file" name="imgfile1" placeholder="Attach File" class="form-control">
                <input type="file" name="imgfile2" id="filename2" placeholder="Attach File" class="form-control">
                <input type="file" name="imgfile3" id="filename3" placeholder="Attach File" class="form-control">
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

<script>
    let locked = 0;

    function show(star) {
        if (locked)
            return;
        let i;
        let image;
        let el;

        for (i = 1; i <= star; i++) {
            image = 'image' + i;
            el = document.getElementById(image);
            el.src = '../review/star/2.png';
        }
    }

    function noshow(star) {
        if (locked)
            return;
        let i;
        let image;
        let el;

        for (i = 1; i <= star; i++) {
            image = 'image' + i;
            el = document.getElementById(image);
            el.src = '../review/star/1.png';
        }
    }

    function lock(star) {
        show(star);
        locked =1;
    }

    function mark(star) {
        lock(star);
        document.reviewF.rstar.value=star;
    }
</script>

<c:import url="/foot"/>
