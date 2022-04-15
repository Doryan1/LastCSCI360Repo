package test;

import java.sql.* ; 
 import javax.swing.* ; 

public class database {
	Connection db = null ; 
	public static Connection dbConnector() 
	{ 
		try {
			Class.forName("org.sqlite.JDBC") ; 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:project.db") ;
			JOptionPane.showMessageDialog(null, "THIS SOFTWARE IS NOT TO BE USED FOR UNIVERSITY MANAGEMENT PURPOSE");
			JOptionPane.showMessageDialog(null, "Database Connection Successful");
			return conn ; 
		}catch (Exception e)  {
			JOptionPane.showMessageDialog(null, "Database Connection Failed");
			return null ; 
		}
	}

}
