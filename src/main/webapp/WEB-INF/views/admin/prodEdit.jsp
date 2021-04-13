<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>



<div class="container" style="margin-top:30px">
	<div class="row">


		<div class="col-sm-9">
			<h1 class="text-center">상품정보 수정[ADMIN모드]</h1>

			<form name="prodF" id="prodF" action="prodEdit" method="POST"
				  enctype="multipart/form-data">
				<table class="table table-condensed table-bordered mt-4">
					<thead>
					<tr class="bg-warning text-white">
						<th colspan="2" class="text-center">
							<h3>:::Product Edit:::</h3>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td width="20%"><b>카테고리</b></td>
						<td width="80%">
							<span style="font-weight:bold">${item.allCategory}</span>

							<select name="cg_num" id="cg_num" class="form-control">
								<option value="99">::카테고리 유형::</option>
								<option value="3">스포츠</option>
								<option value="2">패션</option>
								<option value="1">식품</option>

							</select></td>
					</tr>
					<tr>
						<td><b>상품번호</b></td>
						<td>${product.pnum}
							<input type="hidden" name="pnum"
								   value="${product.pnum}" id="pnum" >
						</td>
					</tr>
					<tr>
						<td width="20%"><b>상품명</b></td>
						<td width="80%">
							<input type="text" name="pname" value="${product.pname}"
								   id="pname">
							<span style="color: red">
							</span>
						</td>
					</tr>

					<tr>
						<td rowspan="2">상품이미지</td>
						<td>
							${product.pimage}/${product.pimage2}
							<img src="../product/upload/${product.pimage}"
								 class="img-thumbnail" style="width:20%"
								 alt="상품이미지">

							<img src="../product/upload/${product.pimage2}"
								 class="img-thumbnail" style="width:20%"
								 alt="상품이미지2">

							<img src="../product/upload/${product.pimage3}"
								 class="img-thumbnail" style="width:20%"
								 alt="상품이미지3">

						</td>
					</tr>
					<tr>
						<!-- <td>상품이미지</td> -->
						<td>
							<!-- hidden file data(기존에 첨부한 이미지파일을 hidden data로)----- -->
							<input type="hidden" name="old_pimage" value="${product.pimage}">
							<input type="hidden" name="old_pimage2" value="${product.pimage2}">
							<input type="hidden" name="old_pimage3" value="${product.pimage3}">
							<!-- ---------------------------------- -->
							<input type="file" name="pimage"><br>
							<input type="file" name="pimage2"><br>
							<input type="file" name="pimage3"><br>
						</td>
					</tr>
					<tr>
						<td width="20%"><b>상품판매가</b></td>
						<td width="80%"><input type="text" name="price"
											   value="${product.price}"
											   id="price"> 원
							<span style="color: red">
					</span>

						</td>
					</tr>
					<tr>
						<td width="20%"><b>상품설명</b></td>
						<td width="80%"><textarea name="pcontent" id="pcontent"
												  rows="5" cols="60">${product.pcontent}</textarea></td>
					</tr>

					<tr>
						<td colspan="2">
							<button class="btn btn-info" onclick="submit()">상품수정</button>
							<button type="reset"
									class="btn btn-danger">다시쓰기</button>
						</td>
					</tr>
					</tbody>
				</table>
			</form>


		</div>
	</div>
</div>
<c:import url="/foot"/>