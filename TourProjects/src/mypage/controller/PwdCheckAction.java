package mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class PwdCheckAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("idx");

        req.setAttribute("idx", idx);
        this.setViewPage("mypage/pwdCheck.jsp");
        this.setRedirect(false);

    }
}
