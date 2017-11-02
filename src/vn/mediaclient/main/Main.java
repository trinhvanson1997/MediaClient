package vn.mediaclient.main;


import vn.mediaclient.client.Client;

import vn.mediaclient.view.LoginBox;

public class Main {
	
	public static void main(String[] args) {
		Client client = new Client();
		

		LoginBox login = new LoginBox(client);
		System.out.println("lala");

	}
}
