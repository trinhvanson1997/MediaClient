package vn.mediaclient.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import vn.media.models.DiaNhac;
import vn.media.models.DiaPhim;
import vn.media.models.KhachHang;
import vn.media.models.MuaHang;
import vn.media.models.Sach;
import vn.media.serialization.HistoryWait;
import vn.mediaclient.controller.AddProductClient;
import vn.mediaclient.controller.ChangeTableClient;
import vn.mediaclient.controller.ClickTableClient;
import vn.mediaclient.controller.HistoryController;
import vn.mediaclient.controller.SearchBookClient;
import vn.mediaclient.controller.SearchMovieClient;
import vn.mediaclient.controller.SearchMusicClient;
import vn.mediaclient.controller.SeeCartController;
import vn.mediaclient.controller.TableBookController;
import vn.mediaclient.controller.TableMoviesController;
import vn.mediaclient.controller.TableMusicController;
import vn.mediaclient.view.ClientUI;

public class Client {
	public static final int LOGIN=1,LOGIN_SUCCESS=2,LOGIN_FAIL=3,GET_ALL_BOOK=4,GET_ALL_MOVIE=5
			,GET_ALL_MUSIC=6,GET_CUSTOMER_NAME=7,GET_COIN_CUS=8,GET_SLTK=9,UPDATE_COIN=10
					,UPDATE_NUMBER_PRODUCT=11,UPDATE_CUSTOMER_INFO=12
					,GET_CUSTOMER=13,CHECK_SERIAL=14,GET_VALUE_CARD=15,CHECK_EXIST_USERNAME=16
							,ADD_CUSTOMER=17,CLOSE_REQUEST=18,ORDER_REQUEST=19
	,COUNT_BOOK=20,COUNT_MOVIE=21,COUNT_MUSIC=22,SERVER_CLOSE=23,GET_HISTORY=24
			,SEARCH_BOOK_BY_NAME_AND_PUBLISHER = 25,SEARCH_BOOK_BY_NAME_AND_AUTHOR = 26,
			SEARCH_BOOK_BY_PUBLISHER_AND_AUTHOR = 27,SEARCH_BOOK_BY_NAME_AND_PUBLISHER_AND_AUTHOR = 28,
			SEARCH_BOOK =29;
	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;

