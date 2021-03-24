package com.t4er.user.controller;

import com.t4er.common.CommonUtil;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CommonUtil util;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @GetMapping("/join")
    public String joinForm() {

        return "/user/join";
    }

    @PostMapping("/join")
    public String joinEnd(Model m, @Valid @ModelAttribute("user") UserVO user, BindingResult result, HttpServletRequest req) {
        log.info("user==" + user);
        // 유효성 체크한 결과==> BidingResult객체에 담긴다.
        // 이 객체에 에러를 가지고 있다면
        if (result.hasErrors()) {
            log.info("유효성 체크 실패");
            return "user/join";
        }

        // 비밀번호 암호화(sha256) => 암호화는 되는데 비밀번호가 틀리다면서 로그인이 안됨.
        //System.out.println("첫번째:" + user.getPwd());
        //String encryPassword = UserSha256.encrypt(user.getPwd());
        //user.setPwd(encryPassword);
        //System.out.println("두번째:" + user.getPwd());

        // 인증메일 보내기
        this.userService.mailSendWithUserKey(user.getEmail(), user.getId(), req);

        // 에러가 없다면 아래 로직 수행
        int n = this.userService.createUser(user);
        String str = (n > 0) ? "회원가입 완료" : "회원가입 실패";
        String loc = (n > 0) ? "/index" : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
    }

    // 이메일 인증 완료
    @GetMapping(value = "/stat_alter")
    public String statAlter(Model m, @RequestParam("id") String id) {

        this.userService.statAlter(id);

        return "user/joinSuccess";

    }

    @GetMapping(value = "/idcheck", produces = "application/json")
    public @ResponseBody
    Map<String, String> idCheck(@RequestParam("id") String id) {
        boolean isUse = userService.idCheck(id);

        String msg = (isUse) ? id + "는 사용 가능합니다." : id + "는 이미 사용중입니다";
        int n = (isUse) ? 1 : -1;
        Map<String, String> map = new HashMap<>();
        map.put("idResult", msg);
        map.put("isUse", String.valueOf(n));
        return map;
    }

    @GetMapping(value = "/emailcheck", produces = "application/json")
    public @ResponseBody
    Map<String, String> emailCheck(@RequestParam("email") String email) {
        boolean isEma = userService.emailCheck(email);

        String msg = (isEma) ? "사용 가능한 이메일입니다." : "이미 등록된 이메일입니다.";
        int n = (isEma) ? 1 : -1;
        Map<String, String> map = new HashMap<>();
        map.put("emailResult", msg);
        map.put("isEma", String.valueOf(n));
        return map;
    }

    @GetMapping(value = "/nickcheck", produces = "application/json")
    public @ResponseBody
    Map<String, String> nickCheck(@RequestParam("nick") String nick) {
        boolean isNic = userService.nickCheck(nick);

        String msg = (isNic) ? "사용 가능한 닉네임입니다." : "이미 존재하는 닉네임입니다.";
        int n = (isNic) ? 1 : -1;
        Map<String, String> map = new HashMap<>();
        map.put("nickResult", msg);
        map.put("isNic", String.valueOf(n));
        return map;
    }

    @GetMapping(value = "/telcheck", produces = "application/json")
    public @ResponseBody
    Map<String, String> telCheck(@RequestParam("tel") String tel) {
        boolean isTel = userService.telCheck(tel);

        String msg = (isTel) ? "사용 가능한 전화번호입니다." : "이미 존재하는 전화번호입니다.";
        int n = (isTel) ? 1 : -1;
        Map<String, String> map = new HashMap<>();
        map.put("telResult", msg);
        map.put("isTel", String.valueOf(n));
        return map;
    }

}
