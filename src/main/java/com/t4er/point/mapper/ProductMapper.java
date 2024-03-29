package com.t4er.point.mapper;

import java.util.List;
import java.util.Map;

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

    int getProdByCateTotalCount(ProductPagingVO paging);

    // 여기부터 관리자
    int insertProd(ProductVO product);
    ProductVO getProd(Integer pnum);
    int updateProd(ProductVO prod);
    void delCartByOrder(Map<String, Integer> map);
    /* List<OrderVO> listOrder(AdminPagingVO pvo); */
}
