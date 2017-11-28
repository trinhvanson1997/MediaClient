package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	private JButton btnRefresh,btnAdvancedSearch;
	private String username;
	private long coin;
	
	private int pageBook;
	private int pageMovies;
	private int pageMusic;
	
	private Client client;



	
	public ClientUI(String username,Client client) {
		this.client = client;
		this.username = username;
		KhachHang kh = client.getCus(username);
		this.id = kh.getId();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle("MediaOne (Client)");
		setIconImage(new ImageIcon(getClass().getResource("/store.png")).getImage());
		funcClientPanel = new FuncClientPanel();
		
		listDH = new ArrayList<>();
		
		listBook 	= client.getAllBookFromServer(0); pageBook =0;
		listMovie 	= client.getAllMoviesFromServer(0); pageMovies =0;
		listMusic 	= client.getAllMusicFromServer(0); pageMusic =0;
		
	
		
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
		initAdvancedSearch();
		
		
		
		setVisible(true);
	}
	
	private JPanel createTopPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		topPanel.setSize(990,30);
		
		String name = client.getCusName(username);
		System.out.println(name);
		coin = client.getCoinCus(id);
		System.out.println(coin);
		JLabel label = new JLabel("Xin chào bạn,   "+name+"  !");
		
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		String strCoin = format.format(coin).toString();
		JLabel lbCoin = new JLabel("Coin : "+ strCoin);
		
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
		btnAdvancedSearch = new JButton("TÌM KIẾM NÂNG CAO");
		
		btnRefresh.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
		btnRefresh.addActionListener(this);
		btnAdvancedSearch.addActionListener(this);
		
		JPanel pButton = new JPanel(new GridLayout(1, 2,20,0));
		pButton.add(btnRefresh);
		pButton.add(btnAdvancedSearch);
		
		tablePanel.add(pButton,BorderLayout.NORTH);
		
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
				new EditCusView(ClientUI.this,client, kh);
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
				
				if(serial.equals(null) || serial.equals("")) {
					return;
				}
				else {
					if(client.checkSerial(serial)) {
						
						
						long value = client.getValueCard(serial);
						
						coin += value;
						
						client.updateCoin(username, coin);
						
						
						
						topPanel.remove(topPanel.getComponent(1));
						DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
						String strCoin = format.format(coin).toString();
						
						JLabel lbCoin = new JLabel("Coin : "+strCoin);
						lbCoin.setHorizontalAlignment(JLabel.LEFT);
						topPanel.add(lbCoin,BorderLayout.EAST);
						
						topPanel.validate();
						topPanel.repaint();
						JOptionPane.showMessageDialog(null, "Nạp thẻ thành công");
					}
					else {
						JOptionPane.showMessageDialog(null, "Nạp thẻ thất bại");
					}
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
					List<Sach> list = client.getAllBookFromServer(pageBook);
					listBook = list;
					tabbedProduct.getTableBookPanel().updateTableClient(list);
				}
				else if(now == 1) {
					List<DiaPhim> list = client.getAllMoviesFromServer(pageMovies);
					listMovie = list;
					tabbedProduct.getTableMoviesPanel().updateTableClient(list);
				}
				else if(now == 2) {
					List<DiaNhac> list = client.getAllMusicFromServer(pageMusic);
					listMusic = list;
					tabbedProduct.getTableMusicPanel().updateTableClient(list);
				}
				
			}
		});
	}
	
	public void initAdvancedSearch() {
	btnAdvancedSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int now = tabbedProduct.getSelectedIndex();
				
				if(now == 0) {
					new AdvancedSearchBookView(ClientUI.this, client);
				}
				else if(now == 1) {
					
				}
				else if(now == 2) {
			
				}
				
			}
		});
	}
	
	
	public void display() {
		System.out.println("List CLIENT");
		for(MuaHang m : listDH) {
		
			System.out.println(m.getIdSanPham());
		}
	}
	
	
	
	
	
	
	
	public int getPageBook() {
		return pageBook;
	}

	public void setPageBook(int pageBook) {
		this.pageBook = pageBook;
	}

	public int getPageMovies() {
		return pageMovies;
	}

	public void setPageMovies(int pageMovies) {
		this.pageMovies = pageMovies;
	}

	public int getPageMusic() {
		return pageMusic;
	}

	public void setPageMusic(int pageMusic) {
		this.pageMusic = pageMusic;
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

	public JButton getBtnAdvancedSearch() {
		return btnAdvancedSearch;
	}

	public void setBtnAdvancedSearch(JButton btnAdvancedSearch) {
		this.btnAdvancedSearch = btnAdvancedSearch;
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
