package com.yizhan.artical;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ArticalDao {

	/**查询所有文章*/
	List<Artical> queryAllArtical();
	
	/**修改文章*/
	boolean updateArtical(String artical_id,String title,String content,String cover_url,String update_time);

	/**查询指定文章*/
	Artical queryArtical(String id);
	
	/**删除指定文章*/
	boolean delArtical(String id);
}
