package admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class PointShopListAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        this.setViewPage("admin/shopList.jsp");
        this.setRedirect(false);
    }
}
