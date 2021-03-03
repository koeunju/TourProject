package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;

public class UserDeleteAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("idx");
        if (idx == null || idx.trim().isEmpty()) {
            this.setViewPage("userList.do");
            this.setRedirect(true);
            return;
        }
        UserDAOMyBatis dao = new UserDAOMyBatis();
        // UserVO user=dao.getUser(idx);

        int n = dao.deleteUser(idx);
        String str = (n > 0) ? "삭제성공" : "삭제실패";

        req.setAttribute("message", str);
        req.setAttribute("loc", "userList.do");

        this.setViewPage("msg.jsp");
        this.setRedirect(false);

    }

}
