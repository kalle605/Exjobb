package main.skit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.datahandler.DatabaseConnector;

public class Evaluation {
	public Integer calculatePeople(Date date) throws SQLException,
			ClassNotFoundException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = sdf.format(new Date(date.getTime() + 1000));
		String time2 = sdf.format(new Date(date.getTime() - 360000));

		Statement s = DatabaseConnector.getConnection();
		ResultSet rs = s.executeQuery("SELECT max(diditmove) * max(amplitud)"
				+ " FROM times NATURAL JOIN movement NATURAL"
				+ " JOIN sound WHERE time between " + "'" + time2 + "'"
				+ " AND  " + "'" + time1 + "'");
		while (rs.next())
			return rs.getString(1) != null
					&& Double.parseDouble(rs.getString(1)) > 1 ? 1 : 0;
		return 0;
	}
}
