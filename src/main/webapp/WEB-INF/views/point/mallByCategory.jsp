<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
div.shop {
  margin: 25px;
  margin-left: auto;
  border: 1px solid #ccc;
  float: left;
  width: 180px;
}
div.shop:hover {
  border: 1px solid #777;
}
div.shop img {
  width: 100%;
  height: auto;
  
}
div.desc {
  padding: 15px;
  text-align: center;
}
</style>

<c:import url="/top" />

<div class="container">
    <!-- category include --------------------- -->
    <c:import url="/point/category">
        <c:param name="category" value="CATE" />
    </c:import>
    <!-- ------------------------------------- -->
    <script type="text/javascript">
        $(function() {
            $('#findF').on('submit', function() {
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
    <c:set var="myctx" value="${pageContext.request.contextPath}" />
    <form id="pageF" name="pageF" action="">
        <input type="hidden" name="findKeyword" value="${param.findKeyword}">
      
    </form>

    <div class="col-sm-9 text-center">
        <!-- 검색폼------------------------------------- -->
        <form name="findF" id="findF" action="" class="form-inline">
            <input type="text" name="findKeyword" id="findKeyword"
                   placeholder="상품명을 입력하세요" class="form-control m-3" required>
                    <input type="hidden" name="cgnum" value="${cgnum}">
            <button class="btn btn-primary">검 색</button>
        </form>
        <!-- ----------------------------- -->
    </div>


    <div class="container" style="margin-top: 30px">
        <div class="row">
            <div class="col-sm-12 text-center">
                <div class="row"></div>
            </div>
            <%--         ${cList } --%>
            <c:if test="${cList eq null or empty cList }">
                <div class="col-md-3">
                    <h3>상품 준비 중입니다.</h3>
                </div>
            </c:if>

            <c:if test="${cList ne null and not empty cList }">
                <c:forEach var="pd" items="${cList}" varStatus="state">
                    <div class="shop">
                        <c:if test="${pd.pimage eq null or empty pd.pimage}">
                            <a href="/point/detail?pnum=${pd.pnum}"> <img
                                    src="../image/noimage.png"  style="width: 177px; height: 160px"/>
                            </a> <br> <br>
                        </c:if>
                        <c:if test="${pd.pimage ne null and not empty pd.pimage }">
                            <a href="/point/detail?pnum=${pd.pnum}"> <img
                                    src="../product/upload/${pd.pimage}"  style="width: 177px; height: 160px"/>
                            </a><br> <br>
                        </c:if>
                        <h6>
                            <a href="/point/detail?pnum=${pd.pnum}">${pd.pname}</a>
                        </h6>
                            ${pd.price}
                        <H1 class="badge badge-info">Point</H1>
                    </div>
                </c:forEach>
            </c:if>
            <div class="container" style="padding-top: 80px;">
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