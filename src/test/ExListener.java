package test;


import java.awt.event.ActionListener;

import javax.swing.*;


//��������¼��ӿ�
public interface ExListener extends ActionListener{
	//��ť1  �Ͱ�ť2  ��ǩ��ʾ�ĵط�  ״̬
	public void set(JButton J1,JButton J2,JLabel rs,boolean isSotp);
}