package eu.unicredit.fiipractic.integration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String url = "jdbc:mysql://localhost:3306/";
	private static String dbName = "fii_practic";
	private static String user = "root";
	private static String password = "";
	private static Connection con;

	private ConnectionFactory() {

	}

	public static Connection getConnection() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		if (con == null) {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url + dbName, user, password);
		}
		return con;
	}
}
