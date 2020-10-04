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
	
	private JLabel lblNewLabel_1;  //从随机点名页面传过来的标签
	private boolean isStop; //线程改变以后的状态
	int number; 
	int total = 0; 

	public void setbool(Boolean isStop) {
		this.isStop = isStop; 
		
	}
	//存储从数据库中取出来的值
	Vector vector = new Vector(); 
	
	
	public RandomThread(JLabel lblNewLabel_1) {
		this.lblNewLabel_1 = lblNewLabel_1; 
		v(vector); 
	}


	private void v(Vector vector) {
		try {
			//建立连接
			Connection cn = JdbcUtils.getConnection();
			Statement cmd = cn.createStatement(); 
			String sql = "select * from user"; 
			ResultSet result = cmd.executeQuery(sql);
			//将每行数据都依次取出来
			while(result.next()) {
				//这里取到第二个字段 名字的列
				String name = result.getString(2); 
				vector.add(name); 
			}
			//这里给出集合里面元素的个数
			number = vector.size();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//这里线程的运行
	@Override 
	public void run() {
		//当true运行线程  false自动死亡
		while(isStop) {
			try {
				//判断取出的集合是否为空,空则提示
				if(number == 0) {
					JOptionPane.showMessageDialog(null, "这里没有学生");
					isStop = false; 
				}
				//这里让线程休眠时间（线程间隔时间）
				Thread.sleep(50);
				lblNewLabel_1.setText((vector.get(getrandom(number)).toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	//返回的是一个number范围内的随机数   这样就让点名名字有随机性
	private int getrandom(int number) {
		Random random = new Random(); 
		int rand = random.nextInt(number);
		return rand;
	}
	
	
}
