package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Randoms extends JFrame {
	
	//基本面板显示
	JLabel num;//显示标题
	JButton start,stop; // 开始、停止按钮
	JLabel rs; //显示结果
	
	JPanel jp1,jp2;
	ExListener listener;
	 
	static boolean isSotp = true; //判断当前状态
	public Randoms() {
		init();
	}
		
	void init() {
		num = new JLabel("<<玩的就是心跳>>");
		num.setFont(new Font("黑体", 2, 40));
		start = new JButton("开始");
		stop = new JButton("停止");
		//显示区域
		rs = new JLabel("0");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		getContentPane().add(num,BorderLayout.NORTH);
		jp1.add(rs);
		getContentPane().add(jp1,BorderLayout.CENTER);
		FlowLayout layout = new FlowLayout();//面板布局
		layout.setAlignment(FlowLayout.CENTER);
//		jp1.setLayout(layout);
//		jp2.setLayout(layout);
		//这里面板添加开始 暂停按钮
		jp2.add(start);
		jp2.add(stop);
		//字体设置
		rs.setFont(new Font("宋体", 1, 30));//设置结果样式
		rs.setForeground(Color.RED);
		getContentPane().add(jp2,BorderLayout.SOUTH);
	}
	
	//监听函数
	void AListener(ExListener listener) {
		this.listener = listener;
		//监听对象中  添加开始 暂停按钮 还有显示标签  显示状态
		listener.set(start, stop, rs, isSotp); 
		start.addActionListener(listener); //添加监听
		stop.addActionListener(listener); //添加监听
	}
}
