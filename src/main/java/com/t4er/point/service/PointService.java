package com.t4er.point.service;

import java.util.List;

import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

public interface PointService {
	
	/*Pspec ���� ��ǰ ���� ��������*/
	public List<ProductVO> selectByPspec(String pspec);
	/*ī�װ��� ��ǰ���� ��������*/
	public List<ProductVO> selectByCategory(String code);
	/**��ǰ��ȣ�� Ư�� ��ǰ ���� ��������*/
	public ProductVO selectByPnum(Integer pnum);

    public List<Product_CategoryVO> getCategory();
}
