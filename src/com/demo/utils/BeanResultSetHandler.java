package com.demo.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
//ResultSet-数据库结果集的数据表
public class BeanResultSetHandler<T> implements ResultSetHandler<T>{
	
	
	private Class clazz;
	public BeanResultSetHandler(Class clazz) {
		this.clazz=clazz;
	}
	@Override
	public T handle(ResultSet rs) {
		try {
			//需要返回的单个对象
			Object obj=clazz.newInstance();
			//拿到需要取出来的字段
			Field[] fs=clazz.getDeclaredFields();
			//处理结果集，把得到的行和列内容写入我们obj对象中
			while(rs.next()) {
				for(Field f:fs) {
					//获得访问权
					f.setAccessible(true);
					//获取成员变量的名字
					String columnLabel=f.getName();
					//以 J Object 的形式获取此 ResultSet 对象的当前行中指定列的值。
					Object value=rs.getObject(columnLabel);
					//将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
					f.set(obj, value);
				}
			}
			return (T) obj;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

}
