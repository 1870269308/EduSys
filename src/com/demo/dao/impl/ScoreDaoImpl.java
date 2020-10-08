package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.demo.base.BaseDaoImpl;
import com.demo.dao.ScoreDao;
import com.demo.pojo.Score;

public class ScoreDaoImpl extends BaseDaoImpl<Score> implements ScoreDao{
	@Override
	public ResultSet getDatas(Connection conn) throws Exception {
		String sql = "select sumScore from score";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
