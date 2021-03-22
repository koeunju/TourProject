$(function () {
    $('#btnWrite').on('click', function (e) {
        e.preventDefault();
        var $pname = $('#pname');
        var $pcontent = $('#pcontent');
        var $price = $('#price');

        if (!$pname.val()) {
            alert('상품명을 입력하세요');
            $pname.focus();
            return;
        }
        if (!$pcontent.val()) {
            alert('상품설명을 입력하세요');
            $pcontent.focus();
            return;
        }
        if (!$price.val()) {
            alert('상품판매가를 입력하세요');
            $price.focus();
            return;
        }

        $('#boardF').submit();
    })
})