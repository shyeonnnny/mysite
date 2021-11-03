package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy //<!-- auto proxy --> 지워줌
@ComponentScan({"com.douzone.mysite.service","com.douzone.mysite.repository","com.douzone.mysite.aspect"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

/**
 * ComponentScan에는
 * applicationContext의 <context:component-scan> 부분을 변경한 것
 */
}
