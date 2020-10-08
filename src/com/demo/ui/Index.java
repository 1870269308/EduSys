package com.demo.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.demo.pojo.UserManage;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.SystemColor;

public class Index {

	private JFrame frame;
	private static UserManage userMessage = new UserManage();

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
					Index window = new Index();
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
	public Index() {
		initialize();
	}

	public Index(UserManage userMessage) {

		this.userMessage = userMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 938, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/**
		 * ��������
		 */

		// �α���
		JButton btnNewButton_1 = new JButton("\u8BFE\u8868\u5B89\u6392");
		btnNewButton_1.setIcon(new ImageIcon(Index.class.getResource("/images/kebiao.png")));
		ListenerCourse lc = new ListenerCourse(frame, userMessage);
		btnNewButton_1.addActionListener(lc);
		btnNewButton_1.setBounds(325, 0, 113, 42);
		frame.getContentPane().add(btnNewButton_1);

		// ����ϵͳ
		JButton btnNewButton_2 = new JButton("\u8003\u8BD5\u7CFB\u7EDF");
		btnNewButton_2.setIcon(new ImageIcon(Index.class.getResource("/images/kaos.png")));
		ListenerExam le = new ListenerExam(frame);
		btnNewButton_2.addActionListener(le);
		btnNewButton_2.setBounds(435, 0, 123, 42);
		frame.getContentPane().add(btnNewButton_2);
		listenerExit lx = new listenerExit(frame);

		// ��������
		JButton btnNewButton_4 = new JButton("\u67E5\u770B\u53CD\u9988");
		btnNewButton_4.setIcon(new ImageIcon(Index.class.getResource("/images/fankui.png")));
		listenerFeed lf = new listenerFeed(frame);
		btnNewButton_4.addActionListener(lf);
		btnNewButton_4.setBounds(678, 0, 123, 42);
		frame.getContentPane().add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton_3.setIcon(new ImageIcon(Index.class.getResource("/images/out.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(801, 0, 119, 42);
		frame.getContentPane().add(btnNewButton_3);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(14, 55, 892, 416);
		frame.getContentPane().add(desktopPane);
		myCalendar mycalendar=new myCalendar();
		mycalendar.setBackground(SystemColor.textHighlight);
		mycalendar.getContentPane().setBackground(SystemColor.textHighlight);
		mycalendar.setBounds(0, 0, 892, 416);
		desktopPane.add(mycalendar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 139, 42);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6559\u5E08\u8D26\u53F7\u7BA1\u7406");
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		mnNewMenu.setIcon(new ImageIcon(Index.class.getResource("/images/teacher.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u6DFB\u52A0\u6559\u5E08\u8D26\u53F7");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ӽ�ʦ��Ϣ
				//��ת������û�����AddUser
				frame.setVisible(false);
				new AddUserFrm().getFrame().setVisible(true);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(Index.class.getResource("/images/add.png")));
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u5220\u9664\u6559\u5E08\u8D26\u53F7");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ɾ����ʦ��Ϣ
				frame.setVisible(false);
				new DeleteUserFrm().getFrame().setVisible(true);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(Index.class.getResource("/images/dl.png")));
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		mnNewMenu.add(mntmNewMenuItem_1);
		JButton btnNewButton = new JButton("\u4E0A\u8BFE\u70B9\u540D");
		btnNewButton.setBounds(556, 0, 123, 42);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(Index.class.getResource("/images/dm.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//���������
				RandomCall random = new RandomCall();
				random.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		mycalendar.setVisible(true);
		 // ����ͼƬ
	     ImageIcon background = new ImageIcon(
	                 this.getClass().getResource("/images/bkgm.jpg"));
	     // �ѱ���ͼƬ��ʾ��һ����ǩ����
	     JLabel label = new JLabel(background);
	     // ���ñ�ǩ��С
	     label.setBounds(0, 0,938, 542);
	     // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
	     JPanel imagePanel = (JPanel) frame.getContentPane();
	     imagePanel.setOpaque(false);
	     frame.getLayeredPane().setLayout(null);
	     // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
	     frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//���ô������
		frame.setLocationRelativeTo(null);
	}
}

//�γ����ü���
class ListenerCourse implements ActionListener {
	private UserManage userMessage = new UserManage();
	private JFrame frame;

	public ListenerCourse(JFrame frame, UserManage userMessage) {
		this.frame = frame;
		this.userMessage = userMessage;

	}

	public void actionPerformed(ActionEvent e) {
		CourseFrm window1 = new CourseFrm(userMessage);
		frame.setVisible(false);
		window1.getFrame().setVisible(true);
	}
}

//�����¼�����
class ListenerExam implements ActionListener {
	private JFrame frame;

	public ListenerExam(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		AdminFrm window1 = new AdminFrm();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);
	}
}

//�˳��¼�����
class listenerExit implements ActionListener {
	private JFrame frame;

	public listenerExit(JFrame frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Login window1 = new Login();
		frame.setVisible(false);
		window1.getFrame().setVisible(true);
	}
}

//�����¼�����
class listenerFeed implements ActionListener {
	private JFrame frame;

	public listenerFeed(JFrame frame) {
		this.frame = frame;
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		feedback feed = new feedback();
		frame.setVisible(false);
		feed.getFrame().setVisible(true);
	}

}