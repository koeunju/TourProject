package com.t4er.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t4er.common.CommonUtil;
import com.t4er.user.model.UserVO;
import com.t4er.user.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
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
	public String joinEnd(Model m, @Valid  @ModelAttribute("user") UserVO user, BindingResult result) {
		log.info("user=="+user);
		//��ȿ�� üũ�� ���==> BidingResult��ü�� ����.
		//�� ��ü�� ������ ������ �ִٸ�
		if(result.hasErrors()) {
			log.info("��ȿ�� üũ ����");
			return "user/join";

		}
		//������ ���ٸ� �Ʒ� ���� ����
		int n = this.userService.createUser(user);
		String str=(n>0)?"ȸ������ �Ϸ�":"ȸ������ ����";
		String loc=(n>0)?"index":"javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}

	@GetMapping(value="/idcheck",produces = "application/json")
	public @ResponseBody Map<String, String> idCheck(@RequestParam("id") String id){
		boolean isUse=userService.idCheck(id);

		String msg=(isUse)? id+"�� ��� �����մϴ�.":id+"�� �̹� ������Դϴ�";
		int n=(isUse)? 1: -1;
		Map<String, String> map=new HashMap<>();
		map.put("idResult", msg);
		map.put("isUse", String.valueOf(n));
		return map;
	}//---------------------------
}