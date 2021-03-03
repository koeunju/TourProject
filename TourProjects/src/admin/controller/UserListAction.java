package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.UserDAOMyBatis;
import user.model.UserVO;

public class UserListAction extends AbstractAction {

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
                psStr = "5";
            }
        }
        session.setAttribute("pageSize", psStr);

        int cpage = Integer.parseInt(cpStr.trim());
        if (cpage < 1) {
            cpage = 1;
        }


        UserDAOMyBatis dao = new UserDAOMyBatis();

        int totalCount = dao.getUserTotalCount();

        int pageSize = Integer.parseInt(psStr.trim());
        int pageCount = (totalCount - 1) / pageSize + 1;

        if (cpage > pageCount) {
            cpage = pageCount;
        }
        int end = cpage * pageSize;
        int start = end - (pageSize - 1);


        List<UserVO> uList = dao.getUserList(start, end);

        int pagingBlock = 5;
        int prevBlock = 0, nextBlock = 0;

        System.out.println("pageCount====" + pageCount);
        prevBlock = (cpage - 1) / pagingBlock * pagingBlock;
        nextBlock = prevBlock + (pagingBlock + 1);

        req.setAttribute("totalCount", totalCount);
        req.setAttribute("userList", uList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("cpage", cpage);
        req.setAttribute("pageSize", pageSize);

        req.setAttribute("prevBlock", prevBlock);
        req.setAttribute("nextBlock", nextBlock);
        req.setAttribute("pagingBlock", pagingBlock);


        this.setViewPage("admin/userList.jsp");
        this.setRedirect(false);

    }

}