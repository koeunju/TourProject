<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/top"/>
<script>
    function goWrite(contentId) {
        location.href="/review/write?contentId=" + contentId;
    }
</script>
<input type="text" name="tnum" value="${contentId}">
<input type="text" name="idx" value="${idx}">
<button class="btn btn-primary" onclick="goWrite(${contentId})">리뷰 작성</button>
<c:import url="/foot"/>

