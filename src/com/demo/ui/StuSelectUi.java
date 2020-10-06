package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.demo.dao.PaperDao;
import com.demo.dao.ScoreDao;
import com.demo.dao.impl.PaperDaoImpl;
import com.demo.pojo.Paper;
import com.demo.pojo.User;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class StuSelectUi {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private PaperDao pd=new PaperDaoImpl();
	private String selectId;
	private static User userMessage = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuSelectUi window = new StuSelectUi();
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
	public StuSelectUi() {
		initialize();
	}
	public StuSelectUi(User userMessage) {

		this.userMessage = userMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 777, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"\u8BD5\u5377\u8BF4\u660E\uFF1A1\u662F\u6570\u5B66\u30012\u662F\u8BED\u6587\u30013\u662F\u82F1\u8BED");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setBounds(34, 13, 362, 18);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u8BD5\u5377\u7F16\u53F7");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(105, 44, 107, 32);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(187, 49, 98, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		// ��ѯ��ť
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectPerformed();
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton.setBounds(357, 48, 113, 27);
		frame.getContentPane().add(btnNewButton);
		// ��ʼ����
		JButton btnNewButton_1 = new JButton("\u5F00\u59CB\u8003\u8BD5");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPerformed();
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 18));
		btnNewButton_1.setBounds(502, 48, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 118, 681, 285);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "\u8BD5\u5377Id", "\u8BD5\u5377\u540D\u79F0", "\u5F00\u59CB\u65F6\u95F4" }));
		scrollPane.setViewportView(table);
		// ��ʼ��������
		fillTable();
	}
	//��ʼ����
	private void startPerformed() {
		//�ж��Ƿ�ѡ������
		if(textField.getText().equals("")) {
			JOptionPane.showMessageDialog(frame, "��ѡ��Ҫ���Ե��Ծ�");
			return;
		}
		//�رյ�ǰ����
		frame.dispose();
		ExaminationUi.examId=Integer.parseInt(textField.getText());
		ExaminationUi window=new ExaminationUi(userMessage);
		window.frame.setVisible(true);
	}

	/**
	 * ������е�ID����ȡ����
	 */
	private void fillTable() {
		// TableModel����ѯ�ʱ��ʽ����ģ�͵ķ�����
		TableModel tm = table.getModel();
		// DefaultTableModel���� TableModel ��һ��ʵ�֣���ʹ��һ�� Vector ���洢��Ԫ���ֵ����
		DefaultTableModel model = (DefaultTableModel) tm;
		// ��ձ��е�����
		model.setRowCount(0);
		//ȡ��������е�����
		//ȡ�����е�id����
		List<Paper> datas=pd.gettableDatas();
		for(Paper p:datas) {
			Vector lineData=new Vector();
			lineData.add(p.getId());
			lineData.add(p.getPaperName());
			lineData.add(p.getJionDate());
			model.addRow(lineData);
		}
		System.out.println("ˢ�³ɹ�");
		//����ϵĵ���¼�
		mouseClicked();
	}
	//������¼�
	private void mouseClicked() {
		table.addMouseListener(new MouseListener() {
			//��д������¼�
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ȡ��Ԫ��
				//����
				int columnCount=table.getSelectedColumn();
				//����
				int rowCount=table.getSelectedRow();
				//�õ���Ԫ������
				Object value =table.getValueAt(rowCount, columnCount);
				//��ȡ����Ϣ
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				//��ȡ���������е�����
				Vector v=model.getDataVector();
				//������ת��Ϊ�ַ���
				String str =v.get(rowCount).toString();
				//�õ���һ�е��Ծ�����
				String examNamestr=str.split(",")[0].substring(1);
				//���õ����ֶδ����ı�����
				textField.setText(examNamestr);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
	}

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
		selectId = textField.getText();
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
		mouseClicked();

	}
}
