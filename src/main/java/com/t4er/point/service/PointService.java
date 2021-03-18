package com.t4er.point.service;


import java.util.List;

import com.t4er.point.model.PagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

public interface PointService {
	
	/*Pspec 별로 상품 정보 가져오기*/
	List<ProductVO> selectByPspec(String pspec);
	/*카테고리별 상품정보 가져오기*/
	List<ProductVO> selectByCategory(PagingVO pvo);
	/**상품번호로 특정 상품 정보 가져오기*/
	ProductVO selectByPnum(Integer pnum);

    List<Product_CategoryVO> getCategory();
    
 
    int getProductTotalCount();
	int getProductTotalCount(PagingVO paging);
	
	List<ProductVO> getProdList(PagingVO paging);
	int getProductByCateTotalCount(PagingVO paging);
	

}
