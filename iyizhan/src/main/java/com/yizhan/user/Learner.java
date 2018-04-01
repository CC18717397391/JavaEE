package com.yizhan.user;

public class Learner {

	private int uid;
	
	private String name;
	
	private String company;
	
	private String img_url;

	private String job;
	
	private String salary;

	public Learner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Learner(int uid, String name, String company, String img_url, String job, String salary) {
		super();
		this.uid = uid;
		this.name = name;
		this.company = company;
		this.img_url = img_url;
		this.job = job;
		this.salary = salary;
	}



	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Learner [uid=" + uid + ", name=" + name + ", company=" + company + ", img_url=" + img_url + ", job="
				+ job + ", salary=" + salary + "]";
	}
	
	
}
