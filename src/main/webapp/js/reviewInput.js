$(() => {
    $('#reviewF').on('submit', () => {
        if(!$('#rstar').val()) {
            alert('별점을 선택해주세요');
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