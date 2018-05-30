package com.imooc.listener;

import java.util.Enumeration;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
//@WebListener("This is My Servlet 3.0 Listener")
public class MyServletRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent servletrequestevent) {
		System.out.println("requestDestroyed ");
	}

	public void requestInitialized(ServletRequestEvent servletrequestevent) {
		String name = servletrequestevent.getServletRequest().getParameter("name");
		/*
		 * ��֪��request�����������,���ɿ���ͨ��getParameterNames()��ö�ٶ����ȡ����ֵ
		 * Enumeration name1 = servletrequestevent.getServletRequest().getParameterNames();
		 */
		
		System.out.println("requestInitialized name:"+name);
	}

}
