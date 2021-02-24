package tour.controller;

import common.controller.AbstractAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourDetailAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        this.setRedirect(false);
        this.setViewPage("tour/tourDetail.jsp");

    }
}
