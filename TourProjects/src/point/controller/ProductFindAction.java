package point.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import common.util.CommonUtil;
import point.model.*;


public class ProductFindAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        String findKeyword = req.getParameter("findKeyword");
        if (findKeyword == null || findKeyword.trim().isEmpty()) {
            findKeyword = (String) session.getAttribute("findKeyword");

            if (findKeyword == null) {
                CommonUtil.addMsgBack(req, "검색어를 입력하세요");
                this.setViewPage("msg.jsp");
                this.setRedirect(false);
                return;
            }
        }
        session.setAttribute("findKeyword", findKeyword);

        String cpStr = req.getParameter("cpage");
        if (cpStr == null || cpStr.trim().isEmpty()) {
            cpStr = "1";
        }

        String psStr = req.getParameter("pageSize");

        if (psStr == null || psStr.trim().isEmpty()) {
            psStr = (String) session.getAttribute("pageSize");
            if (psStr == null || psStr.trim().isEmpty()) {
                psStr = "5";
            }
        }

        int cpage = Integer.parseInt(cpStr.trim());
        if (cpage < 1) {
            cpage = 1;//1페이지를 기본값으로 설정
        }

        // 검색한 총 상품 수 가져오기

        ProductDAOMyBatis dao = new ProductDAOMyBatis();

        int totalCount = dao.getFindTotalCount(findKeyword);

        int pageSize = Integer.parseInt(psStr.trim());

        int pageCount = (totalCount - 1) / pageSize + 1;

        if (cpage > pageCount) {
            cpage = pageCount;
        }

        int end = cpage * pageSize;
        int start = end - (pageSize - 1);

        List<ProductVO> bList = dao.getFindList(start, end, findKeyword);

        int pagingBlock = 5;
        int prevBlock = 0, nextBlock = 0;

        System.out.println("pageCount====" + pageCount);
        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);

        req.setAttribute("totalCount", totalCount);
        req.setAttribute("bList", bList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("cpage", cpage);
        req.setAttribute("pageSize", pageSize);

        req.setAttribute("prevBlock", prevBlock);
        req.setAttribute("nextBlock", nextBlock);
        req.setAttribute("pagingBlock", pagingBlock);

        this.setViewPage("point/pointFind.jsp");
        this.setRedirect(false);

    }

}
