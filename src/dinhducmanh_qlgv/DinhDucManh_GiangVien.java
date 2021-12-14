package dinhducmanh_qlgv;

import java.util.Date;

public abstract class DinhDucManh_GiangVien {
	public enum LoaiGiangVien {
		GIANG_VIEN_CO_HUU, GIANG_VIEN_THINH_GIANG
	}
	public enum ChucDanh {
		GIAO_SU, PHO_GIAO_SU, TIEN_SI, THAC_SY, CU_NHAN, KHAC
	}
	protected int id;
	protected String ten;
	protected Date ngaySinh;
	protected String diaChi;
	protected DinhDucManh_Khoa khoa;
	protected DinhDucManh_BoMon boMon;
	protected ChucDanh chucDanh;

	public DinhDucManh_GiangVien(int id, String ten, Date ngaySinh, String diaChi, DinhDucManh_BoMon boMon, DinhDucManh_Khoa khoa, ChucDanh chucDanh) {
		this.id = id;
		this.ten = ten;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.boMon = boMon;
		this.khoa = khoa;
		this.chucDanh = chucDanh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public DinhDucManh_BoMon getBoMon() {
		return boMon;
	}

	public void setBoMon(DinhDucManh_BoMon boMon) {
		this.boMon = boMon;
	}

	public DinhDucManh_Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DinhDucManh_Khoa khoa) {
		this.khoa = khoa;
	}

	public ChucDanh getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(ChucDanh chucDanh) {
		this.chucDanh = chucDanh;
	}

	public abstract void inThongTinGiangVien();
}
