package review.model;

public class ReviewVO {

    private int rnum;
    private int star;
    private int recommend;
    private String file1;
    private String file2;
    private String file3;
    private int contenttypeId;
    private int idx;

    public ReviewVO(int rnum, int star, int recommend, String file1, String file2, String file3, int contenttypeId, int idx) {
        this.rnum = rnum;
        this.star = star;
        this.recommend = recommend;
        this.file1 = file1;
        this.file2 = file2;
        this.file3 = file3;
        this.contenttypeId = contenttypeId;
        this.idx = idx;
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        this.file2 = file2;
    }

    public String getFile3() {
        return file3;
    }

    public void setFile3(String file3) {
        this.file3 = file3;
    }

    public int getContenttypeId() {
        return contenttypeId;
    }

    public void setContenttypeId(int contenttypeId) {
        this.contenttypeId = contenttypeId;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
