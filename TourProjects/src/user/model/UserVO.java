package user.model;

import java.sql.Date;

public class UserVO implements java.io.Serializable
{
  private int idx;
  private String id;
  private String pwd;
  private String email;
  private String name;
  private String tel;
  private int stat;
  private Date indate;
  private int point;
  
  public UserVO(int idx, String id, String pwd, String email, String name, String tel, int stat, Date indate, int point) {
	super();
	this.idx = idx;
	this.id = id;
	this.pwd = pwd;
	this.email = email;
	this.name = name;
	this.tel = tel;
	this.stat = stat;
	this.indate = indate;
	this.point = point;
}


public UserVO() {
	// TODO Auto-generated constructor stub
}


public int getIdx() {
	return idx;
}

public void setIdx(int idx) {
	this.idx = idx;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public int getStat() {
	return stat;
}

public void setStat(int stat) {
	this.stat = stat;
}

public Date getIndate() {
	return indate;
}

public void setIndate(Date indate) {
	this.indate = indate;
}

public int getPoint() {
	return point;
}

public void setPoint(int point) {
	this.point = point;
}
}
