package test;

import javax.swing.JFrame;

public class Menu {
	


	public static void main(String[] args) { //������
		
		Randoms n = new Randoms();
		
		//����һ�������¼�����
		FromEx f=new FromEx();
	
		n.AListener(f);
		n.setResizable(false);
		n.setTitle("ѧ�ŵ���С����");
		n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		n.setBounds(700, 200, 100, 200);
		n.setSize(400, 300);
		n.setVisible(true);
	}
}
