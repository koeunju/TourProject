package com.t4er.tour.model;

import lombok.Data;

import java.sql.Date;

@Data
public class TourVO {

    private Integer contentId;
    private Integer idx;
    private Date sdate;
    private String title;

}
