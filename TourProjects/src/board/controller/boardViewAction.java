package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;
import common.controller.AbstractAction;

import java.util.List;

public class boardViewAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String idx = req.getParameter("bnum");
        if (idx == null || idx.trim().isEmpty()) {
            this.setViewPage("boardList.do");
            this.setRedirect(true);
            return;
        }

        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        boolean bool = dao.updateReadnum(idx.trim());

        BoardVO board = dao.getBoard(idx.trim());
        List<CategoryVO> cateList = dao.getCategory();

        req.setAttribute("getCategory", cateList);
        req.setAttribute("board", board);

        this.setViewPage("board/boardView.jsp");
        this.setRedirect(false);
    }

}