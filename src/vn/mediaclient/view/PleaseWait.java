package vn.mediaclient.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class PleaseWait extends JDialog{
	
	 private JProgressBar progressBar; 
	 
 public PleaseWait() {
	 setLayout(new BorderLayout());
	 setLocationRelativeTo(null);
	 
	 
	 progressBar = new JProgressBar();
     
     progressBar.setStringPainted(true);
     progressBar.setVisible(true);
     progressBar.setBorderPainted(true);
     progressBar.setString("Please wait ...");
     progressBar.setIndeterminate(true);
     
     add(progressBar,BorderLayout.CENTER);
     pack();
     setVisible(true);
}
 
 public static void main(String[] args) {
	new PleaseWait();
}
}
