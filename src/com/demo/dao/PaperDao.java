package com.demo.dao;

import java.util.List;

import com.demo.pojo.Paper;


public interface PaperDao {
	//��ȡ���еõ���Ԫ��
	List<Paper> gettableDatas();
	//��ѯ�¼�
	List<Paper> getSelectDatas(Integer obj);
}
