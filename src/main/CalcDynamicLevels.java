package main;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import main.datahandler.DatabaseConnector;
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
		double co2th = 0.0;
		double soundth = 0.0;
		double movementth = 1;
		double lightth = 200.0;

		try {
			Statement dc = DatabaseConnector.getConnection();
			ResultSet rs = dc.executeQuery("SELECT * FROM idlevalue");
			while (rs.next()) {
				co2th = rs.getDouble("co2");
				soundth = rs.getDouble("sound");
				lightth = rs.getDouble("light");
			}
			dc.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		values.add(soundth);
		values.add(movementth);
		values.add(lightth);
		values.add(co2th);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestFrame(values);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void measure(List<String> v) {
		frame.updatePeople(Integer.parseInt(v.get(v.size() - 1)));
		int pirValue = 1;
		int lightValue = 2;
		SimpleDateFormat d = new SimpleDateFormat("HH");
		String date = d.format(new Date());
		boolean hasChanged = false;
		if (Double.parseDouble(v.get(pirValue)) == 0
				&& Double.parseDouble(v.get(lightValue)) < 100) {
			// if (((System.currentTimeMillis() - time) / 3600000) >= 1
			if (((System.currentTimeMillis() - time) / 1) >= 3
					&& (Integer.parseInt(date) > 12 || Integer.parseInt(date) < 5)) {
				for (int i = 0; i < values.size(); i++) {
					Double temp = Double.parseDouble(v.get(i));
					if (values.get(i) < temp * 1.10 && i != 1 && i != 2) {
						values.set(i, temp * 1.10);
						integrator.get(LineChart.NAMES[i]).setTreshHold(
								temp * 1.10);
						hasChanged = true;
					}
				}
				frame.updateValues(values);

			}
		} else
			time = System.currentTimeMillis();
		if (hasChanged) {
			try {
				System.out.println("Försöker lägga in i DB");
				DatabaseConnector.getConnection()
						.execute(
								"UPDATE idlevalue SET co2=" + values.get(3)
										+ ", sound = " + values.get(0)
										+ "WHERE ID = 1");
				System.out.println("Lyckades!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
