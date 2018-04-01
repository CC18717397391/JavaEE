package com.yizhan.bean;

public class User {

	private int uid;
	
	private String username;
	
	private String password;
	
	private String login_time;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int uid, String username, String password, String login_time) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.login_time = login_time;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username!=null ? username : "";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password!=null ? password : "";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_time() {
		return login_time!=null ? login_time : "";
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", login_time=" + login_time
				+ "]";
	}

	

}
