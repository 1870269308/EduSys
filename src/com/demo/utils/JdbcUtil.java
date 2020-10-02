package com.demo.utils;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcUtil {
	private static String url="";
	private static String user="";
	private static String password="";
	private static String driver="";
	static{
		//¼ÓÔØÇý¶¯
		try {
			Properties prop=new Properties();
			prop.load(new BufferedInputStream(new FileInputStream("src//com//demo//db.properties")));
			url=prop.getProperty("url");
			user=prop.getProperty("user");
			password=prop.getProperty("password");
			driver=prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void close(Connection conn) {
		if(null!=conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
