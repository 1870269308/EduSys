package com.demo.pojo;

import java.util.Date;

// ‘Ã‚¿‡
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor

public class Question {
	private int id;
	private int subjectId;
	private String title;
	private String keyvalue;
	private String keyA;
	private String keyB;
	private String keyC;
	private String keyD;
	private String remarks;
	private Date joinTime;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyvalue() {
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

	public String getKeyA() {
		return keyA;
	}

	public void setKeyA(String keyA) {
		this.keyA = keyA;
	}

	public String getKeyB() {
		return keyB;
	}

	public void setKeyB(String keyB) {
		this.keyB = keyB;
	}

	public String getKeyC() {
		return keyC;
	}

	public void setKeyC(String keyC) {
		this.keyC = keyC;
	}

	public String getKeyD() {
		return keyD;
	}

	public void setKeyD(String keyD) {
		this.keyD = keyD;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Question(int id, String title, String keyvalue, String keyA, String keyB, String keyC, String keyD,
			String remarks) {
		this.id = id;
		this.title = title;
		this.keyvalue = keyvalue;
		this.keyA = keyA;
		this.keyB = keyB;
		this.keyC = keyC;
		this.keyD = keyD;
		this.remarks = remarks;
	}
	
	public Question(int id,int subjectId, String title, String keyvalue, String keyA, String keyB, String keyC, String keyD,
			String remarks) {
		this.id = id;
		this.subjectId=subjectId;
		this.title = title;
		this.keyvalue = keyvalue;
		this.keyA = keyA;
		this.keyB = keyB;
		this.keyC = keyC;
		this.keyD = keyD;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", keyvalue=" + keyvalue + ", keyA=" + keyA + ", keyB="
				+ keyB + ", keyC=" + keyC + ", ketD=" + ", remarks=" + remarks + "]";
	}

}
