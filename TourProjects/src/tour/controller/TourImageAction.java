package tour.controller;

import common.controller.AbstractAction;
import tour.api.TourAPIImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourImageAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String contentId = req.getParameter("contentid");

        if (contentId == null || contentId.trim().isEmpty()) {
            contentId = "126508";
        }

        TourAPIImage api = new TourAPIImage();

        String tourImg = api.TourAPIImage(contentId);

        req.setAttribute("tourImg", tourImg);

        this.setViewPage("tour/tourImg.jsp");
        this.setRedirect(false);

    }
}
