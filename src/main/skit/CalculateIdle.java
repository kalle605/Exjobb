package main.skit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.datahandler.DatabaseConnector;

public class CalculateIdle {
	private int irCounter;
	private double soundMaxValue;
	private double lightValue;
	private double temp;
	private String lastSelectedIndex;

	public CalculateIdle() {
		irCounter = 0;
		soundMaxValue = 0;
		lightValue = 0;
		temp = 0;
		lastSelectedIndex = "LAST_INSERT_ID()";
		try {
			Statement myCon = DatabaseConnector.getConnection();
			ResultSet rs = myCon
					.executeQuery("Select ID, amplitud, celsius FROM times NATURAL JOIN"
							+ " idlevalue NATURAL JOIN sound NATURAL JOIN temp "
							+ "NATURAL JOIN idlevalue");

			if (rs.next()) {
				soundMaxValue = Double.parseDouble(rs.getString("amplitud"));
				temp = Double.parseDouble(rs.getString("celsius"));
				lastSelectedIndex = rs.getString("ID");
			}
			myCon.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		irCounter = 0;
	}

	public void loop(double soundMaxValue, double lightValue, double temp) {
		soundMaxValue = Math.max(soundMaxValue, this.soundMaxValue);
		lightValue = Math.min(lightValue, this.lightValue);
		temp = Math.max(temp, this.temp);
		if (irCounter > 30 && lightValue < 5) {
			try {
				Statement myCon = DatabaseConnector.getConnection();
				myCon.execute("Insert VALUES(" + lastSelectedIndex
						+ ") INTO idlevalue");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		irCounter++;
	}

}
