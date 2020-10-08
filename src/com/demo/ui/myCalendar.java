package com.demo.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class myCalendar extends JInternalFrame {
	static String str;//字符串
	static LinkedList link = new LinkedList();//链表
	private JButton left;
	private JLabel title;
	private JLabel[] date = new JLabel[49];
	static Calendar calendar = Calendar.getInstance();
	private JButton right;
	{
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		myCalendar.kalendar(calendar);

	}

	public myCalendar() {
		setBackground(new Color(255, 0, 0));
		init();
		listener();
	}

	private void listener() {
		//上个月
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftChange(calendar);
			}

		});
		//下个月
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightChange(calendar);
			}

		});
	}
	//下个月执行代码
	private void rightChange(Calendar calendar) {
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		myCalendar.kalendar(calendar);
		title.setText(str);
		for (int i = 0; i < 49; i++) {
			date[i].setText(" ");
		}
		for (int i = 0; i < link.size(); i++) {
			date[i].setText((String) link.get(i));
		}

	}
	//上个月执行事件
	private void leftChange(Calendar calendar) {
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		myCalendar.kalendar(calendar);
		title.setText(str);
		for (int i = 0; i < 49; i++) {
			date[i].setText("");
		}
		for (int i = 0; i < link.size(); i++) {
			date[i].setText((String) link.get(i));
		}

	}

	private void init() {
		this.setTitle("日历");
		// 设置界面大小
		this.setSize(800, 600);
		// 居中
		//	this.setLocationRelativeTo(null);
		// 设置面板中的组件
		this.setContentPane(createContentPane());
		}

		private Container createContentPane() {
			// 新建面板
			JPanel panel = new JPanel();
			// 设置此容器的布局管理器(流水，网格，边框)，此处为边框
			panel.setLayout(new BorderLayout());
			panel.add(createNorthPane(), BorderLayout.NORTH);
			panel.add(createCenterPane(), BorderLayout.CENTER);
			// 设置边框间距，逆时针
			// panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			return panel;
		}
		
	private Component createNorthPane() {
		// 新建面板
		JPanel panel = new JPanel();
		//设置背景颜色
		panel.setBackground(Color.WHITE);
		left = new JButton("上个月");
		left.setBackground(new Color(135, 206, 235));
		right = new JButton("下个月");
		right.setBackground(new Color(135, 206, 235));
		title = new JLabel(str, JLabel.CENTER);
		title.setBackground(Color.LIGHT_GRAY);
		// 设置此容器的布局管理器(流水，网格，边框)，此处为边框
		panel.setLayout(new BorderLayout());
		panel.add(left, BorderLayout.WEST);
		panel.add(title, BorderLayout.CENTER);
		panel.add(right, BorderLayout.EAST);
		// 设置边框间距，逆时针
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}

	private Component createCenterPane() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		// GridLayout() 网格布局
		panel.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < 49; i++) {
			date[i] = new JLabel("", JLabel.CENTER);
		}
		for (int i = 0; i < link.size(); i++) {
			date[i].setText((String) link.get(i));
			panel.add(date[i]);
		}
		for (int i = link.size(); i < 49; i++) {
			panel.add(date[i]);
		}
		return panel;
	}
	//显示日历
	private static void kalendar(Calendar calendar) {
		link.clear();//清空
		//获取月份
		String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		str = "* * * * * * * * * *  " + calendar.get(Calendar.YEAR) + "年" + month[calendar.get(Calendar.MONTH)]
				+ "月  * * * * * * * * * *";
		link.add("星期日");
		link.add("星期一");
		link.add("星期二");
		link.add("星期三");
		link.add("星期四");
		link.add("星期五");
		link.add("星期六");
		//每月的一号
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		for (int i = 7; i < 7 - 1 + calendar.get(Calendar.DAY_OF_WEEK); i++) {
			link.add(" ");
		}
		int count = 1;
		for (int i = 7 - 1 + calendar.get(Calendar.DAY_OF_WEEK); i < 7 - 1 + calendar.get(Calendar.DAY_OF_WEEK)
				+ calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if (count < 10) {
				link.add("" + count);
			} else {
				link.add(count + "");
			}
			count++;
		}
	}
	public static void main(String[] args) {
		myCalendar test = new myCalendar();
		test.setVisible(true);
		// 设置默认关闭选项
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}