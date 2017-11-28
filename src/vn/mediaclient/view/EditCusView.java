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
import vn.mediaclient.client.Client;

public class EditCusView extends JDialog implements ActionListener{
	
	private static final int WARNING_MESSAGE = 0;
	private static final int INFORMATION_MESSAGE = 0;
	private JLabel lbID, lbHoTen, lbNgaySinh, lbNgay, lbThang, lbNam, lbDiaChi, lbSDT, lbLuong,lbUsername,lbPassword;
	private JTextField tfID, tfHoTen, tfNgaySinh, tfNgay, tfThang, tfNam, tfDiachi, tfSDT, tfLuong,tfPassword,tfUsername;
	private JPanel p1, p2,p3;
	private JButton btnThem, btnHuy;
	
	private Client client;
	private KhachHang kh;
	private ClientUI clientUI;
	public EditCusView(ClientUI clientUI,Client client,KhachHang kh) {
		this.client = client;
		this.kh = kh;
		this.clientUI = clientUI;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		setTitle("Thông tin tài khoản");

		lbID       = new JLabel("ID");  
		lbHoTen    = new JLabel("Họ Tên");
		lbNgaySinh = new JLabel("Ngày Sinh");
		lbDiaChi   = new JLabel("Địa Chỉ");
		lbSDT      = new JLabel("SĐT");
		
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		
		tfID       = new JTextField(15); 	tfID.setText(kh.getId()); tfID.setEditable(false);
		tfHoTen    = new JTextField(15);	tfHoTen.setText(kh.getHoTen());
		tfNgaySinh = new JTextField(15);	tfNgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(kh.getNgaySinh()));
		tfDiachi   = new JTextField(15);	tfDiachi.setText(kh.getDiaChi());
		tfSDT      = new JTextField(15);	tfSDT.setText(kh.getsDT());
	
		tfUsername = new JTextField(15);	tfUsername.setText(kh.getUsername()); tfUsername.setEditable(false);
		tfPassword = new JTextField(15);	tfPassword.setText(kh.getPassword());
		
		btnThem    = new JButton("CẬP NHẬT ");		btnThem.addActionListener(this);
		btnHuy     = new JButton("ĐÓNG");		btnHuy.addActionListener(this);
		
		
		
		
		p1 = new JPanel(); 
		p2 = new JPanel(); 
		p3 = new JPanel();
		
		p1.setLayout(new GridLayout(8, 1, 10, 10));		p1.setBorder(new EmptyBorder(10,10,10,10));
		p2.setLayout(new GridLayout(8, 1, 10, 10));		p2.setBorder(new EmptyBorder(10,10,10,10));
		p3.setLayout(new GridLayout(1, 2, 10, 10));		p3.setBorder(new EmptyBorder(10,10,10,10));
		
		p1.add(lbID);           p2.add(tfID);
		p1.add(lbHoTen);		p2.add(tfHoTen);	 
		p1.add(lbNgaySinh);		p2.add(tfNgaySinh);	
		p1.add(lbDiaChi);		p2.add(tfDiachi);
		p1.add(lbSDT);			p2.add(tfSDT);
		
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
		}
		if (e.getSource() == btnThem) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(checkFormat() == true){
				try {
					Timestamp date = new Timestamp(format.parse(tfNgaySinh.getText()).getTime());
					
					KhachHang a = new KhachHang(tfID.getText(), tfHoTen.getText(), date,
							tfDiachi.getText(), tfSDT.getText(), kh.getCoin(),tfUsername.getText(),tfPassword.getText());
					client.editCustomer(a);
					
					dispose();
					JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
					if(tfHoTen.getText().compareTo(kh.getHoTen()) != 0) {
						clientUI.getTopPanel().remove(clientUI.getTopPanel().getComponent(0));
						JLabel lbUser = new JLabel("Xin chào bạn,  "+tfHoTen.getText()+"  !");
						lbUser.setHorizontalAlignment(JLabel.LEFT);
					
						
						
						clientUI.getTopPanel().add(lbUser,BorderLayout.CENTER);
						
						clientUI.getTopPanel().validate();
						clientUI.getTopPanel().repaint();
						
					} 
				} catch (NumberFormatException e1) {
				
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
		 
		 if(tfHoTen.getText().equals(null) || tfHoTen.getText().equals("") ||
				tfNgaySinh.getText().equals(null) || tfNgaySinh.getText().equals("") ||
				tfDiachi.getText().equals(null) || tfDiachi.getText().equals("") ||
				tfSDT.getText().equals(null) || tfSDT.getText().equals("") ||
			
				tfUsername.getText().equals(null) || tfUsername.getText().equals("") ||
				tfPassword.getText().equals(null) || tfPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Các trường dữ liệu không được để trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
			return false;
		}

		
		return true;
	}
}