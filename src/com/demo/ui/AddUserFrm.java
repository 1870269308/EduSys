package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.demo.dao.UserManageDao;
import com.demo.dao.impl.UserManageDaoImpl;
import com.demo.pojo.UserManage;
import com.demo.utils.StringUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUserFrm {

	private JFrame frame;
	private JTextField idText;
	private JTextField nameText;
	private JTextField pwText;
	private JTextField isText;
	
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
					AddUserFrm window = new AddUserFrm();
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
	public AddUserFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("添加教师账号");
		frame.setBounds(100, 100, 724, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u6DFB\u52A0\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 13, 678, 407);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7F16  \u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(126, 57, 87, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(126, 135, 87, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(126, 216, 87, 32);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u79D1  \u76EE\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(126, 297, 87, 32);
		panel.add(lblNewLabel_3);
		
		idText = new JTextField();
		idText.setBounds(227, 54, 261, 42);
		panel.add(idText);
		idText.setColumns(10);
		
		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(227, 132, 261, 42);
		panel.add(nameText);
		
		pwText = new JTextField();
		pwText.setColumns(10);
		pwText.setBounds(227, 213, 261, 42);
		panel.add(pwText);
		
		isText = new JTextField();
		isText.setColumns(10);
		isText.setBounds(227, 294, 261, 42);
		panel.add(isText);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加用户
				addUser();
			}
		});
		btnNewButton.setIcon(new ImageIcon(AddUserFrm.class.getResource("/images/add.png")));
		btnNewButton.setBounds(138, 464, 113, 45);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		//返回
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Index().getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AddUserFrm.class.getResource("/images/out.png")));
		btnNewButton_1.setBounds(447, 464, 113, 45);
		frame.getContentPane().add(btnNewButton_1);
		// 设置窗体居中
		frame.setLocationRelativeTo(null);
	}

	/**
	 * 添加事件处理
	 */
	private void addUser() {
		//获取文本域的内容
		String id=idText.getText().trim();
		String name=nameText.getText().trim();
		String password=pwText.getText().trim();
		String subject=isText.getText().trim();
		//判断是否为空
		if(StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请输入完整信息！");
			return;
		}else {//如果不是空
			UserManageDao userManageDao=new UserManageDaoImpl();
			userManageDao.add(new UserManage(Integer.parseInt(id),name,password,subject));
			JOptionPane.showMessageDialog(null, "添加成功！");
		}
	}
}
