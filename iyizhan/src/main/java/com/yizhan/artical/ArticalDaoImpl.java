package com.yizhan.artical;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhan.util.DbTool;
@Repository
public class ArticalDaoImpl implements ArticalDao {

	//SELECT * FROM artical order by uid asc limit((page-1)*limit, page*limit)
	@Override
	public List<Artical> queryAllArtical() {
		String sql = "SELECT uid,title,create_time FROM artical order by uid asc;";
		return DbTool.getObjectList(sql, Artical.class, new String[]{});
	}

	@Override
	public boolean updateArtical(String artical_id,String title,String content,String cover_url,String update_time) {
		String sql = "update artical set title='"+ title +"', content='"+ content +"', cover_url='"+ cover_url +"', update_time='"+ update_time +"'"
				+ " where uid=" + artical_id;
		return DbTool.updateBySQL(sql);
	}

	@Override
	public Artical queryArtical(String id) {
		String sql = "SELECT * FROM artical where uid=" + id;
		return DbTool.getObject(sql, Artical.class, new String[]{});
	}

	@Override
	public boolean delArtical(String id) {
		String sql = " DELETE FROM artical WHERE uid =" + id;
		return DbTool.updateBySQL(sql);
	}
	
	

}
