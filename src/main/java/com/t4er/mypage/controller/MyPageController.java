package com.t4er.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;
import com.t4er.common.CommonUtil;
import com.t4er.mypage.service.MypageService;
import com.t4er.point.model.PointPagingVO;
import com.t4er.point.model.PointVO;
import com.t4er.tour.model.TourVO;
import com.t4er.user.model.UserVO;
import com.t4er.user.security.UserSha256;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user")
public class MyPageController {

	@Autowired
	private MypageService mypageService;
	@Autowired
	private CommonUtil util;

	@GetMapping("/myInfo")
	public String mypageHome(Model m, @RequestParam Integer idx, HttpServletRequest req,
			@RequestHeader("User-Agent") String userAgent, @ModelAttribute("paging") PointPagingVO paging) {
		if (idx == null)
			return util.addMsgLoc(m, "잘못된 접근입니다.", "/myInfo");

		paging.setPagingBlock(5); // 페이지에 들어가는 수
		paging.init(req.getSession());
		paging.setIdx(idx);
		int totalCount = this.mypageService.myTotalCountPoint(paging); // 전체 개수
		paging.setPointTotalCount(totalCount);
		System.out.println(totalCount + "||||||||||" + paging.getIdx());

		// 포인트 목록 가져오기
		List<PointVO> pointList = this.mypageService.mypoint(paging);

		// 내 최종 포인트
		String mypoint = this.mypageService.myTotalPoint(idx);

		m.addAttribute("mytotalpoint", mypoint);
		m.addAttribute("mypoint", pointList);
		log.info("idx===" + idx);


		// 정보검색
		UserVO user = this.mypageService.selectMy(idx);
		String mypoint2 = this.mypageService.myTotalPoint(idx);
		m.addAttribute("user", user);
		m.addAttribute("mytotalpoint", mypoint);

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

		// 비밀번호 암호화 로직 수행
		String encryPassword = UserSha256.encrypt(user.getPwd());
		user.setPwd(encryPassword);

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
	public String myPoint(Model m, @RequestParam Integer idx, HttpServletRequest req,
			 @ModelAttribute("paging") PointPagingVO paging) {
		if (idx == null)
			return util.addMsgLoc(m, "잘못된 접근입니다.", "/myInfo");
		
		int totalCount = this.mypageService.myTotalCountPoint(paging); // 전체 개수
		paging.setPointTotalCount(totalCount);
		paging.setPagingBlock(5); // 페이지에 들어가는 수
		paging.init(req.getSession());
		paging.setIdx(idx);
		
		// 포인트 목록 가져오기
		List<PointVO> pointList = this.mypageService.mypoint(paging);
		String myctx = req.getContextPath();
		String loc = "user/mypoint";
		String pageNavi = paging.getPageNavi(myctx, loc, idx);

		// 내 최종 포인트
		String mypoint = this.mypageService.myTotalPoint(idx);

		m.addAttribute("mytotalpoint", mypoint);
		m.addAttribute("mypoint", pointList);
		m.addAttribute("pageNavi", pageNavi);

		return "user/mypage/mypoint";
	}

	/* 내가 쓴 글 조회 */
	@GetMapping("/write")
	public String mywrite(Model m, @RequestParam("idx") Integer idx, HttpServletRequest req,
			 @ModelAttribute("paging") BoardPagingVO paging) {

		log.info("mywrite?idx===" + idx);
		if (idx == null)
			return util.addMsgLoc(m, "잘못된 접근입니다.", "/myInfo");
		int totalCount = this.mypageService.myTotalCountBoard(paging); // 전체 개수
		paging.setTotalCount(totalCount);
		paging.setPagingBlock(5); // 페이지에 들어가는 수
		paging.init(req.getSession());
		paging.setIdx(idx);
		
		
		List<BoardVO> board = this.mypageService.selMyBoard(paging);
		String myctx = req.getContextPath();
		String loc = "user/write";
		String pageNavi = paging.getPageNavi(myctx, loc, idx);


		m.addAttribute("board", board);
		m.addAttribute("pageNavi", pageNavi);
		
		return "/user/mypage/mypageWrite";
	}

	/* 비밀번호 체크 */
	@GetMapping(value = "/pwdcheck", produces = "application/json")
	public @ResponseBody Map<String, String> pwdCheck(@RequestParam int idx, @RequestParam String pwd) {
		log.info("idx/pwd==" + idx + "/" + pwd);

		String encryPassword = UserSha256.encrypt(pwd);

		boolean check = this.mypageService.pwdCheck(idx, encryPassword);

		String msg = (check) ? "비밀번호 변경 가능합니다." : "비밀번호가 일치하지 않습니다.";
		int n = (check) ? 1 : -1;

		Map<String, String> map = new HashMap<>();
		map.put("okPwd", msg);
		map.put("check", String.valueOf(n));

		return map;
	}
	
	   /*찜한 여행지*/
    @GetMapping("/myTour")
    public String mytour(Model m,@RequestParam Integer idx) {
    	log.info("idx=="+idx);
    	List<TourVO> mytour = this.mypageService.myTour(idx);
    	System.out.println(mytour);
    	m.addAttribute("mytour",mytour);
    	
    	return "/user/mypage/myTour";
    }
}
