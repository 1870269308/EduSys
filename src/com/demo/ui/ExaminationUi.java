package com.demo.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.demo.pojo.Question;
import com.demo.utils.BeanListResultSethandler;
import com.demo.utils.QueryRunner;

public class ExaminationUi {

	JFrame frame;
	private JTextField nowNumField;
	private JTextField totalCountField;
	private JTextField answerCountField;
	private JTextField unanswerCountField;
	private JTextArea textArea;
	private JLabel realTimeLabel = new JLabel();
	private JTextField anwertext;
	// 设置从StuSelectUi传递过来的试卷id
	public static Integer examId;
	// 设置分数的变量
	public Integer score,singleScore,moreScore;
	//设置时间
	public Date inserTime;
	// 设置题目的初始值
	int p = 0;
	// 题目的总数记录
	int num = 5;
    private int nowNumber = 0;//当前题号
    private int totalCount = 5;//题目总数
    private int answerCount = 0;//已答
    private int unanswerCount = totalCount;//未答
	// 使用来保存试题信息
	ArrayList<String> suStr = new ArrayList<String>();
	// 保存题库中的正确答案
	ArrayList<String> answer = new ArrayList<String>();
	// 保存回答的答案‘
	ArrayList<String> answerResponse = new ArrayList<String>();
	// 存储是什么类型的题目
	ArrayList<String> remak = new ArrayList<String>();

	// 实现倒计时
	ClockDispaly mt;// 考试倒计时

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExaminationUi window = new ExaminationUi();
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
	public ExaminationUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5B66\u751F\u8003\u8BD5\u7CFB\u7EDF");
		frame.setBounds(100, 100, 886, 569);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel timeLabel = new JLabel("\u5269\u4F59\u8003\u8BD5\u65F6\u95F4");
		timeLabel.setBounds(701, 93, 134, 35);
		frame.getContentPane().add(timeLabel);

		JLabel lblNewLabel_1_1 = new JLabel("\u5F53\u524D\u9898\u53F7");
		lblNewLabel_1_1.setBounds(675, 285, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1);
		// 当前题目编号
		nowNumField = new JTextField();
		// 文本框内的内容
//		nowNumField.setText(nowNumber+1+"");
		nowNumField.setEditable(false);
		nowNumField.setBounds(749, 287, 86, 24);
		frame.getContentPane().add(nowNumField);
		nowNumField.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u9898\u76EE\u603B\u6570");
		lblNewLabel_1_1_1.setBounds(675, 330, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		// TODO
		// 题目总数
		totalCountField = new JTextField();
		totalCountField.setText(totalCount+"");
		totalCountField.setEditable(false);
		totalCountField.setColumns(10);
		totalCountField.setBounds(749, 332, 86, 24);
		frame.getContentPane().add(totalCountField);

		JLabel lblNewLabel_1_1_2 = new JLabel("\u5DF2\u7B54\u9898\u6570");
		lblNewLabel_1_1_2.setBounds(675, 371, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		//已答题数
		answerCountField = new JTextField();
//		answerCountField.setText(++answerCount+"");
		answerCountField.setEditable(false);
		answerCountField.setColumns(10);
		answerCountField.setBounds(749, 373, 86, 24);
		frame.getContentPane().add(answerCountField);

		JLabel lblNewLabel_1_1_3 = new JLabel("\u672A\u7B54\u9898\u6570");
		lblNewLabel_1_1_3.setBounds(675, 417, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		//还未答的题目
		unanswerCountField = new JTextField();
		unanswerCountField.setText(--unanswerCount+"");
		unanswerCountField.setEditable(false);
		unanswerCountField.setColumns(10);
		unanswerCountField.setBounds(749, 419, 86, 24);
		frame.getContentPane().add(unanswerCountField);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(86, 91, 515, 267);
		frame.getContentPane().add(textArea);

		// TODO
		// 下一题
		JButton nextButton = new JButton("\u4E0B\u4E00\u9898");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p++;
				if (p == num) {
					JOptionPane.showMessageDialog(null, "已经是最后一题了");
					p--;
				}
				// 刷出下一题
				showQuestion();
				// 将回答的答案传入集合中
				answerResponse.add(anwertext.getText());
				System.out.println(answerResponse);
				// 清空输入框
				anwertext.setText("");
				// requestFocus用于获得焦点
				anwertext.requestFocus();
				 nowNumber++;
				////修改当前已答题 未答题
				nowNumField.setText(nowNumber+1+"");
				answerCountField.setText(++answerCount+"");
				unanswerCountField.setText(--unanswerCount+"");
				
			}
		});
		nextButton.setBounds(447, 461, 113, 27);
		frame.getContentPane().add(nextButton);

