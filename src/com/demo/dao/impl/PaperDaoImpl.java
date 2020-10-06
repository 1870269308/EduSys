package com.demo.dao.impl;

import java.util.List;

import com.demo.dao.PaperDao;
import com.demo.pojo.Paper;
import com.demo.utils.BeanListResultSethandler;
import com.demo.utils.QueryRunner;



public class PaperDaoImpl implements PaperDao{
	//查询全表数据
	@Override
	public List<Paper> gettableDatas() {
		String sql="select * from paper";
		Object[] params=null;
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSethandler<Paper>(Paper.class));
		return list;
	}
	//查询该表的Id数据
	@Override
	public List<Paper> getSelectDatas(Integer obj) {
		String sql="select * from paper where id=?";
		Object[] params=null;
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSethandler<Paper>(Paper.class));
		return list;
	}
	

}
