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
		paging.setPagingBlock(5);//페이징 블럭에 들어가는 개수말하는걹꺼에요 넵 그럼 일단 8개로 줄게요
		paging.init(req.getSession());
		log.info(paging);
		//start가 0으로 넘어가네욤 얘네들이 스타트랑 엔드를 어디서 가져오는건가요?
		
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

	  
	 return "point/category";
	  
	 }
	 
	

	 
}
