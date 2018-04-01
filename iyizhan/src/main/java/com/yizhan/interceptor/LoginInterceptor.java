package com.yizhan.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yizhan.bean.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// GET请求不拦截
		String method = req.getMethod();
		if(method.equalsIgnoreCase("get")){
			return true;
		}else{
			User user = (User) req.getSession().getAttribute("user");
			if (user != null) {
				// 有登陆信息，允许继续往下访问
				return true;
			} else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("state", "REDIRECT");
				map.put("redirect_url", "/iyizhan/login.html");
				res.setCharacterEncoding("utf-8");
				res.setContentType("application/json");
				res.getWriter().print(new Gson().toJson(map));
				// 没有登录过，不允许继续访问
				return false;// 终止后续执行
			}
		}
	}

}
