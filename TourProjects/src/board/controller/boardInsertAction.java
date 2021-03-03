package board.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.*;
import common.controller.AbstractAction;
import common.util.CommonUtil;

import java.io.File;


public class boardInsertAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        if (!req.getMethod().equalsIgnoreCase("post")) {
            // post방식이 아니라면
            this.setRedirect(true);
            this.setViewPage("boardInsert.do");
            return;
        }

        // 업로드 할 디렉토리 생성
        String path = "board/Upload"; // 폴더 경로
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성
        if (!Folder.exists()) {
            try {
                Folder.mkdir(); // 폴더 생성

            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        // 업로드할 디렉토리 절대경로
        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("board/Upload");// 경로에 폴더 없음
        System.out.println(upDir);

        // 파일 업로드
        DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
        MultipartRequest mr = new MultipartRequest(req, upDir, 100 * 1024 * 1024, "UTF-8", df);

        // 게시판
        String btitle = mr.getParameter("btitle");// 글제목
        String bcontent = mr.getParameter("bcontent");// 글내용

        String bupload1 = mr.getFilesystemName("bupload1");// 업로드1
        String bupload2 = mr.getFilesystemName("bupload2");// 업로드2
        String bupload3 = mr.getFilesystemName("bupload3");// 업로드3

        // 후처리
        BoardVO board = new BoardVO(0, btitle, bcontent, 0, null, bupload1, bupload2, bupload3, 0, 0, 0);

        BoardDAOMyBatis dao = new BoardDAOMyBatis();
        int n = dao.insertBoard(board);

        String str = (n > 0) ? "글쓰기 성공" : "글쓰기 실패";
        String loc = (n > 0) ? "boardList.do" : "javascript:history.back()";

        CommonUtil.addMsgLoc(req, str, loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);
    }
}