package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class stu_back_msg {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stu_back_msg window = new stu_back_msg();
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
	public stu_back_msg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u53CD\u9988\u4FE1\u606F");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);
		
		JButton button = new JButton("\u63D0\u4EA4");
		
		button.setBounds(24, 109, 93, 23);
		frame.getContentPane().add(button);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(176, 44, 191, 170);
		frame.getContentPane().add(textArea);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg=textArea.getText();
				if(null==msg) {
					System.out.println("default");
				}else {
					String result="反馈信息为："+msg;
					System.out.println(result);
				}
				
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}
}
