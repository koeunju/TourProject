package com.t4er.user.controller;

import com.t4er.common.CommonUtil;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/join")
    public String joinForm() {

        return "/user/join";
    }

    @PostMapping("/join")
    public String joinEnd(Model m, @Valid @ModelAttribute("user") UserVO user, BindingResult result) {
        log.info("user==" + user);
        // 유효성 체크한 결과==> BidingResult객체에 담긴다.
        // 이 객체에 에러를 가지고 있다면
        if (result.hasErrors()) {
            log.info("유효성 체크 실패");
            return "user/join";
        }
        // 에러가 없다면 아래 로직 수행
        int n = this.userService.createUser(user);
//        int addpoint = this.userService.setPoint(user);
        String str = (n > 0) ? "회원가입 완료" : "회원가입 실패";
        String loc = (n > 0) ? "/index" : "javascript:history.back()";
        return util.addMsgLoc(m, str, loc);
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


}
