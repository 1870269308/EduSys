package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.pojo.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDao {
	public User login(Connection con, User user) throws SQLException {
		
		User resultUser = null;
		
		//数据库查询语句
		String sql = "select * from user where username= ? and `password` = ? and role=?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, user.getUserName()); 
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getRole());
		ResultSet rs = pstmt.executeQuery(); 
		//查询数据库中是否有值
		if(rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id")); 
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setRole(rs.getString("role"));
		}
		return resultUser; 
	}
}
