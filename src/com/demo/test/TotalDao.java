package com.demo.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TotalDao {
	
	public ResultSet query(Connection conn) throws SQLException;
}
