<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>올랑올랑</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="" />
<meta name="author" content="" />
<link href="../css/styles.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-pp">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/index">메뉴</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<div class="input-group"></div>
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="userDropdown" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<c:if test="${loginUser eq null}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/user/login">로그인</a>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/user/join">회원가입</a>
					</c:if>
					<c:if test="${loginUser ne null}">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/user/myInfo?idx=${loginUser.idx}">마이
							페이지</a>
					</c:if>
					<c:if test="${loginUser.stat == 9 or loginUser.stat == 8}">
						<div class="dropdown-divider"></div>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/admin/adminMain">관리자 페이지</a>
					</c:if>
				</div></li>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-pp" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">::여행지::</div>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/tour/areaSearch">
							 키워드 추천
						</a> <a class="nav-link"
							href="${pageContext.request.contextPath}/tour/keywordSearch">
							 여행지 추천
						</a>
						<div class="sb-sidenav-menu-heading">::고객 광장::</div>
						<a class="nav-link collapsed" href="#" data-toggle="collapse"
							data-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							 게시판
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="#">이달의여행지</a> <a class="nav-link"
									href="/board/list?cg_num=1">자유게시판</a> <a class="nav-link"
									href="/board/list?cg_num=2">고객센터</a> <a class="nav-link"
									href="#">명예의 전당</a>
							</nav>
						</div>
						<a class="nav-link"
							href="${pageContext.request.contextPath}/point/list">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> 포인트샵
						</a>

						<div class="sb-sidenav-menu-heading">::개인 광장::</div>
						<a class="nav-link collapsed" href="#" data-toggle="collapse"
							data-target="#collapseLayouts2" aria-expanded="false"
							aria-controls="collapseLayouts">
							마이페이지
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts2"
							aria-labelledby="headingOne" data-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="${pageContext.request.contextPath}/user/myInfo?idx=${loginUser.idx}">내
									정보</a> <a class="nav-link"
									href="${pageContext.request.contextPath}/user/myTour?idx=${loginUser.idx}">찜한
									여행지</a> <a class="nav-link"
									href="${pageContext.request.contextPath}/user/write?idx=${loginUser.idx}">내가
									쓴글</a> <a class="nav-link" href="#">내 구매정보</a>
							</nav>
						</div>
								<c:if test="${loginUser.stat eq 9 or loginUser.stat eq 8 }">
						<div class="sb-sidenav-menu-heading">::관리자광장::</div>
						<a class="nav-link collapsed" href="#" data-toggle="collapse"
							data-target="#collapseLayouts3" aria-expanded="false"
							aria-controls="collapseLayouts"> 관리 </a>
						<div class="collapse" id="collapseLayouts3"
							aria-labelledby="headingOne" data-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="${pageContext.request.contextPath}/admin/userList">유저관리</a>
								<a class="nav-link"
									href="${pageContext.request.contextPath}/admin/shopList">포인트샵관리</a>
								<a class="nav-link"
									href="${pageContext.request.contextPath}/admin/orderList">구매
									정보 관리</a> <a class="nav-link" href="#">내 구매정보</a>
							</nav>
						</div>
					</c:if>
					</div>
			
				</div>



				<div class="sb-sidenav-footer">
					<div class="small">현재 로그인 :</div>
					<c:if test="${loginUser eq null}">
						게스트
						</c:if>
					<c:if test="${loginUser ne null}">
						${loginUser.nick }님 접속중
						</c:if>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">
			<main id="font2">
				<div id="title">
					<img src="../image/title1.png" style="width: 100%">
				</div>

				<div class="container" style="margin-top: 5%">