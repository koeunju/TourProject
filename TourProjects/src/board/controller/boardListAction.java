package board.controller;

import java.util.List;

import common.controller.AbstractAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.*;

public class boardListAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String cpStr = req.getParameter("cpage");

        if (cpStr == null || cpStr.trim().isEmpty()) {
            cpStr = "1";
        }

        String psStr = req.getParameter("pageSize");
        HttpSession session = req.getSession();

        if (psStr == null || psStr.trim().isEmpty()) {

            psStr = (String) session.getAttribute("pageSize");
            if (psStr == null || psStr.trim().isEmpty()) {
                psStr = "10";
            }
        }
        session.setAttribute("pageSize", psStr);

        int cpage = Integer.parseInt(cpStr.trim());
        if (cpage < 1) {
            cpage = 1;
        }

        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        //페이지 카운트
        int totalCount = dao.getBoardTotalCount();
        int pageSize = Integer.parseInt(psStr.trim());

        int pageCount = (totalCount - 1) / pageSize + 1;

        if (cpage > pageCount) {
            cpage = pageCount; //마지막 페이지로 지정
        }

        int end = cpage * pageSize;
        int start = end - (pageSize - 1);

        List<BoardVO> bList = dao.getBoardList(start, end);

        int pagingBlock = 10;
        int prevBlock = 0, nextBlock = 0;

        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);

        req.setAttribute("totalCount", totalCount);
        req.setAttribute("boardList", bList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("cpage", cpage);
        req.setAttribute("pageSize", pageSize);

        req.setAttribute("prevBlock", prevBlock);
        req.setAttribute("nextBlock", nextBlock);
        req.setAttribute("pagingBlock", pagingBlock);

        this.setViewPage("/board/boardList.jsp");
        this.setRedirect(false);
    }
}