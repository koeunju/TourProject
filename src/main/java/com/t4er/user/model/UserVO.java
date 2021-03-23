package com.t4er.user.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserVO {
	
	  private String idx;
	  @NotBlank
	  @Length(min=4, max=18)
	  private String id;
	  
	  @Length(min=4, max=15)
	  private String pwd;
	  private String email;
	  private String nick;
	  private String tel;
	  private String image;
	  private String stat;
	  private java.sql.Date indate;
	  private int point;

}
