package com.demo.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalDaoImpl implements TotalDao {
	/*
	 * 查询指定数据
	 */
	public ResultSet query(Connection conn) throws SQLException  {
		String sql = "select id, name, sumScore from score order by sumScore DESC"; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
