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
        url: "tourList.do?keyword=" + keyword,
        dataType: 'json',
        cache: false
    }).done(function (data) {
        let items =data.response.body.items.item;
        showList(items, keyword);
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
        let contentTypeId = tour.contenttypeid;

        if (contentTypeId == 12) {
            contentTypeId = "관광지";
        }else if (contentTypeId == 14) {
            contentTypeId = "문화시설";
        }else if (contentTypeId == 15) {
            contentTypeId = "행사/공연/축제";
        }else if (contentTypeId == 25) {
            contentTypeId = "여행코스";
        }else if (contentTypeId == 28) {
            contentTypeId = "레포츠";
        }else if (contentTypeId == 32) {
            contentTypeId = "숙박";
        }else if (contentTypeId == 38) {
            contentTypeId = "쇼핑";
        }else if (contentTypeId == 39) {
            contentTypeId = "음식점";
        }
        str += "<td width='20%'>";
        str += "<h4><a href=tourDt.do>" + tour.title + "</a></h4>";
        str += "<h4>" + contentTypeId + "</h4>";
        str += "<img src='"+tour.firstimage+"' style='width:20%'>"
        str += "</td>";

        str += "</tr>"
    })
    str += "</table>"
    $('#openAPI').html(str)

}