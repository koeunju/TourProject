package com.t4er.point.service;


import java.util.List;

import com.t4er.point.model.PagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

public interface ProductService {
	
	/*Pspec ���� ��ǰ ���� ��������*/
	List<ProductVO> selectByPspec(String pspec);
	/*ī�װ��� ��ǰ���� ��������*/
	List<ProductVO> selectByCategory(PagingVO pvo);
	/**��ǰ��ȣ�� Ư�� ��ǰ ���� ��������*/
	ProductVO selectByPnum(Integer pnum);

    List<Product_CategoryVO> getCategory();
    
 
	int getProductTotalCount(PagingVO paging);
	
	int getProdByCateTotalCount(PagingVO paging);
	
	List<ProductVO> getProdList(PagingVO paging);

	

}
