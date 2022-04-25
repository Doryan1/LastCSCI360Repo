package test;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.*;

public class admin implements ActionListener {
	private static JTable tableTA, tableStaff, tableprof;
	private JTextField txtFNProf, txtIDProf, txtLNProf, txtDOBProf, txtCTeachingProf, txtCTaughtProf, txtDepartmentProf, txtTAProf;
	private JTextField txtFNTA, txtIDTA, txtLNTA, txtDOBTA, txtCAssistingTA, txtCAssistedTA, txtDepartmentTA;
	private JTextField txtIDStaff, txtFNStaff, txtLNStaff, txtDOBStaff, txtDepartmentStaff;
	private JPanel adminprofpanel, admintapanel, adminstudentpanel, adminstaffpanel;
	private JLabel lblNewLabelProf, lblThisIsFor_2, lblThisIsFor_1, lblThisIsFor;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	protected Object insertProfessor;
	public static JTextField userText;
	public static JButton LogInbutton;
	static int LogSuccess;
	static Connection db = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	private JTextField tfIDstudent;
	private JTextField tfFNstudent;
	private JTextField tfLNstudent;
	private JTextField tfDOBstudent;
	private JTextField tfCourseStudent;
	private JTextField tfSemesterStudent;
	private JTextField tfNumberGradeStudent;
	private static JTable tableStudent;

	/**
	 * @throws SQLException
	 * @wbp.parser.entryPoint
	 */
	public void ad() {
		db = database.dbConnector();
		JFrame frame = new JFrame("Admin Manager");
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		frame.setSize(937, 636);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 22, 911, 564);
		panel.add(tabbedPane);

		adminprofpanel = new JPanel();
		adminprofpanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Professor Manager", null, adminprofpanel, null);
		adminprofpanel.setLayout(null);

		lblNewLabelProf = new JLabel("This is for professors");
		lblNewLabelProf.setBounds(726, 11, 198, 14);
		adminprofpanel.add(lblNewLabelProf);

