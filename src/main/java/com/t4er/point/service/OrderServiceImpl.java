package com.t4er.point.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.point.mapper.OrderMapper;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductVO;
import com.t4er.user.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@Service("orderServiceImple")
@Log4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private UserMapper userMapper;

	// orderDesc, orderProduct, receiver테이블에 데이터 insert
	@Override
	public String orderInsert(OrderVO ovo) {
		// 1. 주문번호 생성 (알파벳대문자3개년월일시분초)
		String onum = makeOnum();
		log.info("onum===" + onum);
		ovo.setOnum(onum);// 주문번호 셋팅

		// 2. 주문 개요 정보 insert
		int n = this.orderMapper.orderDescInsert(ovo);
		log.info("주문개요 정보 insert결과: n=" + n);

		// 3. 주문 상품 정보 insert
		List<ProductVO> orderList = ovo.getOrderList();
		if (orderList != null) {
			for (ProductVO prod : orderList) {
				prod.setOnum(onum);// PK => (onum+pnum)
				int n2 = this.orderMapper.orderProductInsert(prod);
			}
		}
		// 4. 수령자 정보 insert
		/*
		 * int n3 = this.orderMapper.receiverInsert(ovo);
		 */
		
		/*
		 * // 5. 사용마일리지(omileage)가 있으면 회원의 마일리지(mileage) 점수에서 차감 if (ovo.getOmileage() >
		 * 0) { this.userMapper.updateMilegeDown(ovo);
		 * 
		 * }
		 * 
		 * // 6. 결제방식이 카드결제라면=> 회원의 마일리지(Mileage) 점수에 구매 포인트(ototalPoint)를 적립 if
		 * (ovo.getOpayway().equals("200")) { this.userMapper.updateMilegeUp(ovo); }
		 */
		
		return onum;
		
		
	}// -------------------------------------

	private String makeOnum() {
		char ch1 = (char) (Math.random() * 26 + 'A');
		char ch2 = (char) (Math.random() * 26 + 'A');
		char ch3 = (char) (Math.random() * 26 + 'A');
		// char + char ==>int

		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String str = sdf.format(d);

		String onum = String.valueOf(ch1) + ch2 + ch3 + str;
		return onum;
	}// ----------------------------------

	@Override
	public List<OrderVO> getOrderDesc(String onum) {
		return this.orderMapper.getOrderDesc(onum);
	}

	@Override
	public List<OrderVO> getUserOrderList(int midx_fk) {
		
		return null;
	}

	
	

}
