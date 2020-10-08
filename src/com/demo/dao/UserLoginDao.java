package com.demo.dao;

import java.sql.Connection;

import com.demo.pojo.User;
import com.demo.pojo.UserManage;

public interface UserLoginDao {
	public User login(Connection con, User user);
	public UserManage loginManage(Connection con, UserManage user);
}
