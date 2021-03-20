package com.t4er.mypage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.t4er.common.CommonUtil;
import com.t4er.mypage.service.MypageService;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
public class MyPageController {

    @Autowired
    private MypageService mypageService;
    @Autowired
    private CommonUtil util;

    @GetMapping("/myInfo")
    public String mypageHome(Model m, @RequestParam String idx) {
        log.info("idx===" + idx);
        if (idx == null)
            return util.addMsgLoc(m, "로그인정보에 문제가 있습니다.", "index");

        // 정보검색
        UserVO user = this.mypageService.selectMy(idx);
        m.addAttribute("user", user);

        return "user/mypage/mypageHome";
    }

    @GetMapping("/edit")
    public String mypageEdit(Model m, @RequestParam String idx) {
        log.info("idx===" + idx);
        if (idx == null)
            return "redirect:/mypage";

        // 정보검색
        UserVO user = this.mypageService.selectMy(idx);
        m.addAttribute("user", user);

        return "user/mypage/mypageHEdit";
    }

    @PostMapping("/edit")
    public String mypageEditEnd(Model m, @RequestParam String idx, @ModelAttribute("user") UserVO user) {
        if (user.getIdx() == null)
            return "user/myInfo";

        if (user.getStat() == null)
            user.setStat("9");
        ;
        int n = this.mypageService.updateUser(user);
        String str = (n > 0) ? "정보 수정 완료" : "정보 수정 실패";
        String loc = (n > 0) ? "/user/myInfo?idx=" + user.getIdx() : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }

    @PostMapping("/del")
    public String mypageDel(Model m, @RequestParam String idx) {
        if (idx == null) {
            return util.addMsgLoc(m, " 문제가 있습니다.", "/index");
        }
        int n = this.mypageService.leaveMember(idx);
        String str = (n > 0) ? "탈퇴 처리가 완료되었습니다." : "탈퇴 실패";
        String loc = (n > 0) ? "/user/logout" : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);

    }

    @RequestMapping("/mypageMenubar")
    public void mypageMenubar() {

    }

}
