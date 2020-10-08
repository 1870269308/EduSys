package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeedbackDao {
	// 查询
	public ResultSet query(Connection conn) throws Exception {
		String sql = "select * from Feedback";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	}

	// 根据id查询
	public ResultSet query(Connection conn, int id) throws Exception {
		String sql = "select * from Feedback where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// System.out.println(pstmt.executeQuery());
		pstmt.setInt(1, id);
		return pstmt.executeQuery();
	}

	// 根据id查询
	public ResultSet query(Connection conn, String name) throws Exception {
		String sql = "select * from Feedback where userName=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// System.out.println(pstmt.executeQuery());
		pstmt.setString(1, name);
		return pstmt.executeQuery();
	}
	/*
	public ResultSet query1(Connection conn, String Feedback) throws Exception {
		String sql = "select * from Feedback where Feedback=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// System.out.println(pstmt.executeQuery());
		pstmt.setString(1, Feedback);
		return pstmt.executeQuery();
	}
*/
}
