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
                <td width="30%" class="text-center">
                    <c:if test="${board.cg_num==0 }">
                        <b>여행지 추천</b>
                    </c:if>
                    <c:if test="${board.cg_num==1 }">
                        <b>자유게시판</b>
                    </c:if>
                    <c:if test="${board.cg_num==2 }">
                        <b>고객센터</b>
                    </c:if>
                </td>
                <th width="25%">글쓴이</th>
                <td width="30%"><c:out value="${board.idx}" /></td>
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
                        <img
                                src="${pageContext.request.contextPath}/Upload/${board.filename}"
                                class="img-thumbnail" width="100px">
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
            <button class="btn btn-primary" onclick="goList()">목록</button>
        </form>
        <h6 class='text-right'>
            <button class="btn btn-primary" onclick="goRe()">답글달기</button>
        </h6>
    </div>
</div>
<!-- 댓글 폼---------------------------------------------- -->

<div class="col-lg-12">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="form-group col-sm-2">
                    <input class="form-control input-sm" id="newReplyWriter"
                           type="text" value="${board.idx }" disabled>
                </div>
                <div class="form-group col-sm-8">
                    <input class="form-control input-sm" id="newReplyText" type="text"
                           placeholder="댓글 입력">
                </div>
                <div class="form-group col-sm-2">
                    <button type="button"
                            class="btn btn-primary btn-sm btn-block replyAddBtn">
                        <i class="fa fa-save"></i> 저장
                    </button>
                </div>
            </div>
        </div>
        <div class="card-footer">
            <ul id="replies">
            </ul>
        </div>
        <div class="card-footer">
            <nav aria-label="Contacts Page Navigation">
                <ul
                        class="pagination pagination-sm no-margin justify-content-center m-0">
                </ul>
            </nav>
        </div>
    </div>
</div>
<div class="modal fade" id="modifyModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">댓글 수정창</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="reply_no">댓글 번호</label> <input class="form-control"
                                                               id="reply_no" name="reply_no" readonly>
                </div>
                <div class="form-group">
                    <label for="reply_text">댓글 내용</label> <input class="form-control"
                                                                 id="reply_text" name="reply_text" placeholder="댓글 내용을 입력해주세요">
                </div>
                <div class="form-group">
                    <label for="reply_writer">댓글 작성자</label> <input
                        class="form-control" id="reply_writer" name="reply_writer"
                        readonly>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left"
                        data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-success modalModBtn">수정</button>
                <button type="button" class="btn btn-danger modalDelBtn">삭제</button>
            </div>
        </div>
    </div>
</div>

<!-- -답변 달기 form -->
<form name="reF" action="reInsert" method="post">
    <!-- hidden으로 부모글(원글)의 글번호(idx)와 제목(subject)를 넘기자. -->
    <input type="hidden" name="bnum" value="${board.bnum}">
    <input type="hidden" name="btitle" value="${board.btitle}">
    <input type="hidden" name="cg_num" value="${board.cg_num}">

</form>


<!-- 파일 다운로드 form -->
<form name="fileF" id="fileF" action="fileDown" method="post">
    <input type="hidden" name="fname" value="${board.filename}"> <input
        type="hidden" name="origin_fname" value="${board.originFilename}">
</form>

<!-- 댓글---------------------------------------------------- -->
<script type="text/javascript" src="../js/reply.js"></script>
<script>
    /* 댓글 가져오는 이벤트 */
    $(document).ready(function () {

        var bnumValue = '<c:out value="${board.bnum}"/>';
        var replyUL = $(".chat");

        showList(1);
        function showList(page) {
            replyService.getList(bnum:bnumValue,page: page||1
        }, function(list) {
            var str="";
            if(list==null||list.length==0) {
                replyUL.html("");

                return;
            }
            for(var i = 0; len = list.length || 0; i < len; i++) {
                str += "<li class='left clearfix' data-rnum='"+list[i].rnum+"'>";
                str += " <div><div class='header'><strong class='primary-font'>"+list[i].idx+"</strong>";
                str += " <small class='pull-right text-muted'>"+list[i].replyDate+"</small></div>";
                str += " <p>"+list[i].reply+"</p></div></li>";
            }
            replyUL.html(str);
        });
    }
    });

    console.log('----------');
    console.log("JS Test");

    var bnumValue = '<c:out value="${bouard.bnum}"/>';

    //추가
    replyService.add(
        {reply:"JS TEST", idx:"tester", bnum:bnumValue}
        ,
        function(result){
            alert("Result "+Result);
        }
    );

    //리스트
    replyService.getList({bnum:bnumValue, page:1}, function(list){
        for(var i = 0, len = list.length||0; i < len; i++) {
            console.log(list[i]);
        }
    });

    //삭제
    replyService.remove(rnum:rnumValue, function(count) {
        console.log(count);

        if(count ==="success") {
            alert("삭제완료");
        }
    }, function(err) {
        alert('Error...')
    });

    //수정
    replyService.update({
        rnum : rnumvalue,
        bnum : bnumValue,
        reply : "midified Reply..."
    }, function(result) {
        alert("수정완료");
    });

    //목록
    replyService.get(rnum:rnumValue, function(data) {
        console.log(data);
    });

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