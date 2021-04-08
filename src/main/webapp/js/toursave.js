
function Detail(contentId) { // 디테일
alert('test');
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
	/*alert(contentId)
	str += "<form action=" + contextPath + "'/review/list?contentId='" + contentId + " method='get'>"
	str += ' <input type="text" name="contentId" value="' + contentId + '">'
	str += "<button class='btn btn-primary'>리뷰 보러 가기</button>";
	str += "</form>"*/

	str += "<button class='btn btn-primary' onclick='review(" + contentId + ")'>리뷰 보러 가기</button>";
	//str += "<button class='btn btn-primary'><a href='"+contextPath+"/review/list?contentId="+contentId+"'>리뷰 보러 가기</a></button>";
	str += "&nbsp;&nbsp;&nbsp;&nbsp;";
	str += "<button class='btn btn-success' onclick='noSaveInfo(" + contentId + ")'>여행지 삭제</button>";
	str += "&nbsp;&nbsp;&nbsp;&nbsp;";
	str += "<button class='btn btn-info' onclick='javascript:historyback()'>돌아가기</button>";

	$('#openAPI').html(str)
}

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

	/*let tnum = contentId;
	str += "<form action=" + contextPath + "'/review/list?contentId='" + tnum + ">"
	str += ' <input type="text" name="tnum" value="' + tnum + '">'
	str += "<button class='btn btn-primary'>리뷰 보러 가기</button>";
	str += "</form>"*/

	str += "<button class='btn btn-primary' onclick='review(" + contentId + ")'>리뷰 보러 가기</button>";
	str += "&nbsp;&nbsp;&nbsp;&nbsp;";
	str += "<button class='btn btn-success' onclick='saveInfo(" + contentId + ")'>여행지 저장</button>";
	str += "&nbsp;&nbsp;&nbsp;&nbsp;";
	str += "<button class='btn btn-info' onclick='rollBack()'>돌아가기</button>";

	$('#openAPI').html(str)
}

function review(contentId) {
	location.href = contextPath + '/review/list?contentId=' + contentId;
}