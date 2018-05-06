package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 工具类，提供了增加，删除，查询Cookie的一些方法
 */
public class CookieUtil {
	
	private	static int age=30*24*60*60;
	private static String path="/";
	
	/*
	 * 添加Cookie
	 */
	public static void addCookie(String name,String value,int age,HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie c = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		c.setMaxAge(age);
		c.setPath(path);
		response.addCookie(c);
	}
	/*
	 * 添加Cookie(使用缺省值的生存时间)
	 */
	public static void addCookie(String name,String value,HttpServletResponse response)throws UnsupportedEncodingException{
		addCookie(name,value,age,response);
	}
	
	/*
	 * 依据Cookie的名字查询cookie的值，找不到返回null
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
	 * 删除cookie
	 */
	public static void deleteCookie(String name,HttpServletResponse response){
		Cookie c=new Cookie(name,"");
		c.setMaxAge(0);
		c.setPath(path);
		response.addCookie(c);
	}
	
}
	
	
	

