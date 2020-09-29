package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.demo.utils.StringUtils;

import lombok.Getter;
import lombok.Setter;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@Setter
@Getter
public class LogOnFrm{

	private JFrame frame;
	private JTextField nameText;
	private JPasswordField passwordText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm window = new LogOnFrm();
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
	public LogOnFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("登录界面");
		frame.setResizable(false);
		frame.setBounds(100, 100, 570, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 40));
		lblNewLabel.setBounds(139, 44, 316, 53);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setBounds(154, 199, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1_1.setBounds(154, 246, 95, 34);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		nameText = new JTextField();
		nameText.setBounds(226, 196, 150, 24);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setBounds(84, 311, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(415, 311, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(226, 251, 150, 24);
		frame.getContentPane().add(passwordText);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("--请选择--");
		comboBox.addItem("老师");
		comboBox.addItem("学生");
		comboBox.setBounds(226, 142, 150, 24);
		
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("\u89D2  \u8272\uFF1A");
		lblNewLabel_2.setBounds(154, 145, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("\u6CE8\u518C");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				registerFrm rs=new registerFrm();
				rs.getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(248, 311, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
	}
	/**
	 * 登录验证
	 * @param e
	 */
	protected void loginActionPerformed(MouseEvent e) {
		String userName=nameText.getText().trim();
		char[] ps=passwordText.getPassword();
		String password=ps.toString();
		if(StringUtils.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
			return;
		}
		if(StringUtils.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
			return;
		}
		//销毁界面
		//System.exit(0);
		frame.dispose();
		//closeThis();
		MainFrm mf=new MainFrm();
		mf.getFrame().setVisible(true);
		//new MainFrm().firtPage();
		
	}

	/**
	 * 重置
	 * @param e
	 */
	protected void resetValueActionPerformed(MouseEvent e) {
		this.nameText.setText("");
		this.passwordText.setText("");
	}
}
