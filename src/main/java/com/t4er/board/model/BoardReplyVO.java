package com.t4er.board.model;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardReplyVO {
    private Integer rnum;
    private Integer bnum;

    private String reply;
    private Integer idx;
    private Date replyDate;
    private Date updateDate;
}
