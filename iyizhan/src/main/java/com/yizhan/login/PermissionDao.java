package com.yizhan.login;

import org.springframework.stereotype.Repository;

import com.yizhan.bean.User;

@Repository
public interface PermissionDao {

	User queryUser(String username);
	
	boolean saveUser(User user);
	
}