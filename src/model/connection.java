package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Daniel Lopes
 *
 *Class created to acess the database. This class therefore can be instantiated in order to access the database.
 */
public class connection {

	Statement stmt;

	public connection() {
		try {
			// Load the database driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String dbServer = "jdbc:mysql://apontejaj.com:3306/daniel?";
			String user = "daniel";
			String password = "Pass1234!";
			

			// Get a connection to the database
			Connection conn = DriverManager.getConnection(dbServer, user, password);

			// Get a statement from the connection
			stmt = conn.createStatement();

		} catch (SQLException se) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	// this method is used to set queries, it does not require any return as confirmation.
	public ResultSet executeQuery(String query) {

		try {
			ResultSet rs = stmt.executeQuery(query);

			return rs;
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);

			return null;
		}

	}

	//this method on the other hand, executes the Query and returns weather or not the object has been inserted (true/false)
	public boolean ExecuteSet(String query) {
		
		boolean result = false;
		try {
			stmt.execute(query);
			
			result = true;
		} catch (SQLException ex) {
			Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);

		}
		return result;
	}
}