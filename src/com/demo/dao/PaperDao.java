package com.demo.dao;

import java.util.List;

import com.demo.pojo.Paper;


public interface PaperDao {
	//获取所有得到的元素
	List<Paper> gettableDatas();
	//查询事件
	List<Paper> getSelectDatas(Integer obj);
}
