package user.controller;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import user.model.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginEndAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 로그인 처리 로직
        String myctx = req.getContextPath();

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String saveId = req.getParameter("saveId");

        if (id == null || pwd == null) {
            String msg = "아이디와 비밀번호를 다시 확인하세요";
            String loc = myctx + "/login.do";
            CommonUtil.addMsgLoc(req, msg, loc);
            this.setViewPage(myctx + "/msg.jsp");
            this.setRedirect(false);
            return;
        }

        UserDAOMyBatis dao = new UserDAOMyBatis();
        String stat = dao.checkState(id);

        if (stat.equals("4")) {
            String msg = "탈퇴회원입니다. 고객센터에 문의하여 주십시오";
            String loc = "index.do"; // 일단은 메인페이지로 가게 고객센터 완성되면 고객센터로
            CommonUtil.addMsgLoc(req, msg, loc);
            this.setViewPage("/msg.jsp");
            this.setRedirect(false);
            return;
        }

        UserVO user = dao.loginCheck(id, pwd);
        // user 가 null이 아니라면=> 회원 인증받은 것이므로
        // 세션에 user를 저장 (loginUser라는 key로 저장하기)
        HttpSession ses = req.getSession();
        if (user != null) {
            ses.setAttribute("loginUser", user);
            // 세션에 저장
            // 아이디 저장에 체크했다면/안했다면
            Cookie ck = new Cookie("uid", user.getId());
            if (saveId != null) {
                ck.setMaxAge(7 * 24 * 60 * 60); // 7일간 보관
            } else {
                ck.setMaxAge(0); // 쿠키 삭제
            }
            ck.setPath("/");
            res.addCookie(ck);

        } else {
            this.setRedirect(false);
            this.setViewPage("/msg.jsp");
            return;

        }

        this.setViewPage(myctx + "/index.do");
        this.setRedirect(true);
    }

}
