package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.demo.dao.QuestionDao1;
import com.demo.dao.impl.QuestionDao1Impl;
import com.demo.pojo.Question;
import com.demo.utils.JdbcUtil;
import com.demo.utils.StringUtils;

import lombok.Getter;
import lombok.Setter;
import javax.swing.ImageIcon;

/**
 * @author anus
 *
 */

public class AddTitleFrm {

	private JFrame frame;
	private JTextField textId;
	private JTextField keya;
	private JTextField keyb;
	private JTextField keyc;
	private JTextField keyd;
	private JTextField titleText;
	private JTextField key;
	private JTextField keyRemask;
	private JTextField subjectText;
	

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
					AddTitleFrm window = new AddTitleFrm();
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
	public AddTitleFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("�������");
		frame.setBounds(100, 100, 779, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"\u8BD5\u9898\u79D1\u76EE\uFF1A1\u4E3A\u6570\u5B66\uFF0C2\u4E3A\u8BED\u6587\uFF0C3\u4E3A\u82F1\u8BED");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel.setBounds(179, 13, 395, 38);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u9898\u76EE\u7F16\u53F7");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(45, 57, 91, 38);
		frame.getContentPane().add(lblNewLabel_1);

		textId = new JTextField();
		textId.setBounds(150, 64, 172, 24);
		frame.getContentPane().add(textId);
		textId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u9898   \u76EE");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(45, 128, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 101, 346, 77);
		frame.getContentPane().add(scrollPane);

		titleText = new JTextField();
		scrollPane.setViewportView(titleText);
		titleText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u5907\u9009\u7B54\u6848\u533A");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(45, 247, 91, 29);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u7B54\u6848 A");
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(55, 289, 72, 18);
		frame.getContentPane().add(lblNewLabel_4);

		keya = new JTextField();
		keya.setBounds(150, 288, 346, 24);
		frame.getContentPane().add(keya);
		keya.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("\u7B54\u6848 B");
		lblNewLabel_4_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(55, 331, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_1);

		keyb = new JTextField();
		keyb.setColumns(10);
		keyb.setBounds(150, 330, 346, 24);
		frame.getContentPane().add(keyb);

		JLabel lblNewLabel_4_2 = new JLabel("\u7B54\u6848 C");
		lblNewLabel_4_2.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_4_2.setBounds(55, 371, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_2);

		keyc = new JTextField();
		keyc.setColumns(10);
		keyc.setBounds(150, 370, 346, 24);
		frame.getContentPane().add(keyc);

		JLabel lblNewLabel_4_3 = new JLabel("\u7B54\u6848 D");
		lblNewLabel_4_3.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_4_3.setBounds(55, 408, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_3);

		keyd = new JTextField();
		keyd.setColumns(10);
		keyd.setBounds(150, 407, 346, 24);
		frame.getContentPane().add(keyd);

		key = new JTextField();
		key.setColumns(10);
		key.setBounds(150, 514, 346, 24);
		frame.getContentPane().add(key);

		JLabel lblNewLabel_4_3_1 = new JLabel("  \u7B54\u6848 ");
		lblNewLabel_4_3_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_4_3_1.setBounds(55, 515, 81, 23);
		frame.getContentPane().add(lblNewLabel_4_3_1);

		JButton titleAdd = new JButton("\u6DFB\u52A0");
		titleAdd.setIcon(new ImageIcon(AddTitleFrm.class.getResource("/images/add.png")));
		titleAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���
				addAction(e);
			}

		});
		titleAdd.setBounds(85, 585, 124, 44 );
		frame.getContentPane().add(titleAdd);

		JButton titleReset = new JButton("\u91CD\u7F6E");
		titleReset.setIcon(new ImageIcon(AddTitleFrm.class.getResource("/images/de.png")));
		titleReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����
				resetValueActionPerformed(e);
			}

		});
		titleReset.setBounds(290, 585, 124, 44);
		frame.getContentPane().add(titleReset);

		JButton exitTitle = new JButton("\u9000\u51FA");
		exitTitle.setIcon(new ImageIcon(AddTitleFrm.class.getResource("/images/out.png")));
		exitTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ع���Ա������ϵͳ������
				// ��ת���޸�ɾ������
				// ���ٸý���
				frame.dispose();
				new AdminFrm().getFrame().setVisible(true);
				
			}
		});
		exitTitle.setBounds(489, 585, 124, 44);
		frame.getContentPane().add(exitTitle);

		JLabel lblNewLabel_5 = new JLabel("  \u5907\u6CE8");
		lblNewLabel_5.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(55, 461, 72, 18);
		frame.getContentPane().add(lblNewLabel_5);

		keyRemask = new JTextField();
		keyRemask.setColumns(10);
		keyRemask.setBounds(150, 460, 346, 24);
		frame.getContentPane().add(keyRemask);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u8BD5\u9898\u79D1\u76EE");
		lblNewLabel_1_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(336, 57, 91, 38);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		subjectText = new JTextField();
		subjectText.setColumns(10);
		subjectText.setBounds(441, 64, 172, 24);
		frame.getContentPane().add(subjectText);
		//���ô������
		frame.setLocationRelativeTo(null);
	}

	/**
	  *   ����¼�
	 * 
	 * @param e
	 */
	protected void addAction(ActionEvent e) {
		// ��ȡ�ı�������
		
		String id=textId.getText();
		//��ȡ��Ŀ��Ϣ
		String subjectId=subjectText.getText().trim();		
		// ��ȡ��Ŀ
		String title = titleText.getText().trim();
		// ��ȡ��
		String keyvalue = key.getText().trim();
		// aѡ��
		String keyA = keya.getText().trim();
		// bѡ��
		String keyB = keyb.getText().trim();
		// cѡ��
		String keyC = keyc.getText().trim();
		// cѡ��
		String keyD = keyd.getText().trim();
		//��ע
		String remarks=keyRemask.getText().trim();
		//�п�
		if(StringUtils.isEmpty(title)) {
			JOptionPane.showMessageDialog(null, "��Ŀ����Ϊ��");
			return;
		}
		if(StringUtils.isEmpty(keyvalue)||StringUtils.isEmpty(keyA)||StringUtils.isEmpty(keyB)||StringUtils.isEmpty(keyC)||StringUtils.isEmpty(keyD)||StringUtils.isEmpty(remarks)) {
			JOptionPane.showMessageDialog(null, "���ݲ���Ϊ��");
			return;
		}
		//
//		System.out.println(title+"--"+keyvalue+"--"+keyA+"--"+keyB+"--"+keyC+"--"+keyD+"--"+remarks);
		QuestionDao1 questionDao=new QuestionDao1Impl();
		
		questionDao.register(new Question(Integer.valueOf(id),Integer.valueOf(subjectId),title,keyvalue,keyA,keyB,keyC,keyD,remarks));
		JOptionPane.showMessageDialog(null, "�������ɹ���");
	}
	
	
	/**
	 *  ����
	 * @param e
	 */
	protected void resetValueActionPerformed(ActionEvent e) {
		this.textId.setText("");
		this.subjectText.setText("");
		this.titleText.setText("");
		this.key.setText("");
		this.keya.setText("");
		this.keyb.setText("");
		this.keyc.setText("");
		this.keyd.setText("");
		this.keyRemask.setText("");
	}
}
