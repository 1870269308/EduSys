package com.demo.base;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

public interface BaseDao<T> {
	public void register(T t);//×¢²á
	public void udpate(T t);//ÐÞ¸Ä
	public void add(T t);//Ôö¼Ó
	public void delete(int id);//É¾³ý
	public ResultSet query();
	
	
	public void update(T t);
}