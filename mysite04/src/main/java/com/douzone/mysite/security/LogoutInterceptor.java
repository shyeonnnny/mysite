package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session == null) {
			return false;
		}
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		response.sendRedirect(request.getContextPath());
		return false;
	}

}

// new로 만들면 주입이되는게아니라 null로 아예 새로운게 만들어짐
// UserVo authUser = new UserService().getUser(email, password);