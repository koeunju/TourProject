package point.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import point.model.*;

public class ProductByCategoryAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        String cgnum = req.getParameter("cgnum");
        if (cgnum == null) {
            this.setViewPage("index.do");
            this.setRedirect(true);
            return;
        }

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

        session.setAttribute("pageSize", psStr);

        int cpage = Integer.parseInt(cpStr.trim());
        if (cpage < 1) {
            cpage = 1;
        }

        ProductDAOMyBatis dao = new ProductDAOMyBatis();
        int totalCount = dao.getProductTotalCount();

        int pageSize = Integer.parseInt(psStr.trim());

        int pageCount = (totalCount - 1) / pageSize + 1;

        if (cpage > pageCount) {
            cpage = pageCount;
        }

        int end = cpage * pageSize;
        int start = end - (pageSize - 1);

        List<ProductVO> bList = dao.getProdList(start, end);

        int pagingBlock = 5;
        int prevBlock = 0, nextBlock = 0;

        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);

        List<ProductVO> pList = dao.selectByCategory(cgnum);
        List<Product_CategoryVO> clist = dao.getCategory();

        req.setAttribute("totalCount", totalCount);
        req.setAttribute("bList", bList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("cpage", cpage);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("prevBlock", prevBlock);
        req.setAttribute("nextBlock", nextBlock);
        req.setAttribute("pagingBlock", pagingBlock);

        req.setAttribute("prodList", pList);
        req.setAttribute("cList", clist);
        this.setViewPage("point/mallByCategory.jsp");
        this.setRedirect(false);
    }
}
