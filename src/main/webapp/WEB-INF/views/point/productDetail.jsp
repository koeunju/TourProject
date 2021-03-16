<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/top" />

<script src="js/jquery.magnifier.js"></script>

<script type="text/javascript">
    function openPop(fname) {
        var url = "product_images/" + fname;
        var obj = new Image();
        obj.src = url;
        var w = obj.width + 50;
        var h = obj.height + 50;

        window.open(url, 'preview', "width=" + w + ", height=" + h + ", top=100, left=100")
    }

    function goOrder() {
        frm.action = "user/order.do";
        frm.method = 'get';
        frm.submit();
    }

</script>

<div>
    <div align="center" class="row">
        <c:if test="${item eq null}">
            <div class="col-md-10 offset-md-1">
                <h2>해당 상품은 존재하지 않아요</h2>
            </div>
        </c:if>
        <c:if test="${item ne null}">
            <div class="col-md-10 offset-md-1 table-responsive">

                <table class="table m-5 p-5">
                    <thead>
                    <tr>
                        <th colspan="2" class="text-center"><h3>상품 정보</h3></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="center" width="50%">
                            <img src="product_images/${item.pimage}"
                                 class="img-fluid magnify"  data-magnifyby="2" style="width: 50%;">
                            <!-- </a> -->
                        </td>
                        <td align="left"  width="50%" class="pl-5">
                            상품이름: ${item.pname} <br>
                            가격:<span style="color:red;font-weight:bold">
				<fmt:formatNumber value="${item.price}"
                                  pattern="###,###" />
			</span><H1 class="badge badge-info">Point</H1><br>
                            <!-- form시작---------- -->
                            <form name="frm" id="frm" method="POST">
                                <!-- 상품번호를 hidden으로 넘기자------ -->
                                <input type="hidden" name="pnum" value="${item.pnum}">
                            </form>
                            <!-- form end------------ -->
                            <button type="button" onclick="goOrder()"
                                    class="btn btn-warning">구매하기</button>

                        </td>
                    </tr>
                    <tr style="border:0">
                        <td align="center">
                            <img src="product_images/${item.pimage2}"
                                 class="img-fluid img-thumbnail"  style="width: 50%;">
                        </td>
                        <td align="center">
                            <img src="product_images/${item.pimage3}"
                                 class="img-fluid img-thumbnail"  style="width: 50%;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <p>상품설명</p>
                            <pre>${item.pcontent}</pre>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div><!-- col end -->
        </c:if>
    </div><!-- row end -->
</div>

<c:import url="/foot" />
