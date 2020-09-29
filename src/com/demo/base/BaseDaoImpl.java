package com.demo.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.demo.utils.QueryRunner;
import com.mysql.jdbc.Connection;


public class BaseDaoImpl<T> implements BaseDao<T>{
	private QueryRunner qr=new QueryRunner();
	private Class clazz;
	public BaseDaoImpl() {
		//拿到父类类型
		Type type=this.getClass().getGenericSuperclass();
		//获取到父类的泛型类型
		//ParameterizedType-表示参数化类型
		ParameterizedType pt=(ParameterizedType) type;
		//取出泛型中的参数
		Type tType=pt.getActualTypeArguments()[0];
		//获得到class
		this.clazz=(Class)tType;
	
	}
	

	@Override
	public void register(T t) {
		//字符串拼接
//		String sql="insert into "+t.getClass().getSimpleName()+" (id,name,age,pid) values (?,?,?,?)";
		StringBuilder sb=new StringBuilder();
		//开始拼接sql语句
		String preStr="insert into ";
		sb.append(preStr);
		//获取表名
		//这里的表名和类名相同，但是有大小写区别，得转换
		//this.clazz.getSimpleName()--获取类名
		//toLowerCase()--转小写
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		//System.out.println(sb.toString());
		//  insert into person
		//  (id,name,age,pid) 
		Field[] fs=this.clazz.getDeclaredFields();
		Object[] params=new Object[fs.length];
		String sqlTemp="( ";
		for(Field f:fs) {
			//获得访问权
			f.setAccessible(true);
			//获得列名
			String column=f.getName();
			sqlTemp+=column+",";
		}
		//System.out.println(sb.toString());
		//insert into `person` id,name,age,pid,
		//多了个逗号
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1)+" )";
		sb.append(sqlTemp);
		// values (?,?,?,?)"
		sb.append(" values (");
		sqlTemp="";
		for(int i=0;i<fs.length;i++) {
			sqlTemp+="?,";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1);
		sb.append(sqlTemp);
		sb.append(")");
		String sql=sb.toString();
		System.out.println(sql);
		
		//获取到增加Person的属性的值
		//对象的getter方法的调用
		for(int i=0;i<fs.length;i++) {
			//给权限
			fs[i].setAccessible(true);
			try {
				params[i]=fs[i].get(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		qr.execute(sql, params);
		
		
	}


}

