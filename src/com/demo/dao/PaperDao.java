package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.Paper;
import com.demo.pojo.UserManage;


public interface PaperDao extends BaseDao<Paper>{
	//��ȡ���еõ���Ԫ��
	List<Paper> gettableDatas();
	//��ѯ�¼�
	List<Paper> getSelectDatas(Integer obj);
}
