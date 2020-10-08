package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.demo.base.BaseDaoImpl;
import com.demo.dao.CourScheDao;
import com.demo.pojo.CouSche;
import com.demo.pojo.User;
import com.demo.utils.BeanListResultSethandler;

public class CourScheDaoImpl extends BaseDaoImpl<CouSche> implements CourScheDao {
	private User userMessage = new User();

	public CourScheDaoImpl() {
	}

	public CourScheDaoImpl(User userMessage) {
		this.userMessage = userMessage;
	}

	@Override
	public ResultSet getDatas(Connection conn, String foreign_id) throws SQLException {
		String sql = "select id,content,rate from CouSche where teacher_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, foreign_id);
		return pstmt.executeQuery();
	}

	@Override
	public int updCourse(Connection conn, String foreign_id,Object[] params) throws Exception {
		String sql = "update CouSche set rate=? where id=? and teacher_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, params[1]);
		pstmt.setObject(2, params[0]);
		pstmt.setString(3, foreign_id);
		return pstmt.executeUpdate();
	}

}
