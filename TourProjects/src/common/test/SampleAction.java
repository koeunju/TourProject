package common.test;

import common.controller.AbstractAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws  Exception {
        System.out.println("SampleAction의 execute()");

        SampleDAOMyBatis dao = new SampleDAOMyBatis();

        int count = dao.getTableCount();

        req.setAttribute("msg", "안녕 Sample");
        req.setAttribute("count", count);

        this.setViewPage("/test.jsp");
        this.setRedirect(false); // redirect
    }
}
