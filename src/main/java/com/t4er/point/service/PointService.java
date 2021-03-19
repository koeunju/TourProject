package com.t4er.point.service;

import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;

import java.util.List;

public interface PointService {
    /* Pspec 별로 상품 정보 가져오기 */
    List<ProductVO> selectByPspec(String pspec);
    /* 카테고리별 상품정보 가져오기 */
    List<ProductVO> selectByCategory(ProductPagingVO pvo);
    /** 상품번호로 특정 상품 정보 가져오기 */
    ProductVO selectByPnum(Integer pnum);

    List<ProductCategoryVO> getCategory();

    int getProductTotalCount();
    int getProductTotalCount(ProductPagingVO paging);

    List<ProductVO> getProdList(ProductPagingVO paging);
    int getProductByCateTotalCount(ProductPagingVO paging);

}
