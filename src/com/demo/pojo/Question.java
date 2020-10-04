package com.demo.pojo;


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
	private String title;
	private String keyvalue;
	private String keyA;
	private String keyB;
	private String keyC;
	private String keyD;
	private String remarks;
	
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
	
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", title=" + title + ", keyvalue=" + keyvalue + ", keyA=" + keyA + ", keyB="
				+ keyB + ", keyC=" + keyC + ", ketD=" + ", remarks=" + remarks + "]";
	}






	
	
}
