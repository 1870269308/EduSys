package com.demo.dao;

import java.util.List;

import com.demo.base.BaseDao;
import com.demo.pojo.User;


/**
 * �û�dao��
 * @author 86152
 *
 */
public interface UserDao extends BaseDao<User>{
	
	//�ȸ����ķ���  ��ȡ����-��ûʵ��
	public List<User> getPsByParams(Object[] params);


}
