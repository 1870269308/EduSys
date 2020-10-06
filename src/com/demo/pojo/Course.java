package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {
	private int id;
	private String section;
	private String time;
	private String Monday;
	private String Tuesday;
	private String Wednesday;
	private String Thursday;
	private String Friday;
	public Course(int id, String section, String time, String monday, String tuesday, String wednesday, String thursday,
			String friday) {
		this.id = id;
		this.section = section;
		this.time = time;
		Monday = monday;
		Tuesday = tuesday;
		Wednesday = wednesday;
		Thursday = thursday;
		Friday = friday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFriday() {
		return Friday;
	}
	public void setFriday(String friday) {
		Friday = friday;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMonday() {
		return Monday;
	}
	public void setMonday(String monday) {
		Monday = monday;
	}
	public String getTuesday() {
		return Tuesday;
	}
	public void setTuesday(String tuesday) {
		Tuesday = tuesday;
	}
	public String getWednesday() {
		return Wednesday;
	}
	public void setWednesday(String wednesday) {
		Wednesday = wednesday;
	}
	public String getThursday() {
		return Thursday;
	}
	public void setThursday(String thursday) {
		Thursday = thursday;
	}

	 
	
}
