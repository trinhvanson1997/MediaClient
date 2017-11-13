package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.models.Sach;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;

public class TableBookController {
private JButton btnBack,btnNext;
	
	public TableBookController(ClientUI clientUI,Client client) {
	btnBack = clientUI.getTabbedProduct().getTableBookPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =clientUI.getPageBook();
				
				if( page > 0) {
					clientUI.setPageBook(page-1);
					List<Sach> list = client.getAllBookFromServer(page-1);
					clientUI.getTabbedProduct().getTableBookPanel().updateTableClient(list);
				}
				
			}
		});
		
		btnNext = clientUI.getTabbedProduct().getTableBookPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = clientUI.getPageBook();
				
				if(page< client.getCountBook()/20 ) {
					clientUI.setPageBook(page+1);
					List<Sach> list = client.getAllBookFromServer(page+1);
					clientUI.getTabbedProduct().getTableBookPanel().updateTableClient(list);
				}
				
			}
		});
	}
}
