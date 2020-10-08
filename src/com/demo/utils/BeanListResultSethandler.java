package com.demo.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BeanListResultSethandler<T> implements ResultSetHandler<T> {

	
	
	private Class clazz;
	public BeanListResultSethandler(Class clazz) {
		this.clazz=clazz;
	}
	@Override
	public Object handle(ResultSet rs) {
		try {
			//׼��һ��list����
			ArrayList list=new ArrayList();
			//�õ���Ҫȡ�������ֶ�
			Field[] fs=clazz.getDeclaredFields();
			//�����������ѵõ����к�������д������obj������
			while(rs.next()) {
				//��Ҫ���صĵ�������
				Object obj=clazz.newInstance();
				for(Field f:fs) {
					//��÷���Ȩ
					f.setAccessible(true);
					//��ȡ��Ա����������
					String columnLabel=f.getName();
					//�� J Object ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ
					Object value=rs.getObject(columnLabel);
					//��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ
					f.set(obj, value);
				}
				list.add(obj);
			}
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

}