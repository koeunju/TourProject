package com.t4er.point.mapper;

import java.util.List;

import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

public interface ProductMapper {

	List<ProductVO> selectByPspec(String pspec);

	ProductVO selectByPnum(Integer pnum);

	List<ProductVO> selectByCategory(String code);

	List<Product_CategoryVO> getCategory();

}
