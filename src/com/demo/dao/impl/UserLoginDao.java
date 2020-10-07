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
		//这里的user登录界面传入的值
		pstmt.setString(1, user.getUserName()); 
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getRole());
		//查询返回结果
		ResultSet rs = pstmt.executeQuery(); 
		//查询数据库中是否有值
		if(rs.next()) {
			//新创建一个对象来存储数据库中查到的数据
			resultUser = new User();
			resultUser.setId(rs.getInt("id")); 
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setRole(rs.getString("role"));
		}
		//获得从数据库中返回匹配的对象
		return resultUser; 
	}
}
