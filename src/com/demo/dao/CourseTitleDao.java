package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.Course;
import com.demo.pojo.CourseTitle;

public interface CourseTitleDao extends BaseDao<CourseTitle>{
	
	public String[] getTitles();
}
