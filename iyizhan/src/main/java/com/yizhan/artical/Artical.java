package com.yizhan.artical;

public class Artical {

	private int uid;
	
	private String title;
	
	private String content;
	
	private String cover_url;

	private String create_time;
	
	private String update_time;

	public Artical() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artical(String title, String content, String cover_url, String create_time, String update_time) {
		super();
		this.title = title;
		this.content = content;
		this.cover_url = cover_url;
		this.create_time = create_time;
		this.update_time = update_time;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "Artical [uid=" + uid + ", title=" + title + ", content=" + content + ", cover_url=" + cover_url
				+ ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	
	
}
