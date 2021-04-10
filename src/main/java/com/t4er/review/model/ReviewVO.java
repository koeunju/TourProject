package com.t4er.review.model;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewVO {

    private Integer rnum;
    private Integer rstar;
    private Integer rrecommend;
    private String rfile1;
    private String rfile2;
    private String rfile3;
    private String rcontent;
    private Date rdate;
    private Integer contentId;
    private Integer idx;
    private String nick;
}
