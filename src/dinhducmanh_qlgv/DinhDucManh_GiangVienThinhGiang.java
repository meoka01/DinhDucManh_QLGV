package dinhducmanh_qlgv;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DinhDucManh_GiangVienThinhGiang extends DinhDucManh_GiangVien {
	private int soTietDay; //So tiet day trong 1 tuan

	public DinhDucManh_GiangVienThinhGiang(int id, String ten, Date ngaySinh, String diaChi, DinhDucManh_BoMon boMon, DinhDucManh_Khoa khoa, ChucDanh chucDanh, int soTietDay) {
		super(id, ten, ngaySinh, diaChi, boMon, khoa, chucDanh);
		this.soTietDay = soTietDay;
	}

	public int getSoTietDay() {
		return soTietDay;
	}

	public void setSoTietDay(int soTietDay) {
		this.soTietDay = soTietDay;
	}

	@Override
	public void inThongTinGiangVien() {
		String ns = new SimpleDateFormat("dd/MM/yyyy").format(ngaySinh);
		System.out.println("Mã: " + id + ", họ và tên: " + ten + ", ngày sinh: " + ns + ", địa chỉ: " + diaChi + ", khoa: " + khoa.getTen()
				+ ", bộ môn: " + boMon.getTen() + ", chức danh: " + chucDanh + ", số tiết dạy: " + soTietDay);	}
}
