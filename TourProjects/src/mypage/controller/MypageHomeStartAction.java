package mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MypageHomeStartAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 회원정보 불러오기
				HttpSession ses=req.getSession();
				UserVO loginUser=(UserVO) ses.getAttribute("loginUser");
				String idx=loginUser.getIdx()+"";
				
				// 현재 상태 검색
				UserDAOMyBatis userdao = new UserDAOMyBatis();
				
				List<UserVO> uList = userdao.selectMy(idx);

				this.setViewPage("mypageHome.do");
				this.setRedirect(false);

	}

}
