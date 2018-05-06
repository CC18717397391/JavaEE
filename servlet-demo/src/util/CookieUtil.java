package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * �����࣬�ṩ�����ӣ�ɾ������ѯCookie��һЩ����
 */
public class CookieUtil {
	
	private	static int age=30*24*60*60;
	private static String path="/";
	
	/*
	 * ���Cookie
	 */
	public static void addCookie(String name,String value,int age,HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie c = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		c.setMaxAge(age);
		c.setPath(path);
		response.addCookie(c);
	}
	/*
	 * ���Cookie(ʹ��ȱʡֵ������ʱ��)
	 */
	public static void addCookie(String name,String value,HttpServletResponse response)throws UnsupportedEncodingException{
		addCookie(name,value,age,response);
	}
	
	/*
	 * ����Cookie�����ֲ�ѯcookie��ֵ���Ҳ�������null
	 */
	public static String findCookie(HttpServletRequest request,String name) throws UnsupportedEncodingException{
		Cookie[] cookies = request.getCookies();
		String value=null;
		 if(cookies!=null){
	           for(int i=0;i<cookies.length;i++) {
	        	   Cookie c = cookies[i];
	              if(name.equals(c.getName())){
	                  value=URLDecoder.decode(c.getValue(),"utf-8");
	              }
	           }
		 }
		return value;
	}
	/*
	 * ɾ��cookie
	 */
	public static void deleteCookie(String name,HttpServletResponse response){
		Cookie c=new Cookie(name,"");
		c.setMaxAge(0);
		c.setPath(path);
		response.addCookie(c);
	}
	
}
	
	
	

