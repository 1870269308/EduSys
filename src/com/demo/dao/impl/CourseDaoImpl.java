package com.demo.dao.impl;

import java.util.List;

import com.demo.dao.CourseDao;
import com.demo.dao.CourseTitleDao;
import com.demo.pojo.Course;
import com.demo.pojo.User;
import com.demo.pojo.UserManage;
import com.demo.utils.BeanListResultSethandler;
import com.demo.base.BaseDaoImpl;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	private UserManage userMessage=new UserManage();
	
	public CourseDaoImpl() {
	}

	public CourseDaoImpl(UserManage userMessage) {
		this.userMessage = userMessage;
	}

	@Override
	public List<Course> getDatas() {
		int foreignId=userMessage.getId();
		String sql = "select id,section,time,Monday,Tuesday,Wednesday,Thursday,Friday from Course where teacher_id="+foreignId;
		Object[] params = null;
		List<Course> list = (List<Course>) qr.query(sql, params, new BeanListResultSethandler<Course>(Course.class));
		return list;
	}

	@Override
	public boolean updCourse(Course cou) {
		this.update(cou);
		return true;
	}}
