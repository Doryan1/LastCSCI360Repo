package test;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class refresh implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		login s1 = new login();
		try {
			login.main(null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}