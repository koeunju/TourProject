<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>올랑올랑</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark text-white" id="font1">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin.do"">관리자 페이지</a>
            </li>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <a href="index.do"><img src="image/head1.png"></a>
    </div>
    <div
            class="navbar-collapse collapse w-100 order-1 dual-collapse2 text-white">
        <ul class="navbar-nav ml-auto">
            <c:if test="${loginUser eq null}">
                <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/login.do">로그인</a></li>
            </c:if>
            <c:if test="${loginUser ne null}">
                <li class="nav-link"><a class="nav-link" href='#'>${loginUser.id}님</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout.do">로그아웃</a></li>
            </c:if>
            <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/join.do">회원가입</a>
        </ul>
    </div>
</nav>

<div id="title">
    <img src="image/title1.png" style="width:100%">
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark" id="font1">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/tourSearch.do">관광지 추천</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">게시판</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">이달의 여행지</a>
                    <a class="dropdown-item" href="boardList.do">자유게시판</a>
                    <a class="dropdown-item" href="#">고객센터</a>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/point.do">포인트샵</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/hof.do">명예의 전당</a>
            </li>
        </ul>
    </div>
</nav>


<nav class="navbar navbar-expand-md navbar-white bg-white text-white" id="font1">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/userList.do">유저관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/pointshopList.do">포인트 샵 관리</a>
            </li>
             <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/purchase.do">구매 정보 관리</a>
            </li>
            </ul>
</div>
</nav>

<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">

<!-- 여기까지가 top -->

 <c:set var="myctx"  value="${pageContext.request.contextPath}"/>
<!--컨텍스트명을 myctx변수에 할당  -->
<script src="./js/productInsert.js"></script>



<div class="container" style="margin-top:30px">
  <div class="row">
   
    <div class="col-sm-12 text-left">
	
	 <div class="col-sm-12">
      <h1 class="text-center">상품 등록</h1>
         
         
         <form name="prodF" id="prodF" action="prodInsert.do" method="POST"
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
                     <input type="file" name="pimage"  placeholder="Attach File" class="form-control"><br> 
                   	 <input type="file" name="pimage2" placeholder="Attach File" class="form-control"><br> 
                   	 <input type="file" name="pimage3" placeholder="Attach File" class="form-control"><br> 
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
	
		
		
<jsp:include page="/foot.jsp"/>