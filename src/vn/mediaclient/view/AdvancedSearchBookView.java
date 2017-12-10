package vn.mediaclient.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.media.models.Sach;
import vn.mediaclient.client.Client;

public class AdvancedSearchBookView extends JDialog implements ActionListener{
	private JLabel lbTenSach,lbNXB,lbTacGia;
	private JTextField tfTenSach,tfNXB,tfTacGia;
	private JButton btnTimKiem,btnDong;
	private ClientUI clientUI;
	private Client client;
	private TableBookPanel tableBookPanel;
	
	public AdvancedSearchBookView(ClientUI clientUI,Client client) {
		this.clientUI = clientUI;
		this.client = client;
		tableBookPanel = clientUI.getTableBookPanel();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		
		setTitle("Tìm kiếm nâng cao");
		
		add(createLabels(),BorderLayout.WEST);
		add(createButtons(),BorderLayout.SOUTH);
		add(createTextFields(),BorderLayout.CENTER);
		
		
		pack();
		setVisible(true);
	}
	
	private JPanel createLabels() {
		JPanel p = new JPanel(new GridLayout(3, 1,5,5));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		p.add(new JLabel("Tên sách      "));
		p.add(new JLabel("Nhà xuất bản  "));
		p.add(new JLabel("Tác giả       "));
		return p;
	}
	
	private JPanel createTextFields() {
		JPanel p = new JPanel(new GridLayout(3, 1,5,5));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		tfTenSach = new JTextField(15);
		tfNXB	  = new JTextField(15);
		tfTacGia  = new JTextField(15);
		
		p.add(tfTenSach);
		p.add(tfNXB);
		p.add(tfTacGia);
		return p;
	}
	
	private JPanel createButtons() {
		JPanel p2 = new JPanel(new BorderLayout(5,5));
		
		JPanel p = new JPanel(new GridLayout(1, 2,5,5));
		p.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnTimKiem = new JButton("TÌM KIẾM"); btnTimKiem.addActionListener(this);
		btnDong    = new JButton("ĐÓNG    "); btnDong.addActionListener(this);
		
		JLabel label = new JLabel("* Chú ý: Các tác giả, nxb cách nhau bởi dấu phẩy");
		label.setForeground(Color.RED);
		
		Font font = label.getFont().deriveFont(Font.ITALIC, 13f);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(btnTimKiem);
		p.add(btnDong);
		
		p2.add(p,BorderLayout.CENTER);
		p2.add(label,BorderLayout.NORTH);
		return p2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnDong) {
			dispose();
		}
		
		if(e.getSource() == btnTimKiem) {
			String tensach = tfTenSach.getText();
			String nxb 	   = tfNXB.getText();
			String tacgia = tfTacGia.getText();
			
			if( tensach.equals("") && nxb.equals("") && tacgia.equals("")) {
				
				dispose();
			}
			else if(tensach.compareTo("") != 0 && nxb.compareTo("") != 0 && tacgia.equals("")) {
				List<Sach> list = client.getBookByNameAndPublisher(tensach.toLowerCase(), nxb.toLowerCase());
				tableBookPanel.updateTableClient(list);
			//	dispose();
			}
			else if(tensach.compareTo("") != 0 && nxb.equals("") && tacgia.compareTo("") != 0) {
				List<Sach> list = client.getBookByNameAndAuthor(tensach.toLowerCase(), tacgia.toLowerCase());
				tableBookPanel.updateTableClient(list);
			//	dispose();
			}
			else if(tensach.equals("") && nxb.compareTo("") != 0 && tacgia.compareTo("") != 0) {
				List<Sach> list = client.getBookByPublisherAndAuthor(nxb.toLowerCase(), tacgia.toLowerCase());
				tableBookPanel.updateTableClient(list);
			//	dispose();
			}
			else if(tensach.compareTo("") != 0 && nxb.compareTo("") != 0 && tacgia.compareTo("") != 0) {
				List<Sach> list = client.getBookByNameAndPublisherAndAuthor(tensach.toLowerCase(), nxb.toLowerCase(),tacgia.toLowerCase());
				tableBookPanel.updateTableClient(list);
			//	dispose();
			}
			else if((tensach.compareTo("") != 0 && nxb.equals("") && tacgia.equals(""))  ||
					(tensach.equals("")  && nxb.compareTo("") != 0 && tacgia.equals(""))  ||
					(tensach.equals("") && nxb.equals("") && tacgia.compareTo("") !=0)
					
					) {
				System.out.println(tacgia.toLowerCase());
				List<Sach> list = client.getBookByNameOrPublisherOrAuthor(tensach.toLowerCase(), nxb.toLowerCase(),tacgia.toLowerCase());
				tableBookPanel.updateTableClient(list);
				//dispose();
			}
			
		}
		
	}
}
