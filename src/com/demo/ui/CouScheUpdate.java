package com.demo.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.demo.dao.CourScheDao;
import com.demo.dao.CourseDao;
import com.demo.dao.impl.CourScheDaoImpl;
import com.demo.dao.impl.CourseDaoImpl;
import com.demo.pojo.CouSche;
import com.demo.pojo.Course;
import com.demo.pojo.User;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@Getter
public class CouScheUpdate {

	private JFrame frame;
	private User UserMessage = new User();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CouScheUpdate window = new CouScheUpdate();
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
	public CouScheUpdate() {
		initialize();
	}

	public CouScheUpdate(User UserMessage) {
		this.UserMessage = UserMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 43, 316, 165);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(null, new String[] { "ID", "教学内容", "教学进度" }) {
			// 设置第一,二列单元格不能修改
			public boolean isCellEditable(int row, int column) {
				if (column < 2) {
					return false;
				} else {
					return true;
				}
			}
		};
		table.setModel(model);
		scrollPane.setViewportView(table);
		// 填充表格
		fillTable();

		// 保存按钮
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		updateCourseSche ucs = new updateCourseSche(model, UserMessage);
		btnNewButton.addActionListener(ucs);
		btnNewButton.setBounds(49, 253, 113, 27);
		frame.getContentPane().add(btnNewButton);

		// 退出按钮
		JButton btnNewButton_1 = new JButton("返回");
		returnCourse rc = new returnCourse(frame,UserMessage);
		btnNewButton_1.addActionListener(rc);
		btnNewButton_1.setBounds(344, 253, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
	}

	private void fillTable() {
		TableModel jmodel = table.getModel();
		DefaultTableModel model = (DefaultTableModel) jmodel;
		String foreign_id = UserMessage.getId().toString();
		Connection conn=null;
		// 清空一下数据
		model.setRowCount(0);
		JdbcUtil dbUtil = new JdbcUtil();
		CourScheDao csd = new CourScheDaoImpl();
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = csd.getDatas(conn, foreign_id);
			while (rs.next()) {
				Vector lineData = new Vector();
				lineData.add(rs.getString("id"));
				lineData.add(rs.getString("content"));
				lineData.add(rs.getString("rate"));
				model.addRow(lineData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.close(conn);
		}
	}
}

//保存事件监听
class updateCourseSche implements ActionListener {
	private DefaultTableModel model;
	private User UserMessage;

	public updateCourseSche(DefaultTableModel model, User UserMessage) {
		this.model = model;
		this.UserMessage = UserMessage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JdbcUtil dbUtil = new JdbcUtil();
		CourScheDao csd = new CourScheDaoImpl();
		ArrayList<Object> x = new ArrayList<Object>();
		String foreign_id = UserMessage.getId().toString();
		int count = 0;// 验证保存是否成功
		Connection conn = null;
		for (int i = 0; i < 5; i++) {
			x.add(model.getValueAt(i, 0));
			x.add(model.getValueAt(i, 2));
			Object[] params = x.toArray();
			try {
				conn = dbUtil.getConnection();
				count += csd.updCourse(conn, foreign_id, params);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				dbUtil.close(conn);
			}
			x.clear();
		}
		if (count == 5) {
			JOptionPane.showMessageDialog(null, "保存成功");
		} else {
			JOptionPane.showMessageDialog(null, "保存失败", "错误提示", JOptionPane.WARNING_MESSAGE);
		}
	}
}

//退出按钮事件监听:返回课程进度图界面
class returnCourse implements ActionListener {
	private JFrame frame;
	private User UserMessage;

	public returnCourse(JFrame frame,User UserMessage) {
		this.frame = frame;
		this.UserMessage=UserMessage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BarChartFrm bct = new BarChartFrm(UserMessage);
		frame.setVisible(false);
		bct.getFrame().setVisible(true);
	}

}