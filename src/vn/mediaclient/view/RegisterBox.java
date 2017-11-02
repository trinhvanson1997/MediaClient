package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import vn.media.models.SanPham;
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
		//lbCoin    = new JLabel("Lương");
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		
		
		tfHoTen    = new JTextField(15);
		tfNgaySinh = new JTextField(15);
		tfDiachi   = new JTextField(15);
		tfSDT      = new JTextField(15);
		//tfCoin    = new JTextField(15);
		tfUsername = new JTextField(15);
		tfPassword = new JTextField(15);
		
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
		//p1.add(lbCoin);		p2.add(tfCoin);
		p1.add(lbUsername);		p2.add(tfUsername);
		p1.add(lbPassword);		p2.add(tfPassword);
		
		p3.add(btnThem);		p3.add(btnHuy);
		
		add(p1,BorderLayout.WEST);
		add(p3,BorderLayout.SOUTH);
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
							tfDiachi.getText(), tfSDT.getText(),0,tfUsername.getText(),tfPassword.getText());
					client.addCus(kh);
					
					
					JOptionPane.showMessageDialog(null, "Đăng ký thành công !");
					
					
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
	
	private boolean checkFormat(){
	 if(client.checkExistUsername(tfUsername.getText())) {
			JOptionPane.showMessageDialog(null, "Username '"+tfUsername.getText()+"' đã tồn tại!", "Warning",
					WARNING_MESSAGE);
			return false;
		}
		else if(tfHoTen.getText().equals(null) || tfHoTen.getText().equals("") ||
				tfNgaySinh.getText().equals(null) || tfNgaySinh.getText().equals("") ||
				tfDiachi.getText().equals(null) || tfDiachi.getText().equals("") ||
				tfSDT.getText().equals(null) || tfSDT.getText().equals("") ||
				//tfCoin.getText().equals(null) || tfCoin.getText().equals("") ||
				tfUsername.getText().equals(null) || tfUsername.getText().equals("") ||
				tfPassword.getText().equals(null) || tfPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}
}
