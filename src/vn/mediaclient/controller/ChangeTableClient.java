package vn.mediaclient.controller;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.Sach;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;
import vn.mediaclient.view.SearchClientPanel;
import vn.mediaclient.view.TabbedProduct;
import vn.mediaclient.view.TableBookPanel;
import vn.mediaclient.view.TableMoviesPanel;
import vn.mediaclient.view.TableMusicPanel;


public class ChangeTableClient {
	private TabbedProduct tabbedProduct;
	private JPanel tablePanel; 
	private SearchClientPanel searchClientPanel;
	private TableBookPanel tableBookPanel;
	private TableMoviesPanel tableMoviesPanel;
	private TableMusicPanel tableMusicPanel;
	
	
	public ChangeTableClient(ClientUI clientUI) {
		tabbedProduct = clientUI.getTabbedProduct();
		tablePanel = clientUI.getTablePanel();
		searchClientPanel = clientUI.getSearchClientPanel();
		
		tableBookPanel 		= clientUI.getTabbedProduct().getTableBookPanel();
		tableMoviesPanel	=clientUI.getTabbedProduct().getTableMoviesPanel();
		tableMusicPanel		=clientUI.getTabbedProduct().getTableMusicPanel();
		
		tabbedProduct.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if(tabbedProduct.getSelectedIndex() == 0) {
	            	tablePanel.remove(tablePanel.getComponent(2));
					tablePanel.add(searchClientPanel.getSearchBookPanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
					
					List<Sach> l = clientUI.getListBook();
					tableBookPanel.updateTableClient(l);
	            }
	            else if(tabbedProduct.getSelectedIndex() == 1) {
	            
	            	tablePanel.remove(tablePanel.getComponent(2));
					tablePanel.add(searchClientPanel.getSearchMoviePanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
					
					List<DiaPhim> l = clientUI.getListMovie();
					tableMoviesPanel.updateTableClient(l);
					
	            }
	            else if(tabbedProduct.getSelectedIndex() == 2) {
	            	tablePanel.remove(tablePanel.getComponent(2));
					tablePanel.add(searchClientPanel.getSearchMusicPanel(), BorderLayout.SOUTH);
					tablePanel.revalidate();
					tablePanel.repaint();
					
					List<DiaNhac> l = clientUI.getListMusic();
					tableMusicPanel.updateTableClient(l);
	            }
	        }
	    });
	}
}
