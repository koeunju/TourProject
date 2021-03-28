<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top" />
<div class="container">
    <!-- category include --------------------- -->
    <c:import url="/point/category">
        <c:param name="category" value="CATE"/>
    </c:import>
    <!-- ------------------------------------- -->

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
    <form id="pageF" name="pageF" action="point#bbs">
        <input type="hidden" name="findKeyword" value="${param.findKeyword}">
    </form>

    <div class="col-sm-9 text-center">
        <!-- 검색폼------------------------------------- -->
        <form name="findF" id="findF" action="point#bbs"
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

            <c:if test="${bList eq null or empty bList }">
                <div class="col-md-3">
                    <h3>상품 준비 중 입니다.</h3>
                </div>
            </c:if>

            <c:if test="${bList ne null and not empty bList}">
                <c:forEach var="pd" items="${bList}" varStatus="state">
                    <div class="col-md-3">
                        <c:if test="${pd.pimage eq null or empty pd.pimage}">
                            <a href="/point/detail?pnum=${pd.pnum}"> <img
                                    src="../image/noimage.png" class="rounded img-fluid"
                                    style="height: 100px"/>
                            </a> <br> <br>
                        </c:if>
                        <c:if test="${pd.pimage ne null and not empty pd.pimage }">
                            <a href="/point/detail?pnum=${pd.pnum}"> <img
                                    src="../product/upload/${pd.pimage}" class="rounded img-fluid"
                                    style="height: 100px"/>
                            </a> <br> <br>
                        </c:if>
                        <h6><a href="/point/detail?pnum=${pd.pnum}">${pd.pname}</a></h6>
                            ${pd.price}
                        <h1 class="badge badge-info">Point</h1>
                    </div>
                </c:forEach>
            </c:if>
            <div class="container">
                ${pageNavi}
                <%-- <td colspan="2"><span class="text-primary">총상품수: <c:out
                            value="${paging.totalCount}" />개
                </span> <br> <span class="text-danger">${paging.cpage}</span>
                / <span>${paging.pageCount}</span>
                </td> --%>
            </div>
        </div>
    </div>
</div>


<c:import url="/foot" />
© 2021 GitHub, Inc.