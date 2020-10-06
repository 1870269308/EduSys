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
	// ���ô�StuSelectUi���ݹ������Ծ�id
	public static Integer examId;
	// ���÷����ı���
	public Integer score,singleScore,moreScore;
	//����ʱ��
	public Date inserTime;
	// ������Ŀ�ĳ�ʼֵ
	int p = 0;
	// ��Ŀ��������¼
	int num = 5;
    private int nowNumber = 0;//��ǰ���
    private int totalCount = 5;//��Ŀ����
    private int answerCount = 0;//�Ѵ�
    private int unanswerCount = totalCount;//δ��
	// ʹ��������������Ϣ
	ArrayList<String> suStr = new ArrayList<String>();
	// ��������е���ȷ��
	ArrayList<String> answer = new ArrayList<String>();
	// ����ش�Ĵ𰸡�
	ArrayList<String> answerResponse = new ArrayList<String>();
	// �洢��ʲô���͵���Ŀ
	ArrayList<String> remak = new ArrayList<String>();

	// ʵ�ֵ���ʱ
	ClockDispaly mt;// ���Ե���ʱ

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
		// ��ǰ��Ŀ���
		nowNumField = new JTextField();
		// �ı����ڵ�����
//		nowNumField.setText(nowNumber+1+"");
		nowNumField.setEditable(false);
		nowNumField.setBounds(749, 287, 86, 24);
		frame.getContentPane().add(nowNumField);
		nowNumField.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u9898\u76EE\u603B\u6570");
		lblNewLabel_1_1_1.setBounds(675, 330, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		// TODO
		// ��Ŀ����
		totalCountField = new JTextField();
		totalCountField.setText(totalCount+"");
		totalCountField.setEditable(false);
		totalCountField.setColumns(10);
		totalCountField.setBounds(749, 332, 86, 24);
		frame.getContentPane().add(totalCountField);

		JLabel lblNewLabel_1_1_2 = new JLabel("\u5DF2\u7B54\u9898\u6570");
		lblNewLabel_1_1_2.setBounds(675, 371, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		//�Ѵ�����
		answerCountField = new JTextField();
//		answerCountField.setText(++answerCount+"");
		answerCountField.setEditable(false);
		answerCountField.setColumns(10);
		answerCountField.setBounds(749, 373, 86, 24);
		frame.getContentPane().add(answerCountField);

		JLabel lblNewLabel_1_1_3 = new JLabel("\u672A\u7B54\u9898\u6570");
		lblNewLabel_1_1_3.setBounds(675, 417, 89, 28);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		//��δ�����Ŀ
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
		// ��һ��
		JButton nextButton = new JButton("\u4E0B\u4E00\u9898");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p++;
				if (p == num) {
					JOptionPane.showMessageDialog(null, "�Ѿ������һ����");
					p--;
				}
				// ˢ����һ��
				showQuestion();
				// ���ش�Ĵ𰸴��뼯����
				answerResponse.add(anwertext.getText());
				System.out.println(answerResponse);
				// ��������
				anwertext.setText("");
				// requestFocus���ڻ�ý���
				anwertext.requestFocus();
				 nowNumber++;
				////�޸ĵ�ǰ�Ѵ��� δ����
				nowNumField.setText(nowNumber+1+"");
				answerCountField.setText(++answerCount+"");
				unanswerCountField.setText(--unanswerCount+"");
				
			}
		});
		nextButton.setBounds(447, 461, 113, 27);
		frame.getContentPane().add(nextButton);

		// TODO
				// ��һ��
				JButton prevButton = new JButton("\u4E0A\u4E00\u9898");
				prevButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p--;
						if (p == -1) {
							JOptionPane.showMessageDialog(null, "�Ѿ��ǵ�һ����");
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
		// ��ʼ����
		JButton begin = new JButton("\u5F00\u59CB\u8003\u8BD5");
		begin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (examId == null) {
					JOptionPane.showMessageDialog(null, "���������µ�¼��ѡ������Ŀ");
					return;
				}
				// ����ȡ������ķ���
				createExam();
				// ��Ŀ���
				p = 0;
				// չʾ����������Ϣ
				showQuestion();
				// ����ʱ����
				mt.start();
				// ���ð�ť�����ٴε��
				begin.setEnabled(false);

			}
		});
		begin.setBounds(722, 53, 113, 27);
		frame.getContentPane().add(begin);

		// �ύ�Ծ�
		JButton submitButton = new JButton("\u63D0\u4EA4\u8BD5\u5377");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �õ�����
				score = showScore();
				//
				transferTable();
				submitButton.setEnabled(false);
			}
		});
		submitButton.setBounds(722, 13, 113, 27);
		frame.getContentPane().add(submitButton);

		// ���ò����õ���ʱʱ��
		mt = new ClockDispaly(realTimeLabel, 60);

	}
	//����������scoer����
	public void transferTable() {
		//�ɼ�id,����ʱ�䣬�ܳɼ����Ծ�id��
		String sql="insert into score value(null,\"�½�\",?,?,?,?)";
		Object[] obj= {singleScore,moreScore,score,inserTime};
		System.out.println(sql);
		new QueryRunner().execute(sql, obj);
	}

	private Integer showScore() {
		// ��ѡ��ȷ������
		int singleRight = 0;
		// ��ѡ���������
		int singleError = 0;
		// ��ѡ��ȷ������
		int moreRight = 0;
		// ��ѡ���������
		int moreError = 0;
		for (int i = 0; i < num; i++) {
			//equals�������ݱȽ�
			if (remak.get(i).equals("��ѡ")) {
				if (answer.get(i).equals(answerResponse.get(i))) {
					singleRight++;
					System.out.println(singleRight);
				} else {
					singleError++;
				}
			}else if(remak.get(i).equals("��ѡ")) {
				if(answer.get(i).equals(answerResponse.get(i))) {
					moreRight++;
				}else {
					moreError--;
				}
			}
		}
		// ���÷���
		score = (singleRight*10)+(moreRight*10);
//		��ѡ�����
		singleScore=singleRight*10;
		//��ѡ�����
		moreScore=moreRight*10;
		//��ȷ��
		int right=singleRight+moreRight;
		//����
		int error=singleError+moreError;
		JOptionPane.showMessageDialog(null, "�����" + right + "�⣬�����" + error + "��,�ܷ���"+score);
		return score;
	}

	// ��������ģ�飬��ȡ������Ϣ
	public void createExam() {
		// ����һ�����ַ���
		String s = "";
		// ����id��ȡ������
		String sql = "select * from question where subjectId=?";
		Object[] params = { examId };
		System.out.println(examId);
		// ��question�е����ݷ���List��
		List<Question> list = (List<Question>) QueryRunner.query(sql, params,
				new BeanListResultSethandler<Question>(Question.class));
		// ������Question�е�����
		for (Question p : list) {
			String title = p.getTitle();// ��Ŀ
			String keyA = p.getKeyA();
			String keyB = p.getKeyB();
			String keyC = p.getKeyC();
			String keyD = p.getKeyD();// ѡ��������
			String keyvalue = p.getKeyvalue();// ��
			String remark = p.getRemarks();
			inserTime=p.getJoinTime();
			// ��ȡ��������Ϣ
			s = title + "\n" + keyA + "\n" + keyB + "\n" + keyC + "\n" + keyD;
//			System.out.println(s);
			// ����һ�������������Ŀ��Ϣ
			suStr.add(s);
//			System.out.println(suStr);
			// ����һ����������Ŵ�
			answer.add(keyvalue);
			System.out.println(answer);
			// ����һ�������������Ŀ����
			remak.add(remark);
			System.out.println(remak);
		}
	}

	// ���ÿ�������ϵ���Ϣ
	private void showQuestion() {
		textArea.setText("");
		textArea.append(suStr.get(p));
		System.out.println(suStr.get(p));
	}
}

//���Ե���ʱ
class ClockDispaly extends Thread {
	private JLabel leftTime;
	private int testTime;

	public ClockDispaly(JLabel lt, int time) {
		leftTime = lt;
		testTime = time * 60;
	}

	// ��ʼ����
	public void run() {
		// ����ʱ��ĸ�ʽ
		NumberFormat numberFormat = NumberFormat.getInstance();
		// ������ֵ����������������ʾ��Сλ��
		numberFormat.setMinimumIntegerDigits(2);
		// ����ʱ���֡���
		int h, m, s;
		while (testTime >= 0) {
			h = testTime / 3600;
			m = testTime % 3600 / 60;
			s = testTime % 60;
			StringBuffer strb = new StringBuffer();
			// ��ӵ�leftTime��ǩ
			strb.append(
					"ʣ�����ʱ�䣺" + numberFormat.format(h) + ":" + numberFormat.format(m) + ":" + numberFormat.format(s));
			//// ��leftTimer�Ǵ�������ʱ��label
			leftTime.setText(strb.toString());
			// ������ʱ ��ʱΪһ��
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			testTime = testTime - 1;

			if (testTime <= 0) {
				JOptionPane.showMessageDialog(null, "���Խ���");
				System.exit(0);
			}
		}
	}

}
