package point.model;

public class Product_CategoryVO {

    private String Cg_num;//상위 카테고리 코드
    private String Cg_name;

    public Product_CategoryVO() {

    }

    public Product_CategoryVO(String cg_num, String cg_name) {
        super();
        Cg_num = cg_num;
        Cg_name = cg_name;

    }

    public String getCg_num() {
        return Cg_num;
    }

    public void setCg_num(String cg_num) {
        Cg_num = cg_num;
    }

    public String getCg_name() {
        return Cg_name;
    }

    public void setCg_name(String cg_name) {
        Cg_name = cg_name;
    }
}
