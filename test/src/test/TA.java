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

public class TA extends JFrame {
	static Connection db = null ; 
	private JPanel contentPane;
	private JTable table;
	private static JTable tablestudent;
	private JTextField tfSearch;
	private JTextField txtID;
	private JTextField txtCourse;
	private JTextField txtSemester;
	private JTextField txtNumber;

	/**
	 * Launch the application.
	 */

			public void run() {
				db = database.dbConnector() ;
				try {
					TA frame = new TA();
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
	public TA() {
		setTitle("TA Database");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 445);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(176, 224, 230));
		tabbedPane.setBounds(0, 0, 641, 399);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel(); // professor info pane
		panel.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Ta's", null, panel, null);
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
					String query = "SELECT * from TA where id= " + search ; 
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
		
		JButton btnAdd = new JButton("Add"); // add grade button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(10, 191, 89, 23);
		panel_2.add(btnAdd);
		
		JButton btnMod = new JButton("Modify"); // modify grade button
		btnMod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
    	            String value1=txtID.getText();
    	            String value2=txtCourse.getText();
    	            String value3=txtSemester.getText();
    	            String value4=txtNumber.getText();
    	            
    	            String query = "update Student set id='"+value1+"',department='"+value2+"',fname = '"+value3+"',lname='"+value4+"'where id='"+value1+"' " ;
    	            PreparedStatement pst = db.prepareStatement(query) ; 
    	            pst.execute(); 
    	            JOptionPane.showMessageDialog(null, "input modified") ;  
    	            pst.close(); 
    	            
    	            UpdateStudent() ;
    	            
    			} catch (Exception e5) {
    				e5.printStackTrace() ;	
    			}
			}
		});
		
		btnMod.setBounds(10, 225, 89, 23);
		panel_2.add(btnMod);
		
		JButton btnDel = new JButton("Delete"); // delete grade button
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablestudent.getSelectedRow(); 
    			String cell = tablestudent.getModel().getValueAt(row, 0).toString(); 
    			String query = "DELETE FROM student where id = " + cell ; 
    			try {
    				PreparedStatement pst = db.prepareStatement(query) ; 
    				pst.execute(); 
    				
    				
					JOptionPane.showMessageDialog(null, "input deleted") ; 
					UpdateStudent() ; 
    			} catch (Exception e4) {
    				e4.printStackTrace() ;	
    			}
			}
		});
		btnDel.setBounds(10, 259, 89, 23);
		panel_2.add(btnDel);
		
		JButton btnUp = new JButton("Update"); // update table button
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
    				UpdateStudent(); 
				}catch (Exception e3) {
					e3.printStackTrace() ;
				}
			}
		});
		btnUp.setBounds(10, 293, 89, 23);
		panel_2.add(btnUp);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(119, 108, 507, 252);
		panel_2.add(scrollPane_2);
		
		tablestudent = new JTable();
		scrollPane_2.setViewportView(tablestudent);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(10, 337, 89, 23);
		panel_2.add(btnExit);
		
		txtID = new JTextField();
		txtID.setBounds(119, 44, 96, 20);
		panel_2.add(txtID);
		txtID.setColumns(10);
		
		txtCourse = new JTextField();
		txtCourse.setBounds(241, 44, 96, 20);
		panel_2.add(txtCourse);
		txtCourse.setColumns(10);
		
		txtSemester = new JTextField();
		txtSemester.setBounds(362, 44, 96, 20);
		panel_2.add(txtSemester);
		txtSemester.setColumns(10);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(482, 44, 96, 20);
		panel_2.add(txtNumber);
		txtNumber.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(119, 29, 48, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("course");
		lblNewLabel_1.setBounds(253, 29, 48, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("semester");
		lblNewLabel_2.setBounds(378, 29, 48, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("number grade");
		lblNewLabel_3.setBounds(505, 29, 48, 14);
		panel_2.add(lblNewLabel_3);
		
	}
	protected static void UpdateStudent() {
		String query = "select *  from Student" ; 
		try {
			PreparedStatement pst = db.prepareStatement(query) ; 
			ResultSet rs = pst.executeQuery() ; 
			tablestudent.setModel(DbUtils.resultSetToTableModel(rs)) ; 
			
		}catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}