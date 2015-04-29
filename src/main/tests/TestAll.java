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

		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(1000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			String s = (r.nextInt() % 200 + 200) + ";" + 0 + ";" + 0 + ";"
					+ (r.nextInt() % 200 + 200) + ";"
					+ (r.nextInt() % 200 + 200) + ";"
					+ (r.nextInt() % 200 + 200) + ";";
			try {
				data.addData(s);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (true) {
			try {
				Thread.sleep(10000); // 1000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			String s = (r.nextInt(300) + 250) + ";" + 500 + ";"
					+ (r.nextInt(1)) + ";" + (r.nextInt(300) + 250) + ";"
					+ (r.nextInt(300) + 250) + ";" + (r.nextInt(300) + 250)
					+ ";";
			try {
				data.addData(s);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
