package user.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;

public class IdCheckEndAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String id = req.getParameter("id");

        UserDAOMyBatis dao = new UserDAOMyBatis();
        int isUse = dao.idCheck(id);

        req.setAttribute("isUse", isUse);
        req.setAttribute("id", id);

        this.setViewPage("user/idCheckEnd.jsp");
        this.setRedirect(false);


    }

}
