package com.t4er.admin.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import lombok.Data;

@Data
public class AdminPagingVO {

    private int cpage;
    private int pageSize;
    private int totalCount;
    private int pageCount;

    private int start;
    private int end;

    private int pagingBlock = 5;
    private int prevBlock;
    private int nextBlock;

    private String findType;
    private String findKeyword;

    public void init(HttpSession ses) {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (pageSize == 0) {
            Integer ps = (Integer) ses.getAttribute("pageSize");
            if (ps == null) {
                pageSize = 10;
            } else {
                pageSize = ps;
            }
        }
        ses.setAttribute("pageSize", pageSize);
        pageCount = (totalCount - 1) / pageSize + 1;
        if (cpage <= 0) {
            cpage = 1;
        }
        if (cpage > pageCount) {
            cpage = pageCount;
        }
        start = (cpage - 1) * pageSize;
        end = start + (pageSize + 1);

        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);
    }

    public String getPageNavi(String myctx, String loc, String userAgent) {
        findType = (findType == null) ? "" : findType;

        if (findKeyword == null) {
            findKeyword = "";
        } else {
            if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {
                //IE
                try {
                    findKeyword = URLEncoder.encode(findKeyword, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }


        String qStr = "?pageSize=" + pageSize + "&findType=" + findType + "&findKeyword=" + findKeyword;
        StringBuilder buf = new StringBuilder();
        buf.append("<ul class='pagination justify-content-center'>");
        if (prevBlock > 0) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + prevBlock + "'>");
            buf.append("Prev")
                    .append("</a></li>");
        }

        for (int i = prevBlock + 1; i <= nextBlock - 1 && i <= pageCount; i++) {
            String css = "";
            if (i == cpage) {
                css = "active";
            } else {
                css = "";
            }
            buf.append("<li class='page-item " + css + "'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + i + "'>");
            buf.append(i)
                    .append("</a></li>");
        }

        if (nextBlock <= pageCount) {
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + nextBlock + "'>");
            buf.append("Next")
                    .append("</a></li>");
        }
        buf.append("</ul>");
        return buf.toString();
    }
}



/*sss*/







