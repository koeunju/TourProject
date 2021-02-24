<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<jsp:include page="/top.jsp"/>

<script src="./js/boardInsert.js"></script>
<!-- 컨텍스트명을 myctx변수에 할당 이거 때문에 코어태그 쓴건데 얘때문에 오류였음 jsp는--> 
<div class="container" style="margin-top:30px">
  <div class="row">
    
    <div class="col-sm-12 text-center">
      <h1 class="text-center">Board Write</h1>
      <!-- 파일 업로드시. 메소드는 post, 인코딩타입은 multipart/form-data로 주어야함 -->
      <!-- boardWriteEnd.do로 간다고 해놓고 properties선언에서는 insert로 선언을 하니까 경로를 못찾음 -->
      <form name="boardF" id="boardF" action="boardInsertEnd.do" 
      method="post" enctype="multipart/form-data">
      <table class="table table-bordered">
         <tr>
            <th style="width:20%">제목</th>
            <td style="width:80%">
               <input type="text" name="btitle"
                id="btitle" placeholder="" class="form-control">
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
               id="bcontent" placeholder="" class="form-control"></textarea>
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
               <button class="btn btn-success" id="btnWrite">글쓰기</button>
               <button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
            </td>
         </tr>
      </table>
      </form>
      
      
    </div>
  </div>
</div>

<jsp:include page="/foot.jsp"/>