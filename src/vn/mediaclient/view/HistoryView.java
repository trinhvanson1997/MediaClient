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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import vn.media.models.MuaHang;
import vn.media.serialization.HistoryWait;
import vn.mediaclient.client.Client;

public class HistoryView extends JDialog implements ActionListener{
private ClientUI clientUI;
	
	private List<HistoryWait> list;
	private TableHistoryPanel tableHistoryPanel;
	private Client client;
	private JScrollPane scroll;
	private JButton btnClose;
	
	public HistoryView(List<HistoryWait> list) {
		this.list = list;
		
	
		tableHistoryPanel = new TableHistoryPanel();
		tableHistoryPanel.updateTable(list);
		
		scroll = new JScrollPane();
		scroll.setViewportView(tableHistoryPanel);
		
		setSize(600, 400);
		setLayout(new BorderLayout(10,10));
		add(scroll,BorderLayout.CENTER);

       add(createButtonPanel(), BorderLayout.SOUTH);

		setTitle("Lịch sử mua hàng");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		btnClose = createButton("THOÁT");
		p.add(btnClose,BorderLayout.CENTER);
		
		
		return p;
	}
	
	
	
	private JButton createButton(String name)
	{
		JButton button = new JButton(name);
		button.addActionListener(this);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnClose) {
			dispose();
		}
		
	}



	
}
