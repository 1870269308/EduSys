package com.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;

//Orm
public class Demo {

	@Test
	public void addPersonTest() throws Exception{
		
		//加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//创建sql语句
		String sql="insert into person values (1,?,?,?)";
		//创建装sql的车
		PreparedStatement ps=conn.prepareStatement(sql);
		//实例化对象
		Person p=new Person(1,"老王",10,"102020");
		//传入参数
		ps.setString(1, p.getName());
		ps.setInt(2,p.getAge());
		ps.setString(3, p.getPid());
		
		//执行sql
		ps.executeUpdate();
		//关闭连接
		conn.close();
	}
	@Test
	public void updatePersonTest() throws Exception{
		//加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//创建sql语句
		String sql="update person set name=? where id=?";
		//创建桥梁
		PreparedStatement ps=conn.prepareStatement(sql);
		//传入参数
		ps.setString(1, "小红");
		ps.setInt(2,1);
		//执行
		ps.executeUpdate();
		//关闭
		conn.close();
	}
	
	@Test
	//orm 取对象
	public void queryPersonTest() throws Exception{
		//加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//创建sql语句
		String sql="select * from person where id=?";
		//创建装sql的车
		PreparedStatement ps=conn.prepareStatement(sql);
		//传参
		ps.setInt(1, 1);
		Person p=null;
		
		//执行
		ResultSet rs=ps.executeQuery();
		//迭代器操作
		while(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			int age=rs.getInt("age");
			String pid=rs.getString("pid");
			p=new Person(id,name,age,pid);
			
		}
		System.out.println(p);
		conn.close();
	}
	@Test
	public void testPools() throws Exception {
//		System.out.println(JdbcUtils.pools.size());
//		Connection conn=JdbcUtils.getConnection();
//		System.out.println(JdbcUtils.pools.size());
//		conn.close();
//		System.out.println(JdbcUtils.pools.size());
	}
	
	//抽离增删改模块
	@Test
	public void testUtils() {
//		String sql="insert into person values (25,?,?,?)";
//		Object[] params= {"张niu",20,"30009"};
//		execute(sql,params);
		
		String sql2="update person set name=? where id=?";
		Object[] params2= {"春红",1};
		execute(sql2,params2);
		
	}
	@Test
	public void testQuery()throws Exception{
		
//		String sql="select * from person";
//		Object[] params=null;
//		Person p=(Person) queryForBean(sql,params,new BeanResultSetHandler<Person>(Person.class));
//		System.out.println(p);
		
//		String sql2="select * from t_demo where id=?";
//		Object[] params2= {2};
//		Column c=(Column)queryForBean(sql2,params2,new BeanResultSetHandler<Column>(Column.class));
//		System.out.println(c);
//		
		
		String sql3="select * from person";
		Object[] params3=null;
		List<Person> p3=(List<Person>)queryForBean(sql3,params3,new BeanListResultSethandler<Person>(Person.class));
		System.out.println(p3);
		
		
	}
	//返回一个值
	public static <T> Object queryForBean(String sql,Object[] params,ResultSetHandler<T> rsh) {
		
		//获取连接
		Connection conn=null;
		//
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person person=new Person();
		try {
			conn=JdbcUtils.getConnection();
			//预处理sql
			//获取preparedstatement
			ps=conn.prepareStatement(sql);
			//传入参数
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					//i+1代表？的位置，从1开始
					ps.setObject(i+1,params[i]);
				}
			}
			//执行sql
			rs=ps.executeQuery();
			return rsh.handle(rs);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return null;
	}
	//查
	public Person query(String sql,Object[] params) {
		//获取连接
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person person=new Person();
		try {
			conn=JdbcUtils.getConnection();
			//预处理sql
			//获取preparedstatement
			ps=conn.prepareStatement(sql);
			//ps传入参数
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			//执行sql
			rs=ps.executeQuery();
			while(rs.next()) {
				//以int/String 的形式获取此 ResultSet 对象的当前行中指定列的值。
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String pid=rs.getString("pid");
				//放入到Person对象
				person.setId(id);
				person.setName(name);
				person.setAge(age);
				person.setPid(pid);
			}
			return person;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return null;
	}
	
	//增删改
	public int execute(String sql,Object[] params) {
		//获取连接
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtils.getConnection();
			//预处理sql
			//获取preparedStatement
			ps=conn.prepareStatement(sql);
			//传入参数
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
//					i+1代表的是？的位置
					ps.setObject(i+1,params[i]);
				}
			}
			//执行sql
			int result=ps.executeUpdate();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn, ps, null);
		}
		return 0;
	}
	@Test//测试QueryRunner工具
	public void testQueryRunner() {
//		QueryRunner qr=new QueryRunner();
//		String sql="delete from person where id=?";
//		Object[] params= {22};
//		qr.execute(sql, params);
		
		//查询一个列表column
		String sql2="select * from t_demo where id>?";
		Object[] params2= {0};
		List<Column> cc=(List<Column>)queryForBean(sql2,params2,new BeanListResultSethandler<Column>(Column.class));
		System.out.println(cc);
		
		
	}
	
	
	
	
}

class Column{
	private int id;
	private String content;
	
	
	public Column() {
		
	}
	
	public Column(int id, String content) {
		this.id = id;
		this.content = content;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Column [id=" + id + ", content=" + content + "]";
	}
	
	
	
}


class Person{
	private int id;
	private String name;
	private int age;
	private String pid;
	public Person() {
		
	}
	public Person(int id,String name,int age,String pid) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.pid=pid;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", pid=" + pid + "]";
	}
	
	
}