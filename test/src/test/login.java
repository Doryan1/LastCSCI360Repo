// Frank The Management System 
// This Project was done by Doryan B, Adam K, Brian K, Noah V, and Haley M
// The purpose of the Login GUI is to be the main menu for the system. This is where everytihng starts

package test;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class login {
	private JFrame frmDatabaseLogin;
	public static void main(String[] args) { // Launch the application
		//set up to show as soon as the program is ran
		JOptionPane.showMessageDialog(null, "THIS SOFTWARE IS NOT TO BE USED FOR UNIVERSITY MANAGEMENT PURPOSE",
				"Disclaimer", JOptionPane.INFORMATION_MESSAGE);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmDatabaseLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application; This calls the connector from the database.java
	Connection db = null;
	private JTextField tFLogin;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public login() {
		initialize();
		db = database.dbConnector(); // Calls the dbconnector connection form database.java
	}

	//simple gui creation setup 
	private void initialize() { // Initialize the contents of the frame.
		frmDatabaseLogin = new JFrame();
		frmDatabaseLogin.setVisible(true);
		frmDatabaseLogin.setTitle("FRANK - The Database Manager");
		frmDatabaseLogin.getContentPane().setBackground(Color.GREEN);
		frmDatabaseLogin.setBackground(Color.PINK);
		frmDatabaseLogin.setBounds(100, 100, 502, 363);
		frmDatabaseLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabaseLogin.getContentPane().setLayout(null);
		frmDatabaseLogin.setResizable(false);
		frmDatabaseLogin.setLocationRelativeTo(null);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(31, 207, 89, 53);
		frmDatabaseLogin.getContentPane().add(lblLogin);

		tFLogin = new JTextField();
		tFLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tFLogin.setBounds(130, 221, 148, 33);
		frmDatabaseLogin.getContentPane().add(tFLogin);
		tFLogin.setColumns(10);

		//quit is based on stopping the full application if button is pressed
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(324, 247, 103, 40);
		frmDatabaseLogin.getContentPane().add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(324, 199, 103, 37);
		frmDatabaseLogin.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO FRANK");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(123, 11, 265, 33);
		frmDatabaseLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("The Database Manager");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(163, 49, 174, 14);
		frmDatabaseLogin.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please login depending on your roll");
		lblNewLabel_2.setFont(new Font("Source Sans Pro", Font.ITALIC, 26));
		lblNewLabel_2.setBounds(31, 102, 605, 80);
		frmDatabaseLogin.getContentPane().add(lblNewLabel_2);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(81, 283, 306, 40);
		frmDatabaseLogin.getContentPane().add(lblMessage);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tFLogin.getText();
				//calls the database to see if the id put in the textbox matches
				try {
					String query = "Select * from Login where id =?";
					PreparedStatement pst = db.prepareStatement(query);
					pst.setString(1, tFLogin.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					// if it matches it will use this case statment to open up a specific gui, keyword has to be added into database if changed or it will not be recognized.
					while (rs.next()) {
						count = count + 1;
					}
					if (count == 1) {
						switch(user) {
							case "admin":
								JOptionPane.showMessageDialog(null,
										"Login successful. Now entering the admin manager database");
								admin a = new admin();
								a.ad();
								break;
							case "professor":
								JOptionPane.showMessageDialog(null,
										"Login successful. Now entering the professor database");
								Professor p = new Professor();
								p.run();
								break;
							case "student":
								JOptionPane.showMessageDialog(null, "Login successful. Now entering the student database");
								Student s = new Student();
								s.run();
								break;
							case "staff":
								JOptionPane.showMessageDialog(null, "Login successful. Now entering the staff database");
								Staff st = new Staff();
								st.run();
								break;
							case "TA":
								JOptionPane.showMessageDialog(null, "Login successful. Now entering the TA database");
								TA t = new TA();
								t.run();
								break;
						}
						frmDatabaseLogin.dispose();
					} else {
						lblMessage.setText("\"ID not recognized, please try again\"");
					}
					rs.close();
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
	}
}