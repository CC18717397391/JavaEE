package com.yizhan.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhan.bean.User;
import com.yizhan.util.DateUtil;
import com.yizhan.util.DesUtils;


@Controller		
@RequestMapping("/permission")
public class PermissionController {
	
	User user;
	
	@Autowired(required=true)
	PermissionDao permissionDao;

	/**
	 * 登陆认证
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> Validate(
			@RequestParam(value= "username", required = true) String username,
			@RequestParam(value= "password", required = true) String password,
			HttpSession session) throws Exception {
		User user = permissionDao.queryUser(username);
		HashMap<String, String> map = new HashMap<String, String>();
		if(user == null || !new DesUtils().decrypt(user.getPassword()).equals(password)){
			map.put("state", "FAILED");
			map.put("errorMessage", "用户名或密码输入有误！");
		}else{
			map.put("state", "REDIRECT");
			map.put("redirect_url", "/iyizhan/manager.html");
			session.setAttribute("user", user);
		}
		return map;
	}
	
	/**
	 * 重置密码
	 * @throws Exception 
	 */
	@RequestMapping(value="/login", method=RequestMethod.PUT)
	public @ResponseBody Map<String, String> reset(
			@RequestParam(value= "oldPwd", required = true) String oldPwd,
			@RequestParam(value= "newPwd", required = true) String newPwd,
			@RequestParam(value= "confirmPwd", required = true) String confirmPwd,
			HttpSession session) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		User user = (User) session.getAttribute("user");
		Pattern pattern = Pattern.compile("^[A-Za-z]{1}([A-Za-z0-9]|[._*]){5,19}$");
		Matcher matcher = pattern.matcher(newPwd);
		if(!matcher.matches()){
			map.put("state", "FAILED ");
			map.put("errorMessage", "密码格式错误");
			return map;
		}
		if(oldPwd.equals( new DesUtils().decrypt(user.getPassword()) )){
			if(newPwd.equals(confirmPwd)){
				user.setPassword(new DesUtils().encrypt(confirmPwd));
				user.setLogin_time(DateUtil.getNowDateTime());
				permissionDao.saveUser(user);
				session.invalidate();
				map.put("state", "REDIRECT");
				map.put("redirect_url", "/iyizhan/login.html");
			}else{
				map.put("state", "FAILED ");
				map.put("errorMessage", "两次密码不一致");
			}
		}else{
			map.put("state", "FAILED ");
			map.put("errorMessage", "旧密码输入有误");
		}
		return map;
	}
	
	@RequestMapping(value="/permission", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> Permission(HttpSession session) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		User user = (User) session.getAttribute("user");
		if(user==null){
			map.put("state", "REDIRECT");
			map.put("redirect_url", "/iyizhan/login.html");
		}
		return map;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> logout(HttpSession session,HttpServletResponse resp) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		session.invalidate();
		map.put("state", "REDIRECT");
		map.put("redirect_url", "/iyizhan/login.html");
		return map;
	}

}
