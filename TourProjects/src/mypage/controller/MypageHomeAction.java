package mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MypageHomeAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("idx");

        UserDAOMyBatis userdao = new UserDAOMyBatis();
        List<UserVO> uList = userdao.selectMy(idx);
        req.setAttribute("user", uList);

        this.setViewPage("/mypage/mypageHome.jsp");
        this.setRedirect(false);

    }

}
