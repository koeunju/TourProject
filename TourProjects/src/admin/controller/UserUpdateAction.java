package admin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.controller.AbstractAction;

import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class UserUpdateAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String myidx = req.getParameter("idx");
        String name = req.getParameter("myname");
        String pwd = req.getParameter("mypwd");
        String email = req.getParameter("myemail");
        String tel = req.getParameter("mytel");
        String st = req.getParameter("mystat");
        String po = req.getParameter("mypoint");

        if (myidx == null || myidx.trim().isEmpty()) {
            this.setViewPage("mypageHEdit.do");
            this.setRedirect(true);
            return;
        }
        int idx = Integer.parseInt(myidx);
        int stat = Integer.parseInt(st);
        int point = Integer.parseInt(po);

        // 여기 버튼 인식되게 수정하기!

        UserDAOMyBatis dao = new UserDAOMyBatis();
        UserVO user = new UserVO(idx, null, pwd, email, name, tel, stat, null, point);
        int n = dao.updateUser(user);

        String str = (n > 0) ? "유저수정 성공" : "유저수정 실패";
        String loc = (n > 0) ? "userList.do" : "javascript:history.back()";

        req.setAttribute("message", str);
        req.setAttribute("loc", loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);

    }
}
