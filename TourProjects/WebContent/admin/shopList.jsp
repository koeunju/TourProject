<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/top.jsp"/>

<nav class="navbar navbar-expand-md navbar-white bg-white text-white" id="font1">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/userList.do">유저관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/pointshopList.do">포인트 샵 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/purchase.do">구매 정보 관리</a>
            </li>
        </ul>
    </div>
</nav>

<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">

    <div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/prodForm.do">상품 등록 하기</a>
            </li>
        </ul>

    </div>
    <!-- 여기까지가 top -->


    <script type="text/javascript">
        $(function () {
            $('#findF').on('submit', function () {
                var $keyword = $('#findKeyword');
                if (!$keyword.val()) {
                    alert('검색어를 입력하세요');
                    $keyword.focus();
                    return false;
                }
                return true;
            })
        })
    </script>
    <c:set var="myctx" value="${pageContext.request.contextPath}"/>
    <div class="col-sm-9 text-center">
        <!-- 검색폼------------------------------------- -->
        <form name="findF" id="findF" action="productFind.do#bbs"
              class="form-inline">
            <input type="text" name="findKeyword" id="findKeyword"
                   placeholder="상품명을 입력하세요" class="form-control m-3" required>
            <button class="btn btn-primary">검 색</button>
        </form>
        <!-- ----------------------------- -->
    </div>

    <div class="container" style="margin-top: 30px">
        <div class="row">
            <div class="col-sm-12 text-center">
                <div class="row"></div>
            </div>


            <%--제품목록 표시 영역 --%>
            <c:if test="${pList eq null or empty pList }">
                <div class="col-md-3">
                    <h3>상품 준비 중 입니다.</h3>
                </div>
            </c:if>

            <c:if test="${pList ne null and not empty pList}">
                <c:forEach var="pd" items="${pList}" varStatus="state">
                    <div class="col-md-3">
                        <a href="prodDetail.do?pnum=${pd.pnum}"> <img
                                src="product_images/${pd.pimage}" class="rounded img-fluid"
                                style="height: 220px"/>
                        </a> <br> <br>
                        <h6>${pd.pname}</h6>
                            ${pd.price}
                        <H1 class="badge badge-info">Point</H1>
                    </div>
                </c:forEach>
            </c:if>
            <%--  <tr>
                 <td colspan="3" class="text-center">
                     <ul class="pagination justify-content-center">
                         <!-- 페이지블럭 처리---------------------------- -->
                         <c:forEach var="i" begin="${prevBlock+1}" end="${nextBlock-1}"
                                    step="1">
                             <c:if test="${i<pageCount+1 }">
                                 <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
                                     <a class="page-link"
                                        href="boardList.do?cpage=${i}&pageSize=${pageSize}#bbs">
                                             ${i} </a>
                                 </li>
                             </c:if>
                         </c:forEach>
                         <!--  ------------------------------------------->
                     </ul>
                 </td>
             </tr> --%>

        </div>
    </div>