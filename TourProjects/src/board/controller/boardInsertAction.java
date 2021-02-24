package board.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.*;
import common.controller.AbstractAction;


public class boardInsertAction extends AbstractAction {

   @Override
   public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	  
      if(!req.getMethod().equalsIgnoreCase("post")) {
         //post방식이 아니라면
         this.setRedirect(true);
         this.setViewPage("index.do"); //여기는 write.do없는데 호출해서 혹시 몰라서 index.do로 바꿔놓은거임 나중에 바꾸셈
         return;
      }
     
      //업로드할 디렉토리 절대경로
      ServletContext app=req.getServletContext();
      String upDir=app.getRealPath("board/Upload");//경로에 폴더 없음
      System.out.println(upDir);//경로가 없어서 에러떳었음
      
      //파일 업로드
      DefaultFileRenamePolicy df=new DefaultFileRenamePolicy();
	  MultipartRequest mr=new MultipartRequest(req,upDir,100*1024*1024,"UTF-8",df);
	
	  //게시판
	  //idx값을 입력할떈 받아올수가 없음 변수자체가 jsp에 없음
	  //여기서 변수는 서버에 저장된 파라미터값을 불러오기 때문에 getParameter(여기 값은jsp랑 같아야함 안그럼 매칭 못함)
      String btitle = mr.getParameter("btitle");//글제목
      String bcontent = mr.getParameter("bcontent");//글내용
      String bupload1 = mr.getFilesystemName("bupload1");//업로드1
      String bupload2 = mr.getFilesystemName("bupload2");//업로드2
      String bupload3 = mr.getFilesystemName("bupload3");//업로드3
     // int brecommend = Integer.parseInt(mr.getParameter("brecommend"));//추천수 애 역시 jsp에 없습니다. 받아올 값이 없어요
    
      //후처리
      BoardVO board=new BoardVO(0,btitle,bcontent,0,null,bupload1,bupload2,bupload3,0,0,0);

      BoardDAOMyBatis dao=new BoardDAOMyBatis();
      int n = dao.insertBoard(board);

      String str=(n>0)?"글쓰기 성공":"글쓰기 실패";
      String loc=(n>0)? "boardList.do":"javascript:history.back()";
      
      req.setAttribute("message", str);
	  req.setAttribute("loc", loc);
      
      this.setViewPage("msg.jsp");
      this.setRedirect(false);
   }
}