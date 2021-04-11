package com.t4er.common.interceptor;

import com.t4er.user.model.UserVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession ses = request.getSession();
		UserVO user = (UserVO) ses.getAttribute("loginUser");

		if (user != null) {
			if (user.getStat() == 9 || user.getStat() == 8) {
				return true; // 관리자라면 true 반환
			} else {
				// 관리자가 아니라면
				request.setAttribute("msg", "관리자만 이용 가능합니다");
				request.setAttribute("loc", request.getContextPath() + "/index");
				String view = "/WEB-INF/views/message.jsp";
				RequestDispatcher disp = request.getRequestDispatcher(view);
				disp.forward(request, response);
				return false;
			}
		}
		return false;
	}

}
