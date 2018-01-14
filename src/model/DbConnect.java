package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

	public static Connection getRemoteConnection() {

		// Read RDS connection information from the environment
		String dbName = "dbsitecollection";
		String userName = "huntmj01";
		String password = "IpFw12345!";
		String hostname = "huntmj01db.c7leo3jdf5jh.us-west-2.rds.amazonaws.com";
		String port = "3306";

		String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password="
				+ password;

		// Load the JDBC driver
		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
		
		Connection conn = null;
		
		// Create connection to RDS DB instance
	    try {
			conn = DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return conn;
		
	}
	
}
