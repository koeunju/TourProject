package tour.controller;

import common.controller.AbstractAction;
import tour.api.TourAPIDt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourDetailAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String contentId = req.getParameter("contentid");

        if (contentId == null || contentId.trim().isEmpty()) {
            contentId = "126508";
        }

        TourAPIDt api = new TourAPIDt();

        String dtInfo = api.TourAPIDt(contentId);

        req.setAttribute("dtInfo", dtInfo);

        this.setViewPage("tour/tourDetail.jsp");
        this.setRedirect(false);
    }
}
