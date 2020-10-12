package com.demo.ui.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.pojo.User;
import com.demo.utils.StringUtils;


import lombok.Getter;
import lombok.Setter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
@Getter
@Setter
public class registerFrm{

	private JFrame frame;
	private JTextField uNameText;
	private JTextField passwordOneText;
	private JTextField passwordTwoText;

	
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
					registerFrm window = new registerFrm();
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
	public registerFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ע�����");
		frame.setResizable(false);
		frame.setBounds(100, 100, 653, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(174, 112, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		uNameText = new JTextField();
		uNameText.setBounds(238, 109, 190, 24);
		frame.getContentPane().add(uNameText);
		uNameText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_1.setBounds(174, 176, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(160, 244, 85, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordOneText = new JTextField();
		passwordOneText.setColumns(10);
		passwordOneText.setBounds(238, 173, 190, 24);
		frame.getContentPane().add(passwordOneText);
		
		passwordTwoText = new JTextField();
		passwordTwoText.setColumns(10);
		passwordTwoText.setBounds(238, 241, 190, 24);
		frame.getContentPane().add(passwordTwoText);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setIcon(new ImageIcon(registerFrm.class.getResource("/images/add.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ע��
				registerAction(e);
				
			}
		});
		btnNewButton.setBounds(107, 352, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setIcon(new ImageIcon(registerFrm.class.getResource("/images/de.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(276, 352, 113, 27);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\u8FD4\u56DE\u767B\u5F55\u754C\u9762");
		btnNewButton_1_1.setIcon(new ImageIcon(registerFrm.class.getResource("/images/out.png")));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//������ʧ
				//���ص�¼����
				Login login=new Login();
				login.getFrame().setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(441, 352, 148, 27);
		frame.getContentPane().add(btnNewButton_1_1);
		 // ����ͼƬ
	     ImageIcon background = new ImageIcon(
	                 this.getClass().getResource("/images/bkgm.jpg"));
	     // �ѱ���ͼƬ��ʾ��һ����ǩ����
	     JLabel label = new JLabel(background);
	     // ���ñ�ǩ��С
	     label.setBounds(0, 0, 653, 485);
	     // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
	     JPanel imagePanel = (JPanel) frame.getContentPane();
	     imagePanel.setOpaque(false);
	     frame.getLayeredPane().setLayout(null);
	     // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
	     frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		//���ô������
		frame.setLocationRelativeTo(null);
	}
	/**
	 * ע���¼�
	 * @param e
	 */
	protected void registerAction(ActionEvent e) {
		//��ȡ�ı��������
		String userName=uNameText.getText().trim();
		String passwordOne=passwordOneText.getText().trim();
		String passwordTwo=passwordTwoText.getText().trim();
		//System.out.println(userName+" "+passwordOne+" "+passwordTwo);
		//System.out.println(Integer.valueOf(passwordOne)==Integer.valueOf(passwordTwo));
		//�ж��Ƿ�Ϊ��
		if(StringUtils.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
			return;
		}
		if(StringUtils.isEmpty(passwordOne)||StringUtils.isEmpty(passwordTwo)) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
			return;
		}
//		if(Integer.valueOf(passwordOne)!=Integer.valueOf(passwordTwo)) {
//			JOptionPane.showMessageDialog(null, "�����������벻һ�£�����������");
//			resetValueActionPerformed(e);
//			return;
//		}
		UserDao userDao=new UserDaoImpl();
		if(passwordOne.equals(passwordTwo)) {
			userDao.register(new User(0,userName,passwordOne,"ѧ��"));//TODO
			
			resetValueActionPerformed(e);
			int result=JOptionPane.showConfirmDialog(null,"ע��ɹ����Ƿ񷵻ص�¼����");
			if(result==0) {
				frame.dispose();//������ʧ
				new Login().getFrame().setVisible(true);//��ʾ�½���
			}
		}else {
			JOptionPane.showConfirmDialog(null,"�������벻ͬ������������");
		}
	}

	/**
	 * ����
	 * @param e
	 */
	protected void resetValueActionPerformed(ActionEvent e) {
		this.uNameText.setText("");
		this.passwordOneText.setText("");
		this.passwordTwoText.setText("");
	}
}
