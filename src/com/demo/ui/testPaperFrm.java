package com.demo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.demo.dao.QuestionDao;
import com.demo.dao.QuestionDao1;
import com.demo.dao.impl.QuestionDao1Impl;
import com.demo.pojo.Question;
import com.demo.utils.JdbcUtil;
import com.demo.utils.StringUtils;

import lombok.Getter;
import lombok.Setter;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
@Setter
@Getter
public class testPaperFrm {
 
	private JFrame frame;
	private JTable questionTable;
	JdbcUtil dbUtil=new JdbcUtil();
	QuestionDao questionDao=new QuestionDao();
	private JTextField idText;
	private JTextField keyAText;
	private JTextField keyBText;
	private JTextField keyCText;
	private JTextField keyDText;
	private JTextField keyvalueText;
	private JTextField remarksText;
	private JTextArea titleArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testPaperFrm window = new testPaperFrm();
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
	public testPaperFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("修改/删除试题");
		frame.setBounds(100, 100, 1003, 814);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 0, 956, 356);
		frame.getContentPane().add(scrollPane);
		
		questionTable = new JTable();
		questionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//鼠标点击表格事件
				tableMousePressed(e);
			}
		});
		questionTable.setFont(new Font("宋体", Font.BOLD, 18));
		questionTable.setRowHeight(18);
		questionTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BD5\u9898\u7F16\u53F7", "\u8BD5\u9898\u9898\u76EE", "\u8BD5\u9898\u6B63\u786E\u7B54\u6848", "\u7B54\u6848A", "\u7B54\u6848B", "\u7B54\u6848C", "\u7B54\u6848D", "\u5907\u6CE8"
			}
		));
		questionTable.getColumnModel().getColumn(2).setPreferredWidth(113);
		scrollPane.setViewportView(questionTable);
		fillTable();//初始化表格
		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.setIcon(new ImageIcon(testPaperFrm.class.getResource("/images/de.png")));
		btnNewButton.addActionListener(new ActionListener() {
			//修改
			public void actionPerformed(ActionEvent e) {
				updateQuestionAction(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(169, 719, 113, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.setIcon(new ImageIcon(testPaperFrm.class.getResource("/images/dl.png")));
		//删除按钮事件
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除
				deleteAction();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(405, 719, 113, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u9000\u51FA");
		btnNewButton_2.setIcon(new ImageIcon(testPaperFrm.class.getResource("/images/out.png")));
		//退出按钮事件
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//销毁窗口
				//显示考试系统主页
				new AdminFrm().getFrame().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(640, 719, 113, 35);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FEE\u6539\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 379, 956, 327);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BD5\u9898\u7F16\u53F7:");
		lblNewLabel.setBounds(14, 35, 72, 18);
		panel.add(lblNewLabel);
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setBounds(86, 32, 86, 24);
		panel.add(idText);
		idText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BD5\u9898\u9898\u76EE\uFF1A");
		lblNewLabel_1.setBounds(14, 85, 86, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lbla = new JLabel("\u7B54\u6848A:");
		lbla.setBounds(560, 85, 72, 18);
		panel.add(lbla);
		
		keyAText = new JTextField();
		keyAText.setBounds(613, 82, 258, 24);
		panel.add(keyAText);
		keyAText.setColumns(10);
		
		JLabel lblb = new JLabel("\u7B54\u6848B:");
		lblb.setBounds(560, 116, 72, 18);
		panel.add(lblb);
		
		JLabel lblc = new JLabel("\u7B54\u6848C:");
		lblc.setBounds(560, 147, 72, 18);
		panel.add(lblc);
		
		JLabel lbld = new JLabel("\u7B54\u6848D:");
		lbld.setBounds(560, 176, 72, 18);
		panel.add(lbld);
		
		JLabel lbla_4 = new JLabel("\u6B63\u786E\u7B54\u6848\uFF1A");
		lbla_4.setBounds(538, 231, 94, 18);
		panel.add(lbla_4);
		
		JLabel lbla_5 = new JLabel("\u5907\u6CE8\uFF1A");
		lbla_5.setBounds(560, 262, 72, 18);
		panel.add(lbla_5);
		
		keyBText = new JTextField();
		keyBText.setColumns(10);
		keyBText.setBounds(613, 113, 258, 24);
		panel.add(keyBText);
		
		keyCText = new JTextField();
		keyCText.setColumns(10);
		keyCText.setBounds(613, 147, 258, 24);
		panel.add(keyCText);
		
		keyDText = new JTextField();
		keyDText.setColumns(10);
		keyDText.setBounds(613, 178, 258, 24);
		panel.add(keyDText);
		
		keyvalueText = new JTextField();
		keyvalueText.setColumns(10);
		keyvalueText.setBounds(613, 228, 258, 24);
		panel.add(keyvalueText);
		
		remarksText = new JTextField();
		remarksText.setColumns(10);
		remarksText.setBounds(613, 262, 258, 24);
		panel.add(remarksText);
		
		titleArea = new JTextArea();
		titleArea.setBounds(86, 89, 426, 197);
		panel.add(titleArea);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
		
		
	}
	/**
	 * 修改
	 */
	private void updateQuestionAction(ActionEvent e) {
		String id=idText.getText();
		String title=titleArea.getText();
		String keyvalue=keyvalueText.getText();
		String keyA=keyAText.getText();
		String keyB=keyBText.getText();
		String keyC=keyCText.getText();
		String keyD=keyDText.getText();
		String remarks=remarksText.getText();
		//判断是否为空
		if(StringUtils.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的试题");
			return;
		}
		QuestionDao1 questionDao=new QuestionDao1Impl();
		questionDao.udpate(new Question(Integer.parseInt(id),title,keyvalue,keyA,keyB,keyC,keyD,remarks));
		JOptionPane.showMessageDialog(null, "修改成功！");
		resetValue();//重置
		fillTable();//刷新 表格
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private  void tableMousePressed(MouseEvent evt) {
		//获取选中行
		int row=questionTable.getSelectedRow();
		//把获取到的id赋值给修改域的idtext
		idText.setText((String)questionTable.getValueAt(row, 0));
		//试题题目
		titleArea.setText((String)questionTable.getValueAt(row, 1));
		keyvalueText.setText((String)questionTable.getValueAt(row,2));
		keyAText.setText((String)questionTable.getValueAt(row,3));
		keyBText.setText((String)questionTable.getValueAt(row,4));
		keyCText.setText((String)questionTable.getValueAt(row,5));
		keyDText.setText((String)questionTable.getValueAt(row,6));
		remarksText.setText((String)questionTable.getValueAt(row,7));
		
	}
	//表单重置
	private void resetValue() {
		this.idText.setText("");
		this.titleArea.setText("");
		this.keyvalueText.setText("");
		this.keyAText.setText("");
		this.keyBText.setText("");
		this.keyCText.setText("");
		this.keyDText.setText("");
		this.remarksText.setText("");
	}

	//初始化表格
	private void fillTable() {
		//表格模型
		DefaultTableModel dtm=(DefaultTableModel) questionTable.getModel();
		dtm.setRowCount(0);//表格清空
		Connection conn=null;
		//JdbcUtils dbUtils=new JdbcUtils();
		try {
			conn=dbUtil.getConnection();
			ResultSet rs=questionDao.query(conn);
			//System.out.println(rs);
			//com.mysql.jdbc.JDBC42ResultSet@5e75ddd3
			while(rs.next()) {
				//设置一个集合
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("title"));
				v.add(rs.getString("keyvalue"));
				v.add(rs.getString("keyA"));
				v.add(rs.getString("keyB"));
				v.add(rs.getString("keyC"));
				v.add(rs.getString("keyD"));
				v.add(rs.getString("remarks"));
				//一行一行的加
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.close(conn);
		}
	}
	
	//删除方法
	private void deleteAction() {
		//判断id是否为空
		String idd=idText.getText();
		if(StringUtils.isEmpty(idd)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的试题");
			return;
		}
		//获取鼠标选择的列数
		int column=questionTable.getSelectedColumn();
		//获取鼠标选择的行数
		int row = questionTable.getSelectedRow();
		//获取鼠标选择改行和该列对应的值
		Object value=(Object)questionTable.getValueAt(row, column);
		//将object转为int类型
		int id= Integer.parseInt(value.toString());
		//System.out.println(id);
		//获取鼠标选中表格的行数
		int ids=questionTable.getSelectedRow();
		//表格模型
		DefaultTableModel dtm=(DefaultTableModel) questionTable.getModel();

		Connection conn=null;
		try {
			conn=dbUtil.getConnection();
			//删除数据库的内容
			//选中表格id删除
			questionDao.delete(conn,id);
			JOptionPane.showMessageDialog(null, "删除成功！");
			//删除表格一行数据
			dtm.removeRow(ids);
		}catch(Exception e2) {
			e2.printStackTrace();
			
		}finally {
			dbUtil.close(conn);
		}
	}


}
