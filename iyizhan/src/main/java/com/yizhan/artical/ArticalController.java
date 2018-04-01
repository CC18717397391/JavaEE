package com.yizhan.artical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhan.util.DateUtil;
import com.yizhan.util.DbTool;

@Controller		
@RequestMapping("/artical")
public class ArticalController {
	
	@Autowired(required=true)
	ArticalDao articalDao;
	
	/**
	 * 查询文章列表
	 */
	@RequestMapping(value="/articals", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getArticalList(
			@RequestParam(value= "page", required = true) String pageStr,
			@RequestParam(value= "limit", required = true) String limitStr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			int page = Integer.parseInt(pageStr);
			int limit = Integer.parseInt(limitStr);
			List<Artical> all = articalDao.queryAllArtical();
			int total = 0;
			List<Artical> sub = new ArrayList<>();
			if(all.size()!=0){
				total = (int) Math.ceil(all.size()/(double)limit);			
				sub = all.subList((page-1)*limit, page*limit>all.size() ? all.size() : page*limit);
			}
			map.put("state", "SUCCESS");
			map.put("list", sub);
			map.put("total", String.valueOf(total));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "请求出错！");
		}
		return map;
	}
	
	/**
	 * 添加文章
	 */
	@RequestMapping(value="/articals", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addArtical(@RequestParam(value= "title", required = true) String title,
			@RequestParam(value= "cover_url", required = false) String cover_url,
			@RequestParam(value= "content", required = false) String content) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(title)){
			map.put("state", "FAILED");
			map.put("errorMessage", "标题不能为空");
			return map;
		}
		Artical artical = new Artical(title,content,cover_url,DateUtil.getNowDateTime(),"");
		try {
			DbTool.saveOrUpdateObject(Artical.class, "artical", artical);
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "添加失败");
		}
		return map;
	}
	
	/**
	 * 修改文章
	 */
	@RequestMapping(value="/articals/{artical_id}", method=RequestMethod.PUT)
	public @ResponseBody Map<String, Object> modifyArtical(
			@PathVariable("artical_id") Integer artical_id,
			@RequestParam(value= "title", required = true) String title,
			@RequestParam(value= "content", required = false) String content,
			@RequestParam(value= "cover_url", required = false) String cover_url) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(title)){
			map.put("state", "FAILED");
			map.put("errorMessage", "标题不能为空");
			return map;
		}
		try {
			boolean b = articalDao.updateArtical(artical_id+"", title, content, cover_url, DateUtil.getNowDateTime());
			if(b){
				map.put("state", "SUCCESS");
			}else{
				map.put("state", "FAILED");
				map.put("errorMessage", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "修改失败");
		}
		return map;
	}
	
	/**
	 * 查询指定文章
	 */
	@RequestMapping(value="/articals/{artical_id}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getArtical(
			@PathVariable("artical_id") Integer artical_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Artical artical = articalDao.queryArtical(artical_id+"");
			if(artical!=null){
				map.put("state", "SUCCESS");
				map.put("data", artical);
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
	 * 删除指定文章
	 */
	@RequestMapping(value="/articals/{artical_id}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> delArtical(@PathVariable("artical_id") Integer artical_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			boolean b = articalDao.delArtical(artical_id+"");
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