package com.t4er.point.model;

import java.sql.Date;

import lombok.Data;

@Data
public class PointVO {

	private String pidx;
	private String pcontent;
	private Date pdate;
	private int psavePoint;
	private int ptotalPoint;
	private String idx;
}
