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
		
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//����sql���
		String sql="insert into person values (1,?,?,?)";
		//����װsql�ĳ�
		PreparedStatement ps=conn.prepareStatement(sql);
		//ʵ��������
		Person p=new Person(1,"����",10,"102020");
		//�������
		ps.setString(1, p.getName());
		ps.setInt(2,p.getAge());
		ps.setString(3, p.getPid());
		
		//ִ��sql
		ps.executeUpdate();
		//�ر�����
		conn.close();
	}
	@Test
	public void updatePersonTest() throws Exception{
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//����sql���
		String sql="update person set name=? where id=?";
		//��������
		PreparedStatement ps=conn.prepareStatement(sql);
		//�������
		ps.setString(1, "С��");
		ps.setInt(2,1);
		//ִ��
		ps.executeUpdate();
		//�ر�
		conn.close();
	}
	
	@Test
	//orm ȡ����
	public void queryPersonTest() throws Exception{
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		Connection conn=DriverManager.getConnection("jdbc:mysql://172.21.177.4:3306/demo?useUnicode=true&amp;characterEncoding=utf8"//
				, "root","root");
		//����sql���
		String sql="select * from person where id=?";
		//����װsql�ĳ�
		PreparedStatement ps=conn.prepareStatement(sql);
		//����
		ps.setInt(1, 1);
		Person p=null;
		
		//ִ��
		ResultSet rs=ps.executeQuery();
		//����������
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
	
	//������ɾ��ģ��
	@Test
	public void testUtils() {
//		String sql="insert into person values (25,?,?,?)";
//		Object[] params= {"��niu",20,"30009"};
//		execute(sql,params);
		
		String sql2="update person set name=? where id=?";
		Object[] params2= {"����",1};
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
	//����һ��ֵ
	public static <T> Object queryForBean(String sql,Object[] params,ResultSetHandler<T> rsh) {
		
		//��ȡ����
		Connection conn=null;
		//
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person person=new Person();
		try {
			conn=JdbcUtils.getConnection();
			//Ԥ����sql
			//��ȡpreparedstatement
			ps=conn.prepareStatement(sql);
			//�������
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					//i+1������λ�ã���1��ʼ
					ps.setObject(i+1,params[i]);
				}
			}
			//ִ��sql
			rs=ps.executeQuery();
			return rsh.handle(rs);
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return null;
	}
	//��
	public Person query(String sql,Object[] params) {
		//��ȡ����
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Person person=new Person();
		try {
			conn=JdbcUtils.getConnection();
			//Ԥ����sql
			//��ȡpreparedstatement
			ps=conn.prepareStatement(sql);
			//ps�������
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			//ִ��sql
			rs=ps.executeQuery();
			while(rs.next()) {
				//��int/String ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ��
				int id=rs.getInt("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				String pid=rs.getString("pid");
				//���뵽Person����
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
	
	//��ɾ��
	public int execute(String sql,Object[] params) {
		//��ȡ����
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JdbcUtils.getConnection();
			//Ԥ����sql
			//��ȡpreparedStatement
			ps=conn.prepareStatement(sql);
			//�������
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
//					i+1������ǣ���λ��
					ps.setObject(i+1,params[i]);
				}
			}
			//ִ��sql
			int result=ps.executeUpdate();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(conn, ps, null);
		}
		return 0;
	}
	@Test//����QueryRunner����
	public void testQueryRunner() {
//		QueryRunner qr=new QueryRunner();
//		String sql="delete from person where id=?";
//		Object[] params= {22};
//		qr.execute(sql, params);
		
		//��ѯһ���б�column
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