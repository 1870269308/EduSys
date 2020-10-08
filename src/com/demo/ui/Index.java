package com.demo.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.demo.pojo.UserManage;

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
		 * 点名功能
		 */
		JButton btnNewButton = new JButton("\u4E0A\u8BFE\u70B9\u540D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//随机点名类
				RandomCall random = new RandomCall();
				random.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});

		btnNewButton.setBounds(33, 120, 113, 27);
		frame.getContentPane().add(btnNewButton);

		// 课表安排
		JButton btnNewButton_1 = new JButton("\u8BFE\u8868\u5B89\u6392");
		ListenerCourse lc = new ListenerCourse(frame, userMessage);
		btnNewButton_1.addActionListener(lc);
		btnNewButton_1.setBounds(33, 198, 113, 27);
		frame.getContentPane().add(btnNewButton_1);

		// 考试系统
		JButton btnNewButton_2 = new JButton("\u8003\u8BD5\u7CFB\u7EDF");
		ListenerExam le = new ListenerExam(frame);
		btnNewButton_2.addActionListener(le);
		btnNewButton_2.setBounds(33, 280, 113, 27);
		frame.getContentPane().add(btnNewButton_2);
		listenerExit lx = new listenerExit(frame);

		// 反馈内容
		JButton btnNewButton_4 = new JButton("\u53CD\u9988");
		listenerFeed lf = new listenerFeed(frame);
		btnNewButton_4.addActionListener(lf);
		btnNewButton_4.setBounds(33, 362, 113, 27);
		frame.getContentPane().add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("\u9000\u51FA\u767B\u5F55");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(734, 44, 113, 27);
		frame.getContentPane().add(btnNewButton_3);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(268, 99, 585, 372);
		frame.getContentPane().add(desktopPane);
		myCalendar mycalendar=new myCalendar();
		mycalendar.setBounds(0, 0, 585, 372);
		desktopPane.add(mycalendar);
		mycalendar.setVisible(true);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
	}
}

//课程设置监听
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

//考试事件监听
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

//退出事件监听
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

//反馈事件监听
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