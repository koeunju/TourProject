package com.t4er.point.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.t4er.common.CommonUtil;
import com.t4er.point.model.Product_CategoryVO;
import com.t4er.point.service.PointService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PointController {
	
	@Autowired
	private CommonUtil util;
	
	@Autowired
	private PointService pointService;
	
	@GetMapping("/point")
	public String main() {
		
		return "/point/main";
	}
	
	@GetMapping("/category")
	public String productCategory(Model m, @RequestParam(defaultValue="NEW") String pspec) {		
		List<Product_CategoryVO> clist= pointService.getCategory();
		
		m.addAttribute("cList", clist);
		
		return "point/category";
	}
}
