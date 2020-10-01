package test;

import javax.swing.JFrame;

public class Menu {
	


	public static void main(String[] args) { //主程序
		
		Randoms n = new Randoms();
		
		//生成一个监听事件对象
		FromEx f=new FromEx();
	
		n.AListener(f);
		n.setResizable(false);
		n.setTitle("学号点名小程序");
		n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		n.setBounds(700, 200, 100, 200);
		n.setSize(400, 300);
		n.setVisible(true);
	}
}
