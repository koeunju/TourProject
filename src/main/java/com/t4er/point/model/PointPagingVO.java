package com.t4er.point.model;


import javax.servlet.http.HttpSession;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class PointPagingVO {

	private Integer idx;

	private int cpage; // 현재 보여줄 페이지 번호
	private int pointPageSize; // 한 페이지 당 보여줄 목록 개수
	private int pointTotalCount; // 총 적립내역 수
	private int pointPageCount; // 페이지 수

	// DB에서 레코드를 끊어오기 위한 프로퍼티
	private int start;
	private int end;

	// 페이징 블럭처리를 위한 프로퍼티
	private int pagingBlock = 5; // 한 블럭 당 보여줄 페이지 수
	private int prevBlock; // 이전
	private int nextBlock; // 이후

	public void init(HttpSession ses) {
		if (pointPageSize < 0) {
			pointPageSize = 5;
		}
		if (pointPageSize == 0) {
			Integer ps = (Integer) ses.getAttribute("pageSize");
			if (ps == null) {
				pointPageSize = 5;
			} else {
				pointPageSize = ps;
			}
		}
		ses.setAttribute("pageSize", pointPageSize);

		pointPageCount = (pointTotalCount - 1) / pointPageSize + 1;
		if (cpage <= 0) {
			cpage = 1;
		}
		if (cpage > pointPageCount) {
			cpage = pointPageCount;
		}
		start = (cpage - 1) * pointPageSize;
		end = start + (pointPageSize + 1);

		prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
		nextBlock = prevBlock + (pagingBlock + 1);
	}

	public String getPageNavi(String myctx, String loc, Integer idx) {

		String qStr = "?idx=" + idx + "&pageSize=" + pointPageSize ;
		

		StringBuilder buf = new StringBuilder();
		buf.append("<ul class='pagination justify-content-center'>");
		if (prevBlock > 0) {
			// 이전 n개
			buf.append("<li class='page-item'>")
					.append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + prevBlock + "'>");
			buf.append("Prev").append("</a></li>");
		}

		for (int i = prevBlock + 1; i <= nextBlock - 1 && i <= pointPageCount; i++) {
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

		if (nextBlock <= pointPageCount) {
			// 이후 n개
			buf.append("<li class='page-item'>")
					.append("<a class='page-link' href='" + myctx + "/" + loc + qStr + "&cpage=" + nextBlock + "'>");
			buf.append("Next").append("</a></li>");
		}
		buf.append("</ul>");
		log.info("totalCount = " + pointTotalCount);
		log.info("pageCount = " + pointPageCount);
		log.info("start = " + start + " end = " + end);
		log.info("idx=" + idx);
		return buf.toString();
	}
}
