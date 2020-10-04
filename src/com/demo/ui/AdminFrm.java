package com.demo.ui;

import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.demo.Index;
import com.demo.dao.QuestionDao;
import com.demo.dao.ScoreDao;
import com.demo.utils.ExcelExporter;
import com.demo.utils.JdbcUtil;
import lombok.Getter;
import lombok.Setter;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@Setter
@Getter
public class AdminFrm {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JComboBox comboBox;
	JdbcUtil dbUtil = new JdbcUtil();
	ScoreDao scoreDao = new ScoreDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrm window = new AdminFrm();
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
	public AdminFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("考试系统后台管理");
		frame.setBounds(100, 100, 1000, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 1114, 43);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("\u9898\u5E93\u7BA1\u7406");
		mnNewMenu.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/question.png")));
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		//添加
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6DFB\u52A0\u8BD5\u9898");
		mntmNewMenuItem.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/add.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到修改删除界面
				// 销毁该界面
				frame.dispose();
				new AddTitleFrm().getFrame().setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem);
		// 修改 删除
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u4FEE\u6539/\u5220\u9664\u8BD5\u9898");
		mntmNewMenuItem_1.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/dl.png")));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 跳转到修改删除界面
				// 销毁该界面
				frame.dispose();
				new testPaperFrm().getFrame().setVisible(true);

			}
		});
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("\u7528\u6237\u7BA1\u7406");
		mnNewMenu_1.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/user.png")));
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u6DFB\u52A0\u7528\u6237");
		//添加用户
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转到添加用户界面AddUser
				frame.setVisible(false);
				new AddUserFrm().getFrame().setVisible(true);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/add.png")));
		mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u5220\u9664\u7528\u6237");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转删除界面
				frame.setVisible(false);
				new DeleteUserFrm().getFrame().setVisible(true);
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/dl.png")));
		mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(207, 96, 90, 21);
		frame.getContentPane().add(lblNewLabel);

		// 下拉框
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 18));
		comboBox.addItem("--请选择--");
		comboBox.addItem("考生id");
		comboBox.addItem("考生姓名");

		comboBox.setBounds(315, 96, 129, 24);
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("\u67E5\u8BE2\u503C\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(474, 99, 90, 21);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(542, 96, 172, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/query.png")));
		// 查询时间
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryScoreAction();
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(728, 93, 113, 27);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 142, 672, 323);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("宋体", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "\u8003\u751Fid", "\u8003\u751F\u59D3\u540D", "\u5355\u9009\u9898\u5F97\u5206",
				"\u591A\u9009\u9898\u5F97\u5206", "\u603B\u5206", "\u8003\u8BD5\u65F6\u95F4" }) {
			Class[] columnTypes = new Class[] { Integer.class, Object.class, Object.class, Integer.class, Object.class,
					Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setPreferredWidth(92);
		table.getColumnModel().getColumn(4).setPreferredWidth(58);
		table.getColumnModel().getColumn(5).setPreferredWidth(253);
		scrollPane.setViewportView(table);

		JButton btnD = new JButton("\u5BFC\u51FA\u8868\u683C");
		btnD.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/exporter.png")));
		//导出表格
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//导出表格方法
				jButtonActionPerformed();
			}
		});
		btnD.setFont(new Font("宋体", Font.PLAIN, 18));
		btnD.setBounds(38, 272, 152, 27);
		frame.getContentPane().add(btnD);

		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		btnNewButton_2.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/out.png")));
		//返回主页
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//销毁本窗口
				frame.dispose();
				//显示主窗口
				new Index().getFrame().setVisible(true);				
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setBounds(38, 364, 152, 27);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("\u5237\u65B0");
		btnNewButton_1.setIcon(new ImageIcon(AdminFrm.class.getResource("/images/de.png")));
		//刷新
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillScore();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(855, 93, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		// 设置窗体居中
		frame.setLocationRelativeTo(null);
		fillScore();
	}

	private void jButtonActionPerformed() {
		//FileDialog 类显示一个对话框窗口，用户可以从中选择文件。
		//创建一个具有指定标题的文件对话框窗口，用于加载或保存文件。 
		/*
		 * 参数：
         parent - 对话框的所有者
         title - 对话框的标题
         mode - 对话框的模式，可以是 FileDialog.LOAD 或 FileDialog.SAVE 
         */
		 //显示窗体，可以选择导出位置
		 FileDialog fd = new FileDialog(frame, "保存成绩信息", FileDialog.SAVE);
		 //设置窗体大小
		 fd.setLocation(500, 350);
		 //显示
	     fd.setVisible(true);  
	     // getDirectory()-获取此文件对话框的目录。
	     // fd.getFile()-获取此文件对话框的选定文件。
	     String stringfile = fd.getDirectory()+fd.getFile()+".xls";  
         try {
        	 ExcelExporter export = new ExcelExporter();
        	 //传入表格和文件地址
        	 export.exportTable(table, new File(stringfile));
         } catch (IOException ex) {
             ex.printStackTrace();
         }
	}

	// 查询
	private void queryScoreAction() {
		Object object = comboBox.getSelectedItem();
		System.out.println(object);
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		
		// Object getSelectedItem()-返回当前所选项。
		if (object == "考生id") {
			
			String id = textField.getText();
			dtm.setRowCount(0);// 表格清空
			Connection conn = null;
			System.out.println(id);
			// JdbcUtils dbUtils=new JdbcUtils();
			try {
				conn = dbUtil.getConnection();
				ResultSet rs = scoreDao.query(conn, id);
				while(rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("scScore"));
				v.add(rs.getString("mcScore"));
				v.add(rs.getString("sumScore"));
				v.add(rs.getString("examdate"));
				// 一行一行的加
				dtm.addRow(v);
				//fillScore();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbUtil.close(conn);
			}
			//fillScore();
			fillScore1();

		} else if (object == "考生姓名") {
			String id = textField.getText();
			dtm.setRowCount(0);// 表格清空
			Connection conn = null;
			System.out.println(id);
			// JdbcUtils dbUtils=new JdbcUtils();
			try {
				conn = dbUtil.getConnection();
				ResultSet rs = scoreDao.query(conn, id);
				while(rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("scScore"));
				v.add(rs.getString("mcScore"));
				v.add(rs.getString("sumScore"));
				v.add(rs.getString("examdate"));
				// 一行一行的加
				dtm.addRow(v);
				//fillScore();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbUtil.close(conn);
			}
			//fillScore();
			fillScore2();
		}
		
	}

	// 填充表格
	private void fillScore() {
		// 表格模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 表格清空
		Connection conn = null;
		// JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = scoreDao.query(conn);
			while (rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("scScore"));
				v.add(rs.getString("mcScore"));
				v.add(rs.getString("sumScore"));
				v.add(rs.getString("examdate"));
				// 一行一行的加
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn);
		}
	}
	
	
	// 以查询到的id填充表格
	private void fillScore1() {
		// 表格模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 表格清空
		Connection conn = null;
		String id = textField.getText();
		int ids=Integer.valueOf(id);
		// JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = scoreDao.query(conn,ids);
			while (rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("scScore"));
				v.add(rs.getString("mcScore"));
				v.add(rs.getString("sumScore"));
				v.add(rs.getString("examdate"));
				// 一行一行的加
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn);
		}
	}
	//以查询到的姓名填充表格
	private void fillScore2() {
		// 表格模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 表格清空
		Connection conn = null;
		String id = textField.getText();
		// JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = scoreDao.query(conn,id);
			while (rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("scScore"));
				v.add(rs.getString("mcScore"));
				v.add(rs.getString("sumScore"));
				v.add(rs.getString("examdate"));
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
