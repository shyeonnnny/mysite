package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



public class ContextLoadListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String contextConfigLocation = sc.getInitParameter("contextConfigLoacation");
    	// 게스트북컨트롤러 자료 수정할 때 사용됨 
    	
		System.out.println("Application Starts....");
    }
	
	public void contextDestroyed(ServletContextEvent sce)  { 
    }


	
}
