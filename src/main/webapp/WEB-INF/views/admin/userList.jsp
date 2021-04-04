<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top_sub"/>
<script>
    $(function () {
        $('#btFind').click(function () {
            var ftype = $('#findType>option:selected').val();
            if (!ftype) {
                alert("검색 유형을 선택하세요");
                return;
            }
            var fkey = $('#findKeyword');
            if (!fkey.val()) {
                alert('검색어를 입력하세요');
                fkey.focus();
                return;
            }
            $('form[name="findF"]').submit();
            //findF.submit();
        })
    })
</script>
<c:import url="/admin/adminMenubar"/>

<div class="m-5 p-3 text-center"
     style="border: 1px solid gray; border-radius: 15px" id="font2">
    <!-- top끝 -->


    <div class="container" style="margin-top: 20px">
        <div class="row">

            <div class="col-sm-12 text-center">
                <h1 class="text-center m-4">::User List::</h1>

                <table class="table table-striped m-12" id="bbs">
                    <thead>
                    <tr class="table-secondary">
                        <th data-sort="string">회원번호</th>
                        <th data-sort="string">회원이름</th>
                        <th data-sort="date">가입일</th>
                        <th data-sort="int">회원상태</th>
                        <th>포인트</th>
                        <th>회원수정</th>
                        <th>회원탈퇴</th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- 데이터  -->
                    <c:if test="${userList eq null or empty userList}">

                        <tr>
                            <td colspan="5">데이터가 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:if test="${userList ne null and not empty userList}">
                        <c:forEach var="user" items="${userList}">
                            <tr class="record">
                                <td><c:out value="${user.idx}"/></td>
                                <td><c:out value="${user.nick}"/></td>
                                <td><fmt:formatDate value="${user.indate}"
                                                    pattern="yyyy-MM-dd"/></td>

                                <td>
                                    <c:if test="${user.stat==0 }">
                                        인증필요
                                    </c:if>
                                    <c:if test="${user.stat==1 }">
                                        활동회원
                                    </c:if> <c:if test="${user.stat==3 }">
                                    휴먼회원
                                </c:if> <c:if test="${user.stat==4 }">
                                    탈퇴회원
                                    <c:if test="${user.stat==5}">
                                        차단회원
                                    </c:if>
                                    <c:if test="${usesr.stat==8}">
                                        관리자
                                    </c:if>
                                </c:if> <c:if test="${user.stat==9 }">
                                    슈퍼관리자
                                </c:if></td>
                                <td><c:out value="${user.point}"/>포인트</td>

                                <c:if test="${user.stat==0}">
                                    <td></td>
                                </c:if>
                                <c:if test="${user.stat==9}">
                                    <td></td>
                                </c:if>
                                <c:if test="${user.stat==1 or user.stat==3 or user.stat==4}">

                                    <td><a href="/admin/userEdit?idx=${user.idx}">회원수정</a></td>
                                </c:if>
                                <td><a href="userDelete('${user.idx}')">회원탈퇴</a></td>

                            </tr>
                        </c:forEach>
                    </c:if>

                    <tr>
                        <td colspan="3" class="text-center">
                            <ul class="pagination justify-content-center">
                                <c:forEach var="i" begin="1" end="${pageCount}">
                                    <%-- [ ${i} ] --%>
                                    <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
                                        <a class="page-link" href="userList?cpage=${i}"> ${i} </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        <td colspan="4" style="text-align: center">${pageNavi}</td>


                        <td colspan="2" style="text-align: right; padding-right: 10px">
                            <b>총 회원수: ${totalCount} 명</b>
                        </td>
                    </tr>
                    <!-- 유저 검색 창 -->
                    <form name="findF" action="">
                        <select name="findType" id="findType" style="padding: 5px">
                            <option value="">::검색 유형::</option>
                            <option value="1">회원이름</option>
                            <option value="2">아이디</option>
                            <option value="3">연락처</option>
                        </select> <input type="text" name="findKeyword" id="findKeyword"
                                         placeholder="검색어를 입력하세요"
                                         style="width: 60%; border: 1px solid silver; padding: 5px">
                        <button type="button" id="btFind"
                                style="padding: 4px; width: 100px; background: beige; border: 1px solid silver">검
                            색
                        </button>
                    </form>

                    <!--  -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 삭제 form---------------- -->
<form name="pf" id="pf" method="post">
    <input type="hidden" name="idx" id="idx">
</form>
<!-- 삭제 폼 시작----------------- -->
<form name="df" id="df" action="userDelete">
    <input type="hidden" name="idx" id="df">
</form>
<!-- 수정 form시작-------------------- -->
<form name="frm2" method="GET">
</form>
<!-- ------------------------------------- -->
<script type="text/javascript">
    function deleteUser(num) {
        alert(num);
        var n = $('#idx').val(num);
        $('#pf').attr('action', '/admin/del')
        $('#pf').submit();
    }

    function userDelte(num) {

        df.idx.value = num;
        //$('input[name="cartNum"]').val(num);
        df.method = 'post';
        df.submit();
    }

</script>


<c:import url="/foot"/>