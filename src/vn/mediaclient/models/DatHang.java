package vn.mediaclient.models;

import java.io.Serializable;

public class DatHang implements Serializable{
	private String idSanPham;
	private int soLuong;
	private long donGia;
	
	public DatHang() {
		// TODO Auto-generated constructor stub
	}
	
	public DatHang(String idSanPham, int soLuong, long donGia) {
		super();
		this.idSanPham = idSanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(String idSanPham) {
		this.idSanPham = idSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}
	
	
}
