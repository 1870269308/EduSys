package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;

import com.demo.pojo.FeedBack;
import com.demo.pojo.User;
import com.demo.pojo.UserManage;
import com.demo.utils.QueryRunner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class stu_back_msg {

	private JFrame frame;
	private static User userMessage = new User();
	private static FeedBack FeedMessage=new FeedBack();
	private String name;
	private String textmsg;
	private JTextArea textArea;
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
	public stu_back_msg(User userMessage) {

		this.userMessage = userMessage;
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u53CD\u9988\u4FE1\u606F");
		frame.setBounds(100, 100, 662, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);
		
		JButton button = new JButton("\u63D0\u4EA4");
		
		button.setBounds(24, 109, 93, 23);
		frame.getContentPane().add(button);
		
		textArea = new JTextArea();
		textArea.setBounds(176, 44, 446, 246);
		frame.getContentPane().add(textArea);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击按钮返回学生反馈界面
				frame.dispose();
				stu_back bk=new stu_back(userMessage);
				bk.getFrame().setVisible(true);
			}
		});
		button_1.setBounds(24, 228, 93, 23);
		frame.getContentPane().add(button_1);
		
		button.addActionListener(new ActionListener() {
			//提交按钮监听
			public void actionPerformed(ActionEvent e) {
				if ( null==userMessage.getUserName()) {
					JOptionPane.showMessageDialog(null, "错误：请登录，再进行反馈");
					return;
				}
				String msg=textArea.getText();//获取文本域的内容
				String username=userMessage.getUserName();//获取用户名信息
				System.out.println(username);
//				if(FeedMessage.getFeedback()!=msg) {
				String result="反馈信息为："+msg;
				System.out.println(result);
				transferTable();//调用下面的插入方法
//				}else {
//					JOptionPane.showMessageDialog(null, "反馈问题重复！！！");
//					return;
//				}
				
			}
			
		});
	}

	public JFrame getFrame() {
		return frame;
	}
	public void transferTable() {
		//用户名，反馈信息，插入到数据库内
		String sql="insert into Feedback value(null,?,?)";
		name=userMessage.getUserName();
		textmsg=textArea.getText();
		Object[] obj= {name,textmsg};
		System.out.println(sql);
		new QueryRunner().execute(sql, obj);
	}
}
