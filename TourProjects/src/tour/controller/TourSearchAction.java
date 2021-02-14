package tour.controller;

import common.controller.AbstractAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourSearchAction extends AbstractAction {

    public TourSearchAction() {

    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setAttribute("msg", "index");

        this.setViewPage("tour/tour.jsp");
        this.setRedirect(false);
    }
}
