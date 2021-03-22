package board.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.*;
import common.controller.AbstractAction;
import common.util.CommonUtil;

public class boardEditAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if (!req.getMethod().equalsIgnoreCase("post")) {
            // post방식이 아니라면
            this.setRedirect(true);
            this.setViewPage("boardEdit.do");
            return;
        }

        // 업로드할 디렉토리 절대경로
        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("board/Upload");
        System.out.println(upDir);

        File dir = new File(upDir);
        if (dir == null || !dir.exists()) {
            dir.mkdirs();
        }

        // 파일 업로드
        MultipartRequest mr = null;
        try {
            DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
            mr = new MultipartRequest(req, upDir, 100 * 1024 * 1024, "UTF-8", df);
        } catch (Exception e) {
            CommonUtil.addMsgBack(req, "업로드 실패-파일 용량 초과 확인하세요");
            e.printStackTrace();
            this.setViewPage("msg.jsp");
            this.setRedirect(false);
            return;
        }
        // 게시판
        String bnum = mr.getParameter("bnum");
        int bnum_i = Integer.parseInt(bnum);
        String cg_num = mr.getParameter("upCg_code");
        int cg_num_i = Integer.parseInt(cg_num); //게시판 분류
        String btitle = mr.getParameter("btitle");// 글제목
        String bcontent = mr.getParameter("bcontent");// 글내용
        String bupload1 = mr.getFilesystemName("bupload1");// 업로드1
        String bupload2 = mr.getFilesystemName("bupload2");// 업로드2
        String bupload3 = mr.getFilesystemName("bupload3");// 업로드3

        // 후처리
        BoardVO board = new BoardVO(bnum_i, btitle, bcontent, bupload1, bupload2, bupload3, cg_num_i);

        BoardDAOMyBatis dao = new BoardDAOMyBatis();

        int n = dao.editBoard(board);

        String str = (n > 0) ? "글수정 성공" : "글쓰기 실패";
        String loc = (n > 0) ? "boardList.do" : "javascript:history.back()";

        CommonUtil.addMsgLoc(req, str, loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);
    }

}
