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
                            <a href="prodDetail.do?pnum=${pd.pnum}"> <img
                                    src="product_images/noimage.png" class="rounded img-fluid"
                                    style="height: 100px"/>
                            </a> <br> <br>
                        </c:if>
                        <c:if test="${pd.pimage ne null and not empty pd.pimage }">
                            <a href="prodDetail.do?pnum=${pd.pnum}"> <img
                                    src="product_images/${pd.pimage}" class="rounded img-fluid"
                                    style="height: 100px"/>
                            </a> <br> <br>
                        </c:if>
                      <h6><a href="prodDetail.do?pnum=${pd.pnum}">${pd.pname}</a></h6>
                            ${pd.price}
                        <h1 class="badge badge-info">Point</h1>
                    </div>
                </c:forEach>
            </c:if>

            <div class="container">
                <ul class="pagination justify-content-center">
                    <!-- 페이지블럭 처리---------------------------- -->
                    <c:forEach var="i" begin="${prevBlock+1}" end="${nextBlock-1}"
						step="1"><!-- 여기가 for(int i = prevBlock+1;i<nextBlock-1;i++)이랑 같은거에욤 -->
                    <c:if test="${i<pageCount+1 }">
                        <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
                            <a class="page-link"
                               href="point.do?cpage=${i}&pageSize=${pageSize}#bbs">
                                    ${i} </a>
                        </li>
                    </c:if>
                    </c:forEach>
                    <!--  ------------------------------------------->
                    <c:if test="${nextBlock < pageCount+1 }">
                        <li class="page-item">
                            <a class="page-link"
                               href="point.do?cpage=${nextBlock}#bbs">
                            </a>
                        </li>

                    </c:if>

                </ul>
                <h6>전체 상품 개수: ${totalCount }</h6>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/foot.jsp"/>