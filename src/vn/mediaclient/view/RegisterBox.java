package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.models.KhachHang;
import vn.mediaclient.client.Client;

public class RegisterBox extends JDialog implements ActionListener {
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	private JLabel lbID, lbHoTen, lbNgaySinh, lbNgay, lbThang, lbNam, lbDiaChi, lbSDT, lbCoin,lbUsername,lbPassword;
	private JTextField tfID, tfHoTen, tfNgaySinh, tfNgay, tfThang, tfNam, tfDiachi, tfSDT, tfCoin,tfPassword,tfUsername;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	private Client client;
	
	
	
	public RegisterBox(Client client) {
		this.client = client;
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Register Box");

		
		lbHoTen    = new JLabel("Họ Tên");
		lbNgaySinh = new JLabel("Ngày Sinh");
		lbDiaChi   = new JLabel("Địa Chỉ");
		lbSDT      = new JLabel("SĐT");
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		
		
		tfHoTen    = new JTextField(15);
		tfNgaySinh = new JTextField(15);  tfNgaySinh.setText("vd. 1/9/1997");
		tfDiachi   = new JTextField(15);
		tfSDT      = new JTextField(15);
		tfUsername = new JTextField(15);
		tfPassword = new JTextField(15);
		
		tfHoTen.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfHoTen.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		tfNgaySinh.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfNgaySinh.setText("");
		    	tfNgaySinh.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		tfDiachi.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfDiachi.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		tfSDT.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfSDT.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		tfUsername.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfUsername.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		tfPassword.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		    	tfPassword.setBackground(Color.white);
		    }

		    public void focusLost(FocusEvent e) {
		        // nothing
		    }
		});
		
		btnThem    = new JButton("ĐĂNG KÝ");		btnThem.addActionListener(this);
		btnHuy     = new JButton("HỦY ");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(6, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(6, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
	
		p1.add(lbHoTen);		p2.add(tfHoTen);	 
		p1.add(lbNgaySinh);		p2.add(tfNgaySinh);	
		p1.add(lbDiaChi);		p2.add(tfDiachi);
		p1.add(lbSDT);			p2.add(tfSDT);
		p1.add(lbUsername);		p2.add(tfUsername);
		p1.add(lbPassword);		p2.add(tfPassword);
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		JPanel p4 = new JPanel(new BorderLayout(0,5));
		JLabel label = new JLabel("* Chú ý: Tất cả các trường dữ liệu không được để trống");
		label.setForeground(Color.RED);
		
		Font font = label.getFont().deriveFont(Font.ITALIC, 10f);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		p4.add(label,BorderLayout.NORTH);
		p4.add(p3,BorderLayout.CENTER);
		
		add(p1,BorderLayout.WEST);
		add(p4,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
	
		
		pack();
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHuy) {
			dispose();
			new LoginBox(client).setVisible(true);
		}
		if (e.getSource() == btnThem) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
			if(checkFormat() == true){
			

				try {
					Timestamp date = new Timestamp(format.parse(tfNgaySinh.getText()).getTime());
					
					KhachHang kh = new KhachHang("", tfHoTen.getText(), date,
							tfDiachi.getText(), tfSDT.getText(),2000000,tfUsername.getText(),tfPassword.getText());
					client.addCus(kh);
					
					
					JOptionPane.showMessageDialog(null, "Đăng ký thành công \nBạn được nhận 2.000.000đ trong tài khoản !");
					
					
					dispose();
					
					 new LoginBox(client);
				} catch (NumberFormatException e1) {
				System.out.println("Fail to register");
				JOptionPane.showMessageDialog(null, "Đăng ký thất bại !");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}
	
	private boolean checkFormat() {
		if (tfHoTen.getText().equals(null) || tfHoTen.getText().equals("")) {
			tfHoTen.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập họ tên", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!tfHoTen.equals("")) {
			String ten = tfHoTen.getText();
			for(int i=0;i<ten.length();i++) {
				if(Character.isDigit(ten.charAt(i)))
				{
					JOptionPane.showMessageDialog(null, "Họ tên không được chứa ký tự số !", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
		}
		if (tfNgaySinh.getText().equals(null) || tfNgaySinh.getText().equals("")) {
			tfNgaySinh.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập ngày sinh", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tfDiachi.getText().equals(null) || tfDiachi.getText().equals("")) {
			tfDiachi.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập địa chỉ", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (tfSDT.getText().equals(null) || tfSDT.getText().equals("")) {
			tfSDT.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập số điện thoại", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(!tfSDT.getText().equals("")) {
			String s= tfSDT.getText();
			for(int i=0;i<s.length();i++) {
				if(Character.isLetter(s.charAt(i))) {
					JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự chữ !", "Cảnh báo",
							JOptionPane.WARNING_MESSAGE);
					return false;
				}
				
			}
					
		}
		
		if (tfUsername.getText().equals(null) || tfUsername.getText().equals("")) {
			tfUsername.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập username", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tfPassword.getText().equals(null) || tfPassword.getText().equals("")) {
			tfPassword.setBackground(Color.red);
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (client.checkExistUsername(tfUsername.getText())) {
			
			JOptionPane.showMessageDialog(null, "Username '" + tfUsername.getText() + "' đã tồn tại!", "Warning",
					WARNING_MESSAGE);
			return false;
		}

		return true;
	}
}
