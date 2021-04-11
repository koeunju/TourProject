
package com.t4er.point.mapper;

import java.util.List;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductVO;

public interface OrderMapper {


	// 주문 개요정보를 insert하는 메소드
	int orderDescInsert(OrderVO ovo);

	// 주문 상품정보 insert
	int orderProductInsert(ProductVO prod);

	// 수령자 정보 insert
	int receiverInsert(OrderVO ovo);

	List<OrderVO> getOrderDesc(String onum);

	List<OrderVO> getUserOrderList(int midx_fk);
	
	List<OrderVO> listOrder(AdminPagingVO pvo);

	
}

