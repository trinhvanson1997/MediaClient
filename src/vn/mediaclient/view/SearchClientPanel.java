package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchClientPanel implements ActionListener {
	
	private JButton btnTimKiemSach,btnTimKiemPhim,btnTimKiemNhac;
	private JComboBox<String> cbTypeBook,cbTypeMovie,cbTypeMusic;
	private JTextField tfSearchBook,tfSearchMovie,tfSearchMusic;
	
	private String[] sBook =  {"ID", "Tên Sách", "Nhà xuất bản", "Tác giả", "Số lượng tồn kho", "Đơn giá"};
	private String[] sMovie =  { "ID", "Tên đĩa phim", "Đạo diễn", "Diễn viên", "Số lượng tồn kho","Đơn giá"};
	private String[] sMusic  =  { "ID", "Tên đĩa nhạc", "Thể loại", "Ca sĩ", "Số lượng tồn kho","Đơn giá" };
	
	private JPanel searchBookPanel;
	private JPanel searchMoviePanel;
	private JPanel searchMusicPanel;
	
	
	public SearchClientPanel() {
		searchBookPanel = createSearchBookPanel();
		searchMoviePanel = createSearchMoviePanel();
		searchMusicPanel = createSearchMusicPanel();
		
	}
	
	public JPanel createSearchBookPanel() {
		btnTimKiemSach  = createButtons("TÌM KIẾM SÁCH");
		btnTimKiemSach.setIcon(new ImageIcon(getClass().getResource("/search.png")));
		
		cbTypeBook = new JComboBox<>(sBook);
		tfSearchBook = new JTextField(30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(20,0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BorderLayout(20,0));
		
		panelBtn.add(new JLabel("Tìm kiếm theo:"),BorderLayout.WEST);
		panelBtn.add(cbTypeBook,BorderLayout.CENTER);
		panelBtn.add(btnTimKiemSach,BorderLayout.EAST);
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearchBook, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	public JPanel createSearchMoviePanel() {
		btnTimKiemPhim  = createButtons("TÌM KIẾM PHIM");
		cbTypeMovie = new JComboBox<>(sMovie);
		tfSearchMovie = new JTextField(30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(20,0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BorderLayout(20,0));
		
		panelBtn.add(new JLabel("Tìm kiếm theo:"),BorderLayout.WEST);
		panelBtn.add(cbTypeMovie,BorderLayout.CENTER);
		panelBtn.add(btnTimKiemPhim,BorderLayout.EAST);
		btnTimKiemPhim.setIcon(new ImageIcon(getClass().getResource("/search.png")));
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearchMovie, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	public JPanel createSearchMusicPanel() {
		btnTimKiemNhac  = createButtons("TÌM KIẾM NHẠC");
		btnTimKiemNhac.setIcon(new ImageIcon(getClass().getResource("/search.png")));
		
		cbTypeMusic = new JComboBox<>(sMusic);
		tfSearchMusic = new JTextField(30);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(20,0));
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BorderLayout(20,0));
		
		panelBtn.add(new JLabel("Tìm kiếm theo:"),BorderLayout.WEST);
		panelBtn.add(cbTypeMusic,BorderLayout.CENTER);
		panelBtn.add(btnTimKiemNhac,BorderLayout.EAST);
		
		panel.add(new JLabel("Ô tìm kiếm:"),BorderLayout.WEST);
		panel.add(tfSearchMusic, BorderLayout.CENTER);
		panel.add(panelBtn,BorderLayout.EAST);
		return panel;
	}
	
	public JButton createButtons(String name){
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public JButton getBtnTimKiemSach() {
		return btnTimKiemSach;
	}

	public void setBtnTimKiemSach(JButton btnTimKiemSach) {
		this.btnTimKiemSach = btnTimKiemSach;
	}

	public JButton getBtnTimKiemPhim() {
		return btnTimKiemPhim;
	}

	public void setBtnTimKiemPhim(JButton btnTimKiemPhim) {
		this.btnTimKiemPhim = btnTimKiemPhim;
	}

	public JButton getBtnTimKiemNhac() {
		return btnTimKiemNhac;
	}

	public void setBtnTimKiemNhac(JButton btnTimKiemNhac) {
		this.btnTimKiemNhac = btnTimKiemNhac;
	}

	public JComboBox<String> getCbTypeBook() {
		return cbTypeBook;
	}

	public void setCbTypeBook(JComboBox<String> cbTypeBook) {
		this.cbTypeBook = cbTypeBook;
	}

	public JComboBox<String> getCbTypeMovie() {
		return cbTypeMovie;
	}

	public void setCbTypeMovie(JComboBox<String> cbTypeMovie) {
		this.cbTypeMovie = cbTypeMovie;
	}

	public JComboBox<String> getCbTypeMusic() {
		return cbTypeMusic;
	}

	public void setCbTypeMusic(JComboBox<String> cbTypeMusic) {
		this.cbTypeMusic = cbTypeMusic;
	}

	public JTextField getTfSearchBook() {
		return tfSearchBook;
	}

	public void setTfSearchBook(JTextField tfSearchBook) {
		this.tfSearchBook = tfSearchBook;
	}

	public JTextField getTfSearchMovie() {
		return tfSearchMovie;
	}

	public void setTfSearchMovie(JTextField tfSearchMovie) {
		this.tfSearchMovie = tfSearchMovie;
	}

	public JTextField getTfSearchMusic() {
		return tfSearchMusic;
	}

	public void setTfSearchMusic(JTextField tfSearchMusic) {
		this.tfSearchMusic = tfSearchMusic;
	}

	public String[] getsBook() {
		return sBook;
	}

	public void setsBook(String[] sBook) {
		this.sBook = sBook;
	}

	public String[] getsMovie() {
		return sMovie;
	}

	public void setsMovie(String[] sMovie) {
		this.sMovie = sMovie;
	}

	public String[] getsMusic() {
		return sMusic;
	}

	public void setsMusic(String[] sMusic) {
		this.sMusic = sMusic;
	}

	public JPanel getSearchBookPanel() {
		return searchBookPanel;
	}

	public void setSearchBookPanel(JPanel searchBookPanel) {
		this.searchBookPanel = searchBookPanel;
	}

	public JPanel getSearchMoviePanel() {
		return searchMoviePanel;
	}

	public void setSearchMoviePanel(JPanel searchMoviePanel) {
		this.searchMoviePanel = searchMoviePanel;
	}

	public JPanel getSearchMusicPanel() {
		return searchMusicPanel;
	}

	public void setSearchMusicPanel(JPanel searchMusicPanel) {
		this.searchMusicPanel = searchMusicPanel;
	}
	
	
}
