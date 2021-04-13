package com.t4er.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.t4er.admin.model.AdminPagingVO;
import com.t4er.admin.service.AdminService;
import com.t4er.common.CommonUtil;
import com.t4er.mypage.service.MypageService;
import com.t4er.point.model.OrderVO;
import com.t4er.point.model.ProductPagingVO;
import com.t4er.point.model.ProductVO;
import com.t4er.point.service.OrderService;
import com.t4er.point.service.ProductService;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MypageService mypageService;

    @Autowired
    private CommonUtil util;

    @GetMapping("/adminMain")
    public String admin() {

        return "admin/admin";
    }

    @GetMapping("/userList")
    public String userList(Model m, HttpServletRequest req, @RequestHeader("User-Agent") String userAgent,
                           @ModelAttribute("paging") AdminPagingVO paging) {
        int totalUser = this.adminService.getUserCount(paging);

        paging.setTotalCount(totalUser);
        paging.setPagingBlock(5);
        paging.init(req.getSession());

        List<UserVO> userList = adminService.listUser(paging);

        String myctx = req.getContextPath();

        String loc = "admin/userList";
        // 페이지 네비 문자열 받아오기
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent);

        m.addAttribute("userList", userList);
        m.addAttribute("totalCount", totalUser);
        m.addAttribute("pageNavi", pageNavi);
        return "admin/userList";
    }// ----------------------

    @GetMapping("/userEdit")
    public String adminEdit(Model m, HttpServletRequest req, @RequestParam Integer idx) {
        log.info("idx==="+idx);
        if (idx == null)
            return "redirect:/mypage";
        HttpSession ses = req.getSession();
        UserVO ac = (UserVO) ses.getAttribute("loginUser");
        int adminCheck = ac.getStat();

        String mypoint = this.adminService.myTotalPoint2(idx);

        // 정보검색
        UserVO user = this.adminService.selectMy2(idx);
        log.info("user = " + user);

        m.addAttribute("user", user);
        m.addAttribute("adminCheck", adminCheck);
        m.addAttribute("mytotalpoint", mypoint);

        return "admin/userEdit";
    }

    @PostMapping("/userEdit")
    public String adminEditEnd(Model m,@ModelAttribute("user") UserVO user) {

        System.out.println(user);
        int n = this.adminService.updateUser(user);

        String str = (n > 0) ? "수정 성공" : "수정 실패";
        String loc = (n > 0) ? "/admin/userList" : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }

    @GetMapping("/userDelete")
    public String adminDel(Model m, @RequestParam Integer idx) {
        log.info("idxdelte==="+idx);
        if (idx == null) {
            return util.addMsgLoc(m, "문제가 있습니다", "/index");
        }
        int n = this.adminService.deleteUser(idx);
        System.out.println(n);
        String str = (n > 0) ? "탈퇴 성공" : "탈퇴 실패";
        String loc = (n > 0) ? "/admin/userList" : "javascript:history.back()";

        return util.addMsgLoc(m, str, loc);

    }

    @GetMapping("/shopList")
    public String shopList(Model m, HttpServletRequest req, @ModelAttribute("cgnum") String cgnum,
                           @RequestHeader("User-Agent") String userAgent, @ModelAttribute("paging") ProductPagingVO paging) {

        // 총 상품 수 가져오기
        int totalCount = this.productService.getProductTotalCount(paging);
        paging.setTotalCount(totalCount);
        paging.setPagingBlock(5);
        paging.init(req.getSession());
        log.info(paging);

        // 상품 목록 가져오기
        List<ProductVO> pList = this.productService.getProdList(paging);
        String myctx = req.getContextPath();
        String loc = "point";
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent, cgnum);

        m.addAttribute("cgnum", cgnum);
        m.addAttribute("pList", pList);
        m.addAttribute("pageNavi", pageNavi);

        return "admin/shopList";
    }

    @GetMapping("/prodInsert")
    public String prodInsert() {

        return "/admin/prodInsert";
    }

    @PostMapping("/prodInsert")
    public String prodInsertEnd(Model m, HttpServletRequest req, @RequestParam("imgfile1") MultipartFile imgfile1,
                                @RequestParam("imgfile2") MultipartFile imgfile2, @RequestParam("imgfile3") MultipartFile imgfile3,
                                @ModelAttribute("product") ProductVO product) {

        ServletContext app = req.getServletContext();
        String prodDir = app.getRealPath("/product/upload");
        log.info("product = " + product);

        File dir = new File(prodDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String oriname1, oriname2, oriname3;
        String filename1 = "", filename2 = "", filename3 = "";

        if (!imgfile1.isEmpty() || !imgfile2.isEmpty() || !imgfile3.isEmpty()) {

            oriname1 = imgfile1.getOriginalFilename();
            oriname2 = imgfile2.getOriginalFilename();
            oriname3 = imgfile3.getOriginalFilename();

            UUID uuid = UUID.randomUUID();

            if (!oriname1.isEmpty()) {
                filename1 = uuid.toString() + "-" + oriname1;
                product.setPimage(filename1);
            }
            if (!oriname2.isEmpty()) {
                filename2 = uuid.toString() + "-" + oriname2;
                product.setPimage2(filename2);
            }
            if (!oriname3.isEmpty()) {
                filename3 = uuid.toString() + "-" + oriname3;
                product.setPimage3(filename3);
            }

            try {
                if (!filename1.isEmpty()) {
                    imgfile1.transferTo(new File(dir, filename1));
                }
                if (!filename2.isEmpty()) {
                    imgfile2.transferTo(new File(dir, filename2));
                }
                if (!filename3.isEmpty()) {
                    imgfile3.transferTo(new File(dir, filename3));
                }
            } catch (IOException e) {
                log.error("파일 업로드 중 에러 발생: " + e);
            }
        }

        int n = adminService.insertProd(product);

        String str = (n > 0) ? "등록 성공" : "등록 실패";
        String loc = "/admin/shoplist";

        m.addAttribute("msg", str);
        m.addAttribute("loc", loc);

        return "message";
    }

    @GetMapping("/prodEdit")
    public String prodEdit(Model m, HttpServletRequest req, @RequestParam int pnum) {
        log.info("pnum===" + pnum);
        if (pnum == 0)
            return "redirect:/admin";

        ProductVO product = this.adminService.getProd(pnum);
        m.addAttribute("product", product);

        return "admin/prodEdit";
    }

    @PostMapping("/prodEdit")
    public String prodEditEnd(Model m, @RequestParam String pnum, @ModelAttribute("product") ProductVO product) {
        if (product.getPnum() == null)
            return "admin/shopList";

        int n = this.adminService.updateProd(product);
        String str = (n > 0) ? "수정 성공" : "수정 실패";
        String loc = (n > 0) ? "prodEdit?idx=" + product.getPnum() : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }

    @GetMapping("/orderList")
    public String orderList(Model m, HttpServletRequest req, @RequestHeader("User-Agent") String userAgent,
                            @ModelAttribute("paging") AdminPagingVO paging) {
        log.info("paging===" + paging);
        int totalUser = this.adminService.getUserCount(paging);
        log.info("userConut=" + totalUser);

        paging.setTotalCount(totalUser);
        paging.setPageSize(5);
        paging.setPagingBlock(5);
        paging.init(req.getSession());

        List<OrderVO> orderList = adminService.listOrder(paging);

        String myctx = req.getContextPath();

        String loc = "admin/orderList";
        // 페이지 네비 문자열 받아오기
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent);

        m.addAttribute("orderList", orderList);
        m.addAttribute("totalCount", totalUser);
        m.addAttribute("pageNavi", pageNavi);
        return "/admin/orderList";
    }// ----------------------

    @RequestMapping("/adminMenubar")
    public void adminMenubar() {

    }

}