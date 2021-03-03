package mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;

public class MypageWriteAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        this.setViewPage("/mypage/mypageWrite.jsp");
        this.setRedirect(false);
    }
}
