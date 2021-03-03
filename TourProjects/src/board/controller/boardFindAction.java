package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.*;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class boardFindAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        // 검색 유형과 검색어 받기
        // 검색유형과 검색어 받기
        String findType = req.getParameter("findType");
        String findKeyword = req.getParameter("findKeyword");
        if (findType == null || findKeyword == null || findType.equals("0") || findKeyword.trim().isEmpty()) {
            findType = (String) session.getAttribute("findType");
            findKeyword = (String) session.getAttribute("findKeyword");

            if (findType == null || findKeyword == null) {
                CommonUtil.addMsgBack(req, "검색유형과 검색어를 입력하세요");
                this.setViewPage("msg.jsp");
                this.setRedirect(false);
                return;
            }
        }
        session.setAttribute("findType", findType);
        session.setAttribute("findKeyword", findKeyword);


        String cpStr = req.getParameter("cpage");

        if (cpStr == null || cpStr.trim().isEmpty()) {
            cpStr = "1";
        }

        String psStr = req.getParameter("pageSize");

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

        // 1. 검색한 게시글 가져오기
        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        int totalCount = dao.getFindTotalCount(findType, findKeyword);


        int pageSize = Integer.parseInt(psStr.trim()); // trim(앞 뒤 공백 제거)


        int pageCount = (totalCount - 1) / pageSize + 1;

        if (cpage > pageCount) {
            cpage = pageCount; // 마지막 페이지로 지정
        }

        int end = cpage * pageSize;
        int start = end - (pageSize - 1);

        List<BoardVO> bList = dao.getFindList(start, end, findType, findKeyword); // 메소드 이름 getBoardList로 정해줌

        int pagingBlock = 10; // 페이지를 5개 단위로 블럭 처리
        int prevBlock = 0, nextBlock = 0;


        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);


        req.setAttribute("totalCount", totalCount);
        req.setAttribute("boardList", bList); // bList를 저장
        req.setAttribute("pageCount", pageCount); // pageCount를 저장
        req.setAttribute("cpage", cpage);
        req.setAttribute("pageSize", pageSize);

        req.setAttribute("prevBlock", prevBlock);
        req.setAttribute("nextBlock", nextBlock);
        req.setAttribute("pagingBlock", pagingBlock);

        this.setViewPage("board/boardFind.jsp");
        this.setRedirect(false);
    }

}
