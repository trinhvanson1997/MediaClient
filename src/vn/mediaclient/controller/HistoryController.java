package vn.mediaclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import vn.media.serialization.HistoryWait;
import vn.mediaclient.client.Client;
import vn.mediaclient.view.ClientUI;
import vn.mediaclient.view.HistoryView;

public class HistoryController {
	private JButton btnLichSu;
	public HistoryController(ClientUI clientUI, Client client) {
			btnLichSu = clientUI.getFuncClientPanel().getBtnLichSu();
			btnLichSu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					List<HistoryWait> list = client.getHistory(clientUI.id);
					
					new HistoryView(list);
				}
			});
	}
}
