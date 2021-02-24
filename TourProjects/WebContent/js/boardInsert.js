$(function(){
    $('#btnWrite').on('click', function(e){
        e.preventDefault();
        var $btitle=$('#btitle');
        var $bcontent=$('#bcontent');

        if(!$btitle.val()){
            alert('제목을 입력하세요');
            $btitle.focus();
            return;
        }
        if(!$bcontent.val()){
            alert('내용을 입력하세요');
            $bcontent.focus();
            return;
        }


        $('#boardF').submit();
    })
})