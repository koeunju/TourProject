package admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.controller.AbstractAction;
import point.model.ProductDAOMyBatis;
import point.model.ProductVO;


public class ProductInsertAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        ServletContext app = req.getServletContext();
        String upDir = app.getRealPath("product/Upload");

        DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
        MultipartRequest mr = new MultipartRequest(req, upDir, 100 * 1024 * 1024, "UTF-8", df);

        String pname = mr.getParameter("pname");
        String pcontent = mr.getParameter("pcontent");
        int price = Integer.parseInt(mr.getParameter("price"));

        String pimage = mr.getFilesystemName("pimage");
        String pimage2 = mr.getFilesystemName("pimage2");
        String pimage3 = mr.getFilesystemName("pimage3");
        String Cg_num = mr.getParameter("Cg_num");
        ProductVO product = new ProductVO(0, pname, pcontent, price, pimage, pimage2, pimage3, Cg_num);

        ProductDAOMyBatis dao = new ProductDAOMyBatis();

        int n = dao.insertProduct(product);

        String str = (n > 0) ? "등록성공" : "등록실패";
        String loc = (n > 0) ? "pointshopList.do" : "javascript:history.back()";

        req.setAttribute("message", str);
        req.setAttribute("loc", loc);

        this.setViewPage("msg.jsp");
        this.setRedirect(false);
    }

}
