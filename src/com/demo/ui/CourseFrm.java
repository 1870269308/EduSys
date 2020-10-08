package com.demo.ui;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.demo.pojo.Course;
import com.demo.pojo.User;
import com.demo.pojo.UserManage;

import lombok.Getter;

import com.demo.ui.Index;
import com.demo.utils.JdbcUtil;

import com.demo.base.BaseDao;
import com.demo.base.BaseDaoImpl;
import com.demo.dao.CourseDao;
import com.demo.dao.impl.CourseDaoImpl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;

@Getter
public class CourseFrm {

	private JFrame frame;
	private JTable table;
<<<<<<< HEAD
	private static User UserMessage = new User();
=======
	private UserManage UserMessage=new UserManage();
>>>>>>> 180047d14f9805113830dcd5732b9b5fb4879e09
	private DefaultTableModel model;
	private JdbcUtil dbUtil = new JdbcUtil();
	private CourseDao cs= new CourseDaoImpl();
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
					CourseFrm window = new CourseFrm();
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
	public CourseFrm() {
		initialize();
	}
<<<<<<< HEAD

	public CourseFrm(User UserMessage) {
		this.UserMessage = UserMessage;
=======
	public CourseFrm(UserManage UserMessage) {
		this.UserMessage=UserMessage;
>>>>>>> 180047d14f9805113830dcd5732b9b5fb4879e09
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 836, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 31, 729, 237);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowHeight(31);
		model = new DefaultTableModel(null, new String[] { "ID", "第几节","具体时间","星期一","星期二","星期三","星期四","星期五"}) {
			// 设置前三列单元格不能修改
			public boolean isCellEditable(int row, int column) {
				if (column > 2) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		scrollPane.setViewportView(table);
		// 填充表格
		fillTable();

		// 保存按钮
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		updateCourse uc = new updateCourse(model,UserMessage);
		btnNewButton.addActionListener(uc);
		btnNewButton.setBounds(49, 316, 113, 27);
		frame.getContentPane().add(btnNewButton);

		// 导出按钮
		JButton btnNewButton_1 = new JButton("\u5BFC\u51FA");
		exportCourse ec = new exportCourse(frame, table);
		btnNewButton_1.addActionListener(ec);
		btnNewButton_1.setBounds(235, 316, 113, 27);
		frame.getContentPane().add(btnNewButton_1);

		// 课程进度按钮
		JButton btnNewButton_4 = new JButton("\u8BFE\u7A0B\u8FDB\u5EA6\u56FE");
		couSche cs = new couSche(frame,UserMessage);
		btnNewButton_4.addActionListener(cs);
		btnNewButton_4.setBounds(420, 316, 113, 27);
		frame.getContentPane().add(btnNewButton_4);

		// 退出按钮
		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE");
		returnIndex ri = new returnIndex(frame);
		btnNewButton_2.addActionListener(ri);
		btnNewButton_2.setBounds(629, 316, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
	}

	// 以查询到的姓名填充表格
	private void fillTable() {
		// 表格模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 表格清空
		Connection conn = null;
		String foreign_id=UserMessage.getId().toString();

		try {
			conn = dbUtil.getConnection();
			ResultSet rs = cs.getDatas(conn, foreign_id);
			while (rs.next()) {
				// 设置一个集合
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("section"));
				v.add(rs.getString("time"));
				v.add(rs.getString("Mon"));
				v.add(rs.getString("Tues"));
				v.add(rs.getString("Wednes"));
				v.add(rs.getString("Thurs"));
				v.add(rs.getString("Fri"));
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

//退出事件监听
class returnIndex implements ActionListener {
	private JFrame frame;

	public returnIndex(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Index window1 = new Index();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);

	}
}

//保存事件监听
class updateCourse implements ActionListener {
	private DefaultTableModel model;
	private User UserMessage;
	private JdbcUtil dbUtil=new JdbcUtil();
	public updateCourse(DefaultTableModel model,User UserMessage) {
		this.model = model;
		this.UserMessage=UserMessage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int count=0;
		Connection conn = null;
		String foreign_id=UserMessage.getId().toString();
		ArrayList<Object> x = new ArrayList<Object>();
		for (int i = 0; i < 7; i++) {
			x.add(model.getValueAt(i, 0));
			for (int j = 3; j < 8; j++) {
				Object value = model.getValueAt(i, j);
				x.add(value);
			}
			Object[] params=x.toArray();			
			CourseDao cd = new CourseDaoImpl();			
			try {
				conn = dbUtil.getConnection();
				count+= cd.updCourse(conn,foreign_id,params);
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally {
				dbUtil.close(conn);
			}			
			x.clear();
		}
		if(count==7) {
			JOptionPane.showMessageDialog(null, "保存成功");
		}else {
			JOptionPane.showMessageDialog(null, "保存失败","错误提示",JOptionPane.WARNING_MESSAGE);
		}
		
	}
}

//导出事件监听
class exportCourse implements ActionListener {
	private JFrame frame;
	private JTable table;

	public exportCourse(JFrame frame, JTable table) {
		this.frame = frame;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// FileDialog:指定标题的文件对话框窗口，用于加载或保存文件
		// FileDialog(Frame parent, String title, int mode)
		// mode:save或load
		// 文件路径：getDirectory()+getFile()+"后缀名"
		FileDialog fd = new FileDialog(frame, "存储到", FileDialog.SAVE);
		fd.setVisible(true);

		String goalpath = fd.getDirectory() + fd.getFile() + ".xls";
		saveSourse(table, goalpath);
		JOptionPane.showMessageDialog(null, "导出成功");
	}

	private static void saveSourse(JTable table, String goalpath) {
		try {
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(goalpath)));
			// 写入列名
			for (int i = 0; i < table.getColumnCount(); i++) {
				String columnName = table.getColumnName(i);
				os.write(columnName);
				os.write("\t");// 制表，输出到下一个单元格

			}
			os.newLine();// 输出换行
			// 写入表内容
			for (int row = 0; row < table.getRowCount(); row++) {
				for (int col = 0; col < table.getColumnCount(); col++) {
					// 非空判断
					if (table.getValueAt(row, col) == null) {
						os.write("\t");
					} else {
						String value = table.getValueAt(row, col).toString();
						os.write(value);
						os.write("\t");
					}

				}
				os.newLine();
			}
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//课程进度事件监听
class couSche implements ActionListener {
	private JFrame frame;
	private User Usermessage;
	public couSche(JFrame frame,User Usermessage) {
		this.frame = frame;
		this.Usermessage=Usermessage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BarChartFrm csu = new BarChartFrm(Usermessage);
		frame.setVisible(false);
		csu.getFrame().setVisible(true);		
	}

}