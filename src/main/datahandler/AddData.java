package main.datahandler;

import java.sql.SQLException;
import java.util.Arrays;

import org.jfree.data.xy.XYSeriesCollection;

public class AddData {
	private int nbrOfppl = 0;
	private XYSeriesCollection dataset;
	private XYSeriesCollection dataset2;
	private InputOutput IO;

	public AddData(XYSeriesCollection dataset, XYSeriesCollection dataset2) {
		this.dataset = dataset;
		this.dataset2 = dataset2;
		IO = new InputOutput();
	}

	public void addData(String inputLine) throws SQLException,
			ClassNotFoundException {
		String[] values = inputLine.split(";");

		// java.sql.Statement myCon = DatabaseConnector.getConnection();
		// ResultSet rs = myCon.executeQuery("SELECT now();");
		// String time2 = "";

		long time = System.currentTimeMillis();
		// while (rs.next())
		// time2 = rs.getString(1);
		for (int i = 0; i < LineChart.NAMES.length - 1; i++)
			addValues(values[i], time, i);

		// myCon.executeUpdate("INSERT INTO `exjobb`.`times` (`time`) VALUES ('"
		// + time2 + "');");
		// for (int i = 0; i < values.length; i++) {
		// myCon.executeUpdate("INSERT INTO `exjobb`.`" + LineChart.NAMES[i]
		// + "` VALUES (LAST_INSERT_ID()," + values[i] + ");");
		// }

		// myCon.close();
		IO.input(Arrays.asList(values));
		dataset2.getSeries("Detected Presence").add(time, IO.getOutput());
	}

	private void addValues(String value, long time, int i) {
		dataset.getSeries(LineChart.NAMES[i]).add(
				time,
				LineChart.NAMES[i].equals("people") ? nbrOfppl = Math.max(0,
						nbrOfppl + Integer.parseInt(value)) : Double
						.parseDouble(value));
	}
}
