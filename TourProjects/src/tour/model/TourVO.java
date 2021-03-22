package tour.model;

import java.sql.Date;

public class TourVO {

    private String title;
    private String addr1;
    private String addr2;
    private String tel;
    private int contenttypeId;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private Date createdTime;
    private Date modifiedTime;

    public TourVO() {
    }

    public TourVO(String title, int contenttypeId, String firstImage, int readCount, Date createdTime, Date modifiedTime) {
        this.title = title;
        this.contenttypeId = contenttypeId;
        this.firstImage = firstImage;
        this.readCount = readCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public TourVO(String title, String addr1, String addr2, String tel, int contenttypeId, String firstImage, String firstImage2, int readCount, Date createdTime, Date modifiedTime) {
        this.title = title;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.tel = tel;
        this.contenttypeId = contenttypeId;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.readCount = readCount;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getContenttypeId() {
        return contenttypeId;
    }

    public void setContenttypeId(int contenttypeId) {
        this.contenttypeId = contenttypeId;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getFirstImage2() {
        return firstImage2;
    }

    public void setFirstImage2(String firstImage2) {
        this.firstImage2 = firstImage2;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
