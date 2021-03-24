package com.t4er.point.mapper;

import java.util.List;
import java.util.Map;

import com.t4er.point.model.PagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

public interface ProductMapper {

	List<ProductVO> selectByPspec(String pspec);

	ProductVO selectByPnum(Integer pnum);

	List<ProductVO> selectByCategory(PagingVO pvo);

	List<Product_CategoryVO> getCategory();

	List<ProductVO> getProdList(PagingVO paging);

	int getProductTotalCount(PagingVO paging);

	int getProdByCateTotalCount(PagingVO paging);



}
