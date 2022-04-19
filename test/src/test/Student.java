package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Student extends JFrame {
	static Connection db = null;
	private JPanel contentPane;
	private JTextField tFsearch;
	private static JTable table;
	
	public void run() { //Launch the application
		db = database.dbConnector();
		try {
			Student frame = new Student();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Student() { //Create the window
		setTitle("student");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tFsearch = new JTextField();
		tFsearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String search = tFsearch.getText();
				try {
					if (search.matches("^[0-9]+$")) {
						String query = "SELECT * from Student where id= " + search;
						PreparedStatement pst = db.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//GradeUpdate(); 					
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		tFsearch.setBounds(10, 125, 86, 20);
		contentPane.add(tFsearch);
		tFsearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(118, 11, 306, 239);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(7, 227, 89, 23);
		contentPane.add(btnExit);
	}

	protected static void GradeUpdate() {
		String query = "SELECT Numerical_Grade, CASE\n" + "	WHEN Numerical_Grade >=90 THEN \"A\"\n"
				+ "	WHEN Numerical_Grade <90 AND Numerical_Grade >= 85 THEN \"B+\"\n"
				+ "	WHEN Numerical_Grade <85 AND Numerical_Grade >= 80 THEN \"B\"\n"
				+ "	WHEN Numerical_Grade <80 AND Numerical_Grade >= 75 THEN \"C+\"\n"
				+ "	WHEN Numerical_Grade <75 AND Numerical_Grade >= 70 THEN \"C\"\n"
				+ "	WHEN Numerical_Grade <70 AND Numerical_Grade >= 65 THEN \"D+\"\n"
				+ "	WHEN Numerical_Grade <65 AND Numerical_Grade >= 60 THEN \"D\"\n" + "	ELSE 'F' \n"
				+ "	END  Letter_Grade , id, course, semester\n" + "FROM Student";
		try {
			PreparedStatement pst = db.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
