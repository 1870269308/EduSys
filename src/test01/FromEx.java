package test01;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

//ʵ��ExListener�Ľӿ�    
public class FromEx implements ExListener {
	
	JButton start,stop;
	JLabel rs;
	
	static boolean isSotp;
	//�����̶߳���
	ExThread t;
	 
	public void set(JButton J1,JButton J2,JLabel rs,boolean isSotp){
		start=J1;
		stop=J2;
		this.rs=rs;
		this.isSotp=isSotp;
		
//		try {
//			//��rs���̰߳�  �����µ��߳�
//			t= new ExThread(rs);
//		} catch (IOException e) {
//		//	e.printStackTrace();
//		}
	}
	
	//��Ӧ�¼�
	public void actionPerformed(ActionEvent e){
		//��ȡ�����¼�����ʵ�ּ���ӳ�䡣
		Object o = e.getSource();
		if(o == start){
			//String number = num.getText();
			try {
				//��ʾ�����̰߳�
				t= new ExThread(rs);
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
			//	e1.printStackTrace();
			}
			try{
				//int n = Integer.parseInt(number);
				//�ж��߳�״̬ true 
				isSotp = true;
				//t.setint(n);
				//get�߳�����״̬true
				t.setbool(isSotp);
				//�����߳�
				t.start();
			}catch(NumberFormatException e2){
				System.err.println("��ʽ����");
			}
			
		}else if(o == stop){  //����ֹͣ��ť
			//ת̬���ֹͣ
			System.out.println("��������false");
			isSotp = false;
			t.setbool(isSotp);
			//���������� �����°�ť  �̻߳����´���һ��  �������߳����е��³�ͻ
		//	t.start();
			System.out.println("this is stop");
		}
	}
}
