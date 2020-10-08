package com.demo.pojo;

//用户信息管理
public class UserManage {
	private int id;//编号
	private String name;//姓名
	private String password;//密码
	private String subject;//科目
	
	public UserManage() {
		super();
	}
	
	public UserManage(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public UserManage(int id, String name, String password, String subject) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.subject = subject;
	}
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "UserManage [id=" + id + ", name=" + name + ", password=" + password + ", subject=" + subject + "]";
	}
	

}
