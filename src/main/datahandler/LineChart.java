package main.datahandler;

import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame {
	private static final long serialVersionUID = -8186205081849264360L;
	public static final String[] NAMES = { "sound", "movement",
			// "people",
			"light", "piezo", "carbon dioxide", "piezo golv",
			"Detected Presence" };

	public LineChart(String applicationTitle, String chartTitle,
			XYSeriesCollection dataset, XYSeriesCollection dataset2) {
		super(applicationTitle);

		JFreeChart lineChart = ChartFactory.createTimeSeriesChart(chartTitle,
				"Time", "Serie", dataset);

		XYPlot plot = (XYPlot) lineChart.getPlot();
		final NumberAxis axis2 = new NumberAxis("Detected Presence");
		axis2.setAutoRange(false);
		plot.setRangeAxis(1, axis2);
		plot.setDataset(1, dataset2);
		plot.mapDatasetToRangeAxis(1, 1);
		plot.setBackgroundPaint(Color.gray);
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("MMM-dd HH:mm"));

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		chartPanel.setRangeZoomable(true);
		chartPanel.setDomainZoomable(true);
		chartPanel.setMouseZoomable(true);
		setContentPane(chartPanel);

	}
}