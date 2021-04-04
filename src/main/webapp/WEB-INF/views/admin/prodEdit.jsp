<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:import url="/top_sub"/>
<c:import url="/admin/adminMenubar"/>



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
							<select name="upCg_code" id="upCg_code" class="ml-3"
								onchange="selectDCate(this.value)">
									<option value="">::상위 카테고리::</option>
									
								<c:forEach var="upCate" items="${upCategoryList}">
								<option 
								 <c:if test="${item.upCg_code == upCate.upCg_code }">selected</c:if>
								
								value="${upCate.upCg_code}">
									${upCate.upCg_name}
									</option>
								</c:forEach>
									
									<!-- <option value="2">의류</option> -->
									
							</select> 
							<span id="selectDcg"> 
							    <select name="downCg_code">
									<option value="1">컴퓨터</option>
									<option value="2">가전</option>
								</select> 
							</span></td>
						</tr>
						<tr>
							<td><b>상품번호</b></td>
							<td>
								<input type="text" name="pnum"
								 value="${item.pnum}" id="pnum" readonly>
							</td>
						</tr>
						<tr>
							<td width="20%"><b>상품명</b></td>
							<td width="80%">
							<input type="text" name="pname" value="${item.pname}"
							 id="pname">
							<span style="color: red"> 
							</span>
						</td>
						</tr>
						
						<tr>
							<td rowspan="2">상품이미지</td>
							<td>
							${item.pimage}/${item.pimage2}
							<img src="../product/upload/${item.pimage}"
								 class="img-thumbnail" style="width:20%"
								  alt="상품이미지">
								 
							<img src="../product/upload/${item.pimage2}"
								 class="img-thumbnail" style="width:20%"
								 alt="상품이미지2">
								 
							<img src="../product/upload/${item.pimage3}"
								 class="img-thumbnail" style="width:20%"
								 alt="상품이미지3">	 
								 
							</td>
						</tr>
						<tr>
							<!-- <td>상품이미지</td> -->
							<td>
				<!-- hidden file data(기존에 첨부한 이미지파일을 hidden data로)----- -->
				<input type="hidden" name="old_pimage1" value="${item.pimage1}">
				<input type="hidden" name="old_pimage2" value="${item.pimage2}">
				<input type="hidden" name="old_pimage3" value="${item.pimage3}">
				<!-- ---------------------------------- -->
							<input type="file" name="pimage1"><br> 
							<input type="file" name="pimage2"><br> 
							<input type="file" name="pimage3"><br>
						</td>
						</tr>

						<tr>
							<td width="20%"><b>상품수량</b></td>
							<td width="80%">
							<input type="number" name="pqty"
							 value="${item.pqty}" id="pqty">
								개
					<span style="color: red"> 
					</span></td>
								
						</tr>
						<tr>
							<td width="20%"><b>상품정가</b></td>
							<td width="80%">
							<input type="text" name="price" value="${item.price}" id="price">
		
								원
							<span style="color: red"> 
							
					</span>			
								</td>
						</tr>
						<tr>
							<td width="20%"><b>상품판매가</b></td>
							<td width="80%"><input type="text" name="saleprice"
							value="${item.saleprice}" 
								id="saleprice"> 원
								<span style="color: red"> 
							
					</span>	
								
								</td>
						</tr>
						<tr>
							<td width="20%"><b>상품설명</b></td>
							<td width="80%"><textarea name="pcontents" id="pcontents"
									rows="5" cols="60">${item.pcontents}</textarea></td>
						</tr>
						<tr>
							<td width="20%"><b>포인트</b></td>
							<td width="80%">
							<input type="number" name="point" value="${item.point}"  id="point">
								POINT</td>
						</tr>
						<tr>
							<td colspan="2">
								<button class="btn btn-info">상품수정</button>
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
