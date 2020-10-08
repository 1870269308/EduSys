package com.demo.ui.examadmin;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.demo.dao.TotalDao;
import com.demo.dao.impl.TotalDaoImpl;
import com.demo.ui.chart.PieChartFrm;
import com.demo.utils.ExcelExporter;
import com.demo.utils.JdbcUtils;

public class totalFrm {

	private JFrame frame;
	private JTable table;
	TotalDao tatalDao =new TotalDaoImpl();  
	

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
					totalFrm window = new totalFrm();
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
	public totalFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("分数统计");
		frame.setBounds(100, 100, 782, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * 返回上一级按钮
		 */
		JButton btnNewButton = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DOTO 
				AdminFrm admin = new AdminFrm(); 
				admin.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(393, 331, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		//查看图表按钮
		JButton btnNewButton_1 = new JButton("\u56FE\u8868\u663E\u793A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportTableAction(e); 
			}
		});
		btnNewButton_1.setBounds(194, 331, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 48, 464, 237);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			null,
			new String[] {
				"\u8003\u751Fid", "\u8003\u751F\u59D3\u540D", "\u603B\u5206"
			}
		));
		scrollPane.setViewportView(table);
		/**
		 * 表格内容填充函数
		 */
		filltable(); 
		
		//窗体居中
		frame.setLocationRelativeTo(null);
	}

	
	/**
	 * 图表导出
	 * @param e
	 */
	private void exportTableAction(ActionEvent e) {
		PieChartFrm csu = new PieChartFrm();
		csu.getFrame().setVisible(true);
	}
	
	/*
	 * 填充表格数据
	 */
	private void filltable() {
		TableModel jmodel = table.getModel(); 
		DefaultTableModel model = (DefaultTableModel)jmodel; 
		//清空一下数据
		model.setRowCount(0);
		
		Connection conn = null; 
		try {
			conn = JdbcUtils.getConnection(); 
			//这里返回的是多行数据
			ResultSet rs = tatalDao.query(conn);
			while(rs.next()) {
				//设置一个集合  这里存放一行数据
				Vector v = new Vector(); 
				v.add(rs.getString("id")); 
				v.add(rs.getString("name")); 
				v.add(rs.getString("sumScore")); 
				//一行一行单独的加
				model.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
