package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.demo.dao.UserManageDao;
import com.demo.dao.impl.UserManageDaoImpl;
import com.demo.pojo.UserManage;
import com.demo.utils.JdbcUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class DeleteUserFrm {

	private JFrame frame;
	private JTable userTable;
	
	

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public DeleteUserFrm(JFrame frame) {
		super();
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUserFrm window = new DeleteUserFrm();
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
	public DeleteUserFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 724, 324);
		frame.getContentPane().add(scrollPane);

		userTable = new JTable();
		userTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u59D3\u540D", "\u5BC6\u7801", "\u662F\u5426\u53C2\u52A0\u8003\u8BD5"
			}
		));
		userTable.getColumnModel().getColumn(3).setPreferredWidth(126);
		scrollPane.setViewportView(userTable);
		fillTable();
		JButton btnNewButton = new JButton("\u5220\u9664");
		// 删除事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserAction();//删除
			}
		});
		btnNewButton.setIcon(new ImageIcon(DeleteUserFrm.class.getResource("/images/dl.png")));
		btnNewButton.setBounds(185, 362, 113, 27);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u8FD4\u56DE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回
				frame.setVisible(false);
				new AdminFrm().getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(DeleteUserFrm.class.getResource("/images/out.png")));
		btnNewButton_1.setBounds(450, 362, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		// 设置窗体居中
		frame.setLocationRelativeTo(null);

	}

	private void deleteUserAction() {
		//表格模型
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		//获取鼠标选择的列数
		int column=userTable.getSelectedColumn();
		//获取鼠标选择的行数
		int row = userTable.getSelectedRow();
		//获取鼠标选择改行和该列对应的值
		Object value=(Object)userTable.getValueAt(row, column);
		//将object转为int类型
		int id= Integer.parseInt(value.toString());
		System.out.println(id);
		//获取鼠标选中表格的行数
		int ids=userTable.getSelectedRow();
		UserManageDao userManageDao=null;
		try {
			userManageDao=new UserManageDaoImpl();
			userManageDao.delete(id);
			JOptionPane.showMessageDialog(null, "删除成功！");
			//删除表格一行数据
			dtm.removeRow(ids);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 初始化表格
	private void fillTable() {
		//表格模型
		DefaultTableModel dtm = (DefaultTableModel) userTable.getModel();
		dtm.setRowCount(0);// 表格清空
		UserManageDao userManageDao=null;
		try {
			userManageDao=new UserManageDaoImpl();
			ResultSet rs = userManageDao.query();
			//System.out.println(rs);
			//com.mysql.jdbc.JDBC42ResultSet@7c767388
			while(rs.next()) {
				Vector lineRow=new Vector();
				lineRow.add(rs.getInt("id"));
				lineRow.add(rs.getString("name"));
				lineRow.add(rs.getString("password"));
				lineRow.add(rs.getInt("isExam"));
				dtm.addRow(lineRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
