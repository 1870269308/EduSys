package com.demo.ui;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;

import lombok.Getter;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.demo.dao.FeedbackDao;
import com.demo.pojo.User;
import com.demo.utils.JdbcUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;

@Getter
public class feedback {

	private JFrame frame;
	private DefaultTableModel dtm;
	private JTable table;
	private static User userMessage = new User();
	JdbcUtil dbUtil = new JdbcUtil();
	FeedbackDao FeedbackDao = new FeedbackDao();
	

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
					feedback window = new feedback();
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
	public feedback() {
		initialize();
	}
	public feedback(User userMessage) {

		this.userMessage = userMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		dtm =new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"\u7528\u6237\u540D", "\u53CD\u9988\u4FE1\u606F"
			}
		);
		
		JButton button = new JButton("\u67E5\u770B");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillScore();
			}
		});
		button.setBounds(3, 20, 93, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.setBounds(3, 192, 93, 23);
		frame.getContentPane().add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 40, 283, 188);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u53CD\u9988\u4FE1\u606F"
			}
		));
		scrollPane.setViewportView(table);
	}
	private void fillScore() {
		// 表格模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 表格清空
		Connection conn = null;
		// JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = FeedbackDao.query(conn);
			while (rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
//				v.add(rs.getString("id"));
				v.add(rs.getString("userName"));
				v.add(rs.getString("feedback"));
				// 一行一行的加
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn);
		}
	}
}
