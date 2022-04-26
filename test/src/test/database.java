// Frank The Management System 
// This Project was done by Doryan B, Adam K, Brian K, Noah V, and Haley M
// The purpose of the database is to connect java to sqlite. This is where the other classes connect to in order to get info back from the database. 

package test;
import java.sql.*;
import javax.swing.*;


//database connectivity done by Brian K and Doryan B in the other main classes
public class database {
	Connection db = null;
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:project.db");
			return conn;
		} catch (Exception e) {
			//if the database is not connected or sqlite is not downloaded, the application will let you know, and will not work with any inputs you use in the login 
			JOptionPane.showMessageDialog(null, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}