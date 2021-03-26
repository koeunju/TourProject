package com.t4er.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t4er.common.CommonUtil;
import com.t4er.user.model.NotUserException;
import com.t4er.user.model.UserSha256;
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
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	
	
	@GetMapping("/join")
	public String joinForm() {

		return "/user/join";
	}

	@PostMapping("/join")
	public String joinEnd(Model m, HttpSession ses, @Valid  @ModelAttribute("user") UserVO user, BindingResult result, HttpServletRequest req) {
		log.info("user=="+user);
		//��ȿ�� üũ�� ���==> BidingResult��ü�� ����.
		//�� ��ü�� ������ ������ �ִٸ�
		if(result.hasErrors()) {
			log.info("��ȿ�� üũ ����");
			return "user/join";

		}

		//��й�ȣ ��ȣȭ(sha256) 
		System.out.println("ù��°:" + user.getPwd());
		String encryPassword = UserSha256.encrypt(user.getPwd());
		user.setPwd(encryPassword);
		System.out.println("�ι�°:" + user.getPwd());
		


		
		//������ ���ٸ� �Ʒ� ���� ����
		int n = this.userService.createUser(user);

		if(n>0) {
			ses.setAttribute("authUser", user);
		}
		
		String str=(n>0)?"ȸ�������� �Ϸ�Ǿ����ϴ�. �̸��� ������ ���ּ���.":"ȸ������ ����";
		String loc=(n>0)?"chkMail":"javascript:history.back()";
		return util.addMsgLoc(m, str, loc);
	}
	
	//ȸ������ ������ �Ѿ�鼭 �̸��� �߼�ó�� 
	@GetMapping("/chkMail")
	public String chkMail(HttpServletRequest req) {
		HttpSession ses = req.getSession();
		UserVO user = (UserVO) ses.getAttribute("authUser");
		
		String email = user.getEmail();
		String id = user.getId();
		
		this.userService.mailSendWithUserKey(email,id, req);
		return"user/joinEnd";
	}

	
	    // �̸��� ���� �Ϸ� 
	    @GetMapping(value="/user/stat_alter")
		public String statAlter(Model m, @RequestParam("id") String id) {

	    this.userService.statAlter(id);

		return "user/joinSuccess";
		
		}
	
	@GetMapping(value="/idcheck",produces = "application/json")
	public @ResponseBody Map<String, String> idCheck(@RequestParam("id") String id){
		boolean isUse=userService.idCheck(id);

		String msg=(isUse)? "��� ������ ���̵��Դϴ�.":"�̹� �����ϴ� ���̵��Դϴ�.";
		int n=(isUse)? 1: -1;
		Map<String, String> map=new HashMap<>();
		map.put("idResult", msg);
		map.put("isUse", String.valueOf(n));
		return map;
	}//---------------------------
	
	@GetMapping(value="/emailcheck",produces = "application/json")
	public @ResponseBody Map<String,String> emailCheck(@RequestParam("email") String email){
		boolean isEma=userService.emailCheck(email);
	
		String msg=(isEma)? "��� ������ �̸����Դϴ�.":"�̹� ��ϵ� �̸����Դϴ�.";
		int n=(isEma)? 1: -1;
		Map<String, String> map=new HashMap<>();
		map.put("emailResult", msg);
		map.put("isEma", String.valueOf(n));
		return map;
	}
	
	@GetMapping(value="/nickcheck",produces = "application/json")
	public @ResponseBody Map<String,String> nickCheck(@RequestParam("nick") String nick){
		boolean isNic=userService.nickCheck(nick);
	
		String msg=(isNic)? "��� ������ �г����Դϴ�.":"�̹� �����ϴ� �г����Դϴ�.";
		int n=(isNic)? 1: -1;
		Map<String, String> map=new HashMap<>();
		map.put("nickResult", msg);
		map.put("isNic", String.valueOf(n));
		return map;
	}
	
	@GetMapping(value="/telcheck",produces = "application/json")
	public @ResponseBody Map<String,String> telCheck(@RequestParam("tel") String tel){
		boolean isTel=userService.telCheck(tel);
	
		String msg=(isTel)? "��� ������ ��ȭ��ȣ�Դϴ�.":"�̹� �����ϴ� ��ȭ��ȣ�Դϴ�.";
		int n=(isTel)? 1: -1;
		Map<String, String> map=new HashMap<>();
		map.put("telResult", msg);
		map.put("isTel", String.valueOf(n));
		return map;
	}
	
	//���̵� ã��
	@GetMapping("/userSearch")
	public String userSearch() {
		
		return "/user/userSearch";
	}
	
	
	@PostMapping("/userSearch")
	@ResponseBody
	public String userSearchEnd(@RequestParam("inputNick") String nick,
			                 @RequestParam("inputEmail") String email) {
		
		String result = userService.searchId(nick, email);

		return result;
	}
	
	//��й�ȣ ã�� 
	@GetMapping("/pwdSearch")
	public String pwdSearch() {
		
		return "/user/pwdSearch";
	}
	
	@GetMapping("/pwdSearchEnd")
	public String pwdSearchEnd(@RequestParam("id") String id,
			                   @RequestParam("email") String email, HttpServletRequest req, Model m) throws NotUserException {
		 
		UserVO user = this.userService.userCheck(id, email);
		
		if (user != null) {
			userService.mailSendPwd(id, email, req);
		}
		
		return "user/pwdSearchEnd";
	}
		
		@ExceptionHandler(NotUserException.class)
		public String exceptionHandler(Exception ex) {
			return  "user/errorAlert";
		}
	
	
}