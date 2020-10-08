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
import com.demo.pojo.UserManage;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ImageIcon;

@Getter
public class CouScheUpdate {

	private JFrame frame;
	private UserManage UserMessage = new UserManage();
	private JTable table;
	
	

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

	public CouScheUpdate(UserManage UserMessage) {
		this.UserMessage = UserMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("�޸Ŀγ̽���");
		frame.setBounds(100, 100, 547, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 43, 316, 165);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(null, new String[] { "ID", "��ѧ����", "��ѧ����" }) {
			// ���õ�һ,���е�Ԫ�����޸�
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
		// �����
		fillTable();

		// ���水ť
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setIcon(new ImageIcon(CouScheUpdate.class.getResource("/images/de.png")));
		updateCourseSche ucs = new updateCourseSche(model, UserMessage);
		btnNewButton.addActionListener(ucs);
		btnNewButton.setBounds(49, 253, 113, 27);
		frame.getContentPane().add(btnNewButton);

		// �˳���ť
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.setIcon(new ImageIcon(CouScheUpdate.class.getResource("/images/out.png")));
		returnCourse rc = new returnCourse(frame,UserMessage);
		btnNewButton_1.addActionListener(rc);
		btnNewButton_1.setBounds(344, 253, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		//���ô������
		frame.setLocationRelativeTo(null);
	}

	private void fillTable() {
		TableModel jmodel = table.getModel();
		DefaultTableModel model = (DefaultTableModel) jmodel;
		String foreign_id=String.valueOf(UserMessage.getId());
		Connection conn=null;
		// ���һ������
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

//�����¼�����
class updateCourseSche implements ActionListener {
	private DefaultTableModel model;
	private UserManage UserMessage;

	public updateCourseSche(DefaultTableModel model, UserManage UserMessage) {
		this.model = model;
		this.UserMessage = UserMessage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JdbcUtil dbUtil = new JdbcUtil();
		CourScheDao csd = new CourScheDaoImpl();
		ArrayList<Object> x = new ArrayList<Object>();
		String foreign_id=String.valueOf(UserMessage.getId());
		int count = 0;// ��֤�����Ƿ�ɹ�
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
			JOptionPane.showMessageDialog(null, "����ɹ�");
		} else {
			JOptionPane.showMessageDialog(null, "����ʧ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
		}
	}
}

//�˳���ť�¼�����:���ؿγ̽���ͼ����
class returnCourse implements ActionListener {
	private JFrame frame;
	private UserManage UserMessage;

	public returnCourse(JFrame frame,UserManage UserMessage) {
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