package com.t4er.point.model;

import lombok.Data;

@Data
public class ProductVO {

    private String pnum;
    private String pname;
    private String pcontent;
    private int price;
    private String pimage;
    private String pimage2;
    private String pimage3;

    private String Cg_num;
    private String Cg_name;

    //admin
    private int totalPrice;//총판매가 = 판매가 * 수량
    private int totalPoint;//총포인트 = 포인트 * 수량
    private String onum;
    private int pqty;
}
