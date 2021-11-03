package com.douzone.mysite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;
import com.douzone.mysite.interceptor.SiteInterceptor;

@Configuration
@EnableAspectJAutoProxy //<!-- auto proxy --> 지워줌
@ComponentScan({"com.douzone.mysite.controller","com.douzone.mysite.exception"})
@Import({MvcConfig.class, MessageConfig.class, FileUploadConfig.class, SecurityConfig.class}) // 순서 상관이 있나없나 봐야함
public class WebConfig extends WebMvcConfigurerAdapter {
// extends나 Configuration 꼭 달아줘야 실행됨 ( 둘중뭔지는 확인해봐야함 )
	
	// SiteInterceptors
	@Bean
	public HandlerInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}
	
	@Override 
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(siteInterceptor())
		.addPathPatterns("/**");
	}

}
