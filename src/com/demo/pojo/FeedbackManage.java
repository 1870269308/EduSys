package com.demo.pojo;

public class FeedbackManage {
	private int id;
	private String userName;
	private String feedback;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "FeedbackManager [id=" + id + ", userName=" + userName + ", feedback=" + feedback + "]";
	}
}
