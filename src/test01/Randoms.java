package test01;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Randoms extends JFrame {
	
	//���������ʾ
	JLabel num;//��ʾ����
	JButton start,stop; // ��ʼ��ֹͣ��ť
	JLabel rs; //��ʾ���
	JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	JPanel jp1,jp2;
	ExListener listener;
	
	public static void main(String[] args) {
		//����һ�������¼�����
		FromEx f=new FromEx();
		
		Randoms random = new Randoms(); 
		random.AListener(f);
		random.setResizable(false);
		random.setTitle("ѧ�ŵ���С����");
		random.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		random.setBounds(700, 200, 100, 200);
		random.setSize(400, 300);
		random.setVisible(true);
	}
	
	
	 
	static boolean isSotp = true; //�жϵ�ǰ״̬
	public Randoms() {
		init();
	}
		
	void init() {
		num = new JLabel("    <<\u5929\u8FD0\u4E4B\u5B50>>");
		num.setFont(new Font("����", 2, 40));
		start = new JButton("��ʼ");
		stop = new JButton("ֹͣ");
		//��ʾ����
		rs = new JLabel("0");
		rs.setBounds(164, 51, 74, 54);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		getContentPane().add(num,BorderLayout.NORTH);
		jp1.setLayout(null);
		jp1.add(rs);
		getContentPane().add(jp1,BorderLayout.CENTER);
		FlowLayout layout = new FlowLayout();//��岼��
		layout.setAlignment(FlowLayout.CENTER);
//		jp1.setLayout(layout);
//		jp2.setLayout(layout);
		//���������ӿ�ʼ ��ͣ��ť
		jp2.add(start);
		jp2.add(stop);
		//��������
		rs.setFont(new Font("����", Font.BOLD, 34));//���ý����ʽ
		rs.setForeground(Color.black);
		getContentPane().add(jp2,BorderLayout.SOUTH);
	}
	
	//��������
	void AListener(ExListener listener) {
		this.listener = listener;
		//����������  ��ӿ�ʼ ��ͣ��ť ������ʾ��ǩ  ��ʾ״̬
		listener.set(start, stop, rs, isSotp); 
		start.addActionListener(listener); //��Ӽ���
		stop.addActionListener(listener); //��Ӽ���
	}
}
