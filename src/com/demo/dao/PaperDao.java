package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.Paper;
import com.demo.pojo.UserManage;


public interface PaperDao extends BaseDao<Paper>{
	//获取所有得到的元素
	List<Paper> gettableDatas();
	//查询事件
	List<Paper> getSelectDatas(Integer obj);
}
