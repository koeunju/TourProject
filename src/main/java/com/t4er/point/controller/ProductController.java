package com.t4er.point.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.t4er.common.CommonUtil;
import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.model.ProductCategoryVO;
import com.t4er.point.service.ProductService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/point")
public class ProductController {

    @Autowired
    private CommonUtil util;

    @Resource(name = "pointService")
    private ProductService productService;

    @GetMapping("/list")
    public String productList(Model m, HttpServletRequest req,
                              @ModelAttribute("cgnum") String cgnum,
                              @RequestHeader("User-Agent") String userAgent,
                              @ModelAttribute("paging") ProductPagingVO paging) {

        // 총 상품 수 가져오기
        int totalCount = this.productService.getProductTotalCount(paging);
        paging.setTotalCount(totalCount);
        paging.setPagingBlock(5);
        paging.init(req.getSession());
        log.info(paging);

        // 상품 목록 가져오기
        List<ProductVO> bList = this.productService.getProdList(paging);
        String myctx = req.getContextPath();
        String loc = "point/list";
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent, cgnum);

        m.addAttribute("bList", bList);
        m.addAttribute("pageNavi", pageNavi);

        return "point/main";
    }


    @GetMapping("/category")
    public String productCategory(Model m,
                                  @RequestParam(defaultValue = "CATE") String pspe) {
        List<ProductCategoryVO> cList = productService.getCategory();
        m.addAttribute("cList", cList);

        return "point/category";
    }

    @GetMapping("/productByCate")
    public String productByCate(Model m, HttpServletRequest req,
                                @RequestParam("cgnum") String cgnum,
                                @RequestHeader("User-Agent") String userAgent,
                                @ModelAttribute("paging") ProductPagingVO paging) {

        int totalCount = this.productService.getProdByCateTotalCount(paging);
        paging.setTotalCount(totalCount);
        paging.setPagingBlock(5); // 페이지에 들어가는 수
        paging.init(req.getSession());

        // 상품 목록 가져오기
        List<ProductVO> cList = productService.selectByCategory(paging);
        String myctx = req.getContextPath();
        String loc = "point/productByCate";
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent, cgnum);

        m.addAttribute("cList", cList);
        m.addAttribute("pageNavi", pageNavi);

        return "point/mallByCategory";
    }

    @GetMapping("/detail")
    public String productDetail(Model m, @RequestParam(defaultValue = "0") int pnum) {
        if (pnum == 0) {
            return "redirect:index";
        }
        ProductVO prod = this.productService.selectByPnum(pnum);
        m.addAttribute("item", prod);

        return "point/productDetail";
    }
}