	public Client() {
		try {
				
			//socket = new Socket("2405:4800:1484:9a0e:edb6:f52f:cb40:c0c9", 2000);
			//socket = new Socket("localhost", 2000);
			socket = new Socket("fe80::5507:c43d:5d84:5e4e%4", 2000);
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

	
		ClientUI clientUI = new ClientUI(username, this);
		new ChangeTableClient(clientUI);
		new SearchBookClient(clientUI);
		new SearchMovieClient(clientUI);
		new SearchMusicClient(clientUI);
		new ClickTableClient(clientUI);
		new AddProductClient(clientUI, this);
		new SeeCartController(clientUI, this);
		new TableBookController(clientUI, this);
		new TableMoviesController(clientUI, this);
		new TableMusicController(clientUI, this);
		new HistoryController(clientUI, this);
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

	public List<Sach> getAllBookFromServer(int page) {
		try {
			out.writeInt(GET_ALL_BOOK);
			out.flush();
			
			out.writeInt(page);
			out.flush();
			
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Sach> getBookByNameAndPublisher(String name,String publisher) {
		try {
			out.writeInt(SEARCH_BOOK_BY_NAME_AND_PUBLISHER);
			out.flush();
			
		out.writeUTF(name);
		out.flush();
		
		out.writeUTF(publisher);
		out.flush();
			
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Sach> getBookByNameAndAuthor(String name,String author) {
		try {
			out.writeInt(SEARCH_BOOK_BY_NAME_AND_AUTHOR);
			out.flush();
			
		out.writeUTF(name);
		out.flush();
		
		out.writeUTF(author);
		out.flush();
			
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Sach> getBookByPublisherAndAuthor(String publisher,String author) {
		try {
			out.writeInt(SEARCH_BOOK_BY_PUBLISHER_AND_AUTHOR);
			out.flush();
			
		out.writeUTF(publisher);
		out.flush();
		
		out.writeUTF(author);
		out.flush();
			
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public List<Sach> getBookByNameAndPublisherAndAuthor(String name,String publisher,String author) {
		try {
			out.writeInt(SEARCH_BOOK_BY_NAME_AND_PUBLISHER_AND_AUTHOR);
			out.flush();
			
		out.writeUTF(name);
		out.flush();
		
		out.writeUTF(publisher);
		out.flush();
			
		out.writeUTF(author);
		out.flush();
		
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Sach> getBookByNameOrPublisherOrAuthor(String name,String publisher,String author) {
		try {
			out.writeInt(SEARCH_BOOK);
			out.flush();
			
		out.writeUTF(name);
		out.flush();
		
		out.writeUTF(publisher);
		out.flush();
			
		out.writeUTF(author);
		out.flush();
		
			int size = in.readInt();
					
			List<Sach> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> tacGia = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String nhaXB = in.readUTF();
				
				
				Sach s = new Sach(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, nhaXB, tacGia);
				list.add(s);
			}

			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public List<DiaPhim> getAllMoviesFromServer(int page) {
		// TODO Auto-generated method stub
		try {
			out.writeInt(GET_ALL_MOVIE);
			out.flush();

			out.writeInt(page);
			out.flush();
			
			int size = in.readInt();
			
			List<DiaPhim> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> dienVien = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String daoDien = in.readUTF();
				
				
				DiaPhim s = new DiaPhim(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, daoDien, dienVien);
				list.add(s);
			}

			return list;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<DiaNhac> getAllMusicFromServer(int page) {
		// TODO Auto-generated method stub
		try {
			out.writeInt(GET_ALL_MUSIC);
			out.flush();

			out.writeInt(page);
			out.flush();
			
			int size = in.readInt();
			
			List<DiaNhac> list = new ArrayList<>();
			for(int i=0;i<size;i++) {
				List<String> caSi = (List<String>) ois.readObject();
				
				String id = in.readUTF();
				String tenSP = in.readUTF();
				String maLoaiSP = in.readUTF();
				int soLuongTonKho = in.readInt();
				long giaMua = in.readLong();
				long giaBan = in.readLong();
				Timestamp ngayNhapHangCuoi = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String theLoai = in.readUTF();
			
				
				DiaNhac s = new DiaNhac(id, tenSP, maLoaiSP, soLuongTonKho, giaMua, giaBan, ngayNhapHangCuoi, theLoai, caSi);
				list.add(s);
			}
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		 catch (ParseException e) {
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

	public long getCoinCus(String id) {
		try {
			out.writeInt(GET_COIN_CUS);
			out.flush();

			out.writeUTF(id);
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
			System.out.println("coin in client: "+ coin);
			out.writeInt(UPDATE_COIN);
			out.flush();

			System.out.println("send user");
			out.writeUTF(username);
			out.flush();
			
			System.out.println("send coin");
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
			System.out.println(kh.getNgaySinh());
			out.writeInt(UPDATE_CUSTOMER_INFO);
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

	public boolean sendOrderRequest(List<MuaHang> listDH,String idkhachhang) {
		try {
		
			out.writeInt(ORDER_REQUEST);
			out.flush();

			out.writeUTF(idkhachhang);
			out.flush();
			
			out.writeInt(listDH.size());
			out.flush();
			
			for(int i=0;i<listDH.size();i++) {
				out.writeUTF(listDH.get(i).getIdSanPham());
				out.flush();
				
				out.writeInt(listDH.get(i).getSoLuong());
				out.flush();
				
				out.writeLong(listDH.get(i).getDonGia());
				out.flush();
			}
	
			boolean check = in.readBoolean();
			return check;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return false;
	}

	public int getCountBook() {
		try {
			out.writeInt(COUNT_BOOK);
			out.flush();
			
			int count = in.readInt();
			return count;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	public int getCountMovies() {
		try {
			out.writeInt(COUNT_MOVIE);
			out.flush();
			
			int count = in.readInt();
			return count;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}
	
	public int getCountMusic() {
		try {
			out.writeInt(COUNT_MUSIC);
			out.flush();
			
			int count = in.readInt();
			return count;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public List<HistoryWait> getHistory(String idkhachhang){
		try {
			out.writeInt(GET_HISTORY);
			out.flush();
			
			out.writeUTF(idkhachhang);
			out.flush();
			
			int Size = in.readInt();
			
			List<HistoryWait> list = new ArrayList<>();
			for(int i=0;i<Size;i++) {
				
				String name = in.readUTF();
				int soluong = in.readInt();
				Timestamp date = new Timestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(in.readUTF()).getTime());
				String trangthai = in.readUTF();
				HistoryWait h = new HistoryWait( name, soluong, date, trangthai);
				list.add(h);
			}
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	

}
