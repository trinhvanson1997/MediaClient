package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.models.DiaPhim;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;

public class TableMoviesController {
private JButton btnBack,btnNext;
	
	public TableMoviesController(ClientUI mainFrame,Client db) {
	btnBack = mainFrame.getTabbedProduct().getTableMoviesPanel().getBtnTrangTruoc();
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page =mainFrame.getPageMovies();
				
				if( page > 0) {
					mainFrame.setPageMovies(page-1);
					List<DiaPhim> list = db.getAllMoviesFromServer(page-1);
					mainFrame.getTabbedProduct().getTableMoviesPanel().updateTableClient(list);
				}
				
			}
		});
		
		btnNext = mainFrame.getTabbedProduct().getTableMoviesPanel().getBtnTrangSau();
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int page = mainFrame.getPageMovies();
				
				if(page< db.getCountMovies()/20 ) {
					mainFrame.setPageMovies(page+1);
					List<DiaPhim> list = db.getAllMoviesFromServer(page+1);
					mainFrame.getTabbedProduct().getTableMoviesPanel().updateTableClient(list);
				}
				
			}
		});
	}
}
