package com.t4er.point.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO {

	// orderDesc 과 관련된 property
	private String onum; // 주문번호
	private int pnum_fk; //상품번호
	private int opoint;//포인트
	private int oqty;//수량
	private String pimage;//상품대표이미지
	private String odeliverstate;// 배송상태(배송전,배송중,배송완료)
	private String opayway;// 지불방법(100:무통장입금, 200:카드결제)
	private int ototalPoint;// 결제 총포인트
	private String opaystate;// 결제 상태(미결제,결제완료,결제취소)
	private Date orderdate;// 주문일
	private int idx_fk;// 주문자 회원번호
	
	// order_product와 관련한 프로퍼티
	// OrderVO has Products
	private List<ProductVO> orderList; // 주문상품 목록

	// receiver와 관련한 프로퍼티
	// OrderVO has a Receiver
	

}
