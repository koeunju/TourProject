package board.model;

import java.io.Serializable;

public class CategoryVO implements Serializable {

    private int cg_num;
    private String cg_name;

    public CategoryVO(int cg_num, String cg_name) {
        super();
        this.cg_num = cg_num;
        this.cg_name = cg_name;
    }

    public int getCg_num() {
        return cg_num;
    }

    public void setCg_num(int cg_num) {
        this.cg_num = cg_num;
    }

    public String getCg_name() {
        return cg_name;
    }

    public void setCg_name(String cg_name) {
        this.cg_name = cg_name;
    }


}
