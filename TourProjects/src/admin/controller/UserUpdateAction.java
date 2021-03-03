package admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.controller.AbstractAction;

import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class UserUpdateAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if (!req.getMethod().equalsIgnoreCase("post")) { // post방식이 아니라면
            // equalsIgnoreCase = 대소문자 상관안하고 내용이 post 와 같다면

            this.setRedirect(true);
            this.setViewPage("userList.do");
            return;
        }

        String idx = req.getParameter("idx");
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");


        int idx_int = Integer.parseInt(idx);

        UserVO user = new UserVO(idx_int, id, pwd, email, name, tel, 0, null, 0);

        UserDAOMyBatis dao = new UserDAOMyBatis();

        int n = dao.updateUser(user);


        String str = (n > 0) ? "글수정 성공" : "글수정 실패";
        String loc = (n > 0) ? "userList.do" : "javascript:history.back()";

        req.setAttribute("message", str);
        req.setAttribute("loc", loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);

    }
}
