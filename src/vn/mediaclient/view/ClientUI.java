package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.MuaHang;
import vn.media.models.Sach;
import vn.mediaclient.client.Client;


public class ClientUI extends JFrame implements ActionListener{
	public String id;
	
	
	private List<MuaHang> listDH;
	
	private List<Sach > listBook;
	private List<DiaPhim> listMovie;
	private List<DiaNhac> listMusic;
	
	
	private JPanel topPanel;
	private JPanel tablePanel;
	private JPanel funcPanel;
	
	private FuncClientPanel funcClientPanel;
	private TabbedProduct tabbedProduct;
	
	private TableBookPanel tableBookPanel;
	private TableMoviesPanel tableMoviesPanel;
	private TableMusicPanel tableMusicPanel;
	
	private SearchClientPanel searchClientPanel;
	private JButton btnRefresh;
	private String username;
	private long coin;
	
	private Client client;
	
	public ClientUI(String username,Client client) {
		this.client = client;
		this.username = username;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle("MediaOne (Client)");
		
		funcClientPanel = new FuncClientPanel();
		listDH = new ArrayList<>();
		
		listBook 	= client.getAllBookFromServer();
		listMovie 	= client.getAllMoviesFromServer();
		listMusic 	= client.getAllMusicFromServer();
		
	
		
		add(createTopPanel(),BorderLayout.NORTH);
		add(funcClientPanel,BorderLayout.SOUTH);
		add(createTablePanel(),BorderLayout.CENTER);
		
		this.tableBookPanel = tabbedProduct.getTableBookPanel();
		this.tableMoviesPanel = tabbedProduct.getTableMoviesPanel();
		this.tableMusicPanel = tabbedProduct.getTableMusicPanel();
		
		initTable();
		initButtonInfo();
		initButtonLogout();
		initNapTien();
		initRefresh();
		
		KhachHang kh = client.getCus(username);
		this.id = kh.getId();
		setVisible(true);
	}
	
	private JPanel createTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		topPanel.setSize(990,30);
		
		String name = client.getCusName(username);
		System.out.println(name);
		coin = client.getCoinCus(username);
		System.out.println(coin);
		JLabel label = new JLabel("Xin chào bạn,   "+name+"  !");
		JLabel lbCoin = new JLabel("Coin : "+coin);
		
		label.setHorizontalAlignment(JLabel.LEFT);
		lbCoin.setHorizontalAlignment(JLabel.CENTER);
		
		topPanel.add(label, BorderLayout.CENTER);
		topPanel.add(lbCoin,BorderLayout.EAST);
		
		return topPanel;
	}

	
	private JPanel createTablePanel() {
		tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0,10));
		tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		 tablePanel.setPreferredSize(new Dimension(1000, 600));
		 
		btnRefresh = new JButton("Refresh");
		
		btnRefresh.addActionListener(this);
		
		tablePanel.add(btnRefresh,BorderLayout.NORTH);
		
		tabbedProduct = new TabbedProduct();
		tablePanel.add(tabbedProduct, BorderLayout.CENTER);
		
		searchClientPanel = new SearchClientPanel();
		tablePanel.add(searchClientPanel.getSearchBookPanel(),BorderLayout.SOUTH);
		
		return tablePanel;
	}

	
	public void initTable() {
	
		tableBookPanel.updateTableClient(listBook);
		tableMoviesPanel.updateTableClient(listMovie);
		tableMusicPanel.updateTableClient(listMusic);
	}
	
	private void initButtonInfo() {
		funcClientPanel.getBtnThongTin().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = client.getCus(username);
				new EditCusView(client, kh);
			}
		});
	}
	
	private void initButtonLogout() {
		funcClientPanel.getBtnDangXuat().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int click =JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION);
				if(click==JOptionPane.YES_OPTION) {

					dispose();
					new LoginBox(client);
				}
				else if(click == JOptionPane.NO_OPTION) {
					return;
				}
				
				
			}
		});
	}
	
	private void initNapTien() {
		funcClientPanel.getBtnNapTien().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String serial = JOptionPane.showInputDialog("Mời bạn nhập mã thẻ");
				
				if(client.checkSerial(serial)) {
					JOptionPane.showMessageDialog(null, "Nạp thẻ thành công");
					
					long value = client.getValueCard(serial);
					coin += value;
					
					client.updateCoin(username, coin);
					
					
					
					topPanel.remove(topPanel.getComponent(1));
					
					JLabel lbCoin = new JLabel("Coin : "+coin);
					lbCoin.setHorizontalAlignment(JLabel.CENTER);
					topPanel.add(lbCoin,BorderLayout.EAST);
					
					topPanel.validate();
					topPanel.repaint();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Nạp thẻ thất bại");
				}
				
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void initRefresh() {
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int now = tabbedProduct.getSelectedIndex();
				
				if(now == 0) {
					List<Sach> list = client.getAllBookFromServer();
					listBook = list;
					tabbedProduct.getTableBookPanel().updateTableClient(list);
				}
				else if(now == 1) {
					List<DiaPhim> list = client.getAllMoviesFromServer();
					listMovie = list;
					tabbedProduct.getTableMoviesPanel().updateTableClient(list);
				}
				else if(now == 2) {
					List<DiaNhac> list = client.getAllMusicFromServer();
					listMusic = list;
					tabbedProduct.getTableMusicPanel().updateTableClient(list);
				}
				
			}
		});
	}
	
	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public SearchClientPanel getSearchClientPanel() {
		return searchClientPanel;
	}

	public void setSearchClientPanel(SearchClientPanel searchClientPanel) {
		this.searchClientPanel = searchClientPanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JPanel getFuncPanel() {
		return funcPanel;
	}

	public void setFuncPanel(JPanel funcPanel) {
		this.funcPanel = funcPanel;
	}

	public FuncClientPanel getFuncClientPanel() {
		return funcClientPanel;
	}

	public void setFuncClientPanel(FuncClientPanel funcClientPanel) {
		this.funcClientPanel = funcClientPanel;
	}

	public TabbedProduct getTabbedProduct() {
		return tabbedProduct;
	}

	public void setTabbedProduct(TabbedProduct tabbedProduct) {
		this.tabbedProduct = tabbedProduct;
	}

	public String getUsername() {
		return username;
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TableBookPanel getTableBookPanel() {
		return tableBookPanel;
	}

	public void setTableBookPanel(TableBookPanel tableBookPanel) {
		this.tableBookPanel = tableBookPanel;
	}

	public TableMoviesPanel getTableMoviesPanel() {
		return tableMoviesPanel;
	}

	public void setTableMoviesPanel(TableMoviesPanel tableMoviesPanel) {
		this.tableMoviesPanel = tableMoviesPanel;
	}

	public TableMusicPanel getTableMusicPanel() {
		return tableMusicPanel;
	}

	public void setTableMusicPanel(TableMusicPanel tableMusicPanel) {
		this.tableMusicPanel = tableMusicPanel;
	}




	public List<MuaHang> getListDH() {
		return listDH;
	}

	public void setListDH(List<MuaHang> listDH) {
		this.listDH = listDH;
	}

	public List<Sach> getListBook() {
		return listBook;
	}

	public void setListBook(List<Sach> listBook) {
		this.listBook = listBook;
	}

	public List<DiaPhim> getListMovie() {
		return listMovie;
	}

	public void setListMovie(List<DiaPhim> listMovie) {
		this.listMovie = listMovie;
	}

	public List<DiaNhac> getListMusic() {
		return listMusic;
	}

	public void setListMusic(List<DiaNhac> listMusic) {
		this.listMusic = listMusic;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
