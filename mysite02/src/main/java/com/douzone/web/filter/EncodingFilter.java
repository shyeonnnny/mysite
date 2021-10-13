package com.douzone.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class EncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null) { // default encoding charset
			encoding = "UTF-8";
		}
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* request */
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
		/* request */
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}