package com.demo.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public final class JdbcUtils {

	private static String url="";
	private static String user="";
	private static String password="";
	private static String driver="";

	private static int initCount=1;


	
	//准备一串connection  pools池子
	private static LinkedList<Connection> pools=new LinkedList<Connection>();
	
	static{
		//加载驱动
		try {
			//EduSys/src/com/demo/db.properties
			Properties prop=new Properties();
			prop.load(new BufferedInputStream(new FileInputStream("src/com/demo/db.properties")));
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			password=prop.getProperty("password");
			driver=prop.getProperty("driver");
			initCount=Integer.parseInt(prop.getProperty("initCount"));
			Class.forName(driver);
			
			//初始化2条connection
			for(int i=0;i<initCount;i++) {
				Connection conn=createConnection();
				pools.add(proxy(conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//封装close方法的conn动态代理类
	//classLoader 类的加载器
	public static Connection proxy(Connection conn) {
		return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),//
				new Class[] {Connection.class}, new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object result=null;
						//method   Connection.class 里的所有方法
						if(method.getName().equals("close")) {
							pools.add(conn);
						}else {
							//原本是什么方法就执行什么方法
							result=method.invoke(conn, args);
						}
						return result;
					}
				});
	}
	
	//创建conn
	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static Connection getConnection() throws SQLException {
		return pools.removeFirst();
	}
	
	public static void close(Connection conn,Statement statmenet,ResultSet rs) {
		if(null!=conn) {
			try {
				conn.close();//我需要让这个conn.close()--->pools.add(conn)
				//必须在conn的运行代码内存中改动代码方法
//				pools.add(conn);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(null!=statmenet) {
			try {
				statmenet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null!=rs) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}


