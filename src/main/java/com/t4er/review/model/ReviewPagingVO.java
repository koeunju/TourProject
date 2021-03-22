package com.t4er.review.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Log4j
public class ReviewPagingVO {

    private Integer contentId;

    private int cpage;
    private int pageSize;
    private int totalCount;
    private int pageCount;

    private int start;
    private int end;

    private int pagingBlock = 5;
    private int prevBlock;
    private int nextBlock;

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
        ses.setAttribute("pageize", pageSize);

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

    public String getPageNavi(String myctx, String loc, Integer contentId) {

        log.info("contentId = " + contentId);
        // log.info("contentId = " + contentId);

        String qStr = "?contentId=" + contentId + "&pageSize=" + pageSize;

        StringBuilder buf = new StringBuilder();
        buf.append("<ul class='pagination justify-content-center'>");
        if (prevBlock > 0) {
            // 이전 n개
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
            // 이후 n개
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + nextBlock + "'>");
            buf.append("Next")
                    .append("</a></li>");
        }
        buf.append("</ul>");

        return buf.toString();
    }
}
