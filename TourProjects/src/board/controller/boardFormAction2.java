package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;
import common.controller.AbstractAction;


public class boardFormAction2 extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        List<CategoryVO> cateList = dao.getCategory();
        req.setAttribute("getCategory", cateList);
        req.setAttribute("msg", "index");

        this.setViewPage("board/boardInsert2.jsp");
        this.setRedirect(false);
    }

}
