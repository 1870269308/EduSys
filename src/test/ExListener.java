package test;


import java.awt.event.ActionListener;

import javax.swing.*;


//这里监听事件接口
public interface ExListener extends ActionListener{
	//按钮1  和按钮2  标签显示的地方  状态
	public void set(JButton J1,JButton J2,JLabel rs,boolean isSotp);
}