package com.demo.thread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.demo.utils.JdbcUtils;

public class RandomThread extends Thread{
	
	private JLabel lblNewLabel_1;  //���������ҳ�洫�����ı�ǩ
	private boolean isStop; //�̸߳ı��Ժ��״̬
	int number; 
	int total = 0; 

	public void setbool(Boolean isStop) {
		this.isStop = isStop; 
		
	}
	//�洢�����ݿ���ȡ������ֵ
	Vector vector = new Vector(); 
	
	
	public RandomThread(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1; 
		v(vector); 
	}


	private void v(Vector vector) {
		try {
			//��������
			Connection cn = JdbcUtils.getConnection();
			Statement cmd = cn.createStatement(); 
			String sql = "select * from user"; 
			ResultSet result = cmd.executeQuery(sql);
			//��ÿ�����ݶ�����ȡ����
			while(result.next()) {
				//����ȡ���ڶ����ֶ� ���ֵ���
				String name = result.getString(2); 
				vector.add(name); 
			}
			//���������������Ԫ�صĸ���
			number = vector.size();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//�����̵߳�����
	@Override 
	public void run() {
		//��true�����߳�  false�Զ�����
		while(isStop) {
			try {
				//�ж�ȡ���ļ����Ƿ�Ϊ��,������ʾ
				if(number == 0) {
					JOptionPane.showMessageDialog(null, "����û��ѧ��");
					isStop = false; 
				}
				//�������߳�����ʱ�䣨�̼߳��ʱ�䣩
				Thread.sleep(50);
				lblNewLabel_1.setText((vector.get(getrandom(number)).toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	//���ص���һ��number��Χ�ڵ������   �������õ��������������
	private int getrandom(int number) {
		Random random = new Random(); 
		int rand = random.nextInt(number);
		return rand;
	}
	
	
}
