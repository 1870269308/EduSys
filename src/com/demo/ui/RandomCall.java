package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import com.demo.pojo.User;
import com.demo.thread.RandomThread;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RandomCall {

	private JFrame frame;
	private JButton start, stop;
	private JLabel lblNewLabel_1;
	private Boolean isStop; 
	private RandomThread rt;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomCall window = new RandomCall();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RandomCall() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("\u5E78\u8FD0\u8F6C\u76D8");
		lblNewLabel.setBounds(131, 13, 208, 35);
		lblNewLabel.setIcon(new ImageIcon(RandomCall.class.getResource("/images/\u968F\u673A\u7528\u6237.png")));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 30));
		
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		
		/*
		 *名字显示区域
		 */
		lblNewLabel_1 = new JLabel("name");
		lblNewLabel_1.setFont(new Font("宋体", Font.ITALIC, 30));
		lblNewLabel_1.setBounds(178, 91, 113, 52);
		frame.getContentPane().add(lblNewLabel_1);
		
		/*
		 * 开始按钮
		 */
		start = new JButton("\u5F00\u59CB");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAction(e); 
			}
		});
		start.setBounds(14, 198, 113, 27);
		frame.getContentPane().add(start);
		
		/*
		 * 停止按钮
		 */
		JButton stop = new JButton("\u6682\u505C");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopAction(e); 
			}

			
		});
		stop.setBounds(165, 198, 113, 27);
		frame.getContentPane().add(stop);
		
		JButton back = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Index index = new Index(); 
				index.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		back.setBounds(305, 198, 113, 27);
		frame.getContentPane().add(back);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/*
	 * 开始按钮   点击后的操作
	 */
	private void startAction(ActionEvent e) {
		try {
			//显示与线程绑定
			rt = new RandomThread(lblNewLabel_1); 
			isStop = true; 
			rt.setbool(isStop);
			rt.start();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	/*
	 * 停止按钮    点击后的操作
	 */
	private void stopAction(ActionEvent e) {
		isStop = false; 
		rt.setbool(isStop);
		System.out.println("this is stop!");
	}
}
