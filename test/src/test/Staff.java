package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Staff extends JFrame {
	static Connection db = null ; 
	private JPanel contentPane;
	private JTextField tFsearch;
	private JTable table;

	/**
	 * Launch the application.
	 */

			public void run() {
				db = database.dbConnector() ;
				try {
					Staff frame = new Staff();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the frame.
	 */
	public Staff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = tFsearch.getText() ;
				
				try {
					if(search.matches("^[0-9]+$")) {
					String query = "SELECT * from staff where id= " + search ; 
					PreparedStatement pst = db.prepareStatement(query) ; 
					ResultSet rs = pst.executeQuery() ; 
					table.setModel(DbUtils.resultSetToTableModel(rs)) ; 
					}
					else {
						String query = "SELECT * from staff WHERE fname LIKE '%"+search+"%'" ; 
						PreparedStatement pst = db.prepareStatement(query) ; 
						ResultSet rs = pst.executeQuery() ; 
						table.setModel(DbUtils.resultSetToTableModel(rs)) ; 
					}
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1) ; 
				}
			}
		});
		tFsearch.setBounds(22, 132, 86, 20);
		contentPane.add(tFsearch);
		tFsearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 24, 288, 226);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
