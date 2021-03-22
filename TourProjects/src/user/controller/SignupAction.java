package user.controller;

import common.controller.AbstractAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupAction extends AbstractAction {

    public SignupAction() {

    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
   
        this.setViewPage("user/join.jsp");
        this.setRedirect(false);
    }
}
