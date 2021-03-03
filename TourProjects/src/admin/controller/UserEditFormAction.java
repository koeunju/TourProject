
package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;


public class UserEditFormAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("idx");

        if (idx == null || idx.trim().isEmpty()) {
            this.setRedirect(true);
            this.setViewPage("userList.do");
            return;
        }
        UserDAOMyBatis dao = new UserDAOMyBatis();
        UserVO user = dao.getUser(idx);

        req.setAttribute("user", user);
        this.setViewPage("admin/userEdit.jsp");
        this.setRedirect(false);

    }

}
