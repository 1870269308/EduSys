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
	static String str;
	static LinkedList link = new LinkedList();
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
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftChange(calendar);
			}

		});
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightChange(calendar);
			}

		});
	}

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
		this.setTitle("����");
// ���ý����С
		this.setSize(800, 600);
// ����
	//	this.setLocationRelativeTo(null);
// ��������е����
		this.setContentPane(createContentPane());
	}

	private Container createContentPane() {
// �½����
		JPanel panel = new JPanel();
// ���ô������Ĳ��ֹ�����(��ˮ�����񣬱߿�)���˴�Ϊ�߿�
		panel.setLayout(new BorderLayout());
		panel.add(createNorthPane(), BorderLayout.NORTH);
		panel.add(createCenterPane(), BorderLayout.CENTER);
// ���ñ߿��࣬��ʱ��
// panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}

	private Component createNorthPane() {
// �½����
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		left = new JButton("�ϸ���");
		left.setBackground(Color.GRAY);
		right = new JButton("�¸���");
		right.setBackground(Color.GRAY);
		title = new JLabel(str, JLabel.CENTER);
		title.setBackground(Color.LIGHT_GRAY);
// ���ô������Ĳ��ֹ�����(��ˮ�����񣬱߿�)���˴�Ϊ�߿�
		panel.setLayout(new BorderLayout());
		panel.add(left, BorderLayout.WEST);
		panel.add(title, BorderLayout.CENTER);
		panel.add(right, BorderLayout.EAST);
// ���ñ߿��࣬��ʱ��
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return panel;
	}

	private Component createCenterPane() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
// GridLayout() ���񲼾�
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

	private static void kalendar(Calendar calendar) {
		link.clear();
		String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		str = "* * * * * * * * * *  " + calendar.get(Calendar.YEAR) + "��" + month[calendar.get(Calendar.MONTH)]
				+ "��  * * * * * * * * * *";
		link.add("��");
		link.add("һ");
		link.add("��");
		link.add("��");
		link.add("��");
		link.add("��");
		link.add("��");
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
// ����Ĭ�Ϲر�ѡ��
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}