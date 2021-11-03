package com.douzone.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource("classpath:com/douzone/mysite/config/web/fileupload.properties")
public class FileUploadConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env; // Environment propertie파일을 가져올 수 있게 해주는 기능
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
		multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	// ResourceHandlerRegistry에 경로지정과같은 기능이 내장되어있음
	{
		registry
		.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
		.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	} // addResourceHandlers는 파일경로와 위치를 찾는 메서드인데 이 메서드에는 파일 크기나 인코딩을 지정해주는
		// 기능이 없기 때문에 위에 MulipartResolver의 기능들을 사용하기위해 오버라이드했음
	
}
