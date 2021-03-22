package mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.UserDAOMyBatis;

public class MypageDelAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String idx = req.getParameter("idx");
		
		UserDAOMyBatis dao = new UserDAOMyBatis();
		int n = dao.leaveMember(idx);
		
		String msg=(n>0)? "탈퇴처리 성공":"탈퇴처리 실패";
		String loc=(n>0)?"logout.do":"javascript:history.back()";
		CommonUtil.addMsgLoc(req, msg, loc);
		this.setViewPage("msg.jsp");
		this.setRedirect(false);
	}

}
