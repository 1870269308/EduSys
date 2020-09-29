package com.demo.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.demo.utils.QueryRunner;
import com.mysql.jdbc.Connection;


public class BaseDaoImpl<T> implements BaseDao<T>{
	private QueryRunner qr=new QueryRunner();
	private Class clazz;
	public BaseDaoImpl() {
		//�õ���������
		Type type=this.getClass().getGenericSuperclass();
		//��ȡ������ķ�������
		//ParameterizedType-��ʾ����������
		ParameterizedType pt=(ParameterizedType) type;
		//ȡ�������еĲ���
		Type tType=pt.getActualTypeArguments()[0];
		//��õ�class
		this.clazz=(Class)tType;
	
	}
	

	@Override
	public void register(T t) {
		//�ַ���ƴ��
//		String sql="insert into "+t.getClass().getSimpleName()+" (id,name,age,pid) values (?,?,?,?)";
		StringBuilder sb=new StringBuilder();
		//��ʼƴ��sql���
		String preStr="insert into ";
		sb.append(preStr);
		//��ȡ����
		//����ı�����������ͬ�������д�Сд���𣬵�ת��
		//this.clazz.getSimpleName()--��ȡ����
		//toLowerCase()--תСд
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		//System.out.println(sb.toString());
		//  insert into person
		//  (id,name,age,pid) 
		Field[] fs=this.clazz.getDeclaredFields();
		Object[] params=new Object[fs.length];
		String sqlTemp="( ";
		for(Field f:fs) {
			//��÷���Ȩ
			f.setAccessible(true);
			//�������
			String column=f.getName();
			sqlTemp+=column+",";
		}
		//System.out.println(sb.toString());
		//insert into `person` id,name,age,pid,
		//���˸�����
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1)+" )";
		sb.append(sqlTemp);
		// values (?,?,?,?)"
		sb.append(" values (");
		sqlTemp="";
		for(int i=0;i<fs.length;i++) {
			sqlTemp+="?,";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1);
		sb.append(sqlTemp);
		sb.append(")");
		String sql=sb.toString();
		System.out.println(sql);
		
		//��ȡ������Person�����Ե�ֵ
		//�����getter�����ĵ���
		for(int i=0;i<fs.length;i++) {
			//��Ȩ��
			fs[i].setAccessible(true);
			try {
				params[i]=fs[i].get(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		qr.execute(sql, params);
		
		
	}


}

