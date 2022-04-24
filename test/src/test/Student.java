package test;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.*;
import java.awt.event.*;
import java.awt.Font;

public class Student extends JFrame {
	private static final long serialVersionUID = 8897923336313218109L;
	private JPanel contentPane;
	private JTextField tFsearch;
	private static JTable table;
	static Connection db = null;
	private JTextField tFsearch2;
	
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
		setBounds(100, 100, 784, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tFsearch = new JTextField();
//		tFsearch.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent e) {
//				String search = tFsearch.getText();
//				try {
//					if (search.matches("^[0-9]+$")) {
//						String query = "SELECT * FROM Student where First_Name LIKE '%"+search+"%'" ; 
//						PreparedStatement pst = db.prepareStatement(query);
//						ResultSet rs = pst.executeQuery();
//						table.setModel(DbUtils.resultSetToTableModel(rs));
//						//GradeUpdate(); 					
//					}
//				} catch (Exception e1) {
//					JOptionPane.showMessageDialog(null, e1);
//				}
//			}
//		});
		tFsearch.setBounds(7, 122, 139, 20);
		contentPane.add(tFsearch);
		tFsearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 57, 562, 287);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(7, 367, 89, 23);
		contentPane.add(btnExit);
		
		tFsearch2 = new JTextField();
		tFsearch2.setBounds(7, 178, 139, 20);
		contentPane.add(tFsearch2);
		tFsearch2.setColumns(10);
		
		JButton btnSearch = new JButton("Search Student");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn = tFsearch.getText() ; 
				String ln = tFsearch2.getText() ; 
				try {
					String query = "SELECT First_Name,Last_Name,DOB,course,semester,Numerical_Grade FROM Student where First_Name LIKE '%"+fn+"%'" + "AND  Last_Name like '%"+ln+"%'" ; 
					PreparedStatement pst = db.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					GradeUpdate() ; 
					
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "student not found") ; 
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		btnSearch.setBounds(7, 222, 139, 23);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setBounds(7, 153, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(7, 97, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hello Student, Please Type in your First and Last Name To view Your Grades");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(104, 22, 502, 14);
		contentPane.add(lblNewLabel_2);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	protected static void GradeUpdate() {
		String query = "SELECT First_Name, Last_Name, DOB, course, semester, Numerical_Grade, CASE\r\n"
				+ "	WHEN Numerical_Grade >=90 THEN \"A\"\r\n"
				+ "	WHEN Numerical_Grade <90 AND Numerical_Grade >= 85 THEN \"B+\"\r\n"
				+ "	WHEN Numerical_Grade <85 AND Numerical_Grade >= 80 THEN \"B\"\r\n"
				+ "	WHEN Numerical_Grade <80 AND Numerical_Grade >= 75 THEN \"C+\"\r\n"
				+ "	WHEN Numerical_Grade <75 AND Numerical_Grade >= 70 THEN \"C\"\r\n"
				+ "	WHEN Numerical_Grade <70 AND Numerical_Grade >= 65 THEN \"D+\"\r\n"
				+ "	WHEN Numerical_Grade <65 AND Numerical_Grade >= 60 THEN \"D\"\r\n"
				+ "	ELSE 'F' \r\n"
				+ "	END AS  Letter_Grade\r\n"
				+ "FROM Student" ;  
		try {
			PreparedStatement pst = db.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
