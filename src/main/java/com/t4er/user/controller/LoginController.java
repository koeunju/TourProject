package com.t4er.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.t4er.common.CommonUtil;
import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommonUtil util;

	@GetMapping("/login")
	public String login() {

		return "user/login";
	}
	
	@PostMapping("/loginEnd")
	public String loginEnd(Model m, HttpSession ses, @ModelAttribute("user") UserVO user) 
			throws NotUserException {
		log.info("user==="+user);
		
		UserVO loginUser=this.userService.loginCheck(user.getId(), user.getPwd());
		if(loginUser!=null) {
			//ȸ������ ���� �޾Ҵٸ� ==> ���ǿ� �α����� ȸ�������� ��������.
			ses.setAttribute("loginUser", loginUser);
		}
		return "redirect:index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		//���� ��ȿȭ
		ses.invalidate();
		return "redirect:index";
		
	}
}
