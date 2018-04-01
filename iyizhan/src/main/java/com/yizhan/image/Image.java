package com.yizhan.image;

public class Image {

	private int uid;
	
	private String img_url;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(int uid, String img_url) {
		super();
		this.uid = uid;
		this.img_url = img_url;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Override
	public String toString() {
		return "Image [uid=" + uid + ", img_url=" + img_url + "]";
	}
	
}
