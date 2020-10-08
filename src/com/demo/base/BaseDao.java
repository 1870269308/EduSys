package com.demo.base;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

public interface BaseDao<T> {
	public void register(T t);//ע��
	public void udpate(T t);//�޸�
	public void add(T t);//����
	public void delete(int id);//ɾ��
	public ResultSet query();
	public void update(T t);
	public ResultSet query(int id);//id��ѯ
	public ResultSet query(String name);//������ѯ
}