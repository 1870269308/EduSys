package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.demo.dao.QuestionDao;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
import lombok.Setter;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Setter
@Getter
public class testPaperFrm {

	private JFrame frame;
	private JTable questionTable;
	JdbcUtil dbUtil=new JdbcUtil();
	QuestionDao questionDao=new QuestionDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testPaperFrm window = new testPaperFrm();
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
	public testPaperFrm() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("�޸�/ɾ������");
		frame.setBounds(100, 100, 1002, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 0, 956, 393);
		frame.getContentPane().add(scrollPane);
		
		questionTable = new JTable();
		questionTable.setFont(new Font("����", Font.BOLD, 18));
		questionTable.setRowHeight(18);
		questionTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BD5\u9898\u7F16\u53F7", "\u8BD5\u9898\u9898\u76EE", "\u8BD5\u9898\u6B63\u786E\u7B54\u6848", "\u7B54\u6848A", "\u7B54\u6848B", "\u7B54\u6848C", "\u7B54\u6848D", "\u5907\u6CE8"
			}
		));
		questionTable.getColumnModel().getColumn(2).setPreferredWidth(113);
		scrollPane.setViewportView(questionTable);
		fillTable();
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		//�޸İ�ť��ʱ��
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ת���޸Ľ���
				//frame.dispose();//���ٸý���
				int id=getUpdateQuestionId();
				System.out.println(id);
				updateQuestionFrm uq=new updateQuestionFrm(id);
				//frame.setVisible(false);
				JFrame m=uq.getFrame();//��ʾ�޸Ľ���
				System.out.println(m);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(194, 436, 113, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		//ɾ����ť�¼�
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ɾ��
				deleteAction();
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_1.setBounds(422, 436, 113, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u9000\u51FA");
		//�˳���ť�¼�
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//���ٴ���
				//��ʾ����ϵͳ��ҳ
				new AdminFrm().getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_2.setBounds(648, 436, 113, 35);
		frame.getContentPane().add(btnNewButton_2);
		//���ô������
		frame.setLocationRelativeTo(null);
		
		
	}
	//��ȡ��Ҫ�޸������������
	public int getUpdateQuestionId() {
		//��ȡ���ѡ�������
		int column=questionTable.getSelectedColumn();
		//��ȡ���ѡ�������
		int row = questionTable.getSelectedRow();
		//��ȡ���ѡ����к͸��ж�Ӧ��ֵ
		Object value=(Object)questionTable.getValueAt(row, column);
		//��objectתΪint����
		int id= Integer.parseInt(value.toString());
		return id;
		
	}

	//��ʼ�����
	private void fillTable() {
		//���ģ��
		DefaultTableModel dtm=(DefaultTableModel) questionTable.getModel();
		dtm.setRowCount(0);//������
		Connection conn=null;
		//JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn=dbUtil.getConnection();
			ResultSet rs=questionDao.query(conn);
			while(rs.next()) {
				//����һ������
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("title"));
				v.add(rs.getString("keyvalue"));
				v.add(rs.getString("keyA"));
				v.add(rs.getString("keyB"));
				v.add(rs.getString("keyC"));
				v.add(rs.getString("keyD"));
				v.add(rs.getString("remarks"));
				//һ��һ�еļ�
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.close(conn);
		}
		
	}
	//ɾ������
	private void deleteAction() {
		//��ȡ���ѡ�������
		int column=questionTable.getSelectedColumn();
		//��ȡ���ѡ�������
		int row = questionTable.getSelectedRow();
		//��ȡ���ѡ����к͸��ж�Ӧ��ֵ
		Object value=(Object)questionTable.getValueAt(row, column);
		//��objectתΪint����
		int id= Integer.parseInt(value.toString());
		//System.out.println(id);
		//��ȡ���ѡ�б�������
		int ids=questionTable.getSelectedRow();
		//���ģ��
		DefaultTableModel dtm=(DefaultTableModel) questionTable.getModel();
		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			//ɾ�����ݿ������
			//ѡ�б��idɾ��
			questionDao.delete(conn,id);
			//ɾ�����һ������
			dtm.removeRow(ids);
		}catch(Exception e2) {
			e2.printStackTrace();
			
		}finally {
			dbUtil.close(conn);
		}
	}


}
