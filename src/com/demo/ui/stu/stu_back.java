package com.demo.ui.stu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.demo.pojo.FeedBack;
import com.demo.pojo.User;
import com.demo.ui.login.Login;
import com.demo.ui.stu.examstu.StuSelectUi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class stu_back {

	private JFrame frame;
	private static User userMessage = new User();
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
					stu_back window = new stu_back();
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
	public stu_back() {
		initialize();
	}

	public stu_back(User userMessage) {

		this.userMessage = userMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u754C\u9762");
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);
		// ���ھ���
		frame.setLocationRelativeTo(null);
		//��ť�¼�
		JButton button = new JButton("\u8003\u8BD5");

		button.setIcon(new ImageIcon(stu_back.class.getResource("/images/pen.png")));

		button.setIcon(new ImageIcon(stu_back.class.getResource("/images/question.png")));

		// ���԰�ť����
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == userMessage.getUserName()) {
					JOptionPane.showMessageDialog(null, "���������µ�¼���ٽ��п���");
					return;
				}
				// �رյ�ǰ����
				frame.dispose();
				// ��ת��ѧ��ѡ����������
				StuSelectUi stu = new StuSelectUi(userMessage);
				stu.getFrame().setVisible(true);
			}
		});
		button.setBounds(52, 70, 127, 33);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u53CD\u9988");
		button_1.setIcon(new ImageIcon(stu_back.class.getResource("/images/fankui.png")));
		// ������ť����
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ж�ֱ�ӽ��з�����ᵯ����ʾ�ȵ�¼
				if (null == userMessage.getUserName()) {
					JOptionPane.showMessageDialog(null, "���������µ�¼���ٽ��з���");
					return;
				}
				// ��ת�Ĵ���
				frame.dispose();// ������ʧ,��ת������ҳ��
				stu_back_msg msg = new stu_back_msg(userMessage);// ��stu_back��usermessage���뵽��ҳ��
				msg.getFrame().setVisible(true);
			}
		});
		button_1.setBounds(52, 166, 127, 33);
		frame.getContentPane().add(button_1);
		ImageIcon img = new ImageIcon("src/images/feedback1.jpg");
		
		JButton button_1_1 = new JButton("\u9000\u51FA\u767B\u5F55");
		button_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Login().getFrame().setVisible(true);
			}
		});
		button_1_1.setIcon(new ImageIcon(stu_back.class.getResource("/images/out.png")));
		button_1_1.setBounds(52, 263, 127, 33);
		frame.getContentPane().add(button_1_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon(stu_back.class.getResource("/images/feedback1.jpg")));
		btnNewButton.setBounds(226, 70, 404, 339);
		frame.getContentPane().add(btnNewButton);
	}
}