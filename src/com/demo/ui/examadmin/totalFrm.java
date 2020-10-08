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
		frame = new JFrame("����ͳ��");
		frame.setBounds(100, 100, 782, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * ������һ����ť
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
		
		//�鿴ͼ��ť
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
		 * ���������亯��
		 */
		filltable(); 
		
		//�������
		frame.setLocationRelativeTo(null);
	}

	
	/**
	 * ͼ����
	 * @param e
	 */
	private void exportTableAction(ActionEvent e) {
		PieChartFrm csu = new PieChartFrm();
		csu.getFrame().setVisible(true);
	}
	
	/*
	 * ���������
	 */
	private void filltable() {
		TableModel jmodel = table.getModel(); 
		DefaultTableModel model = (DefaultTableModel)jmodel; 
		//���һ������
		model.setRowCount(0);
		
		Connection conn = null; 
		try {
			conn = JdbcUtils.getConnection(); 
			//���ﷵ�ص��Ƕ�������
			ResultSet rs = tatalDao.query(conn);
			while(rs.next()) {
				//����һ������  ������һ������
				Vector v = new Vector(); 
				v.add(rs.getString("id")); 
				v.add(rs.getString("name")); 
				v.add(rs.getString("sumScore")); 
				//һ��һ�е����ļ�
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
