package com.yizhan.image;

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
@RequestMapping("/image")
public class ImageController {
	
	/**
	 * 添加图片
	 */
	@RequestMapping(value="/images", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> addImage(@RequestParam(value= "img_url", required = true) String img_url) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(img_url)){
			map.put("state", "FAILED");
			map.put("errorMessage", "添加失败，图片路径不能为空");
			return map;
		}
		Image img = new Image();
		img.setImg_url(img_url);
		try {
			DbTool.saveOrUpdateObject(Image.class, "image", img);
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "FAILED");
			map.put("errorMessage", "添加失败");
		}
		return map;
	}
	
	
	/**
	 * 查询图片列表
	 */
	@RequestMapping(value="/images", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getImageList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = "SELECT * FROM image";
			List<Image> all = DbTool.getObjectList(sql, Image.class, new String[]{});
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
	 * 删除指定文章
	 */
	@RequestMapping(value="/images/{img_id}", method=RequestMethod.DELETE)
	public @ResponseBody Map<String, Object> delImage(@PathVariable("img_id") Integer img_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = " DELETE FROM image WHERE uid =" + img_id;
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