package com.yizhan.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhan.util.DbTool;

@Controller		
@RequestMapping("/user")
public class LearnController {
	
	/**
	 * 添加学员
	 */
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addLearner(@RequestParam(value= "name", required = true) String name,
			@RequestParam(value= "company", required = false) String company,
			@RequestParam(value= "img_url", required = false) String img_url,
			@RequestParam(value= "job", required = false) String job,
			@RequestParam(value= "salary", required = false) String salary) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(name)){
			map.put("state", "FAILED");
			map.put("errorMessage", "姓名不能为空");
			return map;
		}
		img_url = (img_url==null) ? "" : img_url;
		Learner learner = new Learner(0,name,company,img_url,job,salary);
		try {
			DbTool.saveOrUpdateObject(Learner.class, "learner", learner);
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			map.put("state", "FAILED");
			map.put("errorMessage", "添加失败");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 修改学员
	 */
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.PUT)
	public @ResponseBody Map<String, Object> modifyLearner(
			@PathVariable("user_id") Integer user_id,
			@RequestParam(value= "name", required = true) String name,
			@RequestParam(value= "company", required = false) String company,
			@RequestParam(value= "img_url", required = false) String img_url,
			@RequestParam(value= "job", required = false) String job,
			@RequestParam(value= "salary", required = false) String salary) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(name)){
			map.put("state", "FAILED");
			map.put("errorMessage", "姓名不能为空");
			return map;
		}
		try {
			Learner learner = new Learner(user_id,name,company,img_url,job,salary);
			DbTool.saveOrUpdateObject(Learner.class, "learner", learner);
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			map.put("state", "FAILED");
			map.put("errorMessage", "修改失败");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询学员列表
	 */
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getLearnerList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = "SELECT * FROM learner";
			List<Learner> all = DbTool.getObjectList(sql, Learner.class, new String[]{});
			map.put("state", "SUCCESS");
			map.put("list", all);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "请求出错！");
		}
		return map;
	}
	
	/**
	 * 查询指定学员
	 */
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getLearner(
			@PathVariable("user_id") Integer user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = "SELECT * FROM learner where uid=" + user_id;
			Learner learner = DbTool.getObject(sql, Learner.class, new String[]{});
			if(learner!=null){
				map.put("state", "SUCCESS");
				map.put("data", learner);
			}else{
				map.put("state", "FAILED");
				map.put("errorMessage", "未查询到相关信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "系统错误，查询失败");
		}
		return map;
	}
	
	/**
	 * 删除指定学员
	 */
	@RequestMapping(value="/users/{user_id}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> delLearner(@PathVariable("user_id") Integer user_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = " DELETE FROM learner WHERE uid =" + user_id;
			boolean b = DbTool.updateBySQL(sql);
			if(b){
				map.put("state", "SUCCESS");
			}else{
				map.put("state", "FAILED");
				map.put("errorMessage", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "系统错误，删除失败");
		}
		return map;
	}
	
}