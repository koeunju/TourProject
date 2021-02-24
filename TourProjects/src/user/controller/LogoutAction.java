package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;

public class LogoutAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 로그 아웃 처리 로직
        HttpSession ses = req.getSession();

        ses.invalidate();

        String myctx = req.getContextPath();
        this.setViewPage(myctx + "/index.do");
        this.setRedirect(true);

    }

}