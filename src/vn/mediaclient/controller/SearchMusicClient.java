package vn.mediaclient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.media.models.DiaNhac;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;
import vn.mediaclient.view.TableMusicPanel;

public class SearchMusicClient {
	private TableMusicPanel tableMusicPanel;
	private JButton btnTimKiem;
	private JTextField tfSearch;
	private JComboBox<String> cbType;
	
	
	public SearchMusicClient(ClientUI clientUI) {
		
		
		btnTimKiem =clientUI.getSearchClientPanel().getBtnTimKiemNhac();
		tfSearch =clientUI.getSearchClientPanel().getTfSearchMusic();
		cbType =clientUI.getSearchClientPanel().getCbTypeMusic();
		tableMusicPanel = clientUI.getTabbedProduct().getTableMusicPanel();
		
	
		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
			List<DiaNhac> list =clientUI.getListMusic();
	
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
				if(tfSearch.getText().length()==0) {
					tableMusicPanel.updateTableClient(list);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showSearchList();
			}
			
			public void showSearchList() {
				List<DiaNhac> tempList = new ArrayList<>();
				int type 		= cbType.getSelectedIndex();
				String search    = tfSearch.getText().trim().toLowerCase();
				
				if(type == 0) {
					for(DiaNhac DiaNhac:list) {
						if(DiaNhac.getId().trim().toLowerCase().contains(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 1) {
					for(DiaNhac DiaNhac:list) {
						if(DiaNhac.getTenSP().trim().toLowerCase().contains(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 2) {
					for(DiaNhac DiaNhac:list) {
						if(DiaNhac.getTheLoai().toString().trim().toLowerCase().contains(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
				else if(type == 3) {
					for(DiaNhac DiaNhac:list) {
						
							if(convertListToString(DiaNhac.getCaSi()).trim().toLowerCase().contains(search)) {
								tempList.add(DiaNhac);
							}
						
					}
				}
				else if(type == 4) {
					for(DiaNhac DiaNhac:list) {
						int soluong = DiaNhac.getSoLuongTonKho();
						if(String.valueOf(soluong).startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
			
				else if(type == 5) {
					for(DiaNhac DiaNhac:list) {
						
						long giaban = DiaNhac.getGiaBan();
					
						if(String.valueOf(giaban).startsWith(search)) {
							tempList.add(DiaNhac);
						}
					}
				}
		
				
				tableMusicPanel.updateTableClient(tempList);
			}
			
			
		});
	}
	

	private String convertListToString(List<String> list){
		String temp ="";
		for(String s: list) {
			temp += s;
			temp += ",";
		}
		if(temp.equals("")) {
			return temp;
			}
		else {
			temp=temp.substring(0, temp.length()-1);
			return temp;
		}
	}
}
