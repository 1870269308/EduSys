package com.demo.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
//ResultSet-���ݿ����������ݱ�
public class BeanResultSetHandler<T> implements ResultSetHandler<T>{
	
	
	private Class clazz;
	public BeanResultSetHandler(Class clazz) {
		this.clazz=clazz;
	}
	@Override
	public T handle(ResultSet rs) {
		try {
			//��Ҫ���صĵ�������
			Object obj=clazz.newInstance();
			//�õ���Ҫȡ�������ֶ�
			Field[] fs=clazz.getDeclaredFields();
			//�����������ѵõ����к�������д������obj������
			while(rs.next()) {
				for(Field f:fs) {
					//��÷���Ȩ
					f.setAccessible(true);
					//��ȡ��Ա����������
					String columnLabel=f.getName();
					//�� J Object ����ʽ��ȡ�� ResultSet ����ĵ�ǰ����ָ���е�ֵ��
					Object value=rs.getObject(columnLabel);
					//��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ��
					f.set(obj, value);
				}
			}
			return (T) obj;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	

}
