let pstart = 1;
$(function () {
    $('#btnKeyword').click((e) => {
        e.preventDefault();
        let keyword = $('#keyword').val();
        if (!keyword) {
            alert('키워드를 입력하세요');
            $('#keyword').focus();
            return;
        }
        let url = "tourList.do?keyword=" + keyword + "&pageNo=" + pstart;
        send(url, keyword);
    })
})

function send(url, keyword) {
    $.ajax({
        type: 'get',
        url: url,
        dataType: 'json',
        cache: false
    }).done(function (data) {
        let items = data.response.body.items.item; // 여행장소 정보
        let pages = data.response.body.totalCount; // 검색 결과 값
        let display = data.response.body.numOfRows; // 페이지당 보여줄 개수

        let total = parseInt(pages);

        showList(items, total, keyword);
        showPage(pages, keyword, display)
    }).fail(function (err) {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showPage(total, keyword, display) {
    let totalCount = Math.ceil((total - 1) / display);
    let str = "";
    for (let i = 1; i <= totalCount; i++) {
        let pageNo = i;
        str += "<a href='#tourTable' onclick='fetch(\"" + keyword + "\", " + pageNo + ")'>";
        str += "[" + i + "]";
        str += "</a>";
    }
    $('div.pagination').html(str);
}

function fetch(keyword, pageNo) {
    let url = "tourList.do?keyword=" + keyword + "&pageNo=" + pageNo;
    send(url, keyword);
}

function showList(items, total, keyword) {
    let str = "<h2>" + keyword + " 검색 결과: " + total + "개</h2>"
    str += "<table border='1' id='tourTable'>"
    str += "<tr>"
    $.each(items, function (i, tour) {

        let title = tour.title;
        let contentId = tour.contentid;
        let contentTypeId = tour.contenttypeid;
        let addr1 = tour.addr1;
        let img = tour.firstimage

        if (contentTypeId == 12) {
            contentTypeId = "관광지";
        } else if (contentTypeId == 14) {
            contentTypeId = "문화시설";
        } else if (contentTypeId == 15) {
            contentTypeId = "행사/공연/축제";
        } else if (contentTypeId == 25) {
            contentTypeId = "여행코스";
        } else if (contentTypeId == 28) {
            contentTypeId = "레포츠";
        } else if (contentTypeId == 32) {
            contentTypeId = "숙박";
        } else if (contentTypeId == 38) {
            contentTypeId = "쇼핑";
        } else if (contentTypeId == 39) {
            contentTypeId = "음식점";
        }

        str += "<td width='20%'>";
        str += "<h3><a href='#' onclick='Detail(" + contentId + ")'>" + title + "</a></h3>";
        str += "<h4>" + addr1 + "</h4>";
        str += "<h4>" + contentTypeId + "</h4>";
        if (img == null) {
            img = ""; // noiamge로 대체
        } else {
            str += "<img src='" + img + "' style='width:20%;height: 5%'>";
        }
        str += "</td>";

        str += "</tr>"
    })
    str += "</table>"
    str += "<h3 style='text-align: center'><div class='pagination'></div></h3>"  // 5개당 블록처리 + 중앙에 오게 만들기
    $('#openAPI').html(str)
}

function Detail(contentId) {
    $.ajax({
        type: 'get',
        url: 'tourDt.do?contentid=' + contentId,
        dataType: 'json',
        cache: 'false'
    }).done(function (res) {
        let dtItems = res.response.body.items;
        showDt(dtItems, contentId);
    }).fail(function (err) {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showDt(dtItems, contentId) {
    let str = "";
    // str += "<table border='1' id='detailTable'>"
    // str += "<tr>"
    $.each(dtItems, function (i, detail) {

        let title = detail.title;
        let overview = detail.overview;
        let addr1 = detail.addr1;
        let addr2 = detail.addr2;
        let homepage = detail.homepage;
        let tel = detail.tel;

        str += "<h2>" + title + "</h2>";
        str += "<h3> 상세 정보 </h3>";
        str += "<hr>";
        str += "<h4> " + overview + "</h4>";
        if (homepage == null) {
            homepage = "";
        } else {
            str += "<h4> 홈페이지: " + homepage + "</h4>";
        }
        if(addr1 == null) {
            addr1 = "";
        } else {
            str += "<h4> 주소: " + addr1;
        }
        if(addr2 == null) {
            str += "</h4>";
            addr2 = "";
        } else {
            str +=  addr2 + "</h4>";
        }

        // str += "<td width='20%'>";
        // str += "</td>";
        // str += "</tr>"
    })
    // str += "</table>"
    $('#openAPI').html(str)
}
