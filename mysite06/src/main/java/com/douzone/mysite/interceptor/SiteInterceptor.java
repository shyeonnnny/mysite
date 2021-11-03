package com.douzone.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

public class SiteInterceptor implements HandlerInterceptor {
	@Autowired
	private SiteService siteService;
	@Autowired
	private ApplicationContext ctx;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(handler);
		System.out.println(ctx.getBean(SimpleUrlHandlerMapping.class));
//		ctx.getBean(SimpleUrlHandlerMapping.class).get
		SiteVo site = (SiteVo)request.getServletContext().getAttribute("site");
		if(site == null) {
			site = siteService.getSite();
			request.getServletContext().setAttribute("site", site);
		}

		return true;
	}
}
