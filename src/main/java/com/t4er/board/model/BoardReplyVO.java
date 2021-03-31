package com.t4er.board.model;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardReplyVO {

    private Integer rnum;
    private Integer bnum;
    private String content;
    private String idx;
    private Date reg_dt;
    private Date edit_dt;
}
