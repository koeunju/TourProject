package mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class MypageHEditEndAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String myidx = req.getParameter("idx");
        String name = req.getParameter("myname");
        String pwd = req.getParameter("mypwd");
        String email = req.getParameter("myemail");
        String tel = req.getParameter("mytel");

        if (myidx == null || myidx.trim().isEmpty()) {
            this.setViewPage("mypageHEdit.do");
            this.setRedirect(true);
            return;
        }
        int idx = Integer.parseInt(myidx);

        // 여기 버튼 인식되게 수정하기!

        System.out.println("ttest");

        UserDAOMyBatis dao = new UserDAOMyBatis();
        UserVO user = new UserVO(idx, null, pwd, email, name, tel, 0, null, 0);
        int n = dao.updateMyInfo(user);

        this.setViewPage("mypageHome.do?idx=" + myidx);
        this.setRedirect(true);
    }

}
