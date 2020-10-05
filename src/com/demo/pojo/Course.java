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

	
}
