$(function () {
    $('#reviewF').on('submit', () => {
        if(!$('#star').val()) {
            alert('별점을 입력해주세요');
            $('#star').focus();
            return false;
        }
        if(!$('#content').val()) {
            alert('리뷰를 적어주세요');
            $('#content').focus();
            return false;
        }
        return true;
    })
})