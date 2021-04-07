package com.t4er.tour.controller;

import com.t4er.tour.model.TourVO;
import com.t4er.tour.service.TourService;
import com.t4er.user.model.UserVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@Log4j
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/keywordSearch")
    public String TourKeywordSearch(Model m) {

        return "tour/tourKeyword";
    }

    @GetMapping("/areaSearch")
    public String TourAreaSearch(Model m) {

        return "tour/tourArea";
    }

    @GetMapping("/areaList")
    public String TourAPIAreacode(Model m, @RequestParam String areaCode, String sigunguCode, @RequestParam String cat1,
                                  @RequestParam String contentTypeId, String cat2, @RequestParam String pageNo) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8")); /*지역코드*/
        urlBuilder.append("&" + URLEncoder.encode("sigunguCode", "UTF-8") + "=" + URLEncoder.encode(sigunguCode, "UTF-8")); /*시군구코드(areaCode 필수)*/
        urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(contentTypeId, "UTF-8")); /*관광타입(관광지, 숙박 등) ID*/
        urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode(cat1, "UTF-8")); /*대분류 코드*/
        urlBuilder.append("&" + URLEncoder.encode("cat2", "UTF-8") + "=" + URLEncoder.encode(cat2, "UTF-8")); /*중분류 코드(cat1필수)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND (안드로이드),WIN (원도우폰), ETC*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        m.addAttribute("tourArea", sb.toString());

        return "tour/tourAreaList";
    }

    @GetMapping("/keywordList")
    public String TourAPIKeyword(Model m, @RequestParam String keyword, @RequestParam String pageNo) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*검색 요청할 키워드 (국문=인코딩 필요)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*현재 페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND(안드로이드), ETC*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        m.addAttribute("tourKeyword", sb.toString());

        return "tour/tourKeywordList";
    }

    @GetMapping("/detail")
    public String TourAPIDetail(Model m, @RequestParam String contentId) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8")); /*콘텐츠ID*/
        urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*기본정보 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*원본, 썸네일 대표이미지 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("areacodeYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*지역코드, 시군구코드 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("catcodeYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*대,중,소분류코드 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*주소, 상세주소 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*좌표 X,Y 조회여부*/
        urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*콘텐츠 개요 조회여부*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        m.addAttribute("tourDetail", sb.toString());
        m.addAttribute("contentId", contentId);

        return "tour/tourDetail";
    }

    @GetMapping("/img")
    public String TourAPIImage(Model m, @RequestParam String contentId) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(원도우폰),ETC*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + URLEncoder.encode(contentId, "UTF-8")); /*콘텐츠 ID*/
        urlBuilder.append("&" + URLEncoder.encode("imageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*Y=콘텐츠 이미지 조회, N='음식점'타입의 음식메뉴 이미지*/
        urlBuilder.append("&" + URLEncoder.encode("subImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*Y=원본,썸네일 이미지 조회 N=Null*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        m.addAttribute("tourImg", sb.toString());
        m.addAttribute("contentId", contentId);

        return "tour/tourImg";
    }

    @GetMapping("/save")
    public String tourSave(Model m, HttpServletRequest req,
                           @RequestParam String contentId,
                           @RequestParam String title) {
        HttpSession ses = req.getSession();
        UserVO user = (UserVO) ses.getAttribute("loginUser");

        int idx = user.getIdx();

        log.info("tourSave contentId = " + contentId);
        log.info("tourSave idx = " + idx);
        log.info("tourSave title = " + title);

        m.addAttribute("contentId", contentId);
        m.addAttribute("title", title);
        m.addAttribute("idx", idx);

        return "tour/tourSave";
    }

    @PostMapping("/save")
    public String saveInsert(Model m, HttpServletRequest req,
                             @ModelAttribute("tvo") TourVO tvo) {

        int n = tourService.insertSaveTour(tvo);

        String str = (n > 0) ? "저장 성공" : "저장 실패";
        String loc = "/index";

        m.addAttribute("msg", str);
        m.addAttribute("loc", loc);

        return "message";
    }
}
