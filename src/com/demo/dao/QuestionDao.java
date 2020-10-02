package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.pojo.Question;


//����dao
public class QuestionDao {
		
	//��ѯ
	public ResultSet query(Connection conn) throws Exception{
		String sql="select * from question";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	} 
	//ɾ��
	public void delete(Connection conn,int id) {
		//sql���
		String sql="delete from question where id=?";
		//��������
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			System.out.println(id);
			pstmt.executeUpdate();//��ɾ��
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//�޸�����
	public ResultSet update(Connection conn) throws Exception{
		String sql="select * from question where id=7";
		PreparedStatement pstmt=conn.prepareStatement(sql);
	//	System.out.println(pstmt.executeQuery());
		return pstmt.executeQuery();
	}

}
