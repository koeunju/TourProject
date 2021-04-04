package com.t4er.common;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CommonUtil {

    public String addMsgLoc(Model m, String message, String loc) {
        m.addAttribute("msg", message);
        m.addAttribute("loc", loc);
        return "message";
    }

    public String addMsgBack(Model m, String message) {
        m.addAttribute("msg", message);
        m.addAttribute("loc", "javascript:history.back()");
        return "message";
    }
}
