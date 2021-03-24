package com.t4er.point.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.t4er.point.mapper.ProductMapper;
import com.t4er.point.model.PagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;

@Service("productService")
public class ProductServiceImple implements ProductService {
	
	@Inject
	private ProductMapper productMapper;

	@Override
	public List<ProductVO> selectByPspec(String pspec) {
		return this.productMapper.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(PagingVO pvo) {
		return this.productMapper.selectByCategory(pvo);
	}

	@Override
	public ProductVO selectByPnum(Integer pnum) {
		return this.productMapper.selectByPnum(pnum);
	}
	
	@Override
    public List<Product_CategoryVO> getCategory(){
        return this.productMapper.getCategory();

    }
    
    @Override
     public List<ProductVO> getProdList(PagingVO paging){
    	 return this.productMapper.getProdList(paging);
     }
    
    
	@Override
    public int getProductTotalCount(PagingVO paging) {
    	return this.productMapper.getProductTotalCount(paging);
    
    }
	
	@Override
	public int getProdByCateTotalCount(PagingVO paging) {
		return this.productMapper.getProdByCateTotalCount(paging);
	}
	
}

