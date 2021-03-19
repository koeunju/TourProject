package com.t4er.point.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t4er.point.mapper.ProductMapper;
import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;

@Service("pointService")
public class PointServiceImpl implements PointService {

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
    public int getProductTotalCount() {
        return this.productMapper.getProductTotalCount();
    }

    @Override
    public int getProductTotalCount(ProductPagingVO paging) {
        return this.productMapper.getProductTotalCount(paging);
    }

    public int getProductByCateTotalCount(ProductPagingVO paging) {
        return this.productMapper.getProductByCateTotalCount(paging);
    }
}

