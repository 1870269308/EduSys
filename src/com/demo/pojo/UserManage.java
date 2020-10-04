package com.demo.pojo;

//用户信息管理
public class UserManage {
	private int id;//编号
	private String name;//姓名
	private String password;//密码
	private String isExam;//是否考试
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsExam() {
		return isExam;
	}
	public void setIsExam(String isExam) {
		this.isExam = isExam;
	}
	public UserManage(int id, String name, String password, String isExam) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.isExam = isExam;
	}
	@Override
	public String toString() {
		return "UserManage [id=" + id + ", name=" + name + ", password=" + password + ", isExam=" + isExam + "]";
	}
	

}
