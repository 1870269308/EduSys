package com.demo.pojo;

import java.util.Date;

public class Paper {
	private int id;
	private Date joinDate;
	private String paperName;
	private String subjectId;
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public Paper() {
	
	}
	public Paper(int id, Date joinDate, String paperName) {
		super();
		this.id = id;
		this.joinDate = joinDate;
		this.paperName = paperName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getJionDate() {
		return joinDate;
	}
	public void setJionDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	
}
