package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.demo.Index;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Randoms extends JFrame {
	
	//���������ʾ
	JLabel num;//��ʾ����
	JButton start,stop; // ��ʼ��ֹͣ��ť
	JLabel rs; //��ʾ���
	
	JPanel jp1,jp2;
	ExListener listener;
	Randoms random; 
	 
	
	public Randoms getRandom() {
		return random;
	}

	public void setRandom(Randoms random) {
		this.random = random;
	}

	static boolean isSotp = true; //�жϵ�ǰ״̬
	private JButton btnNewButton;
	public Randoms() {
		init();
	}
		
	void init() {
		num = new JLabel("<<��ľ�������>>");
		num.setFont(new Font("����", 2, 40));
		start = new JButton("��ʼ");
		stop = new JButton("ֹͣ");
		//��ʾ����
		rs = new JLabel("0");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		getContentPane().add(num,BorderLayout.NORTH);
		jp1.add(rs);
		getContentPane().add(jp1,BorderLayout.CENTER);
		FlowLayout layout = new FlowLayout();//��岼��
		layout.setAlignment(FlowLayout.CENTER);
//		jp1.setLayout(layout);
//		jp2.setLayout(layout);
		//����������ӿ�ʼ ��ͣ��ť
		jp2.add(start);
		jp2.add(stop);
		//��������
		rs.setFont(new Font("����", 1, 30));//���ý����ʽ
		rs.setForeground(Color.RED);
		getContentPane().add(jp2,BorderLayout.SOUTH);
		
		btnNewButton = new JButton("\u8FD4\u56DE\u4E3B\u9875\u9762");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Index index = new Index(); 
				index.getFrame().setVisible(true);
				
				random.setVisible(false);
				
			}
		});
		
		jp2.add(btnNewButton);
	}
	
	//��������
	void AListener(ExListener listener) {
		this.listener = listener;
		//����������  ���ӿ�ʼ ��ͣ��ť ������ʾ��ǩ  ��ʾ״̬
		listener.set(start, stop, rs, isSotp); 
		start.addActionListener(listener); //���Ӽ���
		stop.addActionListener(listener); //���Ӽ���
	}
}