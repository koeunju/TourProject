package com.t4er.point.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.t4er.common.CommonUtil;
import com.t4er.point.model.PagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.Product_CategoryVO;
import com.t4er.point.service.PointService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PointController {

	@Autowired
	private CommonUtil util;

	@Resource(name = "pointService")
	private PointService pointService;

	@GetMapping("/point")
	public String productList(Model m, HttpServletRequest req, 
			@RequestHeader("User-Agent") String userAgent,
			@ModelAttribute("paging") PagingVO paging) {
		
		// 총 상품 수 가져오기 
		
		int totalCount = this.pointService.getProductTotalCount(paging);
		paging.setTotalCount(totalCount);
		paging.setPagingBlock(5); //페이지에 들어가는 수
		paging.init(req.getSession());
		log.info(paging);
		
		
		// 상품 목록 가져오기 
		List<ProductVO> bList = this.pointService.getProdList(paging); //요기용
		String myctx=req.getContextPath();
		String loc="point";
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent);
		
		m.addAttribute("bList", bList);
		m.addAttribute("pageNavi", pageNavi);

		return "point/main";
	}
	

	@GetMapping("/category")
	public String productCategory(Model m, 
			@RequestParam(defaultValue = "CATE") String pspe) {
		List<Product_CategoryVO> clist = pointService.getCategory();

		m.addAttribute("cList", clist);


		return "point/category";
	}

	

	 @GetMapping("/productByCate") 
	 public String productByCate(Model m, 
			 HttpServletRequest req, 
			 @ModelAttribute("cgnum") String cgnum,
			 @ModelAttribute("paging") PagingVO paging) { 

		 List<ProductVO> clist=pointService.selectByCategory(cgnum); 
        m.addAttribute("cList", clist);


	 return "point/mallByCategory";
	  
	 }
	 
		@GetMapping("/prodDetail")
		public String productDetail(Model m, @RequestParam(defaultValue = "0") int pnum) {
			if(pnum==0) {
				return "redirect:index";
			}
			ProductVO prod = this.pointService.selectByPnum(pnum);
			m.addAttribute("item", prod);
			
			return "point/productDetail";
		}
	 
	
	

	 
}
