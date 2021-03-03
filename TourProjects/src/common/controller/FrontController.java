package common.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

@WebServlet(
        urlPatterns = {"*.do"},
        initParams = {
                @WebInitParam(name = "config", value = "D:\\JavaWork\\TourProject\\web\\WEB-INF\\Command.properties") // 각자 경로에 맞게 설정
        }
)

public class FrontController extends HttpServlet {

    private HashMap<String, Object> cmdMap = new HashMap<>();

    @Override
    public void init(ServletConfig conf) throws ServletException {

        String props = conf.getInitParameter("config");

        Properties pr = new Properties();

        try {
            FileInputStream fis = new FileInputStream(props);
            pr.load(fis);

            if (fis != null) {
                fis.close();
            }

            Set<Object> set = pr.keySet();
            for (Object key : set) {
                String cmd = key.toString();
                String className = pr.getProperty(cmd);

                if (className != null) {
                    className = className.trim();
                }
                Class<?> cls = Class.forName(className);
                Object cmdInstance = cls.newInstance();
                cmdMap.put(cmd, cmdInstance);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processs(req, resp);
    }

    private void processs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getServletPath();
        Object instance = cmdMap.get(cmd);
        if (instance == null) {
            System.out.println("Action이 null");
            throw new ServletException("Action이 null입니다");
        }

        AbstractAction action = (AbstractAction) instance;

        try {
            action.execute(req, resp);

            String viewPage = action.getViewPage();
            boolean isRedirect = action.isRedirect();
            if (isRedirect) {
                resp.sendRedirect(viewPage);
            } else {
                RequestDispatcher disp = req.getRequestDispatcher(viewPage);
                disp.forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processs(req, resp);
    }
}
