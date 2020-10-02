package test01;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Randoms extends JFrame {
	
	//基本面板显示
	JLabel num;//显示标题
	JButton start,stop; // 开始、停止按钮
	JLabel rs; //显示结果
	JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	JPanel jp1,jp2;
	ExListener listener;
	
	public static void main(String[] args) {
		//生成一个监听事件对象
		FromEx f=new FromEx();
		
		Randoms random = new Randoms(); 
		random.AListener(f);
		random.setResizable(false);
		random.setTitle("学号点名小程序");
		random.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		random.setBounds(700, 200, 100, 200);
		random.setSize(400, 300);
		random.setVisible(true);
	}
	
	
	 
	static boolean isSotp = true; //判断当前状态
	public Randoms() {
		init();
	}
		
	void init() {
		num = new JLabel("    <<\u5929\u8FD0\u4E4B\u5B50>>");
		num.setFont(new Font("黑体", 2, 40));
		start = new JButton("开始");
		stop = new JButton("停止");
		//显示区域
		rs = new JLabel("0");
		rs.setBounds(164, 51, 74, 54);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		getContentPane().add(num,BorderLayout.NORTH);
		jp1.setLayout(null);
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
		rs.setFont(new Font("宋体", Font.BOLD, 34));//设置结果样式
		rs.setForeground(Color.black);
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
