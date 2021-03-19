package com.t4er.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {

    private String idx;
    @NotBlank
    @Length(min=4, max=15)
    private String id;

    @Length(min=4, max=25)
    private String pwd;
    private String email;
    private String name;
    private String tel;
    private String stat;
    private java.sql.Date indate;
    private int point;

}
