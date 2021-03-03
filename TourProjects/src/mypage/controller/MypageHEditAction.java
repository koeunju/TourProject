package mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MypageHEditAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        this.setViewPage("/mypage/mypageHEdit.jsp");
        this.setRedirect(false);

    }

}
