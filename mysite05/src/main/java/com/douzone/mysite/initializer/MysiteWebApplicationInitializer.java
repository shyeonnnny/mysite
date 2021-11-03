package com.douzone.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] {AppConfig.class}; // ? : 아무거나 넣어라 ( 모든 클래스 가능 )
	} // applicationcontext

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] {WebConfig.class};
	} // spring servlet
	
	// 리스너는 스프링에 있어서 따로 안적어주ㅓ도됨


	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

	
	// web.xml : <!-- Encoding Filter -->
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8", false)};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

}
