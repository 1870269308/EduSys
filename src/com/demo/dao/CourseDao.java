package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.Course;

public interface CourseDao extends BaseDao<Course>{
	List<Course> getDatas();
	boolean updCourse(Course cou);
}
