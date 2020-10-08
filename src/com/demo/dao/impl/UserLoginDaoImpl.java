package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.pojo.User;
import com.demo.pojo.UserManage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDaoImpl {
	public User login(Connection con, User user) throws SQLException {
		
		User resultUser = null;
		
		//���ݿ��ѯ���
		String sql = "select * from user where username= ? and `password` = ? and role=?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql); 
		//�����user��¼���洫���ֵ
		pstmt.setString(1, user.getUserName()); 
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getRole());
		//��ѯ���ؽ��
		ResultSet rs = pstmt.executeQuery(); 
		//��ѯ���ݿ����Ƿ���ֵ
		if(rs.next()) {
			//�´���һ���������洢���ݿ��в鵽������
			resultUser = new User();
			resultUser.setId(rs.getInt("id")); 
			resultUser.setUserName(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setRole(rs.getString("role"));
		}
		//��ô����ݿ��з���ƥ��Ķ���
		return resultUser; 
	}
	
	public UserManage loginManage(Connection con, UserManage user) throws SQLException {
		
		UserManage resultUser = null;
		
		//���ݿ��ѯ���
		String sql = "select id,`name`,password from usermanage where name= ? and `password` = ?"; 
		
		PreparedStatement pstmt = con.prepareStatement(sql); 
		//�����user��¼���洫���ֵ
		pstmt.setString(1, user.getName()); 
		pstmt.setString(2, user.getPassword());
		//pstmt.setString(3, user.getSubject());
		//��ѯ���ؽ��
		ResultSet rs = pstmt.executeQuery(); 
		//��ѯ���ݿ����Ƿ���ֵ
		if(rs.next()) {
			//�´���һ���������洢���ݿ��в鵽������
			resultUser = new UserManage();
			resultUser.setId(rs.getInt("id")); 
			resultUser.setName(rs.getString("name"));
			resultUser.setPassword(rs.getString("password"));
			//resultUser.setSubject(rs.getString("subject"));
		}
		//��ô����ݿ��з���ƥ��Ķ���
		return resultUser; 
	}
}
