package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.demo.dao.impl.UserLoginDao;
import com.demo.pojo.User;
import com.demo.utils.JdbcUtils;
import com.demo.utils.StringUtils;

import javax.swing.JPasswordField;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;


public class Longin {

	
	private JFrame frame;
	private JTextField usertext;
	
	private JdbcUtils dbutil = new JdbcUtils();
	private UserLoginDao userDao = new UserLoginDao(); 
	private JRadioButton rdbtnNewRadioButton_man = new JRadioButton("\u7BA1\u7406\u5458");
	private JRadioButton rdbtnNewRadioButton_stu = new JRadioButton("\u7528\u6237");
	private JButton btnNewButton_login = new JButton("\u767B\u5F55");
	private JPasswordField pswtext;

	
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
					Longin window = new Longin();
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
	public Longin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("教务管理系统");
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setType(Type.UTILITY);
		frame.setBounds(100, 100, 632, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setIcon(new ImageIcon(Longin.class.getResource("/images/\u6559\u52A1\u7CFB\u7EDF.png")));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(202, 34, 201, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(Longin.class.getResource("/images/\u7528\u6237.png")));
		lblNewLabel_1.setBounds(155, 98, 96, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		usertext = new JTextField();
		usertext.setBounds(265, 95, 166, 28);
		frame.getContentPane().add(usertext);
		usertext.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5BC6 \u7801\uFF1A");
		lblNewLabel_1_1.setIcon(new ImageIcon(Longin.class.getResource("/images/\u5BC6\u7801 (1).png")));
		lblNewLabel_1_1.setBounds(155, 149, 78, 23);
		frame.getContentPane().add(lblNewLabel_1_1);
		
//		rdbtnNewRadioButton_stu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(rdbtnNewRadioButton_stu.isSelected()){
//				  String radioText = rdbtnNewRadioButton_stu.getText();
//				  System.out.println(radioText);
//				}
//			}
//		});
		
		//按钮监听事件
		rdbtnNewRadioButton_man.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 登录按钮
				 */
				btnNewButton_login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loginAction(e); 
						
					}
				});
			}
		});
		
		
		//登录按钮监听事件
		rdbtnNewRadioButton_stu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 登录按钮
				 */
				btnNewButton_login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loginAction(e);
					}
				});
			}
		});
		
		
		
		
		rdbtnNewRadioButton_stu.setBounds(230, 203, 78, 27);
		frame.getContentPane().add(rdbtnNewRadioButton_stu);
		
		
		
		
		rdbtnNewRadioButton_man.setBounds(358, 203, 96, 27);
		frame.getContentPane().add(rdbtnNewRadioButton_man);
		
		
		//加入到组件中
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton_man);
		group.add(rdbtnNewRadioButton_stu);
		
		
		//btnNewButton_login.setBackground(Color.GRAY);
		btnNewButton_login.setIcon(new ImageIcon(Longin.class.getResource("/images/\u767B\u5F55.png")));
		
		
		btnNewButton_login.setBounds(101, 258, 113, 27);
		frame.getContentPane().add(btnNewButton_login);
		
		
		/**
		 * 重置按钮
		 */
		JButton btnNewButton_reset = new JButton("\u91CD\u7F6E");
		btnNewButton_reset.setIcon(new ImageIcon(Longin.class.getResource("/images/\u91CD\u7F6E.png")));
		btnNewButton_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueAction(e);
			}
		});
		
		
		btnNewButton_reset.setBounds(265, 258, 113, 27);
		frame.getContentPane().add(btnNewButton_reset);
		
		/**
		 * 注册按钮
		 */
		JButton btnNewButton_register = new JButton("\u5B66\u751F\u6CE8\u518C");
		btnNewButton_register.setIcon(new ImageIcon(Longin.class.getResource("/images/\u6CE8\u518C.png")));
		
		btnNewButton_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				LogOnFrm login=new LogOnFrm();
				//	login.getFrame().setVisible(true);
				registerFrm regist = new registerFrm(); 
				regist.getFrame().setVisible(true);
			}
		});
		
		btnNewButton_register.setBounds(426, 258, 113, 27);
		frame.getContentPane().add(btnNewButton_register);
		
		pswtext = new JPasswordField();
		pswtext.setBounds(265, 144, 166, 28);
		frame.getContentPane().add(pswtext);
		
		//设置窗体居中
		frame.setLocationRelativeTo(null);
	}
	
	
	/**
	 * 登录事件处理
	 * @param e
	 */
	private void loginAction(ActionEvent e) {
		String userName = this.usertext.getText(); 
		String password = this.pswtext.getText(); 
		
		if(StringUtils.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null,  "用户名不能为空！");
			return;
		}
		
		if(StringUtils.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空!");
			return;
		}
		
		
		if(rdbtnNewRadioButton_man.isSelected()){
			  String role = rdbtnNewRadioButton_man.getText();
			  System.out.println(role);

			  User user = new User(0,userName, password, role); 
				Connection con = null; 
				try {
					//这里获取连接
					con =  dbutil.getConnection();
					
					//这里将用户输入的信息和数据库中的信息比对
					User currentUser = userDao.login(con, user);
					if(currentUser!=null) {
												
						JOptionPane.showMessageDialog(null, "登录成功");
						//跳转
						Index index = new Index(currentUser); 
						index.getFrame().setVisible(true);
						frame.setVisible(false); 
						
					}else {
						JOptionPane.showMessageDialog(null, "用户名或密码，角色有误");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		if(rdbtnNewRadioButton_stu.isSelected()) {
			String role = rdbtnNewRadioButton_stu.getText();			  
			  User user = new User(0,userName, password, role); 

				Connection con = null; 
				try {
					//这里获取连接
					con =  dbutil.getConnection();
					
					//这里将用户输入的信息和数据库中的信息比对
					User currentUser = userDao.login(con, user);
					if(currentUser!=null) {
						JOptionPane.showMessageDialog(null, "登录成功");
						
						//TODO  这里跳转学生主页面
						stu_back index = new stu_back(); 
						index.getFrame().setVisible(true);
						frame.setVisible(false); 
					}else {
						JOptionPane.showMessageDialog(null, "用户名或密码，角色有误");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
	
	
	/**
	 * 重置事件处理
	 * @param e
	 */
	private void resetValueAction(ActionEvent e) {
		// TODO Auto-generated method stub
		this.usertext.setText("");
		this.pswtext.setText("");
	}
}
