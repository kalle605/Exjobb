package main.datahandler;

import java.sql.*;

public class DatabaseConnector {
	private static Connection con;

	public static Statement getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		if (con == null)
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exjobb", "root", "qwerty");

		return con.createStatement();
	}
}
