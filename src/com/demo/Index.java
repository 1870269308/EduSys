package com.demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTextField;

import com.demo.ui.Longin;
import com.demo.ui.RandomCall;
import com.demo.ui.myCalendar;

import manager.indexListener.ListenerCourse;
import manager.indexListener.ListenerExam;
import manager.indexListener.listenerExit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;

public class Index {

	private JFrame frame;

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
		frame.setBounds(100, 100, 938, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * 点名功能
		 */
		JButton btnNewButton = new JButton("\u4E0A\u8BFE\u70B9\u540D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
//				//随机点名类
				RandomCall random = new RandomCall(); 
				random.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		btnNewButton.setBounds(33, 88, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		//课表安排
		JButton btnNewButton_1 = new JButton("\u8BFE\u8868\u5B89\u6392");
		ListenerCourse lc=new ListenerCourse(frame);
		btnNewButton_1.addActionListener(lc);
		btnNewButton_1.setBounds(33, 224, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		//考试系统
		JButton btnNewButton_2 = new JButton("\u8003\u8BD5\u7CFB\u7EDF");
		ListenerExam le=new ListenerExam(frame);
		btnNewButton_2.addActionListener(le);
		btnNewButton_2.setBounds(33, 369, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		listenerExit lx=new listenerExit(frame);
		
		JButton btnNewButton_3 = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Longin login = new Longin(); 
//				LogOnFrm login=new LogOnFrm();
				//	login.getFrame().setVisible(true);
				login.getFrame().setVisible(true); 
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(734, 44, 113, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(268, 99, 585, 372);
		frame.getContentPane().add(desktopPane);
		myCalendar mycalendar=new myCalendar();
		mycalendar.setBounds(0, 0, 585, 372);
		desktopPane.add(mycalendar);
		mycalendar.setVisible(true);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
	}
}
