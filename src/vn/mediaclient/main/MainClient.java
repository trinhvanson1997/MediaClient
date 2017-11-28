package vn.mediaclient.main;


import javax.swing.JOptionPane;
import javax.swing.UIManager;

import vn.mediaclient.client.Client;

import vn.mediaclient.view.LoginBox;

public class MainClient {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			}
			catch(Exception ex) { }
		
		Client client = new Client();
		if(client.socket==null) {
			
		JOptionPane.showMessageDialog(null, "Máy chủ đang bận", "Thông báo", JOptionPane.WARNING_MESSAGE);	
		}
		else {
		LoginBox login = new LoginBox(client);
		
		}

	}
}
