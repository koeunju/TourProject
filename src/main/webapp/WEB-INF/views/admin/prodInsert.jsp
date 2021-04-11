<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/top_sub"/>
<c:import url="/admin/adminMenubar"/>
<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">

    <!-- 여기까지가 top -->


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
                            <select name="cg_num" id="cg_num" class="form-control">
                                <option value="99">::카테고리 유형::</option>
                                <option value="3">스포츠</option>
                                <option value="2">패션</option>
                                <option value="1">식품</option>

                            </select>
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

<script type="text/javascript">
    $(function() {
        $('#btnWrite').on('click', function(e) {
            e.preventDefault();
            var $pname = $('#pname');
            var $price = $('#price');
            var $pcontent = $('#pcontent');

            if (!$pname.val()) {
                alert('상품이름을 입력하세요');
                $pname.focus();
                return;
            }
            if (!$price.val()) {
                alert('가격을 입력하세요');
                $price.focus();
                return;
            }
            if (!$pcontent.val()) {
                alert('내용을 입력하세요');
                $pcontent.focus();
                return;
            }
            var $cg = $('#cg_num')
            if ($cg.val() == 99) {
                alert('선택할수 없는 카테고리입니다. 다시 선택해주세요')
                $('#cg_num').focus();
                return false;
            }

            $('#prodF').submit();
        })
        /*  $('#btnList').on('click', function(e){
             history.back();
         }) */
    })


</script>


<c:import url="/foot" />