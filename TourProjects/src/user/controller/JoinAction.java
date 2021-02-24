package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.UserVO;
import user.model.UserDAOMyBatis;

public class JoinAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //1. 서버로부터 값 호출받아오기 예) String username = req.getParmeter("userName");//괄호안에 값은 jsp에서 서버로 넘길때 그 id값 입력하시면 될거에요

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String tel = req.getParameter("tel");

        UserVO vo = new UserVO(0, id, pwd, email, name, tel, 0, null, 0);

        //2. 유효성 체크


        //3. userDAOMyBatis통해서 insert액션 실행하기
        UserDAOMyBatis dao = new UserDAOMyBatis();

        int n = dao.createUser(vo);
        String str = (n > 0) ? "회원가입 성공" : "회원가입 실패";
        String loc = (n > 0) ? "index.do" : "javascript:history.back()";

        req.setAttribute("message", str);
        req.setAttribute("loc", loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);
    }

}