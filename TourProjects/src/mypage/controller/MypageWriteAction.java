package mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class MypageWriteAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession ses = req.getSession();//세션 불러오기
        
    	UserVO user = (UserVO) ses.getAttribute("loginUser");
    	int idx = user.getIdx();
    	
    	BoardDAOMyBatis dao = new BoardDAOMyBatis();
    	List<BoardVO> board = dao.selectMyWrite(idx);
    	
    	req.setAttribute("board", board);
    	
        this.setViewPage("/mypage/mypageWrite.jsp");
        this.setRedirect(false);
    }
}
