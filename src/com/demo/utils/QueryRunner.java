package com.demo.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class QueryRunner {
	//查询框架
	public static <T> Object query(String sql, Object[] params, ResultSetHandler<T> rsh) {

		// 获取连接
		Connection conn = null;
		//
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// 预处理sql
			// 获取preparedstatement
			ps = conn.prepareStatement(sql);
			// 传入参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// i+1代表？的位置，从1开始
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行sql
			rs = ps.executeQuery();
			return rsh.handle(rs);
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return null;
	}
	//增删改框架
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

}
