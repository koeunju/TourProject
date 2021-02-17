package tour.controller;

import common.controller.AbstractAction;
import tour.api.TourAPIKeyword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourSearchAction extends AbstractAction {

    public TourSearchAction() {

    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String keyword = req.getParameter("keyword");

        if(keyword == null) {
            keyword = "";
        }

        TourAPIKeyword api = new TourAPIKeyword();
        api.TourAPIKeyword(keyword);

        System.out.println("keyword1= " + keyword);

        if(keyword != null) {
            this.setViewPage("tourSearch.do");
            this.setRedirect(true);
        }
        req.setAttribute("msg", "index");

        this.setViewPage("tour/tourKeyword.jsp");
        this.setRedirect(false);
    }
}
