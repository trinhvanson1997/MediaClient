package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class FuncClientPanel extends JPanel implements ActionListener{
	private JButton btnThemVaoGio;
	private JButton btnXoaKhoiGio;
	private JButton btnThanhToan;
	
	private JButton btnThongTin,btnNapTien,btnDangXuat,btnLichSu;
	
	private JLabel lbSoLuong,lbIDSanPham,lbDonGia,
					lbTamTinh,lbThueVAT,lbTongTien,lbTienChoSP;
	
	private JTextField tfIDSanPham,tfSoLuong,tfDonGia,tfTamTinh,tfThueVAT,tfTongTien,tfTienChoSP;
	
	private JPanel topPanel,leftPanel,centerPanel,rightPanel;
	
	public FuncClientPanel() {
		
		
		setLayout(new BorderLayout(10,10));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBorder(new TitledBorder("chức năng"));
		setPreferredSize(new Dimension(1000, 230));
		
		add(createTopPanel(),BorderLayout.NORTH);
		add(createLeftPanel(),BorderLayout.WEST);
		add(createCenterPanel(),BorderLayout.CENTER);
		add(createRightPanel(),BorderLayout.EAST);
		
		setVisible(true);
	}
	
	private JPanel createTopPanel() {
		topPanel=new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		btnThanhToan=createButton("XEM GIỎ HÀNG VÀ TIẾN HÀNH THANH TOÁN");
		//btnThanhToan.setIcon(new ImageIcon(getClass().getResource("/cart.png")));
		
		topPanel.add(btnThanhToan, BorderLayout.CENTER);
		topPanel.setBorder(new EmptyBorder(0, 200, 0, 200));
		return topPanel;
	}
	
	private JPanel createLeftPanel() {
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new TitledBorder(""));
		
		leftPanel.add(createTFPanel(), BorderLayout.CENTER);
		leftPanel.add(createButtonPanel(),BorderLayout.SOUTH);
		
		return leftPanel;
	}
	
	private JPanel createTFPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2,5,5));
		
		lbIDSanPham = new JLabel("Mã sản phẩm  ");	tfIDSanPham = new JTextField(15); tfIDSanPham.setEditable(false);
		lbDonGia	= new JLabel("Đơn giá      ");	tfDonGia	= new JTextField(15); tfDonGia.setEditable(false);
		lbSoLuong	= new JLabel("Số lượng     ");	tfSoLuong	= new JTextField(15);
		lbTienChoSP = new JLabel("Tổng tiền cho SP này");
		
		tfTienChoSP = new JTextField(15); tfTienChoSP.setEditable(false); 
				
		panel.add(lbIDSanPham);	panel.add(tfIDSanPham);
		panel.add(lbDonGia);	panel.add(tfDonGia);
		panel.add(lbSoLuong);	panel.add(tfSoLuong);
		//panel.add(lbTienChoSP);	panel.add(tfTienChoSP);
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		//panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		btnThemVaoGio=createButton("THÊM VÀO GIỎ ");
		//btnThemVaoGio.setIcon(new ImageIcon(getClass().getResource("/add.png")));
		//btnXoaKhoiGio=createButton("XÓA KHỎI GIỎ ");
		
		panel.add(btnThemVaoGio,BorderLayout.CENTER);
		//panel.add(btnXoaKhoiGio);
		
		return panel;
		
	}
 
	private JPanel createCenterPanel() {
		centerPanel = new JPanel(new BorderLayout(10,5));
		centerPanel.setBorder(new EmptyBorder(0, 0, 0, 100));
		//centerPanel.setBorder(new TitledBorder("Thông tin đơn hàng"));
		
		JPanel p1 = new JPanel(new GridLayout(3, 1,20,10));
		JPanel p2 = new JPanel(new GridLayout(3, 1,20,10));
		
		p1.setBorder(new EmptyBorder(10, 20, 20, 0));
		p2.setBorder(new EmptyBorder(10, 0, 20, 20));
		lbTamTinh = new JLabel("Tạm tính    ");		tfTamTinh = new JTextField(15); tfTamTinh.setEditable(false);
		lbThueVAT = new JLabel("Thuế (VAT)  ");		tfThueVAT = new JTextField(15); tfThueVAT.setEditable(false);
		lbTongTien = new JLabel("Tổng tiền  ");		tfTongTien = new JTextField(15); tfTongTien.setEditable(false);
		
		
		p1.add(lbTamTinh); 		p2.add(tfTamTinh); 
		p1.add(lbThueVAT);		p2.add(tfThueVAT); 
		p1.add(lbTongTien);		p2.add(tfTongTien); 
		
		centerPanel.add(p1,BorderLayout.WEST);
		centerPanel.add(p2,BorderLayout.CENTER);
		return centerPanel;
	}
	
	private JPanel createRightPanel() {
		rightPanel = new JPanel(new GridLayout(4, 1,5,5));
		//rightPanel.setBorder(new EmptyBorder(20, 30, 30, 30));
	
		btnLichSu = createButton("LỊCH SỬ MUA HÀNG");
		btnThongTin = createButton("THÔNG TIN TK");
		btnNapTien = createButton("NẠP TIỀN");
		btnDangXuat = createButton("ĐĂNG XUẤT");
		
		btnThongTin.setIcon(new ImageIcon(getClass().getResource("/info.png")));
		btnNapTien.setIcon(new ImageIcon(getClass().getResource("/coin.png")));
		btnDangXuat.setIcon(new ImageIcon(getClass().getResource("/log_out.png")));
		
		rightPanel.add(btnLichSu);
		rightPanel.add(btnThongTin);
		rightPanel.add(btnNapTien);
		rightPanel.add(btnDangXuat);
		
		return rightPanel;
		
	}
	
	
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	
	
	
	
	
	
	
	
	public JButton getBtnThemVaoGio() {
		return btnThemVaoGio;
	}

	public void setBtnThemVaoGio(JButton btnThemVaoGio) {
		this.btnThemVaoGio = btnThemVaoGio;
	}

	public JButton getBtnXoaKhoiGio() {
		return btnXoaKhoiGio;
	}

	public void setBtnXoaKhoiGio(JButton btnXoaKhoiGio) {
		this.btnXoaKhoiGio = btnXoaKhoiGio;
	}

	public JButton getBtnThanhToan() {
		return btnThanhToan;
	}

	public JLabel getLbTienChoSP() {
		return lbTienChoSP;
	}

	public void setLbTienChoSP(JLabel lbTienChoSP) {
		this.lbTienChoSP = lbTienChoSP;
	}

	public JTextField getTfTienChoSP() {
		return tfTienChoSP;
	}

	public void setTfTienChoSP(JTextField tfTienChoSP) {
		this.tfTienChoSP = tfTienChoSP;
	}

	public void setBtnThanhToan(JButton btnThanhToan) {
		this.btnThanhToan = btnThanhToan;
	}

	public JLabel getLbSoLuong() {
		return lbSoLuong;
	}

	public void setLbSoLuong(JLabel lbSoLuong) {
		this.lbSoLuong = lbSoLuong;
	}

	public JLabel getLbIDSanPham() {
		return lbIDSanPham;
	}

	public void setLbIDSanPham(JLabel lbIDSanPham) {
		this.lbIDSanPham = lbIDSanPham;
	}

	public JLabel getLbDonGia() {
		return lbDonGia;
	}

	public void setLbDonGia(JLabel lbDonGia) {
		this.lbDonGia = lbDonGia;
	}

	public JLabel getLbTamTinh() {
		return lbTamTinh;
	}

	public void setLbTamTinh(JLabel lbTamTinh) {
		this.lbTamTinh = lbTamTinh;
	}

	public JLabel getLbThueVAT() {
		return lbThueVAT;
	}

	public void setLbThueVAT(JLabel lbThueVAT) {
		this.lbThueVAT = lbThueVAT;
	}

	public JLabel getLbTongTien() {
		return lbTongTien;
	}

	public void setLbTongTien(JLabel lbTongTien) {
		this.lbTongTien = lbTongTien;
	}

	public JTextField getTfIDSanPham() {
		return tfIDSanPham;
	}

	public void setTfIDSanPham(JTextField tfIDSanPham) {
		this.tfIDSanPham = tfIDSanPham;
	}

	public JTextField getTfSoLuong() {
		return tfSoLuong;
	}

	public void setTfSoLuong(JTextField tfSoLuong) {
		this.tfSoLuong = tfSoLuong;
	}

	public JTextField getTfDonGia() {
		return tfDonGia;
	}

	public void setTfDonGia(JTextField tfDonGia) {
		this.tfDonGia = tfDonGia;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public JPanel getRightPanel() {
		return centerPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.centerPanel = rightPanel;
	}

	public JButton getBtnThongTin() {
		return btnThongTin;
	}

	public void setBtnThongTin(JButton btnThongTin) {
		this.btnThongTin = btnThongTin;
	}

	public JButton getBtnNapTien() {
		return btnNapTien;
	}

	public void setBtnNapTien(JButton btnNapTien) {
		this.btnNapTien = btnNapTien;
	}

	public JButton getBtnDangXuat() {
		return btnDangXuat;
	}

	public void setBtnDangXuat(JButton btnDangXuat) {
		this.btnDangXuat = btnDangXuat;
	}

	public JTextField getTfTamTinh() {
		return tfTamTinh;
	}

	public void setTfTamTinh(JTextField tfTamTinh) {
		this.tfTamTinh = tfTamTinh;
	}

	public JTextField getTfThueVAT() {
		return tfThueVAT;
	}

	public void setTfThueVAT(JTextField tfThueVAT) {
		this.tfThueVAT = tfThueVAT;
	}

	public JTextField getTfTongTien() {
		return tfTongTien;
	}

	public void setTfTongTien(JTextField tfTongTien) {
		this.tfTongTien = tfTongTien;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public JButton getBtnLichSu() {
		return btnLichSu;
	}

	public void setBtnLichSu(JButton btnLichSu) {
		this.btnLichSu = btnLichSu;
	}
	
}
