$(function () {
    $('#tourF').on('submit', () => {
        if(!$('#keyword').val()) {
            alert('키워드 입력하세요');
            $('#keyword').focus();
            return false;
        }
        return true;
    })
})

