package vn.mediaclient.main;


import javax.swing.JOptionPane;

import vn.mediaclient.client.Client;

import vn.mediaclient.view.LoginBox;

public class Main {
	
	public static void main(String[] args) {
		Client client = new Client();
		if(client.socket==null) {
			
		JOptionPane.showMessageDialog(null, "Máy chủ đang bận", "Thông báo", JOptionPane.WARNING_MESSAGE);	
		}
		else {
		LoginBox login = new LoginBox(client);
		}

	}
}
