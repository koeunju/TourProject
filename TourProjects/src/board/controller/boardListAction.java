package board.controller;

import java.util.List;

import common.controller.AbstractAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.*;

public class boardListAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        BoardDAOMyBatis dao = new BoardDAOMyBatis();
               
        List<BoardVO> bList = dao.getBoardList();
        
        req.setAttribute("boardList", bList);

        this.setViewPage("/board/boardList.jsp");
        this.setRedirect(false);
    }
}