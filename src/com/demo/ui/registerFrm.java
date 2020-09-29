package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.pojo.User;
import com.demo.utils.StringUtils;

import lombok.Getter;
import lombok.Setter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@Getter
@Setter
public class registerFrm{

	private JFrame frame;
	private JTextField uNameText;
	private JTextField passwordOneText;
	private JTextField passwordTwoText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrm window = new registerFrm();
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
	public registerFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("注册界面");
		frame.setBounds(100, 100, 653, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(174, 112, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		uNameText = new JTextField();
		uNameText.setBounds(238, 109, 190, 24);
		frame.getContentPane().add(uNameText);
		uNameText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setBounds(174, 176, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(160, 244, 85, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordOneText = new JTextField();
		passwordOneText.setColumns(10);
		passwordOneText.setBounds(238, 173, 190, 24);
		frame.getContentPane().add(passwordOneText);
		
		passwordTwoText = new JTextField();
		passwordTwoText.setColumns(10);
		passwordTwoText.setBounds(238, 241, 190, 24);
		frame.getContentPane().add(passwordTwoText);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//注册
				registerAction(e);
				
			}
		});
		btnNewButton.setBounds(107, 352, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(276, 352, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\u8FD4\u56DE\u767B\u5F55\u754C\u9762");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//窗体消失
				//返回登录界面
				//LogOnFrm login=new LogOnFrm();
				//login.getFrame().setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(441, 352, 131, 27);
		frame.getContentPane().add(btnNewButton_1_1);
		
		//设置窗体居中
		frame.setLocationRelativeTo(null);
	}
	/**
	 * 注册事件
	 * @param e
	 */
	protected void registerAction(ActionEvent e) {
		//获取文本框的内容
		String userName=uNameText.getText().trim();
		String passwordOne=passwordOneText.getText().trim();
		String passwordTwo=passwordTwoText.getText().trim();
		//System.out.println(userName+" "+passwordOne+" "+passwordTwo);
		//System.out.println(Integer.valueOf(passwordOne)==Integer.valueOf(passwordTwo));
		//判断是否为空
		if(StringUtils.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if(StringUtils.isEmpty(passwordOne)||StringUtils.isEmpty(passwordTwo)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		if(Integer.valueOf(passwordOne)!=Integer.valueOf(passwordTwo)) {
			JOptionPane.showMessageDialog(null, "两次输入密码不一致，请重新输入");
			resetValueActionPerformed(e);
			return;
		}
		UserDao userDao=new UserDaoImpl();
		userDao.register(new User(1,"oo","123","用户"));//TODO
		resetValueActionPerformed(e);
		JOptionPane.showMessageDialog(null, "注册成功，请登录");
	}

	/**
	 * 重置
	 * @param e
	 */
	protected void resetValueActionPerformed(ActionEvent e) {
		this.uNameText.setText("");
		this.passwordOneText.setText("");
		this.passwordTwoText.setText("");
	}
}
