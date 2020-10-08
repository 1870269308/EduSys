package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.demo.dao.CourseDao;
import com.demo.pojo.Course;
import com.demo.pojo.User;
import com.demo.base.BaseDaoImpl;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	private User userMessage = new User();

	public CourseDaoImpl() {
	}

	public CourseDaoImpl(User userMessage) {
		this.userMessage = userMessage;
	}

	@Override
	public ResultSet getDatas(Connection conn, String foreign_id) throws Exception {
		String sql = "select id,section,time,Mon,Tues,Wednes,Thurs,Fri from Course where teacher_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, foreign_id);
		return pstmt.executeQuery();
	}
	
	@Override
	public int updCourse(Connection conn, String foreign_id,Object[] params) throws Exception {
		String sql = "update Course set Mon=?,Tues=?,Wednes=?,Thurs=?,Fri=? where id=? and teacher_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, params[1]);
		pstmt.setObject(2, params[2]);
		pstmt.setObject(3, params[3]);
		pstmt.setObject(4, params[4]);
		pstmt.setObject(5, params[5]);
		pstmt.setObject(6, params[0]);
		pstmt.setString(7, foreign_id);
		return pstmt.executeUpdate();
	}
}
