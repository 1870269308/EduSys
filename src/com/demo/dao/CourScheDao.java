package com.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.demo.pojo.CouSche;


public interface CourScheDao {
	public ResultSet getDatas(Connection conn,String foreign_id) throws Exception;
	public int updCourse(Connection conn, String foreign_id,Object[] params) throws Exception;
}
