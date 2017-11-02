package vn.mediaclient.controller;

import java.text.DecimalFormat;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;

public class ClickTableClient {
	private JTable tableBook;
	private JTable tableMovie;
	private JTable tableMusic;
	
	private JTextField tfIDSanPham,tfDonGia,tfSoLuong,tfTienChoSP;
	
	public ClickTableClient(ClientUI clientUI) {
		tableBook = clientUI.getTabbedProduct().getTableBookPanel().getTable();
		tableMovie = clientUI.getTabbedProduct().getTableMoviesPanel().getTable();
		tableMusic = clientUI.getTabbedProduct().getTableMusicPanel().getTable();
		
		tfIDSanPham = clientUI.getFuncClientPanel().getTfIDSanPham();
		tfDonGia = clientUI.getFuncClientPanel().getTfDonGia();
		tfSoLuong = clientUI.getFuncClientPanel().getTfSoLuong();
		tfTienChoSP = clientUI.getFuncClientPanel().getTfTienChoSP();
		
		
		tableBook.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableBook.rowAtPoint(evt.getPoint());
		        
		        
		        //int col = tableBook.columnAtPoint(evt.getPoint());
		        
		        if (row >= 0 ) {
		        String id = tableBook.getValueAt(row, 0).toString();
		        String dongia = tableBook.getValueAt(row, 5).toString();
		        
		        tfIDSanPham.setText(id);
		        tfDonGia.setText(dongia);
		        tfSoLuong.setText("1");
		        }
		    }
		});
		
		
		tableMovie.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableMovie.rowAtPoint(evt.getPoint());
		        
		        
		        //int col = tableBook.columnAtPoint(evt.getPoint());
		        
		        if (row >= 0 ) {
		        String id = tableMovie.getValueAt(row, 0).toString();
		        String dongia = tableMovie.getValueAt(row, 5).toString();
		        
		        tfIDSanPham.setText(id);
		        tfDonGia.setText(dongia);
		        tfSoLuong.setText("1");
		        }
		    }
		});
		
		
		tableMusic.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableMusic.rowAtPoint(evt.getPoint());
		        
		        
		        //int col = tableBook.columnAtPoint(evt.getPoint());
		        
		        if (row >= 0 ) {
		        String id = tableMusic.getValueAt(row, 0).toString();
		        String dongia = tableMusic.getValueAt(row, 5).toString();
		        
		        tfIDSanPham.setText(id);
		        tfDonGia.setText(dongia);
		        tfSoLuong.setText("1");
		        }
		    }
		});
		
		tfSoLuong.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(tfSoLuong.getText().length()>0) {
					showTotal();
				}else {
					tfTienChoSP.setText("0 đ");
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showTotal();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				showTotal();
			}
			
			
			public void showTotal() {
				long dongia = Long.parseLong(convert(tfDonGia.getText()));
				int soluong = Integer.parseInt(tfSoLuong.getText());
				
				
				DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
				tfTienChoSP.setText(format.format(dongia*soluong));
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
