package com.demo.ui;

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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.demo.pojo.User;

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
		frame.setBounds(100, 100, 631, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);

		JButton button = new JButton("\u8003\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رյ�ǰ����
				frame.dispose();
				//��ת��ѧ��ѡ����������
				new StuSelectUi(userMessage).getFrame().setVisible(true);
			}
		});
		button.setBounds(44, 124, 93, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u53CD\u9988");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ת�Ĵ���
				frame.dispose();//������ʧ,��ת������ҳ��
				stu_back_msg msg=new stu_back_msg(userMessage);//��stu_back��usermessage���뵽��ҳ��
				msg.getFrame().setVisible(true);
			}
		});
		button_1.setBounds(138, 124, 93, 23);
		frame.getContentPane().add(button_1);

		JTextPane textPane = new JTextPane();
		
//		java.net.URL url;
//		try {
//			url = new File("src/images/����û�.png").toURI().toURL();
//			textPane.setPage(url);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		textPane.setText("\u53EF\u4EE5\u6DFB\u52A0\u56FE\u7247");
		textPane.setBounds(288, 45, 225, 203);
		frame.getContentPane().add(textPane);
		JLabel lable=new JLabel();
		ImageIcon img=new ImageIcon("src/images/feedback.jpg");
		lable.setIcon(img);
		lable.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		lable.setVisible(true);
		textPane.add(lable);
		
		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������ҳ��
				frame.dispose();
				Longin idx=new Longin();
				idx.getFrame().setVisible(true);
			}
		});
		button_2.setBounds(90, 157, 93, 23);
		frame.getContentPane().add(button_2);
	}
}