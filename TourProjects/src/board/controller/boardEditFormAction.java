package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;
import common.controller.AbstractAction;


public class boardEditFormAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String bnum = req.getParameter("bnum");

        if (bnum == null || bnum.trim().isEmpty()) {
            this.setRedirect(true);
            this.setViewPage("boardList.do");
            return;
        }

        BoardDAOMyBatis dao = new BoardDAOMyBatis();
        BoardVO board = dao.getBoard(bnum);

        req.setAttribute("board", board);
        this.setViewPage("board/boardEdit.jsp");
        this.setRedirect(false);
    }
}
