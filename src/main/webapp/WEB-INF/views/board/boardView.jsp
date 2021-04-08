<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <td colspan="3">&nbsp; <c:if test="${board.filename ne null}">
                    <!-- 첨부파일이 있다면 -->
                    <a href="#" onclick="down()"> <c:out
                            value="${board.originFilename}" />
                    </a>
                    <!-- 물리적 파일명 소문자 변환 -->
                    <c:set var="fname" value="${fn:toLowerCase(board.filename)}" />

                    <c:if
                            test="${fn:endsWith(fname,'.png')
							or fn:endsWith(fname,'.jpg') or fn:endsWith(fname,'.gif')}">
                        <%-- <img
                            src="${pageContext.request.contextPath}/Upload/${board.filename}"
                            class="img-thumbnail" width="100px"> --%>
                    </c:if>
                    [
                    <c:out value="${board.filesize}" />
                    ]bytes
                </c:if>
                </td>
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
<!-- 댓글 폼---------------------------------------------- -->

<!-- Reply Form {s} -->
<div class="my-3 p-3 bg-white rounded shadow-sm"
     style="padding-top: 10px">
    <form name="form" id="form" action="form" modelAttribute="replyVO"
          method="post">
        <input type="hidden" name="bnum" id="bnum" />
        <div class="row">
            <div class="col-sm-10">
				<textarea name="content" id="content" class="form-control" rows="3"
                          placeholder="댓글을 입력해 주세요"></textarea>
            </div>
            <div class="col-sm-2">
                <input name="idx" class="form-control" id="idx" value="${board.nick}"
                       disabled></input>
                <button type="button" class="btn btn-sm btn-primary"
                        id="btnReplySave" style="width: 100%; margin-top: 10px">저
                    장</button>
            </div>
        </div>
    </form>
</div>

<!-- Reply Form {e} -->

<!-- Reply List {s}-->
<div class="my-3 p-3 bg-white rounded shadow-sm"
     style="padding-top: 10px">
    <h6 class="border-bottom pb-2 mb-0">Reply list</h6>
    <div id="replyList"></div>
</div>
<!-- Reply List {e}-->


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

<!-- 댓글---------------------------------------------------- -->
<script>
    /* 댓글보여주기 */
    $(document).ready(function(){

        showReplyList();

    });

    function showReplyList(){
        var url = "${pageContext.request.contextPath}/restBoard/getReplyList";
        var paramData = {"bnum" : "${boardContent.bnum}"};
        $.ajax({
            type: 'POST',
            url: url,
            data: paramData,
            dataType: 'json',
            success: function(result) {
                var htmls = "";
                if(result.length < 1){
                    htmls.push("등록된 댓글이 없습니다.");
                } else {
                    $(result).each(function(){
                        htmls += '<div class="media text-muted pt-3" id="rnum' + this.rnum + '">';
                        htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
                        htmls += '<title>Placeholder</title>';
                        htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
                        htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
                        htmls += '</svg>';
                        htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                        htmls += '<span class="d-block">';
                        htmls += '<strong class="text-gray-dark">' + this.idx + '</strong>';
                        htmls += '<span style="padding-left: 7px; font-size: 9pt">';
                        htmls += '<a href="javascript:void(0)" onclick="fn_editReply(' + this.rnum + ', \'' + this.idx + '\', \'' + this.content + '\' )" style="padding-right:5px">수정</a>';
                        htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rnum + ')" >삭제</a>';
                        htmls += '</span>';
                        htmls += '</span>';
                        htmls += this.content;
                        htmls += '</p>';
                        htmls += '</div>';
                    });	//each end
                }
                $("#replyList").html(htmls);

            }	   // Ajax success end
        });	// Ajax end /

        <!-- 댓글 저장 버튼 클릭 이벤트 -->
        $(document).on('click', '#btnReplySave', function(){
            var replyContent = $('#content').val();
            var replyidx = $('#idx').val();

            var paramData = JSON.stringify({"content": replyContent
                , "idx": replyidx
                , "bnum":'${boardContent.bnum}'
            });

            var headers = {"Content-Type" : "application/json"
                , "X-HTTP-Method-Override" : "POST"};

            $.ajax({
                url: "${saveReplyURL}"
                , headers : headers
                , data : paramData
                , type : 'POST'
                , dataType : 'text'
                , success: function(result){
                    showReplyList();

                    $('#content').val('');
                    $('#idx').val('');
                }
                , error: function(error){
                    console.log("에러 : " + error);
                }
            });
        });

        /* 댓글수정 */
        function fn_editReply(rnum, idx, content){
            var htmls = "";
            htmls += '<div class="media text-muted pt-3" id="rnum' + rnum + '">';
            htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
            htmls += '<title>Placeholder</title>';
            htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
            htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
            htmls += '</svg>';
            htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
            htmls += '<span class="d-block">';
            htmls += '<strong class="text-gray-dark">' + idx + '</strong>';
            htmls += '<span style="padding-left: 7px; font-size: 9pt">';
            htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rnum + ', \'' + idx + '\')" style="padding-right:5px">저장</a>';
            htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
            htmls += '</span>';
            htmls += '</span>';
            htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
            htmls += content;
            htmls += '</textarea>';

            htmls += '</p>';
            htmls += '</div>';

            $('#rnum' + rnum).replaceWith(htmls);
            $('#rnum' + rnum + ' #editContent').focus();
        }

        /* 수정 후 저장 */
        var fn_updateReply = function(rnum, idx){
            var replyEditContent = $('#editContent').val();

            var paramData = JSON.stringify({
                    "content": replyEditContent
                })
                , "rnum": rnum
        };

    var headers = {"Content-Type" : "application/json"
        , "X-HTTP-Method-Override" : "POST"};

    $.ajax({
        url: "${updateReplyURL}"
        , headers : headers
        , data : paramData
        , type : 'POST'
        , dataType : 'text'
        , success: function(result){
            console.log(result);
            showReplyList();
        }
        , error: function(error){
            console.log("에러 : " + error);
        }
    });
    }

    /* 댓글 삭제 */
    function fn_deleteReply(rnum){
        var paramData = {"rnum": rnum};

        $.ajax({
            url: "${deleteReplyURL}"
            , data : paramData
            , type : 'POST'
            , dataType : 'text'
            , success: function(result){
                showReplyList();
            }
            , error: function(error){
                console.log("에러 : " + error);
            }
        });
    }

</script>



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

    function goRe(){
        reF.submit();
    }
</script>

<c:import url="/foot" />