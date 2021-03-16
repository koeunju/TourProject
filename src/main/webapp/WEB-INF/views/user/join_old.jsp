<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/top" />

<script src="./js/join.js"></script>

<h1>회원가입</h1>
<form name="joinF" id="joinF" action="joinEnd.do" method="POST">
    <table align="center">
        <tr>
            <td width="20%" class="m1"><b>이 름</b></td>
            <td width="80%" class="m2">
                <input type="text" name="name" id="name" placeholder="Name">
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>아이디</b></td>
            <td width="80%" class="m2">
                <input type="text" name="id" id="id"
                       placeholder="User ID" readonly>
                <button type="button" onclick="openWin()">아이디 확인</button>
                <br>
                <p style="font-size:12px;">* 아이디는 영문자,숫자,!,_로 4~8자 이내로 가능합니다.</p>
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>비밀번호</b></td>
            <td width="80%" class="m2">
                <input type="password" name="pwd" id="pwd" placeholder="Password">
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>비밀번호 확인</b></td>
            <td width="80%" class="m2">
                <input type="password" name="pwd2" id="pwd2"
                       placeholder="Re Password">
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>연락처</b></td>
            <td width="80%" class="m2">
                <input type="text" name="tel" id="tel" maxlength="11" placeholder="Tel">
            </td>
        </tr>
        <tr>
            <td width="20%" class="m1"><b>이메일</b></td>
            <td width="80%" class="m2">
                <input type="text" name="email" id="email" placeholder="Email">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="m1 text-center">
                <button id="btnOk">회원가입</button>
                <button type="reset">다시쓰기</button>
            </td>
        </tr>
    </table>
</form>
<c:import url="/foot" />
