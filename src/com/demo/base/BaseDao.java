package com.demo.base;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

public interface BaseDao<T> {
	public void register(T t);//注册
	public void udpate(T t);//修改
	public void add(T t);//增加
	public void delete(int id);//删除
	public ResultSet query();
	public void update(T t);
	public ResultSet query(int id);//id查询
	public ResultSet query(String name);//姓名查询
}