<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/top" />

<div class="container" style="margin-top: 30px">
   <div id="wrap" align="center">
      <h3>게시글 상세보기</h3>

      <%-- <c:if test="${board eq null}"> <!-- arrLaist가 아니라서 null만 체크 -->
             <h2 class="text-danger m-5">${param.idx}번 게시글이 없습니다.</h2>
         </c:if>
        <c:if test="${board ne null}"> --%>
      <table class="table table-bordered">
         <tr>
            <th width="25%">게시물 유형</th>
            <td width="30%" class="text-center"><c:if
                  test="${board.cg_num==0 }">
                  <b>여행지 추천</b>
               </c:if> <c:if test="${board.cg_num==1 }">
                  <b>자유게시판</b>
               </c:if> <c:if test="${board.cg_num==2 }">
                  <b>고객센터</b>
               </c:if></td>
            <th width="25%">글쓴이</th>
            <td width="30%"><c:out value="${board.nick}" /></td>
         </tr>
         <tr>
            <td width="25%"><b>작성일</b></td>
            <td width="30%"><fmt:formatDate value="${board.bdate}"
                  pattern="yyyy-MM-dd hh:mm:ss" /></td>
            <td width="20%"><b>조회수</b></td>
            <td width="30%"><c:out value="${board.binquiry}" />
         </tr>
         <tr>
            <td width="20%"><b>첨부파일</b></td>
            <td colspan="3">&nbsp;
            <c:if test="${board.filename ne null}">
                     <!-- 첨부파일이 있다면 -->
                        <a href="#" onclick="down()"> <c:out
                           value="${board.originFilename}" />
                     </a>
                     <!-- 물리적 파일명 소문자 변환 -->
                     <c:set var="fname" value="${fn:toLowerCase(board.filename)}" />
                     <br>
                     <c:if
                        test="${fn:endsWith(fname,'.png')
                        or fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.gif')}">
                        <img src="../board/upload/${board.filename}"
                           class="img-thumbnail" width="500px">
                     </c:if>
                     <br> [ <c:out value="${board.filesize}" /> ]bytes</td>
            </c:if>
         <tr style="border: 0">
         </tr>
         <tr>
            <td width="20%"><b>제목</b></td>
            <td colspan="3"><c:out value="${board.btitle}" />
         </tr>
         <tr>
            <td width="20%"><b>글내용</b></td>
            <td colspan="3"><pre>
                  <c:out value="${board.bcontent}" />
               </pre></td>
         </tr>
      </table>
      <br> <br>
      <form id="boardF" method="post">
         <input type="hidden" name="bnum" id="bnum"
            value="<c:out value="${board.bnum}"/>">
         <button class="btn btn-primary" onclick="goEdit()">수정</button>
         <button class="btn btn-primary" onclick="goDel()">삭제</button>
      </form>
      <h6 class='text-right'>
         <button class="btn btn-primary" onclick="history.back()">목록</button>
         <button class="btn btn-primary" onclick="goRe()">답글달기</button>
      </h6>
   </div>
</div>



<!-- -답변 달기 form -->
<form name="reF" action="reInsert" method="post">
   <!-- hidden으로 부모글(원글)의 글번호(idx)와 제목(subject)를 넘기자. -->
   <input type="hidden" name="bnum" value="${board.bnum}"> <input
      type="hidden" name="btitle" value="${board.btitle}"> <input
      type="hidden" name="cg_num" value="${board.cg_num}">

</form>


<!-- 파일 다운로드 form -->
<form name="fileF" id="fileF" action="fileDown" method="post">
   <input type="hidden" name="fname" value="${board.filename}"> <input
      type="hidden" name="origin_fname" value="${board.originFilename}">
</form>


<!-- 함수 -------------------------------------- -->
<script type="text/javascript">
   function down() {
      $('#fileF').submit();
   }

   function goDel() {
      var yn = confirm('${board.bnum}번 글을 정말 삭제할까요?');
      if (yn) {

         $('#boardF').prop('method', 'post');
         $('#boardF').prop('action', 'delete');
      }
   }

   function goEdit() {
      $('#boardF').prop('method', 'post');
      $('#boardF').prop('action', 'edit');
   }

   function goList() {
      $('#boardF').prop('method', 'get');
      $('#boardF').prop('action', 'list');
   }

   function goRe() {
      reF.submit();
   }
</script>

<c:import url="/foot" />