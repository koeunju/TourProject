<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/top.jsp"/>

<div class="container">
    <!-- category include --------------------- -->
    <jsp:include page="/point/category.jsp"/>
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

    <c:if test="${prodList ne null && not empty prodList }">
        <c:set var="prod" value="${prodList[0]}"/>
    </c:if>

    <div class="row m-3">
        <c:if test="${prodList eq null or empty prodList }">
            <div class="col-md-3">
                <h3>상품 준비 중</h3>
            </div>
        </c:if>
        <c:if test="${prodList ne null and not empty prodList }">
            <c:forEach var="item" items="${prodList}" varStatus="state">

                <div class="col-md-3">
                    <a href="prodDetail.do?pnum=${item.pnum}"> <img
                            src="product_images/${item.pimage}" class="rounded img-fluid"
                            style="height: 220px"/>
                    </a> <br>
                    <br>
                    <h6>${item.pname}</h6>
                        ${item.price}
                    <H1 class="badge badge-info">Point</H1>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>

<jsp:include page="/foot.jsp"/>