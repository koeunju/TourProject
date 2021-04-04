<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Modal Dialog----------------------- -->
<div class="modal" id="myLoginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="text-primary text-center" style="margin: auto">로그인
                    페이지</h4>
                <button class="close" data-dismiss="modal">&times;</button>
            </div>
            <%-- ${cookie.키.value} --%>
            <!-- Modal Body -->
            <div class="modal-body">
                <form action="/loginEnd" method="POST">
                    <div class="form-group">
                        <label for="userid">아이디:</label>
                        <input type="text" required
                               name="userid" value="${cookie.uid.value}"
                               class="form-control" placeholder="Enter User ID"
                               id="userid">
                    </div>
                    <div class="form-group">
                        <label for="pwd">비밀번호:</label>
                        <input type="password" required
                               name="pwd" class="form-control" placeholder="Enter password"
                               id="pwd">
                    </div>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input type="checkbox"
                            <c:if test="${cookie.uid ne null}">
                                   checked
                            </c:if>
                                   name="saveId" class="form-check-input"> 아이디저장
                        </label>
                    </div>
                    <div class="form-group text-right">
                        <button type="submit"
                                class="btn btn-primary">로그인
                        </button>
                        <button class="btn btn-danger"
                                data-dismiss="modal">닫기
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!-- ----------------------------------- -->

</body>
</html>