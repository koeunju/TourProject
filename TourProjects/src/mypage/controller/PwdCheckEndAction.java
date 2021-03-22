package mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class PwdCheckEndAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("getidx");
        String pwd = req.getParameter("pwd");
        System.out.println(idx + "/////" + pwd);
        UserDAOMyBatis dao = new UserDAOMyBatis();
        UserVO samepwd = dao.checkPwd(idx);
        int result = 0;
        if (samepwd.getPwd().equals(pwd)) {
            result = 1;
        }
        System.out.println(result);
        req.setAttribute("res", result);

        this.setViewPage("mypage/pwdCheckEnd.jsp");
        this.setRedirect(false);
    }

}
