package com.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
	private int id;
	private String section;
	private String time;
	private String Mon;
	private String Tues;
	private String Wednes;
	private String Thurs;
	private String Fri;
}
