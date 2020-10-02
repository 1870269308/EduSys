package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.pojo.Question;


//试题dao
public class QuestionDao {
		
	//查询
	public ResultSet query(Connection conn) throws Exception{
		String sql="select * from question";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	} 
	//删除
	public void delete(Connection conn,int id) {
		//sql语句
		String sql="delete from question where id=?";
		//创建连接
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			System.out.println(id);
			pstmt.executeUpdate();//增删改
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//修改试题
	public ResultSet update(Connection conn) throws Exception{
		String sql="select * from question where id=7";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	}

}
