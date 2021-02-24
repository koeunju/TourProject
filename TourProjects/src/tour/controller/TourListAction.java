package tour.controller;

import common.controller.AbstractAction;
import tour.api.TourAPIKeyword;
import tour.model.TourVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TourListAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String keyword = req.getParameter("keyword");

        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "서울";
        }

        TourAPIKeyword api = new TourAPIKeyword();
        
        String searchKeyword = api.TourAPIKeyword(keyword);

        req.setAttribute("searchKeyword", searchKeyword);

        this.setViewPage("tour/tourList.jsp");
        this.setRedirect(false);
    }
}
