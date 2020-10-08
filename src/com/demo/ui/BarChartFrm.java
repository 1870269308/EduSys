package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.demo.dao.CourScheDao;
import com.demo.dao.impl.CourScheDaoImpl;
import com.demo.pojo.UserManage;
import com.demo.utils.JdbcUtil;

import lombok.Getter;
@Getter
public class BarChartFrm {

	private JFrame frame;
	private JTextField textField;
	private UserManage UserMessage=new UserManage();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarChartFrm window = new BarChartFrm();
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
	public BarChartFrm() {
		initialize();
	}
	public BarChartFrm(UserManage UserMessage) {
		this.UserMessage=UserMessage;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 619, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		//JTextField textField = new JTextField();
		ChartPanel ChartPanel = new BarChart(UserMessage).getChartPanel();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridheight = 9;
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(ChartPanel, gbc_textField);
		//textField.setColumns(10);

		// ���ؿγ̱�ť
		JButton btnNewButton_2 = new JButton("����");
		btnNewButton_2.setSize(100, 100);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.gridheight = 3;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 1, 7);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 9;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		listenerReturn lc = new listenerReturn(frame);
		btnNewButton_2.addActionListener(lc);
		
		//���ÿγ̽��Ȱ�ť
		JButton btnNewButton = new JButton("�޸Ŀγ̽���");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 3;
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		listenerRate lr = new listenerRate(frame,UserMessage);
		btnNewButton.addActionListener(lr);
	}

}

class BarChart {
	ChartPanel frame1;
	private UserManage UserMessage=new UserManage();
	public BarChart(UserManage UserMessage) {
		this.UserMessage=UserMessage;
		CategoryDataset dataset = getDataSet(UserMessage);
		JFreeChart chart = ChartFactory.createBarChart3D("java�γ�", // ͼ�����
				"java�γ�", // Ŀ¼�����ʾ��ǩ
				"�γ̽���/%", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
		);

		// �����￪ʼ
		CategoryPlot plot = chart.getCategoryPlot();// ��ȡͼ���������

		CategoryAxis domainAxis = plot.getDomainAxis(); // ˮƽ�ײ��б�
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 14)); // ����x�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 12)); // ����x��ÿһ�е�����

		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(new Font("����", Font.BOLD, 15));// ����y�����
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));// ������״��ʶ����
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// �����������������

		// �������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������

		frame1 = new ChartPanel(chart, true); // ����Ҳ������chartFrame,����ֱ������һ��������Frame

	}

	private static CategoryDataset getDataSet(UserManage UserMessage) {
		JdbcUtil dbUtil = new JdbcUtil();
		CourScheDao csd = new CourScheDaoImpl();
		Vector rates = new Vector();
		String foreign_id=String.valueOf(UserMessage.getId());
		Connection conn=null;
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = csd.getDatas(conn, foreign_id);			
			while (rs.next()) {				
				rates.add(rs.getString("rate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.close(conn);
		}
		int rate_one=Integer.parseInt(rates.get(0).toString());
		int rate_two=Integer.parseInt(rates.get(1).toString());
		int rate_three=Integer.parseInt(rates.get(2).toString());
		int rate_four=Integer.parseInt(rates.get(3).toString());
		int rate_five=Integer.parseInt(rates.get(4).toString());
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(rate_one, "�����", "ǰ�˻���");
		dataset.addValue(100 - rate_one, "δ���", "ǰ�˻���");
		dataset.addValue(rate_two, "�����", "mysql");
		dataset.addValue(100 - rate_two, "δ���", "mysql");
		dataset.addValue(rate_three, "�����", "JAVAEE");
		dataset.addValue(100 - rate_three, "δ���", "JAVAEE");
		dataset.addValue(rate_four, "�����", "JAVAWEB");
		dataset.addValue(100 - rate_four, "δ���", "JAVAWEB");
		dataset.addValue(rate_five, "�����", "SSM���");
		dataset.addValue(100 - rate_five, "δ���", "SSM���");
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}

//���ؿγ�ҳ���¼�����
class listenerReturn implements ActionListener {
	private JFrame frame;
	public listenerReturn(JFrame frame) {
		this.frame = frame;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CourseFrm cou = new CourseFrm();
		frame.setVisible(false);
		cou.getFrame().setVisible(true);

	}
}
//�޸Ŀγ̽���
class listenerRate implements ActionListener{
	private JFrame frame;
	private UserManage UserMessage;
	public listenerRate(JFrame frame,UserManage UserMessage) {
		this.frame = frame;
		this.UserMessage=UserMessage;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CouScheUpdate cou = new CouScheUpdate(UserMessage);
		frame.setVisible(false);
		cou.getFrame().setVisible(true);		
	}
	
}