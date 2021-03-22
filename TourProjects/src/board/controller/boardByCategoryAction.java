package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;
import common.controller.AbstractAction;

public class boardByCategoryAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {
        String Code = req.getParameter("Code");
        if (Code == null) {
            this.setViewPage("index.do");
            this.setRedirect(true);
            return;
        }
        BoardDAOMyBatis dao = new BoardDAOMyBatis();
        List<CategoryVO> bList = dao.selectByCategory(Code);
        req.setAttribute("boardList", bList);
        this.setViewPage("board/boardList.jsp");
        this.setRedirect(false);
    }
}
