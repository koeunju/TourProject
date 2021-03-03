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
    }).done((data) => {
        let items = data.response.body.items.item; // 여행장소 정보 값
        let item = data.response.body.items; // 특정 키워드 검색시 정보 값
        let pages = data.response.body.totalCount; // 검색 결과 값
        let display = data.response.body.numOfRows; // 페이지당 보여줄 개수
        let total = parseInt(pages);
        
        if(items == null) {
            send3(keyword); // 검색 결과 없을 경우
        }

        if (items.title != null) {
            send2(item, keyword); // 특정 키워드 검색시
        } else {
            showList(items, item, total, keyword);
            showPage(pages, keyword, display)
        }
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showPage(total, keyword, display) { // 페이징 처리
    let totalCount = Math.ceil((total - 1) / display);
    let str = "";
    let end;
    let start;
    for (let i = 1; i <= totalCount; i++) {
        let pageNo = i;
        str += "<a href='#tourTable' onclick='fetch(\"" + keyword + "\", " + pageNo + ")'>";
        str += "[" + i + "]";
        str += "</a>";
    }
    $('div.pagination').html(str);
}

function fetch(keyword, pageNo) { // 페이지 
    let url = "tourList.do?keyword=" + keyword + "&pageNo=" + pageNo;
    send(url, keyword);
}

function showList(items, item, total, keyword) { // 검색결과 1
    let str = "<h2>" + keyword + " 검색 결과: " + total + "개</h2>"
    str += "<table class='table table-hover' id='tourTable'>"
    str += "<tr>"
    $.each(items, (i, tour) => {
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

        if (img == null && title != null) {
            str += "<td rowspan='3'>";
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='./image/noimage.png' style='width:200px;height: 200px'></a>";
            str += "</td>";
        } else if (img != null && title != null) {
            str += "<td rowspan='3'>";
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='" + img + "' style='width:200px;height: 200px'></a>";
            str += "</td>";
        }

        str += "<th> 제목 </th>";
        str += "<td>";
        str += "<a href='#' onclick='Detail(" + contentId + ")'>" + title + "</a>";
        str += "</td>";

        if (addr1 == null) {
            str += "<tr>";
            str += "<th> 주소 </th>";
            str += "<td></td>";
            str += "</tr>";
        } else {
            str += "<tr>";
            str += "<th> 주소 </th>";
            str += "<td>" + addr1 + "</td>";
            str += "</tr>";
        }

        if (contentId == null) {
            str += "<tr>";
            str += "<th> 장르 </th>";
            str += "<td></td>";
            str += "</tr>";
        } else {
            str += "<tr>";
            str += "<th> 장르 </th>";
            str += "<td>" + contentTypeId + "</td>";
            str += "</tr>";
        }
        str += "</tr>"
    })
    str += "</table>"
    str += "<h3 style='text-align: center'><div class='pagination'></div></h3>"  // 5개당 블록처리 + 중앙에 오게 만들기
    $('#openAPI').html(str)
}

function send2(item, keyword) { // 특정 키워드 검색시
    $.ajax({
        type: 'get',
        url: "tourList.do?keyword=" + keyword,
        dataType: 'json',
        cache: false
    }).done((data) => {
        let item = data.response.body.items;
        showList2(item, keyword);
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showList2(item, keyword) { // 특정 키워드 검색시 리스트
    let str = "<h2>" + keyword + " 검색 결과</h2>"
    str += "<table class='table table-hover' id='tourTable'>"
    str += "<tr>"
    $.each(item, (i, tour) => {

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

        if (img == null) {
            str += "<td rowspan='3'>";
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='./image/noimage.png' style='width:200px;height: 200px'></a>";
            str += "</td>";
        } else {
            str += "<td rowspan='3'>";
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='" + img + "' style='width:200px;height: 200px'></a>";
            str += "</td>";
        }

        str += "<th> 제목 </th>";
        str += "<td>";
        str += "<a href='#' onclick='Detail(" + contentId + ")'>" + title + "</a>";
        str += "</td>";

        if (addr1 == null) {
            str += "<tr>";
            str += "<th> 주소 </th>";
            str += "<td></td>";
            str += "</tr>";
        } else {
            str += "<tr>";
            str += "<th> 주소 </th>";
            str += "<td>" + addr1 + "</td>";
            str += "</tr>";
        }

        if (contentId == null) {
            str += "<tr>";
            str += "<th> 장르 </th>";
            str += "<td></td>";
            str += "</tr>";
        } else {
            str += "<tr>";
            str += "<th> 장르 </th>";
            str += "<td>" + contentTypeId + "</td>";
            str += "</tr>";
        }
        str += "</tr>"
    })
    str += "</table>"
    $('#openAPI').html(str)
}

function send3(keyword) { // 검색 결과 없을시
    $.ajax({
        type: 'get',
        url: "tourList.do?keyword=" + keyword,
        dataType: 'json',
        cache: false
    }).done((data) => {
        showList3(keyword);
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showList3(keyword) { // 검색 결과 없을시 리스트
    let str = "<h2>" + keyword + " 검색 결과가 없습니다</h2>"
    str += "<h3>다른 키워드로 검색해주세요</h3>";
    $('#openAPI').html(str)
}

function Detail(contentId) { // 디테일
    $.ajax({
        type: 'get',
        url: 'tourDt.do?contentid=' + contentId,
        dataType: 'json',
        cache: 'false'
    }).done((res) => {
        let dtItems = res.response.body.items;
        showDt(dtItems, contentId);
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showDt(dtItems, contentId) { // 디테일
    let str = "";
    // str += "<table border='1' id='detailTable'>"
    // str += "<tr>"
    $.each(dtItems, (i, detail) => {

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
        if (addr1 == null) {
            addr1 = "";
        } else {
            str += "<h4> 주소: " + addr1;
        }
        if (addr2 == null) {
            str += "</h4>";
            addr2 = "";
        } else {
            str += addr2 + "</h4>";
        }
        if (tel == null) {
            tel = "";
        } else {
            str += "<h4> 연락처: " + tel + "</h4>";
        }

        // str += "<td width='20%'>";
        // str += "</td>";
        // str += "</tr>"
    })
    // str += "</table>"
    $('#openAPI').html(str)
}
