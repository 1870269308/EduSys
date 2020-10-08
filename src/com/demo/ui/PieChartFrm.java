package com.demo.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.demo.dao.ScoreDao;
import com.demo.dao.impl.ScoreDaoImpl;
import com.demo.utils.JdbcUtil;

public class PieChartFrm {
	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PieChartFrm window = new PieChartFrm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public PieChartFrm() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("�༶�ɼ���״ͼ");
		frame.setLayout(new GridLayout(1, 1, 10, 10));
		// frame.add(new BarChart().getChartPanel()); //�������ͼ
		// frame.add(new BarChart1().getChartPanel()); //�������ͼ����һ��Ч��
		frame.add(new PieChart().getChartPanel()); // ��ӱ�״ͼ
		// frame.add(new TimeSeriesChart().getChartPanel()); //�������ͼ
		frame.setBounds(50, 50, 800, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}

}

class PieChart {
	ChartPanel frame1;

	public PieChart() {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("�༶����ͼ", data, true, false, false);
		// ���ðٷֱ�
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// ���һ��DecimalFormat������Ҫ������С������
		NumberFormat nf = NumberFormat.getNumberInstance();// ���һ��NumberFormat����
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// ���StandardPieSectionLabelGenerator����
		pieplot.setLabelGenerator(sp1);// ���ñ�ͼ��ʾ�ٷֱ�

		// û�����ݵ�ʱ����ʾ������
		pieplot.setNoDataMessage("��������ʾ");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// ���ò���ʾ��ֵ
		pieplot.setIgnoreZeroValues(true);// ���ò���ʾ��ֵ
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));// ���ñ�������
		PiePlot piePlot = (PiePlot) chart.getPlot();// ��ȡͼ���������
		piePlot.setLabelFont(new Font("����", Font.BOLD, 10));// �������
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));
	}

	private static DefaultPieDataset getDataSet() {
		int rate_one = 0;// 60������
		int rate_two = 0;// 60~70
		int rate_three = 0;// 70~80
		int rate_four = 0;// 80~90
		int rate_five = 0;// 90����
		JdbcUtil dbUtil = new JdbcUtil();
		ScoreDao sd = new ScoreDaoImpl();
		Connection conn = null;
		try {
			conn = dbUtil.getConnection();
			ResultSet rs = sd.getDatas(conn);
			while (rs.next()) {
				if (rs.getInt("sumScore") > 0 && rs.getInt("sumScore") < 60) {
					rate_one++;
				} else if (rs.getInt("sumScore") < 70) {
					rate_two++;
				} else if (rs.getInt("sumScore") < 80) {
					rate_three++;
				} else if (rs.getInt("sumScore") < 90) {
					rate_four++;
				} else if (rs.getInt("sumScore") < 100) {
					rate_five++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(conn);
		}

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("60����:", rate_one);
		dataset.setValue("60~70:", rate_two);
		dataset.setValue("70~80:", rate_three);
		dataset.setValue("80~90:", rate_four);
		dataset.setValue("90����:", rate_five);
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;

	}
}