<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>올랑올랑</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
 <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark text-white" id="font1">
		<div
			class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">관리자
						페이지</a></li>
			</ul>
		</div>
		<div class="mx-auto order-0">
			<img src="image/head1.png">
		</div>
		<div
			class="navbar-collapse collapse w-100 order-1 dual-collapse2 text-white">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">로그인</a>
				</li>
				<li class="nav-item active"><a class="nav-link" href="#">회원가입</a>
				</li>
			</ul>
		</div>
	</nav>


	<div id="title">
	<img src="image/title1.png" style="width:100%">
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark"  id="font1">
		<a class="navbar-brand" href="#">Menu</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/index.do">관광지 추천</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">게시판</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">이달의 여행지</a> <a
							class="dropdown-item" href="#">자유게시판</a> <a
							class="dropdown-item" href="#">고객센터</a>
					</div></li>

				<li class="nav-item"><a class="nav-link" href="#myLoginModal"
					data-toggle="modal">포인트샵</a></li>


				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/logout.do">명예의 전당</a></li>

			</ul>
		</div>
	</nav>

    <div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">