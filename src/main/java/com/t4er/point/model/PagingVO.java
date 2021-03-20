package com.t4er.point.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class PagingVO {
	
	private String cgnum;
	
	private int cpage;//현재 보여줄 페이지 번호
	private int pageSize; //한 페이지 당 보여줄 목록 개수
	private int totalCount;//총 상품 수
	private int pageCount; //페이지 수
	
	//DB에서 레코드를 끊어오기 위한 프로퍼티
	private int start;
	private int end;
	
	//페이징 블럭처리를 위한 프로퍼티
	private int pagingBlock =5;//한 블럭 당 보여줄 페이지 수
	private int prevBlock; //이전 5개
	private int nextBlock; //이후 5개
	
	//검색 관련 프로퍼티
	private String findKeyword;//검색어
	
	/**페이징 처리를 위해 연산을 수행하는 메소드*/
	public void init(HttpSession ses) {
		
		if(pageSize<0) {
			pageSize = 8; 
		}
		if(pageSize==0) {
			Integer ps = (Integer)ses.getAttribute("pageSize");
				if(ps==null) {
					pageSize = 8;
				}else {
					pageSize = ps;
				}
		}
		ses.setAttribute("pageSize", pageSize);
		
		pageCount = (totalCount-1)/pageSize +1;
		if(cpage<=0) {
			cpage=1;
		}
		if(cpage>pageCount) {
			cpage=pageCount;
		}
		start = (cpage-1) * pageSize;
		end = start + (pageSize+1);
		
		prevBlock = (cpage-1)/pagingBlock * pagingBlock;
		nextBlock = prevBlock + (pagingBlock+1);
		
	}
	
	public String getPageNavi(String myctx, String loc, String userAgent, String cgnum) {
		
		if(findKeyword==null) {
			findKeyword="";
		}else {
			if(userAgent.indexOf("MSIE")>-1 || userAgent.indexOf("Trident")>-1) {
				try {
					findKeyword = URLEncoder.encode(findKeyword,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		String qStr = "";
		if(cgnum == null) {
			 qStr="?pageSize="+pageSize+"&findKeyword="+findKeyword; //전체 목록 
		}else if (cgnum != null) {
			qStr="?cgnum=" +cgnum+ "&pageSize="+pageSize+"&findKeyword="+findKeyword;
		 //카테고리별 목록 
		}
		
		StringBuilder buf =new StringBuilder();
		buf.append("<ul class='pagination justify-content-center'>");
		if(prevBlock>0) {
			//이전 n개
			buf.append("<li class='page-item'>")
		       .append("<a class='page-link' href='"+myctx+"/"+loc+qStr+"&cpage="+prevBlock+"'>");
			buf.append("Prev")
				.append("</a></li>");		
			}//if--
		
		for(int i=prevBlock+1;i<=nextBlock-1 && i<=pageCount ;i++) {
			String css="";
			if(i == cpage) {
				css="active";
			}else {
				css="";
			}
			
			buf.append("<li class='page-item "+css+"'>")
		       .append("<a class='page-link' href='"+myctx+"/"+loc+qStr+"&cpage="+i+"'>");
			buf.append(i)
				.append("</a></li>");
		}//for------------------
		
		if(nextBlock<=pageCount) {
			//이후 n개
	buf.append("<li class='page-item'>")
       .append("<a class='page-link' href='"+myctx+"/"+loc+qStr+"&cpage="+nextBlock+"'>");
	buf.append("Next")
		.append("</a></li>");
		}//if----------------------
		buf.append("</ul>");
		//System.out.println(buf.toString());
		
		log.info("qstr = " + qStr);
		log.info("cgnum = " + cgnum);
		return buf.toString();
	}

}
