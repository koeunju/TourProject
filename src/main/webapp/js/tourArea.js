let pstart = 1;
$(function () {
    $('#btnKeyword').click((e) => {
        e.preventDefault();
        let cat1 = $('#cat1').val();
        let contentTypeId = $('#contentTypeId').val();
        let areaCode = $('#areaCode').val();
        let sigunguCode = $('#sigunguCode').val();
        let cat2 = $('#cat2').val();

        let url = "/tour/areaList?areaCode=" + areaCode + "&sigunguCode=" + sigunguCode + "&contentTypeId=" + contentTypeId + "&cat1=" + cat1 + "&cat2=" + cat2 + "&pageNo=" + pstart;
        send(url, areaCode, sigunguCode, contentTypeId, cat1, cat2, 1);
    })
})

function send(url, areaCode, sigunguCode, contentTypeId, cat1, cat2, pageNo) {
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

        showList(items, total);
        showPage(pages, display, pageNo)
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showPage(total, display, pageNo) { // 페이징 처리

    if (pageNo === undefined) {
        pageNo = 1;
    }

    let totalCount = Math.ceil((total - 1) / display);
    let pagingBlock = 5;
    let prevBlock = Math.floor((pageNo - 1) / pagingBlock) * pagingBlock;
    let nextBlock = prevBlock + (pagingBlock + 1);

    let str = "<ul class='pagination justify-content-center'>";

    let start0 = (prevBlock - 1) * display + 1;
    if (prevBlock > 0) {
        str += "<li class='page-item'>";
        str += "<a class='page-link' href='#tourTable' onclick='fetch(\"" + keyword + "\", " + start0 + "," + prevBlock + ")'>Prev</a>";
        str += "</li>";
    }
    for (let i = prevBlock + 1; i < nextBlock && i <= totalCount; i++) {
        let css = "";
        if (i == pageNo) {
            css = "active";
        } else {
            css = "";
        }
        let start = (i - 1) * display + 1;
        str += "<li class='page-item " + css + "'>";
        str += "<a class='page-link' href='#tourTable' onclick='fetch(\"" + keyword + "\", " + start + "," + i + ")' id='page" + pageNo + "'>";
        str += i;
        str += "</a>";
        str += "</li>";
    }
    if (nextBlock <= totalCount) {
        let start1 = (nextBlock - 1) * display + 1;
        str += "<li class='page-item'>";
        str += "<a class='page-link' href='#tourTable' onclick='fetch(\"" + keyword + "\", " + start1 + "," + nextBlock + ")'>Next</a>";
        str += "</li>";
    }
    str += "</ul>";
    $('div.page').html(str);
}

function fetch(start, areaCode, sigunguCode, contentTypeId, cat1, cat2, pageNo) { // 페이지
    let url = "/tour/areaList?areaCode=" + areaCode + "&sigunguCode=" + sigunguCode + "&contentTypeId=" + contentTypeId + "&cat1=" + cat1 + "&cat2=" + cat2 + "&pageNo=" + pstart;
    send(url, areaCode, sigunguCode, contentTypeId, cat1, cat2, pageNo);
}

function showList(items, total) { // 검색결과 1
    let str = "<h2> 검색 결과: " + total + "개</h2>"
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
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='../image/noimage.png' style='width:200px;height: 200px'></a>";
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
    str += "<div class='page'></div>"  // 5개당 블록처리 + 중앙에 오게 만들기
    $('#openAPI').html(str)
}
/*
function send2(item, keyword, pageNo) { // 특정 키워드 검색시
    $.ajax({
        type: 'get',
        url: "/tour/keywordList?keyword=" + keyword + "&pageNo=" + pageNo,
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
            str += "<a href='#' onclick='Detail(" + contentId + ")'><img src='../image/noimage.png' style='width:200px;height: 200px'></a>";
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

function send3(keyword, pageNo) { // 검색 결과 없을시
    $.ajax({
        type: 'get',
        url: "/tour/keywordList?keyword=" + keyword + "&pageNo=" + pageNo,
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
    let str = "";
    if (keyword == "장민규" || keyword == "정성모" || keyword == "지윤성" || keyword == "천은지" || keyword == "고은주") {
        str += "<h1> 이스터 에그 발견 </h1>";
        str += "<hr>"
        str += "<h3> 성모야 꽃길만 걷자팀 </h3>"
        str += "<h3>장민규 :  우리팀 모두 수고했고 사랑한다!</h3>"
        str += "<img src='../easterEgg/jys.jpg' style='width: 30%'>"
    } else {
        str += "<h2>" + keyword + " 검색 결과가 없습니다</h2>"
        str += "<h3>다른 키워드로 검색해주세요</h3>";
    }
    $('#openAPI').html(str)
}

function Detail(contentId) { // 디테일
    $.ajax({
        type: 'get',
        url: '/tour/detail?contentId=' + contentId,
        dataType: 'json',
        cache: 'false'
    }).done((res) => {
        let dtItems = res.response.body.items;
        sendImg(dtItems, contentId);
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}
*/
/*
function sendImg(dtItems, contentId) { // 사진
    $.ajax({
        type: 'get',
        url: '/tour/img?contentId=' + contentId,
        dataType: 'json',
        cache: 'false'
    }).done((res) => {
        let imgItems = res.response.body.items.item;
        let imgItem = res.response.body.item;
        let imgs = res.response.body.totalCount;

        if (imgs === 1) {
            sendImg2(dtItems, imgItem, contentId);
        } else {
            showDt(dtItems, imgItems, contentId);
        }
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })
}

function showDt(dtItems, imgItems, contentId) { // 디테일
    let str = "";

    $.each(dtItems, (i, detail) => {

        let title = detail.title;
        let overview = detail.overview;
        let addr1 = detail.addr1;
        let addr2 = detail.addr2;
        let homepage = detail.homepage;
        let tel = detail.tel;
        let contentTypeId = detail.contenttypeid;
        let mapx = detail.mapx;
        let mapy = detail.mapy;

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

        str += "<h2>" + title + "</h2>";
        str += "<hr>";

        $.each(imgItems, (j, img) => { // 이미지
            let image = img.originimgurl;

            str += "<img src='" + image + "' style='width: 200px;height: 150px'>"
            // 캐롯셀 적용 해야함
        })

        str += "<h4> " + overview + "</h4>";

        str += "<p></p>";

        // 카카오 지도 API
        str += "<div id='map' style='width: 100%;height: 300px'> 지도 </div>";
        str += "<script src='//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411'></script>";
        str += "<script>";
        str += "var mapContainer = document.getElementById('map'),"; // 지도를 표시할 div
        str += "    mapOption = { ";
        str += "        center: new kakao.maps.LatLng(" + mapy + "," + mapx + "),"; // 지도의 중심좌표
        str += "        level: 3 "; // 지도의 확대 레벨
        str += "    }; ";
        str += "var map = new kakao.maps.Map(mapContainer, mapOption);"; // 지도를 생성합니다

        // 마커가 표시될 위치입니다
        str += "var markerPosition = new kakao.maps.LatLng(" + mapy + "," + mapx + ");";

        // 마커를 생성합니다
        str += "var marker = new kakao.maps.Marker({";
        str += "    position: markerPosition ";
        str += "}); ";

        // 마커가 지도 위에 표시되도록 설정합니다
        str += "marker.setMap(map);";
        str += "</script>";

        str += "<p></p>";

        str += "<table class='table'> <b>상세 정보</b>";
        str += "<tr>";
        if (homepage == null) {
            str += "<th> 홈페이지: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>";
        } else {
            str += "<th> 홈페이지: </th>";
            str += "<td>" + homepage + "</td>";
        }
        if (addr1 == null) {
            str += "<th> 주소: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>"
        } else {
            str += "<th> 주소: </th>";
            str += "<td>" + addr1;
        }
        if (addr2 == null) {
            str += "</td>";
        } else {
            str += addr2 + "</td>";
        }
        str += "<tr>";
        if (tel == null) {
            str += "<th> 연락처: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>";
        } else {
            str += "<th> 연락처: </th>";
            str += "<td>" + tel + "</td>";
        }
        str += "<th> 장르: </th>";
        str += "<td>" + contentTypeId + "</td>";
        str += "</tr>";
    })
    str += "</tr>";
    str += "</table>";

    str += "<button class='btn btn-primary' onclick='review(" + contentId + ")'>리뷰 보러 가기</button>";
    //str += "<button class='btn btn-primary'><a href='"+contextPath+"/review/list?contentId="+contentId+"'>리뷰 보러 가기</a></button>";
    str += "&nbsp;&nbsp;&nbsp;&nbsp;";
    str += "<button class='btn btn-success' onclick='saveInfo(" + contentId + ")'>여행지 저장</button>";
    str += "&nbsp;&nbsp;&nbsp;&nbsp;";
    str += "<button class='btn btn-info' onclick='rollBack()'>돌아가기</button>";

    $('#openAPI').html(str)
}*/
/*
function sendImg2(dtItems, imgItem, contentId) {
    $.ajax({
        type: 'get',
        url: '/tour/img?contentId=' + contentId,
        dataType: 'json',
        cache: 'false'
    }).done((res) => {
        let imgItem = res.response.body.items;
        showDt2(dtItems, imgItem, contentId);
    }).fail((err) => {
        alert('error: ' + err.status)
        console.dir(err)
    })

}

function showDt2(dtItems, imgItem, contentId) { // 디테일
    let str = "";

    $.each(dtItems, (i, detail) => {

        let title = detail.title;
        let overview = detail.overview;
        let addr1 = detail.addr1;
        let addr2 = detail.addr2;
        let homepage = detail.homepage;
        let tel = detail.tel;
        let contentTypeId = detail.contenttypeid;
        let mapx = detail.mapx;
        let mapy = detail.mapy;

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

        str += "<h2>" + title + "</h2>";
        str += "<hr>";

        $.each(imgItem, (j, img) => { // 이미지
            let image = img.originimgurl;

            str += "<img src='" + image + "' style='width: 200px;height: 150px'>"
            // 캐롯셀 적용 해야함
        })

        str += "<h4> " + overview + "</h4>";

        str += "<p></p>";

        // 카카오 지도 API
        str += "<div id='map' style='width: 100%;height: 300px'> 지도 </div>";
        str += "<script src='//dapi.kakao.com/v2/maps/sdk.js?appkey=11a513c1218b7224c39835d20851c411'></script>";
        str += "<script>";
        str += "var mapContainer = document.getElementById('map'),"; // 지도를 표시할 div
        str += "    mapOption = { ";
        str += "        center: new kakao.maps.LatLng(" + mapy + "," + mapx + "),"; // 지도의 중심좌표
        str += "        level: 3 "; // 지도의 확대 레벨
        str += "    }; ";
        str += "var map = new kakao.maps.Map(mapContainer, mapOption);"; // 지도를 생성합니다

        // 마커가 표시될 위치입니다
        str += "var markerPosition = new kakao.maps.LatLng(" + mapy + "," + mapx + ");";

        // 마커를 생성합니다
        str += "var marker = new kakao.maps.Marker({";
        str += "    position: markerPosition ";
        str += "}); ";

        // 마커가 지도 위에 표시되도록 설정합니다
        str += "marker.setMap(map);";
        str += "</script>";

        str += "<p></p>";

        str += "<table class='table'> <b>상세 정보</b>";
        str += "<tr>";
        if (homepage == null) {
            str += "<th> 홈페이지: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>";
        } else {
            str += "<th> 홈페이지: </th>";
            str += "<td>" + homepage + "</td>";
        }
        if (addr1 == null) {
            str += "<th> 주소: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>"
        } else {
            str += "<th> 주소: </th>";
            str += "<td>" + addr1;
        }
        if (addr2 == null) {
            str += "</td>";
        } else {
            str += addr2 + "</td>";
        }
        str += "<tr>";
        if (tel == null) {
            str += "<th> 연락처: </th>";
            str += "<td> 등록된 정보가 없습니다 </td>";
        } else {
            str += "<th> 연락처: </th>";
            str += "<td>" + tel + "</td>";
        }
        str += "<th> 장르: </th>";
        str += "<td>" + contentTypeId + "</td>";
        str += "</tr>";
    })
    str += "</tr>";
    str += "</table>";

    str += "<button class='btn btn-primary' onclick='review(" + contentId + ")'>리뷰 보러 가기</button>";
    str += "&nbsp;&nbsp;&nbsp;&nbsp;";
    str += "<button class='btn btn-success' onclick='saveInfo(" + contentId + ")'>여행지 저장</button>";
    str += "&nbsp;&nbsp;&nbsp;&nbsp;";
    str += "<button class='btn btn-info' onclick='rollBack()'>돌아가기</button>";

    $('#openAPI').html(str)
}
*//*
function rollBack() {
    alert(기능구현중);
}

function review(contentId) {
    location.href = contextPath + '/review/list?contentId=' + contentId;
}

function saveInfo(contentId) {
    location.href = '/tour/save?contentId=' + contentId;
}
*/