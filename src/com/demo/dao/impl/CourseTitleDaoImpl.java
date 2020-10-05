package com.demo.dao.impl;

import java.util.List;

import com.demo.base.BaseDaoImpl;
import com.demo.dao.CourseTitleDao;
import com.demo.pojo.Course;
import com.demo.pojo.CourseTitle;
import com.demo.utils.BeanListResultSethandler;

public class CourseTitleDaoImpl extends BaseDaoImpl<CourseTitle> implements CourseTitleDao{

	@Override
	public String[] getTitles() {
		String sql="select id,title from CourseTitle";
		Object[] params=null;
		List<CourseTitle> list=(List<CourseTitle>) qr.query(sql, params, new BeanListResultSethandler<CourseTitle>(CourseTitle.class));
		String[] titles=new String[list.size()];
		int count=0;
		for(CourseTitle courseTitle:list) {
			titles[count++]=courseTitle.getTitle();
		}
		return titles;
	}





}
