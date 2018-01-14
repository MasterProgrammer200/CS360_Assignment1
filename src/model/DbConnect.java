/** 
 * 
 * Name: 		Matthew Hunt
 * Class:		CS360-01 Software Engineering
 * Instructor: 	eProf Sedlmeyer
 * Project:		Assignment 1: Welcome to the Real World
 * Date:		01/14/18
 * Due:			01/17/18
 * 
 **/
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	///
	/// private fields
	///
	private final String DB_NAME = "dbsitecollection";
	private final String DB_USERNAME = "java";
	private final String DB_PASSWORD = "TeMp12345!";
	private final String DB_HOSTNAME = "huntmj01db.c7leo3jdf5jh.us-west-2.rds.amazonaws.com";
	private final String DB_PORT = "3306";
	
	/**
	 *
	 * getRemoteConnection() creates and returns a connection to the AWS MySQL Database.
	 * Make sure to close the connection when you are done!
	 * 
	 * @return conn : the connection to the AWS MySQL Database
	 * 
	 */
	public Connection getRemoteConnection() {
		
		///
		/// declare local variables 
		///
		Connection conn;	// holds connection to AWS MySQL Database
		String jdbcUrl;		// holds jdbc connection string

		// initalize connection object as null
		conn = null;
		// compile to connection string
		jdbcUrl = "jdbc:mysql://" + DB_HOSTNAME + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USERNAME 
				+ "&password=" + DB_PASSWORD;
		
		// Load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
		
		// Create connection to RDS DB instance
	    try {
			conn = DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    // return the connection
		return conn;
		
	}
	
}
