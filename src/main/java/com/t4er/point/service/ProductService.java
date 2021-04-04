package com.t4er.point.service;

import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;

import java.util.List;

public interface ProductService {
    /* Pspec 별로 상품 정보 가져오기 */
    List<ProductVO> selectByPspec(String pspec);
    /* 카테고리별 상품정보 가져오기 */
    List<ProductVO> selectByCategory(ProductPagingVO pvo);
    /** 상품번호로 특정 상품 정보 가져오기 */
    ProductVO selectByPnum(Integer pnum);

    List<ProductCategoryVO> getCategory();

    int getProductTotalCount(ProductPagingVO paging);

    int getProdByCateTotalCount(ProductPagingVO paging);

    List<ProductVO> getProdList(ProductPagingVO paging);

}
