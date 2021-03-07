package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.CategoryVO;
import common.controller.AbstractAction;

import java.util.List;


public class boardFormAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        List<CategoryVO> cateList = dao.getCategory();
        req.setAttribute("getCategory", cateList);

        this.setViewPage("board/boardInsert.jsp");
        this.setRedirect(false);
    }

}
