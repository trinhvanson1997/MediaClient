package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.models.DiaNhac;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;

public class TableMusicController {
private JButton btnBack,btnNext;
	
	public TableMusicController(ClientUI mainFrame,Client db) {
	btnBack = mainFrame.getTabbedProduct().getTableMusicPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageMusic();
				
				if( page > 0) {
					mainFrame.setPageMusic(page-1);
					List<DiaNhac> list = db.getAllMusicFromServer(page-1);
					mainFrame.getTabbedProduct().getTableMusicPanel().updateTableClient(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTabbedProduct().getTableMusicPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageMovies();
				
				if(page< db.getCountMusic()/20 ) {
					mainFrame.setPageMusic(page+1);
					List<DiaNhac> list = db.getAllMusicFromServer(page+1);
					mainFrame.getTabbedProduct().getTableMusicPanel().updateTableClient(list);
				}
				
			}
		});
	}
}
