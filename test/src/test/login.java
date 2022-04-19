package test;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class login {
	private JFrame frmDatabaseLogin;
	public static void main(String[] args) { // Launch the application
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

	private void initialize() { // Initialize the contents of the frame.
		frmDatabaseLogin = new JFrame();
		frmDatabaseLogin.setVisible(true);
		frmDatabaseLogin.setTitle("Database Login");
		frmDatabaseLogin.getContentPane().setBackground(Color.GREEN);
		frmDatabaseLogin.setBackground(Color.PINK);
		frmDatabaseLogin.setBounds(100, 100, 370, 195);
		frmDatabaseLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabaseLogin.getContentPane().setLayout(null);
		frmDatabaseLogin.setResizable(false);
		frmDatabaseLogin.setLocationRelativeTo(null);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(10, 45, 89, 53);
		frmDatabaseLogin.getContentPane().add(lblLogin);

		tFLogin = new JTextField();
		tFLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tFLogin.setBounds(71, 65, 148, 20);
		frmDatabaseLogin.getContentPane().add(tFLogin);
		tFLogin.setColumns(10);

		JLabel lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMessage.setForeground(new Color(255, 0, 102));
		lblMessage.setBounds(10, 126, 344, 25);
		frmDatabaseLogin.getContentPane().add(lblMessage);

		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(229, 79, 103, 40);
		frmDatabaseLogin.getContentPane().add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(229, 31, 103, 37);
		frmDatabaseLogin.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tFLogin.getText();
				try {
					String query = "Select * from Login where ID =?";
					PreparedStatement pst = db.prepareStatement(query);
					pst.setString(1, tFLogin.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
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