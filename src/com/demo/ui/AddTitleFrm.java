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

import com.demo.dao.QuestionDao;
import com.demo.dao.QuestionDao1;
import com.demo.dao.impl.QuestionDao1Impl;
import com.demo.pojo.Question;
import com.demo.utils.JdbcUtil;
import com.demo.utils.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anus
 *
 */
@Setter
@Getter
public class AddTitleFrm {

	private JFrame frame;
	private JTextField textld;
	private JTextField keya;
	private JTextField keyb;
	private JTextField keyc;
	private JTextField keyd;
	private JTextField titleText;
	private JTextField key;
	private JTextField keyRemask;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 779, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"!!!\u589E\u52A0\u591A\u9009\u9898\u8981\u586B\u6EE1\u4EE5\u4E0B\u6240\u6709\u9009\u9879");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(210, 13, 286, 38);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u9898\u76EE\u7F16\u53F7");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(45, 57, 91, 38);
		frame.getContentPane().add(lblNewLabel_1);

		textld = new JTextField();
		textld.setBounds(150, 64, 346, 24);
		frame.getContentPane().add(textld);
		textld.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u9898   \u76EE");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(45, 128, 72, 18);
		frame.getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 101, 346, 77);
		frame.getContentPane().add(scrollPane);

		titleText = new JTextField();
		scrollPane.setViewportView(titleText);
		titleText.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u5907\u9009\u7B54\u6848\u533A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(45, 226, 91, 29);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u7B54\u6848 A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(55, 268, 72, 18);
		frame.getContentPane().add(lblNewLabel_4);

		keya = new JTextField();
		keya.setBounds(150, 267, 346, 24);
		frame.getContentPane().add(keya);
		keya.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("\u7B54\u6848 B");
		lblNewLabel_4_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(55, 310, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_1);

		keyb = new JTextField();
		keyb.setColumns(10);
		keyb.setBounds(150, 309, 346, 24);
		frame.getContentPane().add(keyb);

		JLabel lblNewLabel_4_2 = new JLabel("\u7B54\u6848 C");
		lblNewLabel_4_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4_2.setBounds(55, 350, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_2);

		keyc = new JTextField();
		keyc.setColumns(10);
		keyc.setBounds(150, 349, 346, 24);
		frame.getContentPane().add(keyc);

		JLabel lblNewLabel_4_3 = new JLabel("\u7B54\u6848 D");
		lblNewLabel_4_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4_3.setBounds(55, 387, 72, 18);
		frame.getContentPane().add(lblNewLabel_4_3);

		keyd = new JTextField();
		keyd.setColumns(10);
		keyd.setBounds(150, 386, 346, 24);
		frame.getContentPane().add(keyd);

		key = new JTextField();
		key.setColumns(10);
		key.setBounds(150, 497, 346, 24);
		frame.getContentPane().add(key);

		JLabel lblNewLabel_4_3_1 = new JLabel("  \u7B54\u6848 ");
		lblNewLabel_4_3_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4_3_1.setBounds(55, 498, 81, 23);
		frame.getContentPane().add(lblNewLabel_4_3_1);

		JButton titleAdd = new JButton("\u6DFB\u52A0");
		titleAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 添加
				addAction(e);
			}

		});
		titleAdd.setBounds(85, 585, 124, 44 );
		frame.getContentPane().add(titleAdd);

		JButton titleReset = new JButton("\u91CD\u7F6E");
		titleReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 重置
				resetValueActionPerformed(e);
			}

		});
		titleReset.setBounds(290, 585, 124, 44);
		frame.getContentPane().add(titleReset);

		JButton exitTitle = new JButton("\u9000\u51FA");
		exitTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回管理员考试子系统主界面
				// 跳转到修改删除界面
				// 销毁该界面
				frame.dispose();
				new AdminFrm().getFrame().setVisible(true);
				
			}
		});
		exitTitle.setBounds(489, 585, 124, 44);
		frame.getContentPane().add(exitTitle);

		JLabel lblNewLabel_5 = new JLabel("  \u5907\u6CE8");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(64, 444, 72, 18);
		frame.getContentPane().add(lblNewLabel_5);

		keyRemask = new JTextField();
		keyRemask.setColumns(10);
		keyRemask.setBounds(150, 443, 346, 24);
		frame.getContentPane().add(keyRemask);
	}

	/**
	  *   添加事件
	 * 
	 * @param e
	 */
	protected void addAction(ActionEvent e) {
		// 获取文本框内容
		// 获取题目
		String title = titleText.getText().trim();
		// 获取答案
		String keyvalue = key.getText().trim();
		// a选项
		String keyA = keya.getText().trim();
		// b选项
		String keyB = keyb.getText().trim();
		// c选项
		String keyC = keyc.getText().trim();
		// c选项
		String keyD = keyd.getText().trim();
		//备注
		String remarks=keyRemask.getText().trim();
		//判空
		if(StringUtils.isEmpty(title)) {
			JOptionPane.showMessageDialog(null, "题目不能为空");
			return;
		}
		if(StringUtils.isEmpty(keyvalue)||StringUtils.isEmpty(keyA)||StringUtils.isEmpty(keyB)||StringUtils.isEmpty(keyC)||StringUtils.isEmpty(keyD)||StringUtils.isEmpty(remarks)) {
			JOptionPane.showMessageDialog(null, "内容不能为空");
			return;
		}
		//
//		System.out.println(title+"--"+keyvalue+"--"+keyA+"--"+keyB+"--"+keyC+"--"+keyD+"--"+remarks);
		QuestionDao1 questionDao=new QuestionDao1Impl();
		
		questionDao.register(new Question(title,keyvalue,keyA,keyB,keyC,keyD,remarks));
		
	}
	
	
	/**
	 *  重置
	 * @param e
	 */
	protected void resetValueActionPerformed(ActionEvent e) {
		this.titleText.setText("");
		this.key.setText("");
		this.keya.setText("");
		this.keyb.setText("");
		this.keyc.setText("");
		this.keyd.setText("");
		this.keyRemask.setText("");
	}
	
	
	
}
