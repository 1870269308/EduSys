package com.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.Course;

public interface CourseDao extends BaseDao<Course>{
	public ResultSet getDatas(Connection conn,String foreign_id) throws Exception;
	public int updCourse(Connection conn, String foreign_id,Object[] params) throws Exception;
}
