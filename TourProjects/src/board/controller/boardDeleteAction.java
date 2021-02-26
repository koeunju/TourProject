package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class boardDeleteAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String bnum = req.getParameter("bnum");

        if (bnum == null || bnum.trim().isEmpty()) {
            this.setViewPage("boardList.do");
            this.setRedirect(true);
            return;
        }

        BoardDAOMyBatis dao = new BoardDAOMyBatis();
        BoardVO board = dao.getBoard(bnum);

        int n = dao.deleteBoard(bnum);

        String msg = (n > 0) ? "삭제 성공" : "삭제 실패";
        String loc = (n > 0) ? "boardList.do" : "javascript:history.back()";
        CommonUtil.addMsgLoc(req, msg, loc);
        this.setViewPage("msg.jsp");
        this.setRedirect(false);

    }

}
