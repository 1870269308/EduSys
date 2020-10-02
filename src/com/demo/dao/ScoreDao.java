package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ScoreDao {
	//��ѯ
	public ResultSet query(Connection conn) throws Exception{
		String sql="select * from score";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	}
	//����id��ѯ
	public ResultSet query(Connection conn,int id) throws Exception{
		String sql="select * from score where id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		pstmt.setInt(1, id);
		return pstmt.executeQuery();
	}
	//����id��ѯ
	public ResultSet query(Connection conn,String name) throws Exception{
		String sql="select * from score where name=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		pstmt.setString(1, name);
		return pstmt.executeQuery();
	}

}
