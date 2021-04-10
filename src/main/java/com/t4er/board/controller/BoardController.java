package com.t4er.board.controller;

import com.t4er.board.model.BoardPagingVO;
import com.t4er.board.model.BoardVO;
import com.t4er.board.service.BoardService;
import com.t4er.common.CommonUtil;
import com.t4er.user.model.UserVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommonUtil util;

    //글 작성
    @GetMapping("/insert")
    public String boardForm(Model m, HttpServletRequest req) {

        HttpSession ses = req.getSession();
        UserVO user = (UserVO) ses.getAttribute("loginUser");
        int idx = user.getIdx();

        m.addAttribute("idx", idx);

        return "board/boardInsert";
    }

    //글 작성
    @GetMapping("/insert2")
    public String boardForm2() {
        return "board/boardInsert2";

    }

    //글작성
    @PostMapping("/insert")
    public String boardInsert(Model m,
                              HttpServletRequest req,
                              @RequestParam("mfilename") MultipartFile mfilename,//여기가 문제가 맞는
                              @ModelAttribute("board") BoardVO board, @RequestParam("idx") Integer idx) {
        log.info("mode===" + board.getMode());
        log.info("board==" + board);

        // 업로드 디렉토리의 절대경로
        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("/board/upload");
        log.info("upDir==" + upDir);

        File dir = new File(upDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리 생성
        }

        // 파일첨부
        if (!mfilename.isEmpty()) {
            // 1.첨부파일명, 파일크기
            String originFile = mfilename.getOriginalFilename(); // 파일이름
            long fsize = mfilename.getSize(); // 파일크기
            log.info(originFile + ">>>" + fsize);

            // 동일한 파일명일 경우 덮어쓰기 방지를 위해서
            // 물리적 파일명을 "랜덤한문자열_원본파일명"
            UUID uuid = UUID.randomUUID();
            String filename = uuid.toString() + "_" + originFile;
            log.info("filename===" + filename);

            board.setOriginFilename(originFile); // 원본파일명
            board.setFilename(filename); // 물리적 파일명
            board.setFilesize(fsize); // 파일크기

            // 2.업로드 처리
            try {
                mfilename.transferTo(new File(dir, filename));
            } catch (IOException e) {
                log.error("파일 업로드 중 에러 발생 : " + e);
            }
        }

        log.info("boardService==" + boardService);
        String mode = board.getMode();
        int n = 0;
        String str = "";
        if (mode.equals("insert")) {
            n = boardService.insertBoard(board);
        } else if (mode.equals("edit")) {
            n = boardService.updateBoard(board);
        } else if (mode.equals("reInsert")) {
            n = boardService.reInsertBoard(board);
        }
        log.info("mode = " + mode);
        log.info("n = " + n);
        String cg_num = board.getCg_num();
        str += (n > 0) ? "성공" : "실패";

        String loc = (n > 0) ? "list?cg_num=" + cg_num : "javascript:history.back()";

        m.addAttribute("msg", str);
        m.addAttribute("loc", loc);

        return "message";
    }//----------------------------------------

    @PostMapping("/insert2")
    public String boardInsert2(Model m,
                               HttpServletRequest req,
                               @RequestParam("mfilename") MultipartFile mfilename,//여기가 문제가 맞는
                               @ModelAttribute("board") BoardVO board) {
        log.info("mode===" + board.getMode());
        log.info("board==" + board);

        // 업로드 디렉토리의 절대경로
        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("/board/upload");
        log.info("upDir==" + upDir);

        File dir = new File(upDir);
        if (!dir.exists()) {
            dir.mkdirs(); // 디렉토리 생성
        }

        // 파일첨부
        if (!mfilename.isEmpty()) {
            // 1.첨부파일명, 파일크기
            String originFile = mfilename.getOriginalFilename(); // 파일이름
            long fsize = mfilename.getSize(); // 파일크기
            log.info(originFile + ">>>" + fsize);

            // 동일한 파일명일 경우 덮어쓰기 방지를 위해서
            // 물리적 파일명을 "랜덤한문자열_원본파일명"
            UUID uuid = UUID.randomUUID();
            String filename = uuid.toString() + "_" + originFile;
            log.info("filename===" + filename);

            board.setOriginFilename(originFile); // 원본파일명
            board.setFilename(filename); // 물리적 파일명
            board.setFilesize(fsize); // 파일크기

            // 2.업로드 처리
            try {
                mfilename.transferTo(new File(dir, filename));
            } catch (IOException e) {
                log.error("파일 업로드 중 에러 발생 : " + e);
            }
        }

        log.info("boardService==" + boardService);
        String mode = board.getMode();
        int n = 0;
        String str = "";
        String loc = "";
        if (mode.equals("insert")) {
            n = boardService.insertBoard(board);
        } else if (mode.equals("edit")) {
            n = boardService.updateBoard(board);
        } else if (mode.equals("reInsert")) {
            n = boardService.reInsertBoard(board);
        }
        log.info("mode = " + mode);
        log.info("n = " + n);

        str += (n > 0) ? "성공" : "실패";
        loc = (n > 0) ? "list2" : "javascript:history.back()";

        m.addAttribute("msg", str);
        m.addAttribute("loc", loc);

        return "message";
    }//----------------------------------------

    //글 목록
    //자유게시판
    @GetMapping("/list")
    public String boardListPaging(Model m, HttpServletRequest req, @RequestHeader("User-Agent") String userAgent,
                                  @ModelAttribute("paging") BoardPagingVO paging, @RequestParam Integer cg_num) {
        log.info("paging===" + paging);
        log.info("cg_num==" + cg_num);
        if (cg_num == null) {
            cg_num = 1;
        }
        // 총 게시글 수 가져오기
        int totalCount = this.boardService.getTotalCount(paging);
        log.info("totalCount=" + totalCount);
        paging.setTotalCount(totalCount);

        paging.setPagingBlock(5);//단위값 =>5
        paging.init(req.getSession());
        List<BoardVO> bArr = null;
        //게시 목록 가져오기
        String loc = "";
        if (cg_num == 2) { //고객센터 리스트 출력
            bArr = this.boardService.selectBoardAllPaging2(paging);
            loc = "board/list?cg_num=2";
        } else { //그 외에 리스트 출력
            bArr = this.boardService.selectBoardAllPaging(paging);// start, end
            loc = "board/list?cg_num=1";
        }

        String myctx = req.getContextPath();

        String pageNavi = paging.getPageNavi(myctx, loc, userAgent);

        //카테고리 번호 조회하기
        m.addAttribute("cg_num", cg_num);
        m.addAttribute("boardList", bArr);
        m.addAttribute("pageNavi", pageNavi);

        return "board/boardList";
    }//----------------------------------------

    //고객센터
    @GetMapping("/list2")
    public String boardListPaging2(Model m, HttpServletRequest req, @RequestHeader("User-Agent") String userAgent,
                                   @ModelAttribute("paging") BoardPagingVO paging) {
        log.info("paging===" + paging);
        // 총 게시글 수 가져오기
        int totalCount = this.boardService.getTotalCount(paging);
        log.info("totalCount=" + totalCount);
        paging.setTotalCount(totalCount);

        paging.setPagingBlock(5);//단위값 =>5
        paging.init(req.getSession());

        //게시 목록 가져오기
        List<BoardVO> bArr = this.boardService.selectBoardAllPaging2(paging);// start, end

        String myctx = req.getContextPath();
        String loc = "board/list2";
        String pageNavi = paging.getPageNavi(myctx, loc, userAgent);


        m.addAttribute("boardList2", bArr);
        m.addAttribute("pageNavi", pageNavi);

        return "board/boardList2";
    }//----------------------------------------

    //글 상세보기
    @GetMapping("/view")
    public String boardView(Model m, @RequestParam int bnum) {
        log.info("bnum==" + bnum);
        if (bnum == 0) {
            return "redirect:list";
        }
        this.boardService.updateReadnum(bnum);
        BoardVO board = this.boardService.selectBoardBybnum(bnum);

        m.addAttribute("board", board);

        return "board/boardView";
    }// -------------------------------------

    //첨부파일 다운로드
    @PostMapping(value = "/fileDown", produces = "application/octet-stream")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> download(HttpServletRequest req,
                                                                         @RequestHeader("User-Agent") String userAgent, @RequestParam("fname") String fname,
                                                                         @RequestParam("origin_fname") String origin_fname) {
        log.info("userAgent=" + userAgent);
        log.info("fname=" + fname);
        //업로드된 디렉토리의 절대경로 얻기
        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("/Upload");
        String filePath = upDir + File.separator + fname;
        log.info("filePath===" + filePath);

        org.springframework.core.io.Resource resource = new FileSystemResource(filePath);

        if (!resource.exists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        //브라우저 유형 별 인코딩
        boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);
        String downloadName = null;
        try {
            if (checkIE) {
                // IE인 경우
                downloadName = URLEncoder.encode(origin_fname, "UTF-8").replaceAll("\\+", " ");
            } else {
                downloadName = new String(origin_fname.getBytes("UTF-8"), "ISO-8859-1");
            }

        } catch (UnsupportedEncodingException e) {
            log.error("파일 다운로드 중 error: " + e);
        }

        //HttpHeaders객체 생성해서 헤더 정보 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + downloadName);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }//----------------------------------------

    //글 삭제
    @PostMapping("/delete")
    public String boardDelete(Model m,
                              @RequestParam(defaultValue = " ") int bnum) {
        if (bnum == 0) {
            return "redirect:list";
        }
        //db 삭제
        int n = this.boardService.deleteBoard(bnum);
        String str = (n > 0) ? "글 삭제 성공" : "삭제 실패";
        return util.addMsgLoc(m, str, "list");
    }//----------------------------------------

    // 글 수정
    @RequestMapping("/edit")
    public String boardEdit(Model m,
                            @RequestParam(defaultValue = " ") int bnum) {
        if (bnum == 0) {
            return "redirect:list";
        }
        log.info("bnum = " + bnum);
        BoardVO board = this.boardService.selectBoardBybnum(bnum);//이거는 불러온는거
        m.addAttribute("board", board);

        return "board/boardEdit";
    }//----------------------------------------

    //답변글 달기
    @RequestMapping("/reInsert")
    public String reInsertForm(Model m,
                               @RequestParam(defaultValue = "0") int bnum,
                               @RequestParam(defaultValue = "") String btitle,
                               @RequestParam String cg_num,
                               HttpServletRequest req) {

        HttpSession ses = req.getSession();
        UserVO user = (UserVO) ses.getAttribute("loginUser");

        int idx = user.getIdx();

        log.info("idx = " + idx);

        m.addAttribute("idx", idx);
        m.addAttribute("bnum", bnum);
        m.addAttribute("btitle", btitle);
        m.addAttribute("cg_num", cg_num);
        return "board/boardReInsert";
    }//----------------------------------------
}
