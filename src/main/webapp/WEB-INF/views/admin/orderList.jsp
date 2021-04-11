<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top_sub" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<c:import url="/admin/adminMenubar"/>

<div class="m-5 p-3 text-center"
     style="border: 1px solid gray; border-radius: 15px" id="font2">
    <!-- top끝 -->



    <div class="container" style="margin-top: 20px">
        <div class="row">

            <div class="col-sm-12 text-center">
                <h1 class="text-center m-4">::Order List::</h1>

                <table class="table table-striped m-12" id="bbs">
                    <thead>
                    <tr class="table-secondary">
                        <th >주문번호</th>
                        <th >상품번호</th>
                        <th >포인트</th>
                        <th >수량</th>
                        <th>상품대표이미지</th>
                        
                    </tr>
                    </thead>
                    <tbody>

                    <!-- 데이터  -->
                    
                        <c:forEach var="order" items="${orderList}">
                            <tr class="record">
                                <td><c:out value="${order.onum}" /></td>
                                <td><c:out value="${order.pnum_fk}" /></td>
                                 <td><c:out value="${order.opoint}" /></td>
                                  <td><c:out value="${order.oqty}" /></td>
                                   <td><c:out value="${order.pimage}" /></td>
							
                                 
                           

                            </tr>
                        </c:forEach>
                    
<%-- 
                    <tr>
                        <td colspan="3" class="text-center">
                            <ul class="pagination justify-content-center">
                                <c:forEach var="i" begin="1" end="${pageCount}">
                                    [ ${i} ]
                                    <li class="page-item <c:if test="${cpage eq i}">active</c:if>">
                                        <a class="page-link" href="userList?cpage=${i}"> ${i} </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        <td colspan="4" style="text-align: center">${pageNavi}</td>


                        <td colspan="2" style="text-align: right; padding-right: 10px">
                            <b>총 회원수: ${totalCount} 명</b>
                        </td>
                    </tr> --%>
                   <!-- 유저 검색 창 -->
                   <!--  <form name="findF" action="">
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
                            색</button>
                    </form>  -->
                    <!--  -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>




<c:import url="/foot" />