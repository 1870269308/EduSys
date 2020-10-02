package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.demo.dao.QuestionDao;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class updateQuestionFrm {

	private JFrame frame;
	private JTextField idText;
	private JTextField AText;
	private JTextField BText;
	private JTextField CText;
	private JTextField DText;
	private JTextField correctText;
	private JTextField remarksText;
	//获取要修改的试题id
	public int id;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateQuestionFrm window = new updateQuestionFrm();
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
	public updateQuestionFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 718, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体居中
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BD5\u9898\u7F16\u53F7");
		lblNewLabel.setBounds(26, 13, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BD5\u9898\u9898\u76EE");
		lblNewLabel_1.setBounds(26, 55, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lbla = new JLabel("\u7B54\u6848A");
		lbla.setBounds(427, 55, 72, 18);
		frame.getContentPane().add(lbla);
		
		JLabel lblb = new JLabel("\u7B54\u6848B");
		lblb.setBounds(427, 119, 72, 18);
		frame.getContentPane().add(lblb);
		
		JLabel lblc = new JLabel("\u7B54\u6848C");
		lblc.setBounds(427, 195, 72, 18);
		frame.getContentPane().add(lblc);
		
		JLabel lbld = new JLabel("\u7B54\u6848D");
		lbld.setBounds(427, 258, 72, 18);
		frame.getContentPane().add(lbld);
		
		JLabel lblzheng = new JLabel("\u6B63\u786E\u7B54\u6848");
		lblzheng.setBounds(192, 334, 72, 18);
		frame.getContentPane().add(lblzheng);
		
		JLabel lblNewLabel_7 = new JLabel("\u5355\u9009/\u591A\u9009");
		lblNewLabel_7.setBounds(26, 334, 72, 18);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(487, 321, 156, 41);
		frame.getContentPane().add(btnNewButton);
		
		idText = new JTextField();
		idText.setBounds(97, 10, 86, 24);
		frame.getContentPane().add(idText);
		idText.setColumns(10);
		
		JTextArea titleArea = new JTextArea();
		titleArea.setBounds(97, 59, 279, 217);
		frame.getContentPane().add(titleArea);
		
		AText = new JTextField();
		AText.setColumns(10);
		AText.setBounds(487, 52, 172, 24);
		frame.getContentPane().add(AText);
		
		BText = new JTextField();
		BText.setColumns(10);
		BText.setBounds(487, 116, 172, 24);
		frame.getContentPane().add(BText);
		
		CText = new JTextField();
		CText.setColumns(10);
		CText.setBounds(487, 192, 172, 24);
		frame.getContentPane().add(CText);
		
		DText = new JTextField();
		DText.setColumns(10);
		DText.setBounds(487, 255, 172, 24);
		frame.getContentPane().add(DText);
		
		correctText = new JTextField();
		correctText.setColumns(10);
		correctText.setBounds(264, 331, 172, 24);
		frame.getContentPane().add(correctText);
		
		remarksText = new JTextField();
		remarksText.setColumns(10);
		remarksText.setBounds(97, 331, 86, 24);
		frame.getContentPane().add(remarksText);
		fillUpdateQuestion();
	}
	
	
	//初始化要修改的试题信息
	private void fillUpdateQuestion() {
//		testPaperFrm tpf=new testPaperFrm();
		//String ids=String.valueOf(id);
//		JdbcUtil dbUtil=new JdbcUtil();
//		QuestionDao questionDao=new QuestionDao();
//		Connection conn=null;
//		try {
//			conn=dbUtil.getConnection();
//			ResultSet rs=questionDao.update(conn);
			//idText.setText(rs.getString("id"));
//			String ids=String.valueOf(id);
//			idText.setText(ids);
//		}catch(Exception e) {
//			
//			
//		}finally {
//			
//		}
		
		
	}
	public updateQuestionFrm(int id) {
		this.id = id;
		this.frame=frame;
	}


}
