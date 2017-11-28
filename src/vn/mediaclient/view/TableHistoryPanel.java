package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import vn.media.models.MuaHang;
import vn.media.serialization.HistoryWait;

public class TableHistoryPanel extends JPanel{
	private JTable table;
	private JLabel lbName;
	private JScrollPane scroll;

	private String[] columns = {"Tên sản phẩm","Số lượng","Ngày đặt mua","Trạng thái"};

	public TableHistoryPanel() {
		setLayout(new BorderLayout(10, 0));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		loadData(table);


		scroll = new JScrollPane();
		scroll.setViewportView(table);
		add(scroll, BorderLayout.CENTER);
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

	public void updateTable(List<HistoryWait> list){
		DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("vi","VN"));
		String[][] data =  new String[list.size()][columns.length];
		for(int i=0;i<list.size();i++){
			HistoryWait s = list.get(i);
		
			
			data[i][0] = s.getName();
			data[i][1] = String.valueOf(s.getSoLuong());
			data[i][2] = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(s.getNgayMua());
			data[i][3] = s.getTrangthai();
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		
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
}
