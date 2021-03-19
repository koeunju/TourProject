package com.t4er.point.mapper;

import java.util.List;

import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;

public interface ProductMapper {

    List<ProductVO> selectByPspec(String pspec);

    ProductVO selectByPnum(Integer pnum);

    List<ProductVO> selectByCategory(ProductPagingVO pvo);

    List<ProductCategoryVO> getCategory();

    List<ProductVO> getProdList(ProductPagingVO paging);

    int getProductTotalCount(ProductPagingVO paging);

    int getProductTotalCount();

    int getProductByCateTotalCount(ProductPagingVO paging);

}
