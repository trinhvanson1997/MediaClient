package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vn.media.models.MuaHang;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;

public class AddProductClient {
	private JButton btnThemVaoGIo;
	
	private JTextField tfIDSanPham,tfDonGia,tfSoLuong,tfTienChoSP;
	private JTextField tfTamTinh,tfThueVAT,tfTongTien;
	
	private List<MuaHang> listDH;
	private Client client;
	
	public AddProductClient(ClientUI clientUI ,Client client) {
		this.client = client;
		
		btnThemVaoGIo 	= clientUI.getFuncClientPanel().getBtnThemVaoGio();
		
		tfIDSanPham 	= clientUI.getFuncClientPanel().getTfIDSanPham();
		tfDonGia 		= clientUI.getFuncClientPanel().getTfDonGia();
		tfSoLuong 		= clientUI.getFuncClientPanel().getTfSoLuong();
		tfTienChoSP 	= clientUI.getFuncClientPanel().getTfTienChoSP();
		
		tfTamTinh 		= clientUI.getFuncClientPanel().getTfTamTinh();
		tfThueVAT		= clientUI.getFuncClientPanel().getTfThueVAT();
		tfTongTien		= clientUI.getFuncClientPanel().getTfTongTien();
		
		
		this.listDH = clientUI.getListDH();
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		
		btnThemVaoGIo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int soluongtonkho =client.getSoLuongTonKho(tfIDSanPham.getText());
				System.out.println("so luong ton kho:"+soluongtonkho);
				
				if(tfIDSanPham.getText().equals(null) || tfIDSanPham.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm !", null, JOptionPane.WARNING_MESSAGE);
				}
				
				else if(tfSoLuong.getText().equals(null) || tfSoLuong.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Số lượng không dược bỏ trống !", null, JOptionPane.WARNING_MESSAGE);
				}
				else if(Integer.parseInt(tfSoLuong.getText())==0) {
					JOptionPane.showMessageDialog(null, "Số lượng không dược bằng 0 !", null, JOptionPane.WARNING_MESSAGE);
				}
				else if(soluongtonkho == 0) {
					JOptionPane.showMessageDialog(null, "Sản phẩm này tạm thời hết hàng. Vui lòng chọn sản phẩm khác", null, JOptionPane.WARNING_MESSAGE);
				}
				else {
					for(MuaHang dh:listDH) {
						if(dh.getIdSanPham().equals(tfIDSanPham.getText())) {
							JOptionPane.showMessageDialog(null, "Sản phẩm này đã ở trong giỏ", null, JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					if(Integer.parseInt(tfSoLuong.getText()) > soluongtonkho) {
						JOptionPane.showMessageDialog(null, "Số lượng đặt mua vượt quá số lượng trong kho !", null, JOptionPane.WARNING_MESSAGE);
					}
					else {
					
						long dongia = Long.parseLong(convert(tfDonGia.getText()));
						
						MuaHang dh = new MuaHang(tfIDSanPham.getText(),Integer.parseInt(tfSoLuong.getText()),dongia);
						
						listDH.add(dh);
						
						long tienTamTinh = 0;
						for(MuaHang d: listDH) {
							tienTamTinh += d.getDonGia()*d.getSoLuong();
						}
						
						tfTamTinh.setText(format.format(tienTamTinh));
						tfThueVAT.setText("0%");
						//tfTongTien.setText(String.valueOf(tienTamTinh + tienTamTinh*0.1));
						
						tfTongTien.setText(String.valueOf(tienTamTinh));
						
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						
					
					}
					
				}
			}
		});
	}
	
	public String convert(String s) {
		String temp="";
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == '.' || s.charAt(i) == ' ' || s.charAt(i)== 'đ') {
				continue;
			}
			else {
				temp += s.charAt(i);
			}
		}
		return temp;
	}
}
