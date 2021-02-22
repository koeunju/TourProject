package tour.api;


import tour.model.TourVO;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TourAPIKeyword {

    public String TourAPIKeyword(String keyword) throws IOException {
        System.out.println("keyword2= " + keyword);

        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*검색 요청할 키워드 (국문=인코딩 필요)*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND(안드로이드), ETC*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        System.out.println("sb= " + sb.toString());
        System.out.println("urlBuilder= " + urlBuilder);

        return sb.toString();
    }

    public List<TourVO> viewTour(String keyword) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=ZuZnL2Q6L4AGTl9CCEG3WVuxf%2Fs4ch23XtpWSX6jM1CsM9kvNS8JHgilZXFDJsIQLPQKvKXDLNhBrkfQtPQvtA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("keyword", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8")); /*검색 요청할 키워드 (국문=인코딩 필요)*/
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS (아이폰), AND(안드로이드), ETC*/
        urlBuilder.append("&_type=json"); // json 형식으로 받아오기

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        String result = sb.toString();

        System.out.println("sb= " + sb.toString());
        System.out.println("urlBuilder= " + urlBuilder);

        /* JSON-SIMPLE lib 이용해서 json 데이터 읽어들이기?
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
        JSONObject parse_response = (JSONObject) jsonObj.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body");// response 로 부터 body 찾아오기
        JSONObject parse_items = (JSONObject) parse_body.get("items");// body 로 부터 items 받아오기
        // items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다.
        JSONArray parse_item = (JSONArray) parse_items.get("item");

        JSONObject obj;


        for (int i = 0; i < parse_item.size(); i++) {
            obj = (JSONObject) parse_item.get(i); // 해당 item을 가져옵니다.

            String title = (String) obj.get("title");
            String addr1 = (String) obj.get("addr1");
            String addr2 = (String) obj.get("addr2");
            String tel = (String) obj.get("tel");
            int contenttypeId = (int) obj.get("contenttypeid");
            String firstImage = (String) obj.get("firstimage");
            String firstImage2 = (String) obj.get("firstimage2");
            int readCount = (int) obj.get("readcount");
            java.sql.Date createdtime = (java.sql.Date) obj.get("createdtime");
            java.sql.Date modifiedTime = (java.sql.Date) obj.get("modifiedtime");

            System.out.println(title + " : " + addr1 + " : " + addr2 + " : " + tel + " : " + contenttypeId + " : " +
                    firstImage + " : " + firstImage2 + " : " + readCount + " : " + createdtime + " : " + modifiedTime);
        }
        */


        List<TourVO> datalist = new ArrayList<TourVO>();

        return datalist;

    }
}
