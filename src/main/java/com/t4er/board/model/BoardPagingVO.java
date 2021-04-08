package com.t4er.board.model;

import lombok.Data;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
public class BoardPagingVO {
    // 페이징 처리
    private int cpage; // 현재 보여줄 페이지 번호
    private int pageSize; // 한 페이지 당 보여줄 목록 개수
    private int totalCount; // 총 게시글 수
    private int pageCount; // 페이지 수

    private Integer idx;
    private int start;
    private int end;

    //페이징 블럭
    private int pagingBlock = 5; // 보여줄 페이지 수
    private int prevBlock; // 이전
    private int nextBlock; // 이후

    //검색 관련
    private String findType; // 검색유형
    private String findKeyword; // 검색어

    //페이징 메소드
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

        // 페이징 블럭 연산
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
            //이전
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + prevBlock + "'>");
            buf.append("Prev").append("</a></li>");
        }//if----------------------

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
        }//for------------------

        if (nextBlock <= pageCount) {
            //이후
            buf.append("<li class='page-item'>")
                    .append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + nextBlock + "'>");
            buf.append("Next").append("</a></li>");
        }//if----------------------
        buf.append("</ul>");

        return buf.toString();
    }
    
    public String getPageNavi(String myctx, String loc, Integer idx) {

		String qStr = "?idx=" + idx + "&pageSize=" + pageSize ;
		

		StringBuilder buf = new StringBuilder();
		buf.append("<ul class='pagination justify-content-center'>");
		if (prevBlock > 0) {
			// 이전 n개
			buf.append("<li class='page-item'>")
					.append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + prevBlock + "'>");
			buf.append("Prev").append("</a></li>");
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
			buf.append(i).append("</a></li>");
		}

		if (nextBlock <= pageCount) {
			// 이후 n개
			buf.append("<li class='page-item'>")
					.append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + nextBlock + "'>");
			buf.append("Next").append("</a></li>");
		}
		buf.append("</ul>");

		return buf.toString();
	}
}
