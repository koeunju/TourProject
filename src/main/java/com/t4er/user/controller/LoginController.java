package com.t4er.user.controller;

import com.t4er.common.CommonUtil;
import com.t4er.user.exception.NotUserException;
import com.t4er.user.model.UserVO;
import com.t4er.user.security.UserSha256;
import com.t4er.user.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Log4j
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonUtil util;

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @PostMapping("/login")
    public String loginEnd(Model m, HttpSession ses, @ModelAttribute("user") UserVO user)
            throws NotUserException {
        log.info("user===" + user);

        String encryPassword = UserSha256.encrypt(user.getPwd());
        user.setPwd(encryPassword);

        UserVO loginUser = this.userService.loginCheck(user.getId(), user.getPwd());
        String st = this.userService.checkState(user.getId());

        if (st.trim().equals("4")) {
            return util.addMsgLoc(m, "탈퇴회원입니다. 고객센터에 문의 바랍니다.", "/index");
        }
        if (st.trim().equals("5")) {
            return util.addMsgLoc(m, "차단회원입니다. 고객센터에 문의 바랍니다.", "/index");
        }
        if(st.trim().equals("0")) {
            return util.addMsgLoc(m, "이메일 인증이 안된 회원입니다. 이메일 인증을 해주세요.", "/index");
        }

        if (loginUser != null) {
            // 회원임을 인증 받았다면 ==> 세션에 로그인한 회원정보를 저장하자.
            ses.setAttribute("loginUser", loginUser);
        }
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession ses) {
        // 세션 무효화
        ses.invalidate();
        return "redirect:/index";

    }

    @ExceptionHandler(NotUserException.class)
    public String exceptionHandler(Exception ex) {
        return "user/errorAlert";
    }
}
