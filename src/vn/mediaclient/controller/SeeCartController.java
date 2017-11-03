package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import vn.media.models.MuaHang;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.CartView;
import vn.mediaclient.view.ClientUI;

public class SeeCartController {
	
	private JButton btnThanhToan;
	public List<MuaHang> listDH ;
	
	public SeeCartController(ClientUI clientUI,Client client) {
		btnThanhToan = clientUI.getFuncClientPanel().getBtnThanhToan();
		this.listDH = clientUI.getListDH();
		
		
		btnThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listDH.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Giỏ hàng trống, vui lòng chọn hàng để xem", null, JOptionPane.WARNING_MESSAGE);
				}
				else {
					new CartView(clientUI,client);
				}
				
			}
		});
	}
}
