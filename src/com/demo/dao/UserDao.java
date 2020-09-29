package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.User;


/**
 * 用户dao类
 * @author 86152
 *
 */
public interface UserDao extends BaseDao<User>{
	
	//比父类多的方法  获取参数-还没实现
	public List<User> getPsByParams(Object[] params);


}
