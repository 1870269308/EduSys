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

		// 返回课程表按钮
		JButton btnNewButton_2 = new JButton("返回");
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
		
		//设置课程进度按钮
		JButton btnNewButton = new JButton("修改课程进度");
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
		JFreeChart chart = ChartFactory.createBarChart3D("java课程", // 图表标题
				"java课程", // 目录轴的显示标签
				"课程进度/%", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
		);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象

		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 设置x轴标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 设置x轴每一列的文字

		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));// 设置y轴标题
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));// 设置柱状标识文字
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置整个表标题字体

		// 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

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
		dataset.addValue(rate_one, "已完成", "前端基础");
		dataset.addValue(100 - rate_one, "未完成", "前端基础");
		dataset.addValue(rate_two, "已完成", "mysql");
		dataset.addValue(100 - rate_two, "未完成", "mysql");
		dataset.addValue(rate_three, "已完成", "JAVAEE");
		dataset.addValue(100 - rate_three, "未完成", "JAVAEE");
		dataset.addValue(rate_four, "已完成", "JAVAWEB");
		dataset.addValue(100 - rate_four, "未完成", "JAVAWEB");
		dataset.addValue(rate_five, "已完成", "SSM框架");
		dataset.addValue(100 - rate_five, "未完成", "SSM框架");
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}

//返回课程页面事件监听
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
//修改课程进度
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