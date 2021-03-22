var win = null;

function openWin() {
    win = window.open("idCheck.do", "idCheck",
        "width=400, height=400, left=100, top=100");
}

$(function () {
    $('#btnOk').on('click', function (e) {
        e.preventDefault();
        var $name = $('#name');
        var $id = $('#id');
        var $pwd = $('#pwd');
        var $pwd2 = $('#pwd2');
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
        if ($pwd.val() != $pwd2.val()) {
            alert('비밀번호가 서로 달라요');
            $pwd2.focus();
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

        $('#joinF').submit();
    })
})