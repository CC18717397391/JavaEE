package com.imooc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletcontextevent) {
		String initParam = servletcontextevent.getServletContext().getInitParameter("initParam");
		/*
		 * ������ServletContext()������������ȫ�ֵ����Զ��������ط�ͨ��name������ȡ
		 * String initParam = servletcontextevent.getServletContext().setAttribute(name, object);
		 */
		System.out.println("contextInitialized : initParam = "+initParam);
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		System.out.println("contextDestroyed");
	}

}
