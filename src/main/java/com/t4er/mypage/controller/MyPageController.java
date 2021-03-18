package com.t4er.mypage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.t4er.common.CommonUtil;
import com.t4er.mypage.service.MypageService;
import com.t4er.user.model.UserVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MyPageController {

	@Autowired
	private MypageService mypageService;
	@Autowired
	private CommonUtil util;

	@GetMapping("/mypage/MyInfo")
	public String mypageHome(Model m, @RequestParam String idx) {
		log.info("idx===" + idx);
		if (idx == null)
			return util.addMsgLoc(m, "�α��������� ������ �ֽ��ϴ�.", "index");

		// �����˻�
		UserVO user = this.mypageService.selectMy(idx);
		m.addAttribute("user", user);

		return "mypage/mypageHome";
	}

	@GetMapping("/mypage/Edit")
	public String mypageEdit(Model m, @RequestParam String idx) {
		log.info("idx===" + idx);
		if (idx == null)
			return "redirect:/mypage";

		// �����˻�
		UserVO user = this.mypageService.selectMy(idx);
		m.addAttribute("user", user);

		return "mypage/mypageHEdit";
	}

	@PostMapping("/mypage/EditEnd")
	public String mypageEditEnd(Model m, @RequestParam String idx, @ModelAttribute("user") UserVO user) {
		if (user.getIdx() == null)
			return "mypage/myhageHome";

		if (user.getStat() == null)
			user.setStat("9");
		;
		int n = this.mypageService.updateUser(user);
		String str = (n > 0) ? "���� ���� �Ϸ�" : "���� ���� ����";
		String loc = (n > 0) ? "MyInfo?idx=" + user.getIdx() : "javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}

	@PostMapping("/mypage/del")
	public String mypageDel(Model m, @RequestParam String idx) {
		if (idx == null) {
			return util.addMsgLoc(m, " ������ �ֽ��ϴ�.", "/index");
		}
		int n = this.mypageService.leaveMember(idx);
		String str = (n>0) ? "Ż�� ó���� �Ϸ�Ǿ����ϴ�.":"Ż�� ����";
		String loc = (n>0) ? "/logout": "javascript:history.back()";
		return util.addMsgLoc(m, str, loc);

	}

}
