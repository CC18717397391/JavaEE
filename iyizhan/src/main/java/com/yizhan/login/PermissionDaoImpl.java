package com.yizhan.login;

import org.springframework.stereotype.Repository;

import com.yizhan.bean.User;
import com.yizhan.util.DbTool;
@Repository
public class PermissionDaoImpl implements PermissionDao {

	@Override
	public User queryUser(String username) {
		String sql = "select * from userinfo where username = '" + username + "';";
		return DbTool.getObject(sql, User.class, new String[]{});
	}

	@Override
	public boolean saveUser(User user) {
		return DbTool.saveOrUpdateObject(User.class, "userinfo", user);
	}
	
	

}