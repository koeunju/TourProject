$(function () {
    $('#btnKeyword').click((e) => {
        e.preventDefault();
        var keyword = $('#keyword').val();
        if (!keyword) {
            alert('키워드를 입력하세요');
            $('#keyword').focus();
            return;
        }
        send(keyword);
    })
})

function send(keyword) {
    $.ajax({
        type: 'get',
        url: "tourSearch.do",
        data: {keyword: keyword},
        dataType: 'json',
        contentType: "application/json;charset:UTF-8",
        cache: false
    }).done(function (data) {
        alert(data)
        showList(data.items, keyword);
    }).fail(function (err) {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showList(items, keyword) {
    var str = "<h2>" + keyword + "</h2>"
    str += "<div class='pagination' style='text-align: center'></div>"
    str += "<table border='1' id='tourTable'>"
    str += "<tr>"
    $.each(items, function (i, tour) {
        str += "<td width='20%'>";
        str += "<h4>" + tour.title + "</h4>";
        str += "</td>";
        str += "</tr>"
        str += "</table>"
        $('#openAPI').html(str)
    })
}