		JButton btnExitProf = new JButton("EXIT");
		btnExitProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExitProf.setBounds(806, 473, 90, 23);
		btnExitProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		adminprofpanel.add(btnExitProf);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 323, 792, 188);
		adminprofpanel.add(scrollPane);

		tableprof = new JTable();
		scrollPane.setViewportView(tableprof);

		txtFNProf = new JTextField();
		txtFNProf.setBounds(97, 103, 189, 38);
		adminprofpanel.add(txtFNProf);
		txtFNProf.setColumns(10);

		JButton btnAddProf = new JButton("ADD");
		btnAddProf.setBounds(515, 289, 89, 23);
		adminprofpanel.add(btnAddProf);
		btnAddProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = " insert into Professor (id,department,fname,lname,dob,cteaching,ctaught,ta) values (?,?,?,?,?,?,?,?) ";
					pst = db.prepareStatement(query); // pst is called at the top as a static
					pst.setString(1, txtIDProf.getText());
					pst.setString(2, txtDepartmentProf.getText());
					pst.setString(3, txtFNProf.getText());
					pst.setString(4, txtLNProf.getText());
					pst.setString(5, txtDOBProf.getText());
					pst.setString(6, txtCTeachingProf.getText());
					pst.setString(7, txtCTaughtProf.getText());
					pst.setString(8, txtTAProf.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "input saved");
					UpdateProfessor();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, " ID must be unique,  Try Again Please ");
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		JButton btnModifyProf = new JButton("Modify"); // This is the modify button for the Professor Manager
		btnModifyProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value1 = txtIDProf.getText();
					String value2 = txtDepartmentProf.getText();
					String value3 = txtFNProf.getText();
					String value4 = txtLNProf.getText();
					String value5 = txtDOBProf.getText();
					String value6 = txtCTaughtProf.getText();
					String value7 = txtCTeachingProf.getText();
					String value8 = txtTAProf.getText();
					String query = "update Professor set id='" + value1 + "',department='" + value2 + "',fname = '"
							+ value3 + "',lname='" + value4 + "',dob= '" + value5 + "',cteaching='" + value6
							+ "',ctaught='" + value7 + "', TA='" + value8 + "' where id='" + value1 + "' ";
					pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input modified");
					pst.close();
					UpdateProfessor();
				} catch (Exception e5) {
					e5.printStackTrace();
				}
			}
		});
		btnModifyProf.setBounds(614, 289, 89, 23);
		adminprofpanel.add(btnModifyProf);

		JButton btnDeleteProf = new JButton("Delete"); // This is the delete button for the Professor Manager
		btnDeleteProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableprof.getSelectedRow();
				String cell = tableprof.getModel().getValueAt(row, 0).toString();
				String query = "DELETE FROM Professor where id= " + cell;
				try {
					PreparedStatement pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input deleted");
					UpdateProfessor();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		btnDeleteProf.setBounds(713, 289, 89, 23);
		adminprofpanel.add(btnDeleteProf);

		JButton btnUpdateProf = new JButton("Update Table"); // This is the update button for the Professor Manager
		btnUpdateProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateProfessor();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnUpdateProf.setBounds(10, 289, 151, 23);
		adminprofpanel.add(btnUpdateProf);

		JLabel lblIDProf = new JLabel("ID : ");
		lblIDProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblIDProf.setBounds(10, 11, 49, 38);
		adminprofpanel.add(lblIDProf);

		JLabel lblFNameProf = new JLabel("First Name :");
		lblFNameProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFNameProf.setBounds(10, 102, 78, 38);
		adminprofpanel.add(lblFNameProf);

		txtIDProf = new JTextField();
		txtIDProf.setColumns(10);
		txtIDProf.setBounds(97, 12, 189, 38);
		adminprofpanel.add(txtIDProf);

		JLabel lblLNameProf = new JLabel("Last Name :");
		lblLNameProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLNameProf.setBounds(316, 102, 78, 38);
		adminprofpanel.add(lblLNameProf);

		txtLNProf = new JTextField();
		txtLNProf.setColumns(10);
		txtLNProf.setBounds(404, 103, 189, 38);
		adminprofpanel.add(txtLNProf);

		JLabel lblDOBProf = new JLabel("DOB :");
		lblDOBProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDOBProf.setBounds(625, 102, 78, 38);
		adminprofpanel.add(lblDOBProf);

		txtDOBProf = new JTextField();
		txtDOBProf.setColumns(10);
		txtDOBProf.setBounds(680, 103, 163, 38);
		adminprofpanel.add(txtDOBProf);

		JLabel lblDepartmentProf = new JLabel("Department :");
		lblDepartmentProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDepartmentProf.setBounds(316, 11, 78, 38);
		adminprofpanel.add(lblDepartmentProf);

		txtCTeachingProf = new JTextField();
		txtCTeachingProf.setColumns(10);
		txtCTeachingProf.setBounds(128, 190, 158, 38);
		adminprofpanel.add(txtCTeachingProf);

		JLabel lblCTaughtProf = new JLabel("Courses Taught :");
		lblCTaughtProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCTaughtProf.setBounds(316, 189, 119, 38);
		adminprofpanel.add(lblCTaughtProf);

		txtCTaughtProf = new JTextField();
		txtCTaughtProf.setColumns(10);
		txtCTaughtProf.setBounds(423, 190, 170, 38);
		adminprofpanel.add(txtCTaughtProf);

		JLabel lblCTeachingProf = new JLabel("Courses Teaching :");
		lblCTeachingProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCTeachingProf.setBounds(10, 189, 119, 38);
		adminprofpanel.add(lblCTeachingProf);

		JLabel lblTAProf = new JLabel("TA :");
		lblTAProf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTAProf.setBounds(625, 189, 63, 38);
		adminprofpanel.add(lblTAProf);

		txtDepartmentProf = new JTextField();
		txtDepartmentProf.setColumns(10);
		txtDepartmentProf.setBounds(404, 12, 189, 38);
		adminprofpanel.add(txtDepartmentProf);

		txtTAProf = new JTextField();
		txtTAProf.setColumns(10);
		txtTAProf.setBounds(680, 190, 163, 38);
		adminprofpanel.add(txtTAProf);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ; 
				new login() ; 
			}
		});
		btnLogOut.setBounds(806, 444, 90, 23);
		adminprofpanel.add(btnLogOut);

		admintapanel = new JPanel();
		admintapanel.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("TA Manager", null, admintapanel, null);
		admintapanel.setLayout(null);

		JPanel adminprofpanel_1 = new JPanel();
		adminprofpanel_1.setLayout(null);
		adminprofpanel_1.setBackground(Color.CYAN);
		adminprofpanel_1.setBounds(0, 0, 906, 536);
		admintapanel.add(adminprofpanel_1);

		JButton btnExitTA = new JButton("EXIT");
		btnExitTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExitTA.setBounds(806, 473, 90, 23);
		btnExitTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		adminprofpanel_1.add(btnExitTA);

		txtFNTA = new JTextField();
		txtFNTA.setColumns(10);
		txtFNTA.setBounds(97, 103, 189, 38);
		adminprofpanel_1.add(txtFNTA);

		JButton btnAddTA = new JButton("ADD");
		btnAddTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = " insert into TA (id,department,fname,lname,dob,cassisting,cassisted) values (?,?,?,?,?,?,?) ";
					pst = db.prepareStatement(query); // pst is called at the top as a static
					pst.setString(1, txtIDTA.getText());
					pst.setString(2, txtDepartmentTA.getText());
					pst.setString(3, txtFNTA.getText());
					pst.setString(4, txtLNTA.getText());
					pst.setString(5, txtDOBTA.getText());
					pst.setString(6, txtCAssistingTA.getText());
					pst.setString(7, txtCAssistedTA.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "input saved");
					UpdateTA();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, " ID must be unique,  Try Again Please ");
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnAddTA.setBounds(515, 289, 89, 23);
		adminprofpanel_1.add(btnAddTA);

		JButton btnModifyTA = new JButton("Modify");
		btnModifyTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value1 = txtIDTA.getText();
					String value2 = txtDepartmentTA.getText();
					String value3 = txtFNTA.getText();
					String value4 = txtLNTA.getText();
					String value5 = txtDOBTA.getText();
					String value6 = txtCAssistingTA.getText();
					String value7 = txtCAssistedTA.getText();
					String query = "update TA set id='" + value1 + "',department='" + value2 + "',fname = '" + value3
							+ "',lname='" + value4 + "',dob= '" + value5 + "',cassisting='" + value6 + "',cassisted='"
							+ value7 + "' where id='" + value1 + "' ";
					pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input modified");
					pst.close();
					UpdateTA();
				} catch (Exception e5) {
					e5.printStackTrace();
				}
			}
		});
		btnModifyTA.setBounds(614, 289, 89, 23);
		adminprofpanel_1.add(btnModifyTA);

		JButton btnDeleteTA = new JButton("Delete");
		btnDeleteTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableTA.getSelectedRow();
				String cell = tableTA.getModel().getValueAt(row, 0).toString();
				String query = "DELETE FROM TA where id= " + cell;
				try {
					PreparedStatement pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input deleted");
					UpdateTA();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		btnDeleteTA.setBounds(713, 289, 89, 23);
		adminprofpanel_1.add(btnDeleteTA);

		JButton btnUpdateTA = new JButton("Update Table");
		btnUpdateTA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateTA();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnUpdateTA.setBounds(10, 289, 151, 23);
		adminprofpanel_1.add(btnUpdateTA);

		JLabel lblIDTA = new JLabel("ID : ");
		lblIDTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblIDTA.setBounds(10, 11, 49, 38);
		adminprofpanel_1.add(lblIDTA);

		JLabel lblFNTA = new JLabel("First Name :");
		lblFNTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFNTA.setBounds(10, 102, 78, 38);
		adminprofpanel_1.add(lblFNTA);

		txtIDTA = new JTextField();
		txtIDTA.setColumns(10);
		txtIDTA.setBounds(97, 12, 189, 38);
		adminprofpanel_1.add(txtIDTA);

		JLabel lblLNTA = new JLabel("Last Name :");
		lblLNTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLNTA.setBounds(316, 102, 78, 38);
		adminprofpanel_1.add(lblLNTA);

		txtLNTA = new JTextField();
		txtLNTA.setColumns(10);
		txtLNTA.setBounds(404, 103, 189, 38);
		adminprofpanel_1.add(txtLNTA);

		JLabel lblDOBTA = new JLabel("DOB :");
		lblDOBTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDOBTA.setBounds(625, 102, 78, 38);
		adminprofpanel_1.add(lblDOBTA);

		txtDOBTA = new JTextField();
		txtDOBTA.setColumns(10);
		txtDOBTA.setBounds(680, 103, 163, 38);
		adminprofpanel_1.add(txtDOBTA);

		JLabel lblDepartment_1 = new JLabel("Department :");
		lblDepartment_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDepartment_1.setBounds(316, 11, 78, 38);
		adminprofpanel_1.add(lblDepartment_1);

		txtCAssistingTA = new JTextField();
		txtCAssistingTA.setColumns(10);
		txtCAssistingTA.setBounds(128, 190, 158, 38);
		adminprofpanel_1.add(txtCAssistingTA);

		JLabel lblCTAssistedTA = new JLabel("Courses Taught :");
		lblCTAssistedTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCTAssistedTA.setBounds(316, 189, 119, 38);
		adminprofpanel_1.add(lblCTAssistedTA);

		txtCAssistedTA = new JTextField();
		txtCAssistedTA.setColumns(10);
		txtCAssistedTA.setBounds(423, 190, 170, 38);
		adminprofpanel_1.add(txtCAssistedTA);

		JLabel lblCTAssistingTA = new JLabel("Courses Teaching :");
		lblCTAssistingTA.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCTAssistingTA.setBounds(10, 189, 119, 38);
		adminprofpanel_1.add(lblCTAssistingTA);

		txtDepartmentTA = new JTextField();
		txtDepartmentTA.setColumns(10);
		txtDepartmentTA.setBounds(404, 12, 189, 38);
		adminprofpanel_1.add(txtDepartmentTA);

		lblThisIsFor_2 = new JLabel("This is for TA");
		lblThisIsFor_2.setBounds(671, 11, 198, 14);
		adminprofpanel_1.add(lblThisIsFor_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 323, 802, 188);
		adminprofpanel_1.add(scrollPane_1);

		tableTA = new JTable();
		scrollPane_1.setViewportView(tableTA);
		
		JButton btnLogOut_1 = new JButton("LOG OUT");
		btnLogOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ; 
				new login() ; 
			}
		});
		btnLogOut_1.setBounds(806, 439, 90, 23);
		adminprofpanel_1.add(btnLogOut_1);

		adminstaffpanel = new JPanel();
		adminstaffpanel.setBackground(new Color(250, 250, 210));
		tabbedPane.addTab("Staff Manager", null, adminstaffpanel, null);
		adminstaffpanel.setLayout(null);

		lblThisIsFor = new JLabel("This is for staff");
		lblThisIsFor.setBounds(10, -2, 198, 14);
		adminstaffpanel.add(lblThisIsFor);

		JButton exitStaff = new JButton("EXIT");
		exitStaff.setBounds(780, 477, 89, 23);
		adminstaffpanel.add(exitStaff);
		exitStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(209, 78, 660, 379);
		adminstaffpanel.add(scrollPane_2);

		tableStaff = new JTable();
		scrollPane_2.setViewportView(tableStaff);

		JLabel lblIDStaff = new JLabel("ID : ");
		lblIDStaff.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblIDStaff.setBounds(10, 23, 49, 38);
		adminstaffpanel.add(lblIDStaff);

		JLabel lblFNStaff = new JLabel("First Name :");
		lblFNStaff.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFNStaff.setBounds(10, 109, 78, 38);
		adminstaffpanel.add(lblFNStaff);

		JLabel lblLNStaff = new JLabel("Last Name :");
		lblLNStaff.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblLNStaff.setBounds(10, 185, 78, 38);
		adminstaffpanel.add(lblLNStaff);

		JLabel lblDOBStaff = new JLabel("DOB :");
		lblDOBStaff.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDOBStaff.setBounds(10, 271, 78, 38);
		adminstaffpanel.add(lblDOBStaff);

		JLabel lblDepartmentStaff = new JLabel("Department :");
		lblDepartmentStaff.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDepartmentStaff.setBounds(10, 348, 78, 38);
		adminstaffpanel.add(lblDepartmentStaff);

		txtIDStaff = new JTextField();
		txtIDStaff.setColumns(10);
		txtIDStaff.setBounds(10, 60, 189, 38);
		adminstaffpanel.add(txtIDStaff);

		txtFNStaff = new JTextField();
		txtFNStaff.setColumns(10);
		txtFNStaff.setBounds(10, 136, 189, 38);
		adminstaffpanel.add(txtFNStaff);

		txtLNStaff = new JTextField();
		txtLNStaff.setColumns(10);
		txtLNStaff.setBounds(10, 222, 189, 38);
		adminstaffpanel.add(txtLNStaff);

		txtDOBStaff = new JTextField();
		txtDOBStaff.setColumns(10);
		txtDOBStaff.setBounds(10, 299, 189, 38);
		adminstaffpanel.add(txtDOBStaff);

		txtDepartmentStaff = new JTextField();
		txtDepartmentStaff.setColumns(10);
		txtDepartmentStaff.setBounds(10, 376, 189, 38);
		adminstaffpanel.add(txtDepartmentStaff);

		JButton btnAddStaff = new JButton("ADD");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = " insert into staff (id,department,fname,lname,dob) values (?,?,?,?,?) ";
					pst = db.prepareStatement(query); // pst is called at the top as a static
					pst.setString(1, txtIDStaff.getText());
					pst.setString(2, txtDepartmentStaff.getText());
					pst.setString(3, txtFNStaff.getText());
					pst.setString(4, txtLNStaff.getText());
					pst.setString(5, txtDOBStaff.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "input saved");
					UpdateStaff();
					pst.close();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, " ID must be unique,  Try Again Please ");
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnAddStaff.setBounds(571, 48, 89, 23);
		adminstaffpanel.add(btnAddStaff);

		JButton btnModifyStaff = new JButton("Modify");
		btnModifyStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value1 = txtIDStaff.getText();
					String value2 = txtDepartmentStaff.getText();
					String value3 = txtFNStaff.getText();
					String value4 = txtLNStaff.getText();
					String value5 = txtDOBStaff.getText();
					String query = "update staff set id='" + value1 
							+ "',department='" + value2 
							+ "',fname = '" + value3
							+ "',lname='" + value4 
							+ "',dob= '" + value5 
							+ "'where id='" + value1 + "' ";
					pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input modified");
					pst.close();
					UpdateStaff();
				} catch (Exception e5) {
					e5.printStackTrace();
				}
			}
		});
		btnModifyStaff.setBounds(670, 48, 89, 23);
		adminstaffpanel.add(btnModifyStaff);

		JButton btnDeleteStaff = new JButton("Delete");
		btnDeleteStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableStaff.getSelectedRow();
				String cell = tableStaff.getModel().getValueAt(row, 0).toString();
				String query = "DELETE FROM staff where id = " + cell;
				try {
					PreparedStatement pst = db.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "input deleted");
					UpdateStaff();
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		});
		btnDeleteStaff.setBounds(769, 48, 89, 23);
		adminstaffpanel.add(btnDeleteStaff);

		JButton btnUpdateStaff = new JButton("Update Table");
		btnUpdateStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateStaff();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnUpdateStaff.setBounds(209, 48, 151, 23);
		adminstaffpanel.add(btnUpdateStaff);
		
		JButton btnLogOut_2 = new JButton("LOG OUT");
		btnLogOut_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ; 
				new login() ;
			}
		});
		btnLogOut_2.setBounds(681, 477, 89, 23);
		adminstaffpanel.add(btnLogOut_2);
		
				adminstudentpanel = new JPanel();
				adminstudentpanel.setBackground(new Color(240, 255, 255));
				tabbedPane.addTab("Student Manager", null, adminstudentpanel, null);
				adminstudentpanel.setLayout(null);
				
						lblThisIsFor_1 = new JLabel("This is for student");
						lblThisIsFor_1.setBounds(12, 11, 198, 14);
						adminstudentpanel.add(lblThisIsFor_1);
						
								JButton exit3 = new JButton("EXIT");
								exit3.setBounds(817, 513, 89, 23);
								adminstudentpanel.add(exit3);
								
								tfIDstudent = new JTextField();
								tfIDstudent.setColumns(10);
								tfIDstudent.setBounds(21, 56, 189, 38);
								adminstudentpanel.add(tfIDstudent);
								
								tfFNstudent = new JTextField();
								tfFNstudent.setColumns(10);
								tfFNstudent.setBounds(21, 109, 189, 38);
								adminstudentpanel.add(tfFNstudent);
								
								tfLNstudent = new JTextField();
								tfLNstudent.setColumns(10);
								tfLNstudent.setBounds(21, 172, 189, 38);
								adminstudentpanel.add(tfLNstudent);
								
								tfDOBstudent = new JTextField();
								tfDOBstudent.setColumns(10);
								tfDOBstudent.setBounds(21, 234, 189, 38);
								adminstudentpanel.add(tfDOBstudent);
								
								tfCourseStudent = new JTextField();
								tfCourseStudent.setColumns(10);
								tfCourseStudent.setBounds(21, 365, 189, 38);
								adminstudentpanel.add(tfCourseStudent);
								
								tfSemesterStudent = new JTextField();
								tfSemesterStudent.setColumns(10);
								tfSemesterStudent.setBounds(21, 414, 189, 38);
								adminstudentpanel.add(tfSemesterStudent);
								
								tfNumberGradeStudent = new JTextField();
								tfNumberGradeStudent.setColumns(10);
								tfNumberGradeStudent.setBounds(21, 463, 189, 38);
								adminstudentpanel.add(tfNumberGradeStudent);
								
								JScrollPane scrollPane_3 = new JScrollPane();
								scrollPane_3.setBounds(232, 56, 674, 449);
								adminstudentpanel.add(scrollPane_3);
								
								tableStudent = new JTable();
								scrollPane_3.setViewportView(tableStudent);
								
								JLabel lblNewLabel = new JLabel("ID");
								lblNewLabel.setBounds(21, 41, 46, 14);
								adminstudentpanel.add(lblNewLabel);
								
								JLabel lblNewLabel_1 = new JLabel("First Name");
								lblNewLabel_1.setBounds(21, 95, 79, 14);
								adminstudentpanel.add(lblNewLabel_1);
								
								JLabel lblNewLabel_2 = new JLabel("Last Name");
								lblNewLabel_2.setBounds(21, 158, 79, 14);
								adminstudentpanel.add(lblNewLabel_2);
								
								JLabel lblNewLabel_3 = new JLabel("DOB");
								lblNewLabel_3.setBounds(21, 221, 65, 14);
								adminstudentpanel.add(lblNewLabel_3);
								
								JLabel lblNewLabel_4 = new JLabel("Course");
								lblNewLabel_4.setBounds(21, 352, 79, 14);
								adminstudentpanel.add(lblNewLabel_4);
								
								JLabel lblNewLabel_5 = new JLabel("Semester");
								lblNewLabel_5.setBounds(21, 402, 79, 14);
								adminstudentpanel.add(lblNewLabel_5);
								
								JLabel lblNGStudent = new JLabel("Numerical Grade");
								lblNGStudent.setBounds(21, 451, 95, 14);
								adminstudentpanel.add(lblNGStudent);
								
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
								adminstudentpanel.add(btnDeleteStudent);
								
								JButton btnModifyStudent = new JButton("Modify");
								btnModifyStudent.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											String value1 = tfIDstudent.getText();
											String value2 = tfFNstudent.getText();
											String value3 = tfLNstudent.getText();
											String value4 = tfDOBstudent.getText();
											String value5 = tfCourseStudent.getText();
											String value6 = tfSemesterStudent.getText();
											String value7 = tfNumberGradeStudent.getText();
											String query = "update Student set id='" + value1 
													+ "',First_Name='" + value2 
													+ "',Last_Name= '" + value3
													+ "',DOB ='" + value4 
													+ "',course= '" + value5 
													+ "',semester= '" + value6 
													+ "',Numerical_Grade= '" + value7 
													+ "'where id='" + value1 
												    +  "' ";
											pst = db.prepareStatement(query);
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
								adminstudentpanel.add(btnModifyStudent);
								
								JButton btnAddStudent = new JButton("Add");
								btnAddStudent.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											String query = " insert into Student (id,First_Name, Last_Name, DOB, course, semester, Numerical_Grade) values (?,?,?,?,?,?,?) " ;
											pst = db.prepareStatement(query); // pst is called at the top as a static
											pst.setString(1, tfIDstudent.getText());
											pst.setString(2, tfFNstudent.getText());
											pst.setString(3, tfLNstudent.getText());
											pst.setString(4, tfDOBstudent.getText());
											pst.setString(5, tfCourseStudent.getText()) ; 
											pst.setString(6, tfSemesterStudent.getText()) ; 
											pst.setString(7, tfNumberGradeStudent.getText()) ; 
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
								adminstudentpanel.add(btnAddStudent);
								
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
								adminstudentpanel.add(btnUpdateStudent);
								
								JLabel lblNewLabel_6 = new JLabel("Enter Student Grades Here");
								lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblNewLabel_6.setBounds(33, 310, 189, 14);
								adminstudentpanel.add(lblNewLabel_6);
								
								JLabel lblNewLabel_7 = new JLabel("Please Enter One Class at a Time");
								lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
								lblNewLabel_7.setBounds(21, 326, 205, 14);
								adminstudentpanel.add(lblNewLabel_7);
								
								JButton btnLogOut_3 = new JButton("LOG OUT");
								btnLogOut_3.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										frame.dispose() ; 
										new login() ; 
									}
								});
								btnLogOut_3.setBounds(697, 513, 89, 23);
								adminstudentpanel.add(btnLogOut_3);
								exit3.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										System.exit(0);
									}
								});
		frame.setVisible(true);
	}

	protected static void UpdateProfessor() {
		String query = "select *  from Professor";
		try {
			pst = db.prepareStatement(query);
			rs = pst.executeQuery();
			tableprof.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void UpdateTA() {
		String query = "select *  from TA";
		try {
			pst = db.prepareStatement(query);
			rs = pst.executeQuery();
			tableTA.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void UpdateStaff() {
		String query = "select *  from staff";
		try {
			pst = db.prepareStatement(query);
			rs = pst.executeQuery();
			tableStaff.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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

	protected void setVisible(boolean b) {
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}