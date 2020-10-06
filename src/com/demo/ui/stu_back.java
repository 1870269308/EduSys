package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.junit.Test;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class stu_back {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stu_back window = new stu_back();
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
	public stu_back() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u754C\u9762");
		frame.setBounds(100, 100, 631, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);

		JButton button = new JButton("\u8003\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//todo
			}
		});
		button.setBounds(44, 124, 93, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u53CD\u9988");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 跳转的窗口
				frame.dispose();//窗体消失,跳转到反馈页面
				stu_back_msg msg=new stu_back_msg();
				msg.getFrame().setVisible(true);
			}
		});
		button_1.setBounds(138, 124, 93, 23);
		frame.getContentPane().add(button_1);

		JTextPane textPane = new JTextPane();
		textPane.setText("\u53EF\u4EE5\u6DFB\u52A0\u56FE\u7247");
		textPane.setBounds(288, 45, 225, 203);
		frame.getContentPane().add(textPane);
	}

}
