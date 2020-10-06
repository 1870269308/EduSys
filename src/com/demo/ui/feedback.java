package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import lombok.Getter;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Getter
public class feedback {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel dtm;

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
					feedback window = new feedback();
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
	public feedback() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"\u7528\u6237\u540D", "\u53CD\u9988\u4FE1\u606F"
			}
		));
		// 建立一个table模型
		dtm = new DefaultTableModel(new Object[][] { },
				new String[] { "\u7528\u6237\u540D", "\u53CD\u9988\u4FE1\u606F" });
		
		table.setBounds(106, 24, 318, 214);
		frame.getContentPane().add(table);
		
		JButton button = new JButton("\u67E5\u770B");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Object[] rowData = { nameText.getText(), ageText.getText() };
//				dtm.addRow(rowData);
			}
		});
		button.setBounds(3, 20, 93, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.setBounds(3, 192, 93, 23);
		frame.getContentPane().add(button_1);
	}
}
