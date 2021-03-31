<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/top"/>

<form name="saveF" id="saveF" action="save" method="post">
    <input type="text" name="contentId" value="${contentId}">
    <input type="text" name="idx" value="${idx}">
    <button>여행지 저장</button>
</form>

<c:import url="/foot"/>