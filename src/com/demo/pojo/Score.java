package com.demo.pojo;



//得分表
public class Score {
	private int id;//考生id
	private String name;//考生姓名
	private int scScore;//单选题得分Single choice
	private int mcScore;//多选题得分Multiple choice
	private int sumScore;//总分
	private String examdate;//考试时间
	
	public Score() {
		super();
	}
	public Score(int id, String name, int scScore, int mcScore, int sumScore, String examdate) {
		super();
		this.id = id;
		this.name = name;
		this.scScore = scScore;
		this.mcScore = mcScore;
		this.sumScore = sumScore;
		this.examdate = examdate;
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
	public int getScScore() {
		return scScore;
	}
	public void setScScore(int scScore) {
		this.scScore = scScore;
	}
	public int getMcScore() {
		return mcScore;
	}
	public void setMcScore(int mcScore) {
		this.mcScore = mcScore;
	}
	public int getSumScore() {
		return sumScore;
	}
	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}
	public String getDate() {
		return examdate;
	}
	public void setDate(String examdate) {
		this.examdate = examdate;
	}
	

}
