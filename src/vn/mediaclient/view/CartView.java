package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.models.MuaHang;
import vn.mediaclient.client.Client;

public class CartView extends JDialog implements ActionListener{
	private ClientUI clientUI;
	
	private List<MuaHang> listDH;
	
	private JButton btnDatHang;
	private JButton btnXoaKhoiGio;
	private JButton btnThoat;
	private TableCart tableCart;
	private long coin;
	private String username;
	private JPanel topPanel;
	private JTextField tfTamTinh,tfThueVAT,tfTongTien;
	private JTextField tfIDSanPham,tfDonGia,tfSoLuong,tfTienChoSP;
	 private JProgressBar progressBar; 
	 
	private TableBookPanel tableBookPanel;
	private TableMoviesPanel tableMoviesPanel;
	private TableMusicPanel tableMusicPanel;
	
	private Client client;
	
	public CartView(ClientUI clientUI,Client client) {
		this.clientUI = clientUI;
		this.client = client;
		
		this.listDH = clientUI.getListDH();
		
		this.coin 		= clientUI.getCoin();
		this.username 	= clientUI.getUsername();
		this.topPanel 	= clientUI.getTopPanel();
		
		this.tfTamTinh 		= clientUI.getFuncClientPanel().getTfTamTinh();
		this.tfThueVAT		= clientUI.getFuncClientPanel().getTfThueVAT();
		this.tfTongTien		= clientUI.getFuncClientPanel().getTfTongTien();
		
		this.tfIDSanPham 	= clientUI.getFuncClientPanel().getTfIDSanPham();
		this.tfDonGia 		= clientUI.getFuncClientPanel().getTfDonGia();
		this.tfSoLuong 		= clientUI.getFuncClientPanel().getTfSoLuong();
		this.tfTienChoSP 	= clientUI.getFuncClientPanel().getTfTienChoSP();
		
		this.tableBookPanel 	=  clientUI.getTabbedProduct().getTableBookPanel();
		this.tableMoviesPanel 	=  clientUI.getTabbedProduct().getTableMoviesPanel();
		this.tableMusicPanel 	=  clientUI.getTabbedProduct().getTableMusicPanel();
		
		tableCart = new TableCart();
		tableCart.updateTable(listDH);
		setSize(400, 400);
		setLayout(new BorderLayout(10,10));
		add(tableCart,BorderLayout.CENTER);

       add(createButtonPanel(), BorderLayout.SOUTH);

		setTitle("Giỏ hàng");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(1, 3,10,10));
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		btnDatHang = createButton("ĐẶT HÀNG");
		btnXoaKhoiGio = createButton("XÓA KHỎI GIỎ");
		btnThoat = createButton("THOÁT");
		
		p.add(btnDatHang); p.add(btnXoaKhoiGio); p.add(btnThoat);
		
		return p;
	}
	
	
	
	private JButton createButton(String name)
	{
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnThoat) {
			dispose();
		}
		
		if(e.getSource() == btnXoaKhoiGio) {
			int index = tableCart.getTable().getSelectedRow();
			
			if(index >=0) {
				listDH.remove(index);
				clientUI.setListDH(listDH);
				
				long tienTamTinh = 0;
				for(MuaHang m: listDH) {
					tienTamTinh += m.getDonGia()*m.getSoLuong();
				}
				
			
				DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
				
				tfTamTinh.setText(format.format(tienTamTinh));
				tfThueVAT.setText("0%");
				//tfTongTien.setText(String.valueOf(tienTamTinh + tienTamTinh*0.1));
				
				tfTongTien.setText(String.valueOf(tienTamTinh));
				
				tableCart.updateTable(listDH);
			}
			else if(listDH.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Không có sản phẩm nào để xóa ");
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm nào để xóa ");
			}
		}
		
		if(e.getSource() == btnDatHang) {
		
//			progressBar = new JProgressBar();
//	        
//	        progressBar.setStringPainted(true);
//	      
//	        progressBar.setBorderPainted(true);
//			 progressBar.setString("Please wait ...");
//		        progressBar.setIndeterminate(true);
//		        progressBar.setVisible(true);
//		        
//		       
//		        this.add(progressBar,BorderLayout.NORTH);
//		        this.validate();
//		        this.repaint();
			
			if(listDH.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Giỏ hàng rỗng, vui lòng chọn hàng để đặt");
			}
			else {
				
				action();
			}
	
		}
	}
	
	public void action() {
		
		
		
		System.out.println("list before send order");
		for(MuaHang mh :listDH) {
			System.out.println(mh.getIdSanPham());
		}
		long tienTamTinh = 0;
		for(MuaHang m: listDH) {
			tienTamTinh += m.getDonGia()*m.getSoLuong();
		}
		System.out.println("tien tam tinh: "+tienTamTinh);
		
		///////////////////////////////////////////////////
		//tienTamTinh += tienTamTinh*0.1;
		
		if(coin < tienTamTinh) {
			JOptionPane.showMessageDialog(null, "Bạn không đủ tiền để thực hiện giao dịch", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		}
		else {

		       
			System.out.println("list before send order");
			for(MuaHang mh :listDH) {
				System.out.println(mh.getIdSanPham());
			}
		
			if(client.sendOrderRequest(listDH,clientUI.id)) {
		
                
				JOptionPane.showMessageDialog(null, "Bạn đã đặt hàng thành công");
				dispose();
			
				
				coin = coin -tienTamTinh;
				System.out.println("coin after minus: "+ coin);
				clientUI.setCoin(coin);
				
				//update coin of customer in database
				client.updateCoin(username, coin);

				//update value coin shown on top panel
				topPanel.remove(topPanel.getComponent(1));
				DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
				String strCoin = format.format(coin).toString();
				
				JLabel lbCoin = new JLabel("Coin : "+strCoin);
				lbCoin.setHorizontalAlignment(JLabel.CENTER);
				topPanel.add(lbCoin,BorderLayout.EAST);
				
				topPanel.validate();
				topPanel.repaint();
			/*
				//update the number of product in table sanpham
				for(MuaHang dh:listDH) {
					String idsanpham = dh.getIdSanPham();
					int soluong = client.getSoLuongTonKho(idsanpham) - dh.getSoLuong();
					
					client.updateNumberProduct(idsanpham,soluong);
				}
				
			
				
				//update the number shown in 3 table
				clientUI.setListBook(client.getAllBookFromServer(clientUI.getPageBook()));
				clientUI.setListMovie(client.getAllMoviesFromServer(clientUI.getPageMovies()));
				clientUI.setListMusic(client.getAllMusicFromServer(clientUI.getPageMusic()));
				
				clientUI.initTable();
				*/
				
				//reset value in text field
				tfIDSanPham.setText(null);
				tfDonGia.setText(null);
				tfSoLuong.setText(null);
				tfTienChoSP.setText(null);
				
				tfTamTinh.setText(null);
				tfTongTien.setText(null);
				
				
			
				
				//update list in listMH
				listDH.removeAll(listDH);
				clientUI.setListDH(listDH);
				tableCart.updateTable(listDH);

			}
			else {
				JOptionPane.showMessageDialog(null, "Đặt hàng thất bại");
			}
			
			
			
		}
	}

}
