package point.model;

import java.io.Serializable;

public class ProductVO implements Serializable {
    private int pnum;
    private String pname;
    private String pcontent;
    private int price;
    private String pimage;
    private String pimage2;
    private String pimage3;

    private String Cg_num;

    public ProductVO(int pnum, String pname, String pcontent, int price, String pimage, String pimage2, String pimage3,
                     String Cg_num) {
        super();
        this.pnum = pnum;
        this.pname = pname;
        this.pcontent = pcontent;
        this.price = price;
        this.pimage = pimage;
        this.pimage2 = pimage2;
        this.pimage3 = pimage3;
        this.Cg_num = Cg_num;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPimage2() {
        return pimage2;
    }

    public void setPimage2(String pimage2) {
        this.pimage2 = pimage2;
    }

    public String getPimage3() {
        return pimage3;
    }

    public void setPimage3(String pimage3) {
        this.pimage3 = pimage3;
    }

    public String getCg_num() {
        return Cg_num;
    }

    public void setCg_num(String cg_num) {
        this.Cg_num = cg_num;
    }

    @Override
    public String toString() {
        return "ProductVO [pnum=" + pnum + ", pname=" + pname + ", pcontent=" + pcontent + ", price" + price
                + ", pimage=" + pimage + ", pimage2=" + pimage2 + ", pimage3=" + pimage3 + ", Cg_num="
                + Cg_num + "]";
    }
}

