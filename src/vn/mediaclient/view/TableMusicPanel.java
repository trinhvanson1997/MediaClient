package vn.mediaclient.view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vn.media.models.DiaNhac;

public class TableMusicPanel extends JPanel implements ActionListener{
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;
	private int currentPage;
	private JButton btnTrangTruoc,btnTrangSau;
	private String[] columns = { "ID", "Tên đĩa nhạc", "Thể loại", "Ca sĩ", "Số lượng tồn kho", "giá mua","giá bán","ngày nhập hàng cuối" };
	private String[] columnsClient = { "ID", "Tên đĩa nhạc", "Thể loại", "Ca sĩ", "Số lượng tồn kho", "Đơn giá" };
	public TableMusicPanel() {
		setLayout(new BorderLayout(10, 0));
		// setBorder(BorderFactory.createEtcheBorder(EtchedBorder.RAISED));
		// setBorder(BorderFactory.createEtchedBorder());
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		
		
		
		// create table
		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		loadData(table);

		// add table to scroll
		scroll = new JScrollPane();
		// scroll.setPreferredSize();
		scroll.setViewportView(table);
		//add(namePanel(),BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);
		add(pagePanel(),BorderLayout.SOUTH);
	}

//	public JPanel createTablePanel() {
//		
//		panel.add(scroll, BorderLayout.CENTER);
//		return panel;
//
//	}
	
	private JPanel namePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder(""));
		//create label
				lbName = new JLabel("DANH SÁCH SÁCH");
				lbName.setHorizontalAlignment(JLabel.CENTER);
			panel.add(lbName,BorderLayout.CENTER);
			
			return panel;
	}
	
	private JPanel pagePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		btnTrangTruoc = new JButton("Trang Trước");		btnTrangTruoc.addActionListener(this);
		btnTrangSau   = new JButton("Trang Sau");		btnTrangSau.addActionListener(this);
		
		panel.add(btnTrangTruoc, BorderLayout.WEST);
		panel.add(btnTrangSau, BorderLayout.EAST);
		
		return panel;
	}
	
	
	private void loadData(JTable table) {
		String[][] data = null;

		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(tableModel);

	}
	
	private String convertListToString(List<String> list){
		String temp ="";
		for(String s: list) {
			temp = temp+s+",";
			
		}
		if(temp.equals("")) {
			return temp;
			}
		else {
			temp=temp.substring(0, temp.length()-1);
			return temp;
		}
	}

	public void updateTableClient(List<DiaNhac> list){
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		
		String[][] data =  new String[list.size()][columnsClient.length];
		for(int i=0;i<list.size();i++){
			DiaNhac s = list.get(i);
			data[i][0] = s.getId();
			data[i][1] = s.getTenSP();
			data[i][2] = s.getTheLoai();
			data[i][3] = convertListToString(s.getCaSi());
			data[i][4] = String.valueOf(s.getSoLuongTonKho());
			//data[i][5] = format.format(s.getGiaMua()).toString();
			data[i][5] = format.format(s.getGiaBan()).toString();
			//data[i][7] =  new SimpleDateFormat("dd/MM/yyyy HH:mm::ss").format(s.getNgayNhapHangCuoi());
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, columnsClient) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		
	}

	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public JButton getBtnTrangTruoc() {
		return btnTrangTruoc;
	}

	public void setBtnTrangTruoc(JButton btnTrangTruoc) {
		this.btnTrangTruoc = btnTrangTruoc;
	}

	public JButton getBtnTrangSau() {
		return btnTrangSau;
	}

	public void setBtnTrangSau(JButton btnTrangSau) {
		this.btnTrangSau = btnTrangSau;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
