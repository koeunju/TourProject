package com.t4er.mypage.controller;

import javax.servlet.http.HttpServletRequest;

import com.t4er.board.model.BoardVO;
import com.t4er.point.model.PointVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.t4er.common.CommonUtil;
import com.t4er.mypage.service.MypageService;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Log4j
@RequestMapping("/user")
public class MyPageController {

    @Autowired
    private MypageService mypageService;
    @Autowired
    private CommonUtil util;

    @GetMapping("/myInfo")
    public String mypageHome(Model m, @RequestParam Integer idx) {
        log.info("idx===" + idx);
        if (idx == null)
            return util.addMsgLoc(m, "로그인정보에 문제가 있습니다.", "index");

        // 정보검색
        UserVO user = this.mypageService.selectMy(idx);
        String mypoint = this.mypageService.myTotalPoint(idx);
        m.addAttribute("user", user);
        m.addAttribute("mytotalpoint",mypoint);

        return "user/mypage/mypageHome";
    }

    @GetMapping("/edit")
    public String mypageEdit(Model m, @RequestParam Integer idx) {
        log.info("idx===" + idx);
        if (idx == null)
            return "redirect:/mypage";

        // 정보검색
        UserVO user = this.mypageService.selectMy(idx);
        m.addAttribute("user", user);

        return "user/mypage/mypageHEdit";
    }

    @PostMapping("/edit")
    public String mypageEditEnd(Model m, @RequestParam Integer idx, @ModelAttribute("user") UserVO user) {
        if (user.getIdx() == null)
            return "user/myInfo";

        int n = this.mypageService.updateUser(user);

        String str = (n > 0) ? "정보 수정 완료" : "정보 수정 실패";
        String loc = (n > 0) ? "/user/myInfo?idx=" + user.getIdx() : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }

    @PostMapping("/del")
    public String mypageDel(Model m, @RequestParam Integer idx) {
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

    /* 회원 포인트 조회 */
    @GetMapping("/mypoint")
    public String myPoint(Model m, @RequestParam Integer idx) {
        if (idx == null)
            return util.addMsgLoc(m, "잘못된 접근입니다.", "/myInfo");

        List<PointVO> pointList = this.mypageService.mypoint(idx);
        String mypoint = this.mypageService.myTotalPoint(idx);

        m.addAttribute("mytotalpoint",mypoint);
        m.addAttribute("mypoint", pointList);

        return "user/mypage/mypoint";
    }

    /* 내가 쓴 글 조회 */
    @GetMapping("/write")
    public String mywrite(Model m, @RequestParam Integer idx) {

        log.info("mywrite?idx===" + idx);
        if (idx == null)
            return util.addMsgLoc(m, "잘못된 접근입니다.", "/myInfo");
        List<BoardVO> board = this.mypageService.selMyBoard(idx);

        m.addAttribute("board", board);

        return "/user/mypage/mypageWrite";
    }

    /*비밀번호 체크*/
    @GetMapping(value="/pwdcheck",produces = "application/json")
    public @ResponseBody Map<String,String> pwdCheck(@RequestParam int idx, @RequestParam String pwd){
        log.info("idx/pwd=="+idx+"/"+pwd);
        boolean check = this.mypageService.pwdCheck(idx, pwd);

        String msg=(check)?"비밀번호 변경 가능합니다.":"비밀번호가 일치하지 않습니다.";
        int n = (check)?1:-1;

        Map<String,String> map = new HashMap<>();
        map.put("okPwd", msg);
        map.put("check", String.valueOf(n));

        return map;
    }
}
