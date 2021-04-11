package com.t4er.point.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.point.mapper.ProductMapper;
import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;

@Service("pointService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductVO> selectByPspec(String pspec) {
        return this.productMapper.selectByPspec(pspec);
    }

    @Override
    public List<ProductVO> selectByCategory(ProductPagingVO pvo) {
        return this.productMapper.selectByCategory(pvo);
    }

    @Override
    public ProductVO selectByPnum(Integer pnum) {
        return this.productMapper.selectByPnum(pnum);
    }

    @Override
    public List<ProductCategoryVO> getCategory() {
        return this.productMapper.getCategory();
    }

    @Override
    public List<ProductVO> getProdList(ProductPagingVO paging) {
        return this.productMapper.getProdList(paging);
    }

    @Override
    public int getProductTotalCount(ProductPagingVO paging) {
        return this.productMapper.getProductTotalCount(paging);
    }

    @Override
    public int getProdByCateTotalCount(ProductPagingVO paging) {
        return this.productMapper.getProdByCateTotalCount(paging);
    }

    @Override
    public void delCartByOrder(int idx_fk, int pnum) {
        Map<String, Integer> map = new HashMap<>();
        map.put("idx_fk", idx_fk);
        map.put("pnum", pnum);
        this.productMapper.delCartByOrder(map);
    }
}