		// TODO
				// 上一题
				JButton prevButton = new JButton("\u4E0A\u4E00\u9898");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p--;
						if (p == -1) {
							JOptionPane.showMessageDialog(null, "已经是第一题了");
							p++;
						}
						showQuestion();
						if (p == 0) {
							return;
						}
						nowNumber--;
						answerResponse.remove(p - 1);
						nowNumField.setText(nowNumber + 1 + "");
		                answerCountField.setText(--answerCount + "");
		                unanswerCountField.setText(++unanswerCount + "");
					}
				});
				prevButton.setBounds(152, 461, 113, 27);
				frame.getContentPane().add(prevButton);

		
		JLabel realTimeLabel = new JLabel();
		realTimeLabel.setText("\u8003\u8BD5\u65F6\u95F4\u4E3A\uFF1A60\u5206\u949F");
		realTimeLabel.setBounds(701, 154, 153, 35);
		frame.getContentPane().add(realTimeLabel);

		JLabel lblNewLabel = new JLabel("\u8BD5\u9898\u4FE1\u606F");
		lblNewLabel.setBounds(14, 13, 144, 35);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("\u7B54\u6848");
		lblNewLabel_2.setBounds(86, 391, 134, 28);
		frame.getContentPane().add(lblNewLabel_2);

		anwertext = new JTextField();
		anwertext.setBounds(141, 393, 244, 28);
		frame.getContentPane().add(anwertext);
		anwertext.setColumns(10);
		// 开始考试
		JButton begin = new JButton("\u5F00\u59CB\u8003\u8BD5");
		begin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (examId == null) {
					JOptionPane.showMessageDialog(null, "错误：请重新登录，选择考试题目");
					return;
				}
				// 调用取出试题的方法
				createExam();
				// 题目编号
				p = 0;
				// 展示考试内容信息
				showQuestion();
				// 倒计时启动
				mt.start();
				// 设置按钮不可再次点击
				begin.setEnabled(false);

			}
		});
		begin.setBounds(722, 53, 113, 27);
		frame.getContentPane().add(begin);

		// 提交试卷
		JButton submitButton = new JButton("\u63D0\u4EA4\u8BD5\u5377");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 拿到分数
				score = showScore();
				//
				transferTable();
				submitButton.setEnabled(false);
			}
		});
		submitButton.setBounds(722, 13, 113, 27);
		frame.getContentPane().add(submitButton);

		// 调用并设置倒计时时间
		mt = new ClockDispaly(realTimeLabel, 60);

	}
	//将分数传入scoer表中
	public void transferTable() {
		//成绩id,加入时间，总成绩，试卷id，
		String sql="insert into score value(null,\"孤街\",?,?,?,?)";
		Object[] obj= {singleScore,moreScore,score,inserTime};
		System.out.println(sql);
		new QueryRunner().execute(sql, obj);
	}

	private Integer showScore() {
		// 单选正确的题数
		int singleRight = 0;
		// 单选错误的题数
		int singleError = 0;
		// 单选正确的题数
		int moreRight = 0;
		// 单选错误的题数
		int moreError = 0;
		for (int i = 0; i < num; i++) {
			//equals进行内容比较
			if (remak.get(i).equals("单选")) {
				if (answer.get(i).equals(answerResponse.get(i))) {
					singleRight++;
					System.out.println(singleRight);
				} else {
					singleError++;
				}
			}else if(remak.get(i).equals("多选")) {
				if(answer.get(i).equals(answerResponse.get(i))) {
					moreRight++;
				}else {
					moreError--;
				}
			}
		}
		// 设置分数
		score = (singleRight*10)+(moreRight*10);
//		单选题分数
		singleScore=singleRight*10;
		//多选题分数
		moreScore=moreRight*10;
		//正确题
		int right=singleRight+moreRight;
		//错题
		int error=singleError+moreError;
		JOptionPane.showMessageDialog(null, "答对了" + right + "题，答错了" + error + "题,总分是"+score);
		return score;
	}

	// 创建考试模块，提取试题信息
	public void createExam() {
		// 定义一个空字符串
		String s = "";
		// 根据id来取出试题
		String sql = "select * from question where subjectId=?";
		Object[] params = { examId };
		System.out.println(examId);
		// 将question中的数据放入List中
		List<Question> list = (List<Question>) QueryRunner.query(sql, params,
				new BeanListResultSethandler<Question>(Question.class));
		// 遍历出Question中的数据
		for (Question p : list) {
			String title = p.getTitle();// 题目
			String keyA = p.getKeyA();
			String keyB = p.getKeyB();
			String keyC = p.getKeyC();
			String keyD = p.getKeyD();// 选择题内容
			String keyvalue = p.getKeyvalue();// 答案
			String remark = p.getRemarks();
			inserTime=p.getJoinTime();
			// 获取到试题信息
			s = title + "\n" + keyA + "\n" + keyB + "\n" + keyC + "\n" + keyD;
//			System.out.println(s);
			// 创建一个集合来存放题目信息
			suStr.add(s);
//			System.out.println(suStr);
			// 创建一个集合来存放答案
			answer.add(keyvalue);
			System.out.println(answer);
			// 创建一个集合来存放题目类型
			remak.add(remark);
			System.out.println(remak);
		}
	}

	// 设置考试面板上的信息
	private void showQuestion() {
		textArea.setText("");
		textArea.append(suStr.get(p));
		System.out.println(suStr.get(p));
	}
}

//考试倒计时
class ClockDispaly extends Thread {
	private JLabel leftTime;
	private int testTime;

	public ClockDispaly(JLabel lt, int time) {
		leftTime = lt;
		testTime = time * 60;
	}

	// 开始运行
	public void run() {
		// 控制时间的格式
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置数值的整数部分允许显示的小位数
		numberFormat.setMinimumIntegerDigits(2);
		// 定义时、分、秒
		int h, m, s;
		while (testTime >= 0) {
			h = testTime / 3600;
			m = testTime % 3600 / 60;
			s = testTime % 60;
			StringBuffer strb = new StringBuffer();
			// 添加到leftTime标签
			strb.append(
					"剩余答题时间：" + numberFormat.format(h) + ":" + numberFormat.format(m) + ":" + numberFormat.format(s));
			//// 这leftTimer是传进来的时间label
			leftTime.setText(strb.toString());
			// 设置延时 延时为一秒
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			testTime = testTime - 1;

			if (testTime <= 0) {
				JOptionPane.showMessageDialog(null, "考试结束");
				System.exit(0);
			}
		}
	}

}
