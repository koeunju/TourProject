package com.t4er.review.controller;

import com.t4er.review.model.ReviewPagingVO;
import com.t4er.review.model.ReviewVO;
import com.t4er.review.service.ReviewService;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/list")
    public String reviewList(Model m, HttpServletRequest req,
                             @RequestParam("contentId") Integer contentId,
                             @ModelAttribute("paging")ReviewPagingVO paging) {

        log.info(paging);
        log.info("controller contentId = " + contentId);
        int totalCount = this.reviewService.getReviewTotalCount(paging);
        paging.setTotalCount(totalCount);
        paging.setPagingBlock(5);
        paging.init(req.getSession());

        List<ReviewVO> rList = reviewService.selectReview(paging);
        String myctx = req.getContextPath();
        String loc = "review/list";
        String pageNavi = paging.getPageNavi(myctx, loc, contentId);

        m.addAttribute("contentId", contentId);
        m.addAttribute("rList", rList);
        m.addAttribute("pageNavi", pageNavi);

        return "review/reviewList";
    }

    @GetMapping("/write")
    public String reviewWrite(Model m, HttpServletRequest req, @RequestParam(defaultValue = "0") String contentId) {

        HttpSession ses = req.getSession();
        UserVO user = (UserVO) ses.getAttribute("loginUser");

        String idx = user.getIdx();

        log.info("idx = " + idx);

        m.addAttribute("contentId", contentId);
        m.addAttribute("idx", idx);
        return "review/reviewWrite";
    }

    @PostMapping("/write")
    public String reviewInsert(Model m, HttpServletRequest req,
                               @RequestParam("imgfile1") MultipartFile imgfile1,
                               @RequestParam("imgfile2") MultipartFile imgfile2,
                               @RequestParam("imgfile3") MultipartFile imgfile3,
                               @RequestParam(defaultValue = "0") String contentId,
                               @ModelAttribute("review") ReviewVO review) {

        log.info("review = " + review);

        ServletContext app = req.getServletContext();
        String reviewDir = app.getRealPath("/review/upload");
        log.info("reviewDir = " + reviewDir);

        File dir = new File(reviewDir);
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
                review.setRfile1(filename1);
            }
            if (!oriname2.isEmpty()) {
                filename2 = uuid.toString() + "-" + oriname2;
                review.setRfile2(filename2);
            }
            if(!oriname3.isEmpty()) {
                filename3 = uuid.toString() + "-" + oriname3;
                review.setRfile3(filename3);
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

        int n = reviewService.insertReview(review);

        String str = (n > 0) ? "등록 성공" : "등록 실패";
        String loc = (n > 0) ? "/review/list?contentId=" + contentId: "redirect:index";

        m.addAttribute("msg", str);
        m.addAttribute("loc", loc);

        return "message";
    }

}
