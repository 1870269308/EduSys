package com.demo.pojo;



//�÷ֱ�
public class Score {
	private int id;//����id
	private String name;//��������
	private int scScore;//��ѡ��÷�Single choice
	private int mcScore;//��ѡ��÷�Multiple choice
	private int sumScore;//�ܷ�
	private String examdate;//����ʱ��
	
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
