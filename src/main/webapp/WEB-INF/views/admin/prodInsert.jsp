<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/top_sub"/>
<c:import url="/admin/adminMenubar"/>
<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">

    <!-- 여기까지가 top -->

    <c:set var="myctx"  value="${pageContext.request.contextPath}"/>
    <!--컨텍스트명을 myctx변수에 할당  -->
    <script src="../js/productInsert.js"></script>

    <div class="container" style="margin-top:30px">
        <div class="row">

            <div class="col-sm-12 text-left">

                <div class="col-sm-12">
                    <h1 class="text-center">상품 등록</h1>


                    <form name="prodF" id="prodF" action="prodInsert" method="post"
                          enctype="multipart/form-data">
                        <table class="table table-condensed table-bordered mt-4">
                            <thead>

                            <tr class="bg-success text-white">
                                <th colspan="2" class="text-center">
                                    <h3>:::Product Register:::</h3>
                                </th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td width="20%"><b>상품명</b></td>
                                <td width="80%"><input type="text" name="pname" id="pname" placeholder="" class="form-control">
                                    <span style="color: red">
                     </span>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%"><b>상품판매가</b></td>
                                <td width="80%"><input type="text" name="price"
                                                       id="price"  placeholder="" class="form-control"> POINT
                                    <span style="color: red">

               </span>
                                </td>
                            </tr>

                            <!--  <tr>
                                <td width="20%"><b>상품스펙</b></td>
                                <td width="80%"><select name="pspec" id="pspec">
                                      <option value="NEW" selected>NEW</option>
                                      <option value="HIT">HIT</option>
                                      <option value="BEST">BEST</option>
                                </select></td>
                             </tr> -->
                            <tr>
                                <td>상품이미지</td>
                                <td>
                                    <input type="file" name="imgfile1"  placeholder="Attach File" class="form-control"><br>
                                    <input type="file" name="imgfile2" placeholder="Attach File" class="form-control"><br>
                                    <input type="file" name="imgfile3" placeholder="Attach File" class="form-control"><br>
                                </td>
                            </tr>

                            <tr>
                                <td width="20%"><b>상품설명</b></td>
                                <td width="80%"><textarea name="pcontent" id="pcontent"
                                                          rows="5" cols="60"  placeholder="" class="form-control"></textarea></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <button class="btn btn-success" id="btnWrite">상품등록</button>
                                    <button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!--  -->
    </div>
</div>

<c:import url="/foot" />