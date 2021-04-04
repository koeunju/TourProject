<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="modal col-md-6 offset-md-3" id="background_modal" style="background:white; width:auto; height:auto; opacity:0.9;">
    <div class="modal-dialog">
	<div class="modal_contents">
	<div class="modal-body">
		<h4>
			<b>고객님의 정보와 일치하는 아이디입니다.</b><span class="close">&times;</span>
		</h4><br>
			<h3 id="id_value"></h3>
		<br>
<!-- 		<button type="button" id="pwSearch_btn" class="btn peach-gradient btn-rounded waves-effect">
		<i class="fa fa-envelope"></i>비밀번호 찾기</button> -->
	</div>
</div>
</div>
</div>
</body>
</html>
