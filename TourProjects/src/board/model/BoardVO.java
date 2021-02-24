package board.model;

import java.io.Serializable;

import java.sql.*;


public class BoardVO implements Serializable {
	   
	   private int bnum;
	   private String btitle;
	   private String bcontent;
	   private int binquiry;
	   private Date bdate;
	   private String bupload1;
	   private String bupload2;
	   private String bupload3;
	   private int brecommend;
	   private int idx;
	   private int cg_num;
	   //0-자유게시판 1-이달의 여행지 2- 고객센터
	   public BoardVO() {
	      
	   }

	public BoardVO(int bnum, String btitle, String bcontent, int binquiry, Date bdate, String bupload1,
			String bupload2, String bupload3, int brecommend, int idx, int cg_num) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.binquiry = binquiry;
		this.bdate = bdate;
		this.bupload1 = bupload1;
		this.bupload2 = bupload2;
		this.bupload3 = bupload3;
		this.brecommend = brecommend;
		this.idx = idx;
		this.cg_num = cg_num;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public int getBinquiry() {
		return binquiry;
	}

	public void setBinquiry(int binquiry) {
		this.binquiry = binquiry;
	}

	public Date getDate() {
		return bdate;
	}

	public void setDate(Date date) {
		this.bdate = date;
	}

	public String getBupload1() {
		return bupload1;
	}

	public void setBupload1(String bupload1) {
		this.bupload1 = bupload1;
	}

	public String getBupload2() {
		return bupload2;
	}

	public void setBupload2(String bupload2) {
		this.bupload2 = bupload2;
	}

	public String getBupload3() {
		return bupload3;
	}

	public void setBupload3(String bupload3) {
		this.bupload3 = bupload3;
	}

	public int getBrecommend() {
		return brecommend;
	}

	public void setBrecommend(int brecommend) {
		this.brecommend = brecommend;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getCg_num() {
		return cg_num;
	}

	public void setCg_num(int cg_num) {
		this.cg_num = cg_num;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

}