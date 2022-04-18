package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Professor extends JFrame {
	static Connection db = null ; 
	private JPanel contentPane;
	private JTable table;
	private JTable gTable;
	private JTextField gText;
	private JTextField tfSearch;

	/**
	 * Launch the application.
	 */

			public void run() {
				db = database.dbConnector() ;
				try {
					Professor frame = new Professor();
					frame.setVisible(true);
					frame.setSize(900,602);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	/**
	 * Create the frame.
	 */
	public Professor() {
		setTitle("Professor");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(176, 224, 230));
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel(); // professor info pane
		panel.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Professors", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 57, 409, 165);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = tfSearch.getText() ;
				
				try {
					if(search.matches("^[0-9]+$")) {
					String query = "SELECT * from Professor where id= " + search ; 
					PreparedStatement pst = db.prepareStatement(query) ; 
					ResultSet rs = pst.executeQuery() ; 
					table.setModel(DbUtils.resultSetToTableModel(rs)) ; 
					}
					
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1) ; 
				}
			}
		});
		tfSearch.setBounds(10, 19, 130, 26);
		panel.add(tfSearch);
		tfSearch.setColumns(10);
		
		JPanel panel_2 = new JPanel(); // grades table
		panel_2.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Grades", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton bAdd = new JButton("Add"); // add grade button
		bAdd.setBounds(10, 11, 89, 23);
		panel_2.add(bAdd);
		
		JButton bMod = new JButton("Modify"); // modify grade button
		
		bMod.setBounds(10, 45, 89, 23);
		panel_2.add(bMod);
		
		JButton bDel = new JButton("Delete"); // delete grade button
		bDel.setBounds(10, 79, 89, 23);
		panel_2.add(bDel);
		
		JButton bUp = new JButton("Update"); // update table button
		bUp.setBounds(10, 113, 89, 23);
		panel_2.add(bUp);
		
		gTable = new JTable(); // grade table
		gTable.setBounds(119, 11, 335, 248); 
		panel_2.add(gTable);
		
		JLabel GLabel = new JLabel("Grade:");
		GLabel.setBounds(10, 161, 46, 14);
		panel_2.add(GLabel);
		
		gText = new JTextField();
		gText.setBounds(13, 186, 86, 20);
		panel_2.add(gText);
		gText.setColumns(10);
		
	}
}
