// Frank The Management System 
// This Project was done by Doryan B, Adam K, Brian K, Noah V, and Haley M
// The purpose of the TA gui is to allow the professor to see their data and for the professor to add,modify,delete the grades of students
// TA and Professsor are basically the same roles

package test;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.*;
import java.awt.event.*;
import java.sql.*;

public class TA extends JFrame {
	private static final long serialVersionUID = -6270910708524833970L;
	private JPanel contentPane;
	static Connection db = null;
	private JTextField tfID;
	private JTextField tfFN;
	private JTextField tfLN;
	private JTextField tfDOB;
	private JTextField tfCourse;
	private JTextField tfSemester;
	private JTextField tfNumberGrade;
	private JTextField txtTA;
	private static JTable tableTA;
	private static JTable tableStudent;

	public void run() { //Launch the application
		db = database.dbConnector();
		try {
			TA frame = new TA();
			frame.setVisible(true);
			frame.setSize(978,630);
			frame.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TA() {
		//main gui layout designed by Noah V, later altered slightly
		setResizable(false); //Create the window
		setTitle("TA");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 978, 609);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 974, 584);
		tabbedPane.setBackground(new Color(176, 224, 230));
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("TA Search", null, panel, null);
		
		txtTA = new JTextField();
		txtTA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String search = txtTA.getText();
				try {
					//this if statement is based on numbers only. It will not accept letters
					if (search.matches("^[0-9]+$")) {
						String query = "SELECT * from TA where id= " + search;
						PreparedStatement pst = db.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						tableTA.setModel(DbUtils.resultSetToTableModel(rs));
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		txtTA.setText("Type In TA ID To Search");
		txtTA.setForeground(Color.GRAY);
		txtTA.setColumns(10);
		txtTA.setBounds(10, 19, 190, 26);
		panel.add(txtTA);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 650, 330);
		panel.add(scrollPane);
		
		tableTA = new JTable();
		scrollPane.setViewportView(tableTA);
		
		JButton btnExit1 = new JButton("EXIT");
		btnExit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit1.setBounds(567, 411, 89, 23);
		panel.add(btnExit1);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose() ; 
				new login() ; 
			}
		});
		btnLogOut.setBounds(468, 411, 89, 23);
		panel.add(btnLogOut);
		
		JPanel profGradTab = new JPanel();
		profGradTab.setLayout(null);
		profGradTab.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Student Manager", null, profGradTab, null);
		
		JButton btnExit2 = new JButton("EXIT");
		btnExit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit2.setBounds(869, 517, 89, 23);
		profGradTab.add(btnExit2);
		
		tfID = new JTextField();
		tfID.setColumns(10);
		tfID.setBounds(21, 56, 189, 38);
		profGradTab.add(tfID);
		
		tfFN = new JTextField();
		tfFN.setColumns(10);
		tfFN.setBounds(21, 109, 189, 38);
		profGradTab.add(tfFN);
		
		tfLN = new JTextField();
		tfLN.setColumns(10);
		tfLN.setBounds(21, 172, 189, 38);
		profGradTab.add(tfLN);
		
		tfDOB = new JTextField();
		tfDOB.setColumns(10);
		tfDOB.setBounds(21, 234, 189, 38);
		profGradTab.add(tfDOB);
		
		tfCourse = new JTextField();
		tfCourse.setColumns(10);
		tfCourse.setBounds(21, 365, 189, 38);
		profGradTab.add(tfCourse);
		
		tfSemester = new JTextField();
		tfSemester.setColumns(10);
		tfSemester.setBounds(21, 414, 189, 38);
		profGradTab.add(tfSemester);
		
		tfNumberGrade = new JTextField();
		tfNumberGrade.setColumns(10);
		tfNumberGrade.setBounds(21, 463, 189, 38);
		profGradTab.add(tfNumberGrade);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(21, 41, 46, 14);
		profGradTab.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(21, 95, 79, 14);
		profGradTab.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(21, 158, 79, 14);
		profGradTab.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DOB");
		lblNewLabel_3.setBounds(21, 221, 65, 14);
		profGradTab.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Course");
		lblNewLabel_4.setBounds(21, 352, 79, 14);
		profGradTab.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Semester");
		lblNewLabel_5.setBounds(21, 402, 79, 14);
		profGradTab.add(lblNewLabel_5);
		
		JLabel lblNGStudent = new JLabel("Numerical Grade");
		lblNGStudent.setBounds(21, 451, 95, 14);
		profGradTab.add(lblNGStudent);
		
		
		//uses the cell clicked on to gather the data of the row. If button pressed , all data in that row is deleted. 
		JButton btnDeleteStudent = new JButton("Delete");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableStudent.getSelectedRow();
				String cell = tableStudent.getModel().getValueAt(row, 0).toString();
				String query = "DELETE FROM Student where id = " + cell;
				try {
					PreparedStatement pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input deleted");
					UpdateStudent();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		btnDeleteStudent.setBounds(807, 32, 89, 23);
		profGradTab.add(btnDeleteStudent);
		
		
		//modify checks to see if id is matching the input wanting to be modified, if the ID matched it will change the differnt inputs and save it into the database
		JButton btnModifyStudent = new JButton("Modify");
		btnModifyStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value1 = tfID.getText();
					String value2 = tfFN.getText();
					String value3 = tfLN.getText();
					String value4 = tfDOB.getText();
					String value5 = tfCourse.getText();
					String value6 = tfSemester.getText();
					String value7 = tfNumberGrade.getText();
					String query = "update Student set id='" + value1 
							+ "',First_Name='" + value2 
							+ "',Last_Name= '" + value3
							+ "',DOB ='" + value4 
							+ "',course= '" + value5 
							+ "',semester= '" + value6 
							+ "',Numerical_Grade= '" + value7 
							+ "'where id='" + value1 
						    +  "' ";
					PreparedStatement pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input modified");
					UpdateStudent();
					pst.close();
					
				} catch (Exception e5) {
					e5.printStackTrace();
					JOptionPane.showMessageDialog(null, "input not modified");
				}
			}
		});
		btnModifyStudent.setBounds(697, 32, 89, 23);
		profGradTab.add(btnModifyStudent);
		
		JButton btnAddStudent = new JButton("Add");
		//add only works if the ID is unique, will check every textfield and add the data to the database if the id is unique
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = " insert into Student (id,First_Name, Last_Name, DOB, course, semester, Numerical_Grade) values (?,?,?,?,?,?,?) " ;
					PreparedStatement pst = db.prepareStatement(query); // pst is called at the top as a static
					pst.setString(1, tfID.getText());
					pst.setString(2, tfFN.getText());
					pst.setString(3, tfLN.getText());
					pst.setString(4, tfDOB.getText());
					pst.setString(5, tfCourse.getText()) ; 
					pst.setString(6, tfSemester.getText()) ; 
					pst.setString(7, tfNumberGrade.getText()) ; 
					pst.execute();
					JOptionPane.showMessageDialog(null, "input saved");
					UpdateStudent();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, " ID must be unique,  Try Again Please ");
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnAddStudent.setBounds(583, 32, 89, 23);
		profGradTab.add(btnAddStudent);
		
		JButton btnUpdateStudent = new JButton("Update Table");
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateStudent();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnUpdateStudent.setBounds(231, 32, 136, 23);
		profGradTab.add(btnUpdateStudent);
		
		JLabel lblNewLabel_6 = new JLabel("Enter Student Grades Here");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(33, 310, 189, 14);
		profGradTab.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Please Enter One Class at a Time");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(21, 326, 205, 14);
		profGradTab.add(lblNewLabel_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(241, 68, 717, 438);
		profGradTab.add(scrollPane_1);
		
		tableStudent = new JTable();
		scrollPane_1.setViewportView(tableStudent);
		
		JButton btnLogOut_1 = new JButton("LOG OUT");
		btnLogOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose() ; 
				new login() ; 
			}
		});
		btnLogOut_1.setBounds(753, 517, 89, 23);
		profGradTab.add(btnLogOut_1);
	}

	protected static void UpdateTA() {
		String query = "select *  from TA";
		try {
			PreparedStatement pst = db.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableTA.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//grade update is used to calculate what the letter grade will be based on the number grade given by the user.
	protected static void UpdateStudent() {
		String query = "SELECT id, First_Name, Last_Name, DOB, course, semester, Numerical_Grade, CASE\r\n"
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
			tableStudent.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
