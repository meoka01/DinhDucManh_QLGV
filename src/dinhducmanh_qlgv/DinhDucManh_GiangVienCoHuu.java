package dinhducmanh_qlgv;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DinhDucManh_GiangVienCoHuu extends DinhDucManh_GiangVien {
	private int bacLuong;

	public DinhDucManh_GiangVienCoHuu(int id, String ten, Date ngaySinh, String diaChi, DinhDucManh_BoMon boMon, DinhDucManh_Khoa khoa, ChucDanh chucDanh, int bacLuong) {
		super(id, ten, ngaySinh, diaChi, boMon, khoa, chucDanh);
		this.bacLuong = bacLuong;
	}

	public int getBacLuong() {
		return bacLuong;
	}

	public void setBacLuong(int bacLuong) {
		this.bacLuong = bacLuong;
	}

	@Override
	public void inThongTinGiangVien() {
		String ns = new SimpleDateFormat("dd/MM/yyyy").format(ngaySinh);
		System.out.println("Mã: " + id + ", họ và tên: " + ten + ", ngày sinh: " + ns + ", địa chỉ: " + diaChi + ", khoa: " +
				khoa.getTen() + ", bộ môn: " + boMon.getTen() + ", chức danh: " + chucDanh + ", bậc lương: " + bacLuong);
	}
}
