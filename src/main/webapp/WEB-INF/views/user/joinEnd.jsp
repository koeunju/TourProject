<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="/top" />
<div>
<form name="chkMail" action="chkMail" method="post">
<h3>회원가입이 완료되었습니다.</h3><br>
<h3>이메일 인증을 해주세요.</h3>
<h3>메일 확인이 불가능할 경우 이메일 인증하기를 다시 시도해주세요.</h3>
<button type="submit" class="btn btn-info">이메일 재인증 하기</button>
</form>
</div>


<c:import url="/foot" />