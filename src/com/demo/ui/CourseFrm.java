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
import com.demo.base.BaseDao;
import com.demo.base.BaseDaoImpl;
import com.demo.dao.CourseDao;
import com.demo.dao.CourseTitleDao;
import com.demo.dao.impl.CourseDaoImpl;
import com.demo.dao.impl.CourseTitleDaoImpl;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

@Getter
public class CourseFrm {

	private JFrame frame;
	private JTable table;
	private UserManage UserMessage=new UserManage();
	private DefaultTableModel model;

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
	public CourseFrm(UserManage UserMessage) {
		this.UserMessage=UserMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 837, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 31, 676, 245);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		// ��ȡ����
		CourseTitleDao ct=new CourseTitleDaoImpl();
		String[] titles = ct.getTitles();
		table.setRowHeight(31);
		model = new DefaultTableModel(null, titles) {
			// ����ǰ���е�Ԫ�����޸�
			public boolean isCellEditable(int row, int column) {
				if (column > 1) {
					return true;
				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(151);
		scrollPane.setViewportView(table);
		// �����
		fillTable();

		// ���水ť
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		updateCourse uc = new updateCourse(model);
		btnNewButton.addActionListener(uc);
		btnNewButton.setBounds(78, 316, 113, 27);
		frame.getContentPane().add(btnNewButton);

		// ������ť
		JButton btnNewButton_1 = new JButton("\u5BFC\u51FA");
		exportCourse ec = new exportCourse(frame,table);
		btnNewButton_1.addActionListener(ec);
		btnNewButton_1.setBounds(279, 316, 113, 27);
		frame.getContentPane().add(btnNewButton_1);

		// �˳���ť
		JButton btnNewButton_2 = new JButton("\u8FD4\u56DE");
		returnIndex ri = new returnIndex(frame);
		btnNewButton_2.addActionListener(ri);
		btnNewButton_2.setBounds(615, 316, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
	}

	private void fillTable() {
		TableModel jmodel = table.getModel();
		DefaultTableModel model = (DefaultTableModel) jmodel;
		// ���һ������
		model.setRowCount(0);
		CourseDao cd=new CourseDaoImpl(UserMessage);
		List<Course> datas = cd.getDatas();
		for (Course p : datas) {
			Vector lineData = new Vector();
			lineData.add(p.getSection());
			lineData.add(p.getTime());
			lineData.add(p.getMonday());
			lineData.add(p.getTuesday());
			lineData.add(p.getWednesday());
			lineData.add(p.getThursday());
			lineData.add(p.getFriday());
			model.addRow(lineData);
		}
	}
}

//�˳��¼�����
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

//�����¼�����
class updateCourse implements ActionListener {
	private DefaultTableModel model;
	private CourseDao coudao;
	CourseDaoImpl coudao2 = (CourseDaoImpl) coudao;

	public updateCourse(DefaultTableModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Object> x = new ArrayList<Object>();		
		for (int i = 0; i < 7; i++) {
			x.add(i+1);
			for (int j = 0; j < 7; j++) {
				Object value = model.getValueAt(i, j);
				x.add(value);
			}
			Object[] params=x.toArray();
			Course cou=new Course((int)params[0],(String)params[1],(String)params[2],(String)params[3],(String)params[4],(String)params[5],(String)params[6],(String)params[7]);
			CourseDao cd=new CourseDaoImpl();
			cd.updCourse(cou);
			x.clear();
		}
		JOptionPane.showMessageDialog(null, "����ɹ�"); 
	}
}

//�����¼�����
class exportCourse implements ActionListener{
	private JFrame frame;
	private JTable table;
	
	public exportCourse(JFrame frame,JTable table) {
		this.frame = frame;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// FileDialog:ָ��������ļ��Ի��򴰿ڣ����ڼ��ػ򱣴��ļ�
		// FileDialog(Frame parent, String title, int mode)
		// mode:save��load
		// �ļ�·����getDirectory()+getFile()+"��׺��"
		FileDialog fd = new FileDialog(frame, "�洢��", FileDialog.SAVE);
		fd.setVisible(true);

		String goalpath = fd.getDirectory() + fd.getFile() + ".xls";
		saveSourse(table, goalpath);
		JOptionPane.showMessageDialog(null, "�����ɹ�");
	}
	
	private static void saveSourse(JTable table, String goalpath) {
		try {
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(goalpath)));
			// д������
			for (int i = 0; i < table.getColumnCount(); i++) {
				String columnName = table.getColumnName(i);
				os.write(columnName);
				os.write("\t");//�Ʊ��������һ����Ԫ��

			}
			os.newLine();//�������
			//д�������
			for(int row=0;row<table.getRowCount();row++) {
				for(int col=0;col<table.getColumnCount();col++) {
					//�ǿ��ж�
					if(table.getValueAt(row, col)==null) {
						os.write("\t");
					}else {
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