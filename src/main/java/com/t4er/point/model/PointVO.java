package com.t4er.point.model;

import lombok.Data;

import java.sql.Date;

@Data
public class PointVO {

    private String pidx;
    private String pcontent;
    private Date pdate;
    private int psavePoint;
    private int ptotalPoint;
    private String idx;

    public int getTotalPoint() {
        return ptotalPoint + psavePoint;
    }
}
