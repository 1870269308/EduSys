package com.demo.ui.examadmin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.demo.dao.PaperDao;
import com.demo.dao.QuestionDao1;
import com.demo.dao.UserManageDao;
import com.demo.dao.impl.PaperDaoImpl;
import com.demo.dao.impl.QuestionDao1Impl;
import com.demo.dao.impl.UserManageDaoImpl;
import com.demo.pojo.Paper;
import com.demo.utils.QueryRunner;



public class AddpaperFrm {

	private JFrame frame;
	private JTable table;
	private PaperDao pd=new PaperDaoImpl();
	private String selectId;
	private JTextField paperIdtext;
	private JTextField paperNametext;
	
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
					AddpaperFrm window = new AddpaperFrm();
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
	public AddpaperFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8BD5\u5377\u8BBE\u7F6E");
		frame.setBounds(100, 100, 829, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// ˢ�°�ť
		JButton btnNewButton = new JButton("\u5237\u65B0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(434, 46, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 118, 681, 285);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u8BD5\u5377\u79D1\u76EE", "\u8BD5\u5377\u540D\u79F0", "\u5F00\u59CB\u65F6\u95F4"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("\u6DFB\u52A0\u8BD5\u5377");
		lblNewLabel_2.setBounds(34, 433, 123, 32);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8BD5\u5377\u79D1\u76EE");
		lblNewLabel_3.setBounds(61, 493, 72, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		paperIdtext = new JTextField();
		paperIdtext.setBounds(171, 490, 86, 24);
		frame.getContentPane().add(paperIdtext);
		paperIdtext.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("\u8BD5\u5377\u540D\u79F0");
		lblNewLabel_3_1.setBounds(61, 541, 72, 18);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		paperNametext = new JTextField();
		paperNametext.setColumns(10);
		paperNametext.setBounds(171, 538, 376, 24);
		frame.getContentPane().add(paperNametext);
		
		JButton btnNewButton_2 = new JButton("\u6DFB\u52A0");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addpaperFormend();
				resetValueActionPerformed();
			}
		});
		btnNewButton_2.setBounds(44, 622, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		//����
		JButton btnNewButton_3 = new JButton("\u91CD\u7F6E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed();
			}
		});
		btnNewButton_3.setBounds(239, 622, 113, 27);
		frame.getContentPane().add(btnNewButton_3);
		//TODO
		JButton btnNewButton_4 = new JButton("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminFrm().getFrame().setVisible(true);
			}
		});
		btnNewButton_4.setBounds(434, 622, 113, 27);
		frame.getContentPane().add(btnNewButton_4);
		//TODO
		JButton btnNewButton_1 = new JButton("\u6DFB\u52A0\u9898\u76EE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�������
				frame.dispose();
				new AddTitleFrm().getFrame().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton_1.setBounds(607, 48, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		// ��ʼ��������
		fillTable();
	}
	
	/**
	 * �����¼�
	 */
	
	protected void resetValueActionPerformed() {
		// TODO Auto-generated method stub
		this.paperIdtext.setText("");
		this.paperNametext.setText("");
	}

	
/**
 * ����¼�
 */
	private void addpaperFormend() {
		
		String sql="insert into paper value(null,now(),?,?)";
		String subjectId=paperIdtext.getText();
		String paperName=paperNametext.getText();
		Object[] obj= {paperName,subjectId};
		System.out.println(sql);
		new QueryRunner().execute(sql, obj);
		JOptionPane.showMessageDialog(null, "����Ծ�ɹ���");
	}

	/**
	 * ������е�ID����ȡ����
	 */
	// ��ʼ�����
	private void fillTable() {
		//���ģ��
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// ������
		PaperDao paperDao=null;
		try {
			paperDao=new PaperDaoImpl();
			ResultSet rs = paperDao.query();
			//System.out.println(rs);
			//com.mysql.jdbc.JDBC42ResultSet@7c767388
			while(rs.next()) {
				Vector lineRow=new Vector();
				lineRow.add(rs.getString("subjectId"));
				lineRow.add(rs.getString("paperName"));
				lineRow.add(rs.getString("joinDate"));
				dtm.addRow(lineRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	//������¼�
//	private void mouseClicked() {
//		table.addMouseListener(new MouseListener() {
//			//��д������¼�
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				//��ȡ��Ԫ��
//				//����
//				int columnCount=table.getSelectedColumn();
//				//����
//				int rowCount=table.getSelectedRow();
//				//�õ���Ԫ������
//				Object value =table.getValueAt(rowCount, columnCount);
//				//��ȡ����Ϣ
//				DefaultTableModel model=(DefaultTableModel) table.getModel();
//				//��ȡ���������е�����
//				Vector v=model.getDataVector();
//				//������ת��Ϊ�ַ���
//				String str =v.get(rowCount).toString();
//				//�õ���һ�е��Ծ�����
//				String examNamestr=str.split(",")[0].substring(1);
//				//���õ����ֶδ����ı�����
//				textField.setText(examNamestr);
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//		});
//	}

	/**
	 * ��ѯ�¼�����>��ʾ��ѯ���
	 */
	private void selectPerformed() {
		// TableModel����ѯ�ʱ��ʽ����ģ�͵ķ�����
		TableModel tm = table.getModel();
		// DefaultTableModel���� TableModel ��һ��ʵ�֣���ʹ��һ�� Vector ���洢��Ԫ���ֵ����
		DefaultTableModel model = (DefaultTableModel) tm;
		// ��ձ��е�����
		model.setRowCount(0);
		// �õ����ڽ�����е�����
		// ����һ����ѯ��ֵ��ȥ
		// ����һ��������ȡ�����ı�������
//		selectId = textField.getText();
		// �ж��Ƿ�Ϊ��
		if (selectId.equals("")) {
			fillTable();
			System.out.println(selectId);
			return;
		}
		int selectNum=Integer.parseInt(selectId);
		List<Paper> datas=pd.getSelectDatas(selectNum);
		for(Paper p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getPaperName());
			lineData.add(p.getJionDate());
			model.addRow(lineData);
		}
		System.out.println("ˢ�³ɹ�");
		//����ڵ�������¼����
//		mouseClicked();

	}
}
