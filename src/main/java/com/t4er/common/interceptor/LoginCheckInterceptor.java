package com.t4er.common.interceptor;

import com.t4er.user.model.UserVO;
import lombok.extern.log4j.Log4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle()");
        HttpSession ses = request.getSession();
        UserVO user = (UserVO) ses.getAttribute("loginUser");
        if (user != null) return  true;

        request.setAttribute("msg", "로그인 해야 이용 가능합니다");
        request.setAttribute("loc", request.getContextPath()+"/index");
        String view = "/WEB-INF/views/message.jsp";
        RequestDispatcher disp = request.getRequestDispatcher(view);
        disp.forward(request, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle()");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion()...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
