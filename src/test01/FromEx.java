package test01;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

//实现ExListener的接口    
public class FromEx implements ExListener {
	
	JButton start,stop;
	JLabel rs;
	
	static boolean isSotp;
	//创建线程对象
	ExThread t;
	 
	public void set(JButton J1,JButton J2,JLabel rs,boolean isSotp){
		start=J1;
		stop=J2;
		this.rs=rs;
		this.isSotp=isSotp;
		
//		try {
//			//将rs与线程绑定  创建新的线程
//			t= new ExThread(rs);
//		} catch (IOException e) {
//		//	e.printStackTrace();
//		}
	}
	
	//响应事件
	public void actionPerformed(ActionEvent e){
		//获取触发事件对象实现监听映射。
		Object o = e.getSource();
		if(o == start){
			//String number = num.getText();
			try {
				//显示块与线程绑定
				t= new ExThread(rs);
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
			//	e1.printStackTrace();
			}
			try{
				//int n = Integer.parseInt(number);
				//判断线程状态 true 
				isSotp = true;
				//t.setint(n);
				//get线程设置状态true
				t.setbool(isSotp);
				//启动线程
				t.start();
			}catch(NumberFormatException e2){
				System.err.println("格式错误");
			}
			
		}else if(o == stop){  //按下停止按钮
			//转态变成停止
			System.out.println("这里变成了false");
			isSotp = false;
			t.setbool(isSotp);
			//这里再启动 当按下按钮  线程会重新创建一次  这两个线程运行导致冲突
		//	t.start();
			System.out.println("this is stop");
		}
	}
}
