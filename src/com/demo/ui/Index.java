package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTextField;

import manager.indexListener.ListenerCourse;
import manager.indexListener.ListenerExam;
import manager.indexListener.listenerExit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Index {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
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
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//上课点名
		JButton btnNewButton = new JButton("\u4E0A\u8BFE\u70B9\u540D");
		btnNewButton.setBounds(33, 44, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		//课表安排
		JButton btnNewButton_1 = new JButton("\u8BFE\u8868\u5B89\u6392");
		ListenerCourse lc=new ListenerCourse(frame);
		btnNewButton_1.addActionListener(lc);
		btnNewButton_1.setBounds(33, 102, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		//考试系统
		JButton btnNewButton_2 = new JButton("\u8003\u8BD5\u7CFB\u7EDF");
		ListenerExam le=new ListenerExam(frame);
		btnNewButton_2.addActionListener(le);
		btnNewButton_2.setBounds(33, 161, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		//退出登录
		JButton btnNewButton_3 = new JButton("\u9000\u51FA\u767B\u5F55");		
		listenerExit lx=new listenerExit(frame);
		btnNewButton_3.addActionListener(lx);
		btnNewButton_3.setBounds(457, 28, 113, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(216, 81, 329, 178);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
