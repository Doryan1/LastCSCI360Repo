package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Professor extends JFrame {

	private JPanel contentPane;
	private JTable pTable;
	private JTable gTable;
	private JTextField gText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Professor frame = new Professor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Professor() {
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
		
		JButton btnNewButton_1 = new JButton("Display"); // display button
		btnNewButton_1.setBounds(10, 11, 89, 23);
		panel.add(btnNewButton_1);
		
		pTable = new JTable();
		pTable.setBounds(10, 57, 409, 165); 
		panel.add(pTable); // prof table
		
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
		gTable.setBounds(119, 11, 300, 211); 
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
