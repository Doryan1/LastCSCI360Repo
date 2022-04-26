// Frank The Management System 
// This Project was done by Doryan B, Adam K, Brian K, Noah V, and Haley M
// The purpose of the staff gui is to allow staff to search their data based on their ID. They arent allowed to modify any data so they can only search like the students.
package test;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;

public class Staff extends JFrame {
	private static final long serialVersionUID = 5884631419523922771L;
	private JPanel contentPane;
	private JTextField tFsearch;
	private JTable table;
	static Connection db = null;
	private JLabel lblNewLabel_1;
	private JButton btnLogOut;

	public void run() { //Launch the application
		db = database.dbConnector();
		try {
			Staff frame = new Staff();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Staff() { //Create the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String search = tFsearch.getText();
				try {
					//this if statement is based on numbers only. It will not accept letters
					if (search.matches("^[0-9]+$")) {
						String query = "SELECT * from staff where id= " + search;
						PreparedStatement pst = db.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		tFsearch.setBounds(10, 127, 117, 20);
		contentPane.add(tFsearch);
		tFsearch.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 24, 557, 226);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(19, 214, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Enter Staff ID Here");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 107, 124, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Hi Staff, Please Enter Your ID in the TextBox Below");
		lblNewLabel_1.setBounds(146, 11, 435, 14);
		contentPane.add(lblNewLabel_1);
		
		btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose() ; 
				new login() ; 
			}
		});
		btnLogOut.setBounds(19, 180, 89, 23);
		contentPane.add(btnLogOut);
	}
}
