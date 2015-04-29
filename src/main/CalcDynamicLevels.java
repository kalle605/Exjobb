package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		for (int i = 0; i < LineChart.NAMES.length - 1; i++)
			values.add(0.0);
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

	public void measure(List<String> v) {
		System.out.println(v);
		int pirValue = 1;
		int lightValue = 2;
		if (Double.parseDouble(v.get(pirValue)) == 0
				&& Double.parseDouble(v.get(lightValue)) < 50) {
			if (((System.currentTimeMillis() - time) / 10000) >= 1) {
				for (int i = 0; i < v.size(); i++) {
					Double temp = Double.parseDouble(v.get(i));
					if (values.get(i) < temp && i != 1 && i != 2) {
						values.set(i, temp);

						integrator.get(LineChart.NAMES[i]).setTreshHold(temp);
					}
				}
				frame.updateValues(values);
			}
		} else
			for (int i = 0; i < values.size(); i++) {
				values.set(i, 0.0);
				time = System.currentTimeMillis();
			}

	}
}
