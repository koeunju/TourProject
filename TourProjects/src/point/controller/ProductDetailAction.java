package point.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import point.model.*;

public class ProductDetailAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String pnum = req.getParameter("pnum");

        if (pnum == null || pnum.trim().isEmpty()) {
            this.setViewPage("index.do");
            this.setRedirect(true);
            return;
        }

        ProductDAOMyBatis dao = new ProductDAOMyBatis();

        ProductVO item = dao.selectByPnum(pnum);

        req.setAttribute("item", item);

        this.setViewPage("point/productDetail.jsp");
        this.setRedirect(false);

    }

}
