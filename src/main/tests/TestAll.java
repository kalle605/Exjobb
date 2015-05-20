package main.tests;

import java.sql.SQLException;
import java.util.Random;

import main.datahandler.AddData;
import main.datahandler.LineChart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;

public class TestAll {
	private XYSeriesCollection dataset;
	private XYSeriesCollection dataset2;
	private AddData data;
	Random r;

	@Before
	public void initialize() {
		dataset = createXYSeriesCollection();
		dataset2 = createXYSeriesCollection1();
		LineChart chart = new LineChart("Mätningsprogram", "Measurements",
				dataset, dataset2);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
		data = new AddData(dataset, dataset2);
		r = new Random();
	}

	@Test
	public void test() {
		String s1 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 300
				+ ";" + "1;";
		String s2 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 400
				+ ";" + "1;";
		String s5 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 405
				+ ";" + "1;";
		String s3 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 250
				+ ";" + "1;";
		String s4 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 100
				+ ";" + "1;";
		String s6 = (r.nextInt(200) + 100) + ";" + 1 + ";" + 100 + ";" + 95
				+ ";" + "1;";
		try {
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data.addData(s1);
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data.addData(s2);
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data.addData(s5);
			data.addData(s3);
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data.addData(s4);
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data.addData(s6);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private XYSeriesCollection createXYSeriesCollection1() {
		XYSeriesCollection temp = new XYSeriesCollection();
		temp.addSeries(new XYSeries("Detected Presence"));
		return temp;
	}

	private XYSeriesCollection createXYSeriesCollection() {
		XYSeriesCollection temp = new XYSeriesCollection();
		for (int i = 0; i < LineChart.NAMES.length - 1; i++)
			temp.addSeries(new XYSeries(LineChart.NAMES[i]));
		return temp;
	}
}
