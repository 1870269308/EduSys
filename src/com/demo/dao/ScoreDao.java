package com.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.demo.base.BaseDao;
import com.demo.pojo.Score;

public interface ScoreDao extends BaseDao<Score>{
	public ResultSet getDatas(Connection conn) throws Exception;
}
