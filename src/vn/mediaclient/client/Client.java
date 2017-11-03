package vn.mediaclient.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JOptionPane;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.Sach;
import vn.mediaclient.controller.AddProductClient;
import vn.mediaclient.controller.ChangeTableClient;
import vn.mediaclient.controller.ClickTableClient;
import vn.mediaclient.controller.SearchBookClient;
import vn.mediaclient.controller.SearchMovieClient;
import vn.mediaclient.controller.SearchMusicClient;
import vn.mediaclient.controller.SeeCartController;
import vn.mediaclient.view.ClientUI;

public class Client {
	public static final int LOGIN = 1, LOGIN_SUCCESS = 2, LOGIN_FAIL = 3, GET_ALL_BOOK = 4, GET_ALL_MOVIE = 5,
			GET_ALL_MUSIC = 6, GET_CUSTOMER_NAME = 7, GET_COIN_CUS = 8, GET_SLTK = 9, UPDATE_COIN = 10,
			UPDATE_NUMBER_PRODUCT = 11, UPDATE_CUSTOMER_INFO = 12, GET_CUSTOMER = 13, CHECK_SERIAL = 14,
			GET_VALUE_CARD = 15, CHECK_EXIST_USERNAME = 16, ADD_CUSTOMER = 17, CLOSE_REQUEST = 18;

	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;

	public Client() {
		try {
			
			socket = new Socket("localhost", 2000);
			
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Error 404");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loginSuccess(String username) {

		getAllBookFromServer();
		ClientUI clientUI = new ClientUI(username, this);
		new ChangeTableClient(clientUI);
		new SearchBookClient(clientUI);
		new SearchMovieClient(clientUI);
		new SearchMusicClient(clientUI);
		new ClickTableClient(clientUI);
		new AddProductClient(clientUI, this);
		new SeeCartController(clientUI, this);

		clientUI.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				sendCloseRequest();
				clientUI.dispose();
			}
		});
	}

	public void sendCloseRequest() {
		try {
			out.writeInt(CLOSE_REQUEST);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean sendLoginRequest(String user, String pass) {
		try {
			out.writeInt(LOGIN);
			out.flush();
			out.writeUTF(user);
			out.flush();
			out.writeUTF(pass);
			out.flush();

			int stt = in.readInt();
			if (stt == LOGIN_SUCCESS) {

				return true;

			} else if (stt == LOGIN_FAIL) {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public List<Sach> getAllBookFromServer() {
		try {
			out.writeInt(GET_ALL_BOOK);
			out.flush();

			List<Sach> list = (List<Sach>) ois.readObject();

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<DiaPhim> getAllMoviesFromServer() {
		// TODO Auto-generated method stub
		try {
			out.writeInt(GET_ALL_MOVIE);
			out.flush();

			List<DiaPhim> list = (List<DiaPhim>) ois.readObject();
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<DiaNhac> getAllMusicFromServer() {
		// TODO Auto-generated method stub
		try {
			out.writeInt(GET_ALL_MUSIC);
			out.flush();

			List<DiaNhac> list = (List<DiaNhac>) ois.readObject();
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String getCusName(String username) {
		try {
			out.writeInt(GET_CUSTOMER_NAME);
			out.flush();

			out.writeUTF(username);
			out.flush();

			String name = in.readUTF();

			return name;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public long getCoinCus(String username) {
		try {
			out.writeInt(GET_COIN_CUS);
			out.flush();

			out.writeUTF(username);
			out.flush();

			long coin = in.readLong();

			return coin;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int getSoLuongTonKho(String id) {
		try {
			out.writeInt(GET_SLTK);
			out.flush();

			out.writeUTF(id);
			out.flush();

			int soluong = in.readInt();

			return soluong;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void updateCoin(String username, long coin) {
		try {
			out.writeInt(UPDATE_COIN);
			out.flush();

			out.writeUTF(username);
			out.flush();

			out.writeLong(coin);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateNumberProduct(String idsanpham, int soluong) {
		try {
			out.writeInt(UPDATE_NUMBER_PRODUCT);
			out.flush();

			out.writeUTF(idsanpham);
			out.flush();

			out.writeInt(soluong);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editCustomer(KhachHang kh) {
		try {
			out.write(UPDATE_CUSTOMER_INFO);
			out.flush();

			oos.writeObject(kh);
			oos.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public KhachHang getCus(String username) {
		try {
			out.writeInt(GET_CUSTOMER);
			out.flush();

			out.writeUTF(username);
			out.flush();

			KhachHang kh = null;
			try {
				kh = (KhachHang) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return kh;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean checkSerial(String serial) {
		try {
			out.writeInt(CHECK_SERIAL);
			out.flush();

			out.writeUTF(serial);
			out.flush();

			boolean check = in.readBoolean();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public long getValueCard(String serial) {
		try {
			out.writeInt(GET_VALUE_CARD);
			out.flush();

			out.writeUTF(serial);
			out.flush();

			long check = in.readLong();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public String getIDRegister() {

		return null;
	}

	public boolean checkExistUsername(String username) {
		try {
			out.writeInt(CHECK_EXIST_USERNAME);
			out.flush();

			out.writeUTF(username);
			out.flush();

			boolean check = in.readBoolean();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean addCus(KhachHang kh) {
		try {
			out.writeInt(ADD_CUSTOMER);
			out.flush();

			oos.writeObject(kh);
			oos.flush();

			boolean check = in.readBoolean();
			return check;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
