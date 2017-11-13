package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.mediaclient.client.Client;

import vn.mediaclient.controller.ChangeTableClient;
import vn.mediaclient.controller.ClickTableClient;
import vn.mediaclient.controller.SearchBookClient;
import vn.mediaclient.controller.SearchMovieClient;
import vn.mediaclient.controller.SearchMusicClient;

public class LoginBox extends JFrame implements ActionListener, KeyListener {
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JButton btnLogin, btnRegister, btnClose;

	private Client client;
	
	public LoginBox(Client client) {
		this.client = client;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Login Box Client");

		add(createLabelPanel(), BorderLayout.WEST);
		add(createTextFieldPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);

		pack();
		setVisible(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		     client.sendCloseRequest();
		     dispose();
		    }
		});
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		btnLogin = createButton("Login");
		btnRegister = createButton("Register");
		btnClose = createButton("Close");

		panel.add(btnLogin);
		panel.add(btnRegister);
		panel.add(btnClose);

		return panel;
	}

	private JPanel createTextFieldPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		tfUsername = new JTextField(15);
		tfPassword = new JPasswordField(15);

		tfUsername.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPassword.requestFocus();
				}
			}

		});

		tfPassword.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}

		});

		panel.add(tfUsername);
		panel.add(tfPassword);
		return panel;
	}

	private JPanel createLabelPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		panel.add(new JLabel("Username"));
		panel.add(new JLabel("password"));
		return panel;
	}

	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.addKeyListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClose) {
			client.sendCloseRequest();
			System.exit(0);
		}

		if (e.getSource() == btnLogin) {
			action();

		}

		if (e.getSource() == btnRegister) {
			dispose();
			RegisterBox register = new RegisterBox(client);
		}

	}

	private void action() {
		boolean check=false;
		System.out.println("click");
		String username = tfUsername.getText().trim();
		String password = tfPassword.getText().trim();
		check = client.sendLoginRequest(username, password);
		if (check==true) {
			dispose();
				client.loginSuccess( username);

		}
		else {
			JOptionPane.showMessageDialog(null, "Thông tin tài khoản hoặc mật khẩu không chinh xác", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
