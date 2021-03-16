package com.t4er.common;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

// @Controllrt, @Service, ....> @Component 를 상속 받고 있음..  무엇을 써야할지 모를때?? 사용

@Component
public class CommonUtil {

	public String addMsgLoc(Model m, String msg, String loc) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", loc);
		return "message";
	}
	
	public String addMsgBack(Model m, String msg) {
		m.addAttribute("msg", msg);
		m.addAttribute("loc", "javascript:history.back()");
		return "message";
	}
}
