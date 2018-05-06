package com.imooc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletcontextevent) {
		String initParam = servletcontextevent.getServletContext().getInitParameter("initParam");
		/*
		 * 可以往ServletContext()上下文中设置全局的属性对象，其他地方通过name绑定名获取
		 * String initParam = servletcontextevent.getServletContext().setAttribute(name, object);
		 */
		System.out.println("contextInitialized : initParam = "+initParam);
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		System.out.println("contextDestroyed");
	}

}
