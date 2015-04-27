package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;

import main.datahandler.LineChart;
import main.filter.Filter;

public class CalcDynamicLevels {
	private long time;
	private TestFrame frame;
	private ArrayList<Double> values;
	private HashMap<String, Filter> integrator;

	public CalcDynamicLevels(HashMap<String, Filter> integrator) {
		this.integrator = integrator;
		time = System.currentTimeMillis();
		values = new ArrayList<Double>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void measure(String arr[]) {
		if (Double.parseDouble(arr[1]) == 0 && Double.parseDouble(arr[3]) > 20) {
			if ((System.currentTimeMillis() - time) / 60000 >= 5) {
				for (int i = 0; i < arr.length; i++) {
					Double temp = Double.parseDouble(arr[i]);
					if (values.get(i) < temp)
						values.set(i, temp);
					integrator.get(LineChart.NAMES[i]).setTreshHold(temp);
				}
				frame.updateValues(arr);

			}
		} else
			for (int i = 0; i < values.size(); i++) {
				values.set(i, 0.0);
				time = System.currentTimeMillis();
			}

	}
}
