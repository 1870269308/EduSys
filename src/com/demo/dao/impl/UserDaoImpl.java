package com.demo.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.demo.base.BaseDaoImpl;
import com.demo.dao.UserDao;
import com.demo.pojo.User;
import com.mysql.jdbc.Connection;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<User> getPsByParams(Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

}
