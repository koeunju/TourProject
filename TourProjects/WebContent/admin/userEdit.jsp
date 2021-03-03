<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/top.jsp"/>

<div class="m-5 p-3 text-center" style="border:1px solid gray; border-radius:15px" id="font2">

    <c:set var="myctx" value="${pageContext.request.contextPath}"/>
    <!-- top -->
    <div class="container" style="margin-top:30px">

        <div class="col-sm-12 text-center">
            <h1 class="text-center">회원 정보 수정 </h1>
            <!--  파일 업로드시. 메소드는 post, 인코딩타입은 multipart/form-data 로 주어야함-->
            <form name="userF" id="userF" action="userEditEnd.do"
                  method="post" enctype="multipart/form-data">

                <table class="table table-bordered">
                    <tr>
                        <th style="width:20%">회원번호</th>
                        <td style="width:80%">
                            <input type="text" name="idx"
                                   value="<c:out value='${user.idx}'/>"
                                   id="idx" placeholder="Idx" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">이름</th>
                        <td style="width:80%">
                            <input type="text" name="name"
                                   value="<c:out value='${user.name}'/>"
                                   id="name" placeholder="Name" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">아이디</th>
                        <td style="width:80%">
                            <input type="text" name="id"
                                   value="<c:out value='${user.id}'/>"
                                   id="id" placeholder="Id" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">비밀번호</th>
                        <td style="width:80%">
                            <input type="password"
                                   name="pwd" id="pwd" placeholder="Password" class="form-control">
                        </td>
                    </tr>

                    <tr>
                        <th style="width:20%">연락처</th>
                        <td style="width:80%">
                            <input type="text" name="tel"
                                   value="<c:out value='${user.tel}'/>"
                                   id="tel" placeholder="Tel" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th style="width:20%">이메일</th>
                        <td style="width:80%">
                            <input type="text" name="email"
                                   value="<c:out value='${user.email}'/>"
                                   id="email" placeholder="Email" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            <button class="btn btn-success" id="btnWrite">정보수정</button>
                            <button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/foot.jsp"/>

<script type="text/javascript">

    $(function () {
        $('#btnWrite').on('click', function (e) {
            e.preventDefault();
            var $name = $('#name');
            var $id = $('#id');
            var $pwd = $('#pwd');
            var $tel = $('#tel');
            var $email = $('#email');

            if (!$name.val()) {
                alert('이름을 입력하세요');
                $name.focus();
                return;
            }
            if (!$id.val()) {
                alert('아이디를 입력하세요');
                $id.focus();
                return;
            }
            if (!$pwd.val()) {
                alert('비밀번호를 입력하세요');
                $pwd.focus();
                return;
            }
            if (!$tel.val()) {
                alert('전화번호를 입력하세요');
                $tel.focus();
                return;
            }
            if (!$email.val()) {
                alert('이메일을 입력하세요');
                $email.focus();
                return;
            }

            $('#userF').submit();
        })
    })

</script>

	
		
	

