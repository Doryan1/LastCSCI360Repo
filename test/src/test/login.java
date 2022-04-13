package test;

import java.awt.EventQueue;

import java.sql.* ; 
import javax.swing.* ;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

public class login {

	private JFrame frmDatabaseLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	/**
	 * Create the application.
	 */
	//this calls the connector from the database.java
	Connection db = null ; 
	private JTextField tFLogin;
	
	PreparedStatement pst = null;
	ResultSet rs = null ; 
	
	@SuppressWarnings("unused")
	public login() {
		initialize();
		//calls the dbconnector connection form database.java
		db = database.dbConnector() ; 
		PreparedStatement pst = null;
		ResultSet rs = null ; 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		
		JLabel lblLogin = new JLabel("login");
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
		
		JButton btnQuit = new JButton("quit");
		btnQuit.setBounds(229, 79, 103, 40);
		frmDatabaseLogin.getContentPane().add(btnQuit);
		btnQuit.addActionListener(new exit());
		
		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(229, 31, 103, 37);
		frmDatabaseLogin.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tFLogin.getText() ;
				try {
					String query = "Select * from Login where ID =?";
					PreparedStatement pst = db.prepareStatement(query) ; 
					pst.setString(1,tFLogin.getText()) ; 
					
					ResultSet rs  = pst.executeQuery() ;
					int count = 0 ; 
					while (rs.next()) {
						count = count + 1 ; 
					}
					if (count ==1){
							if (user.equals("admin")) {
								JOptionPane.showMessageDialog(null,"correct") ;
								admin a = new admin() ; 
								a.ad(); 
								
						}
							else if  (user.equals("prof")) {
								JOptionPane.showMessageDialog(null,"correct 2") ;
					}
					}
						else {
							lblMessage.setText("\"ID NOT Recognized, Please Try Again\"");
						}
							rs.close() ; 
							pst.close() ; 
							
					}catch(Exception e){
					JOptionPane.showMessageDialog(null, e) ;
				}
			}
		}) ;
}
}