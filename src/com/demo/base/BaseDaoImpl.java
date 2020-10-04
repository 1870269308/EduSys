package com.demo.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.utils.JdbcUtil;
import com.demo.utils.JdbcUtils;
import com.demo.utils.QueryRunner;



public class BaseDaoImpl<T> implements BaseDao<T>{
	private QueryRunner qr=new QueryRunner();
	private JdbcUtil dbUtil=new JdbcUtil();
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
	public void udpate(T t) {//修改
		//update person set name='老孟',age=104,pid='10086' where id=8;
		// 参数列表
		Object[] params = null;
		// 字符串拼接
		StringBuilder sb = new StringBuilder();
		String preStr = "update ";
		sb.append(preStr);
		// 获取数据库表名并转为小写(这里的getSimpleName 是返回该类名的简称 如Person再将其转换为小写)
		String tableName = this.clazz.getSimpleName().toLowerCase();
		// 同时注意这里的表明可能为关键字，所以需要转义
		sb.append(" `"+tableName+"` set ");
		Field[] fields = this.clazz.getDeclaredFields();
		// 参数列表的长度就是该对象的属性个数长度
		params = new Object[fields.length];
		// 接着拼接
		String tempStr = "";
		// 遍历字段拼接字段名
		for(Field field:fields) {
			// 首先还是设置可访问
			field.setAccessible(true);
			if("id".equals(field.getName())) {
				continue;
			}
			tempStr += field.getName() + "=?, ";
		}
		// 拼接  由于最后还多一个","所以需要去掉","
		tempStr = tempStr.substring(0, tempStr.length()-2);
		// 接着追加到StringBuilder中 
		sb.append(tempStr+" where id = ?");
		// 转换为sql语句
		String sql = sb.toString();
		// 根据传入对象的get方法调用
		// 定义一个指针用来操作参数放置的顺序
		int ptr = 0;
		for(int i = 0;i < fields.length;i++) {
			// 指针自增
			// 同样设置可访问
			fields[i].setAccessible(true);
			try {
				// 由于这里的id需要放在最后 ， 所以需要在这里操作一下(这里只写了根据id进行查询，如果需要可以根据其他的条件修改)
				if("id".equals(fields[i].getName())) {
					// 将id放在最后一个位置
					params[fields.length-1] = fields[i].get(t);
					// 所以这里需要减一
					continue;
				}
				params[ptr] = fields[i].get(t);
				ptr += 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Object[] params = {t.getId(),t.getName(),t.getAge(),t.getPid()};
		qr.execute(sql, params);
	}


	@Override
	public void add(T t) {
		Object[] params=null;
		//字符串拼接
		StringBuilder sb=new StringBuilder("");
		String preStr="insert into ";
		sb.append(preStr);
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		Field[] fs=this.clazz.getDeclaredFields();
		params=new Object[fs.length];
		String sqlTemp="( ";
		for(Field f:fs) {
			f.setAccessible(true);
			String column=f.getName();
			sqlTemp+=column+",";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1)+") ";
		//insert into person id,name,age,pid
		sb.append(sqlTemp);
		sb.append(" values (");
		//?
		sqlTemp="";
		for(int i=0;i<fs.length;i++) {
			sqlTemp+="?,";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1);
		sb.append(sqlTemp);
		sb.append(")");
		String sql=sb.toString();
		System.out.println(sql);
		//对象的getter方法的调用
		for(int i=0;i<fs.length;i++) {
			fs[i].setAccessible(true);
			try {
				params[i]=fs[i].get(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		Object[] params= {t.getId(),t.getName(),t.getAge(),t.getPid()};
		qr.execute(sql, params);
		
	}


	@Override
	public void delete(int id) {
		Object[] params= {id};
		//字符串拼接
		StringBuilder sb=new StringBuilder("");
		String preStr="delete from ";
		sb.append(preStr);
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		//delete from 表名
		String sqlTemp="where id=?";
		sb.append(sqlTemp);
		String sql=sb.toString();
		System.out.println(sql);
		qr.execute(sql, params);
		
	}


	@Override
	public ResultSet query() {
		//字符串拼接
		StringBuilder sb=new StringBuilder("");
		String preStr="select * from ";
		sb.append(preStr);
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		//delete from 表名
		String sql=sb.toString();
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rsm=ps.executeQuery();
			//conn.close();  TODo
			return rsm;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}

