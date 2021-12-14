package dinhducmanh_qlgv;

import java.util.*;
import java.util.stream.Collectors;

public class DinhDucManh_QuanLy {
	private List<DinhDucManh_GiangVien> giangVienList;

	public DinhDucManh_QuanLy() {
		giangVienList = new ArrayList<>();
	}

	public List<DinhDucManh_GiangVien> getGiangVienList() {
		return giangVienList;
	}

	public void setGiangVienList(List<DinhDucManh_GiangVien> giangVienList) {
		this.giangVienList = giangVienList;
	}

	public void themGiangVien(DinhDucManh_GiangVien giangVien) {
		giangVienList.add(giangVien);
	}

	public void inThongTinGiangVien(List<DinhDucManh_GiangVien> giangViens) {
		giangViens.forEach(g -> g.inThongTinGiangVien());
	}

	public void xoaGiangVien(int idMaGV) throws Exception {
		Optional<DinhDucManh_GiangVien> optGV = giangVienList.stream().filter(g -> g.id == idMaGV).findFirst();
		DinhDucManh_GiangVien gv = optGV.orElseThrow(() -> new Exception("Không tồn tại giảng viên với mã đã chọn!!"));
		giangVienList.remove(gv);
	}

	public void suaThongTinGiangVien(int idMaGV, String ten, Date date, String diaChi, DinhDucManh_Khoa khoa, DinhDucManh_BoMon boMon,
									 DinhDucManh_GiangVien.ChucDanh chucDanh, int optional) throws Exception {
		Optional<DinhDucManh_GiangVien> optGV = giangVienList.stream().filter(g -> g.id == idMaGV).findFirst();
		DinhDucManh_GiangVien gv = optGV.orElseThrow(() -> new Exception("Không tồn tại giảng viên với mã đã chọn!!"));
		if (gv instanceof DinhDucManh_GiangVienCoHuu) {
			DinhDucManh_GiangVienCoHuu giangVienCoHuu = (DinhDucManh_GiangVienCoHuu) gv;
			giangVienCoHuu.setBacLuong(optional);
			giangVienCoHuu.setTen(ten);
			giangVienCoHuu.setNgaySinh(date);
			giangVienCoHuu.setDiaChi(diaChi);
			giangVienCoHuu.setKhoa(khoa);
			giangVienCoHuu.setBoMon(boMon);
			giangVienCoHuu.setChucDanh(chucDanh);
		} else {
			DinhDucManh_GiangVienThinhGiang giangVienThinhGiang = (DinhDucManh_GiangVienThinhGiang) gv;
			giangVienThinhGiang.setSoTietDay(optional);
			giangVienThinhGiang.setTen(ten);
			giangVienThinhGiang.setNgaySinh(date);
			giangVienThinhGiang.setDiaChi(diaChi);
			giangVienThinhGiang.setKhoa(khoa);
			giangVienThinhGiang.setBoMon(boMon);
			giangVienThinhGiang.setChucDanh(chucDanh);
		}

	}

	public List<DinhDucManh_GiangVien> timKiem(int so, String search) {
		List<DinhDucManh_GiangVien> giangVienList = new ArrayList<>();
		if (so == 1) {
			giangVienList = this.giangVienList.stream().filter(g -> g.getTen().contains(search)).collect(Collectors.toList());
		} else if (so == 2) {
			giangVienList = this.giangVienList.stream().filter(g -> g.getDiaChi().contains(search)).collect(Collectors.toList());
		}
		return giangVienList;
	}

	public List<DinhDucManh_GiangVien> sapXepGiangVien(int so) {
		List<DinhDucManh_GiangVien> giangVienList;
		if (so == 1) {
			giangVienList = this.giangVienList.stream().sorted(Comparator.comparing(DinhDucManh_GiangVien::getNgaySinh).reversed()).collect(Collectors.toList());
		} else {
			giangVienList = this.giangVienList.stream().sorted(Comparator.comparing(DinhDucManh_GiangVien::getTen)).collect(Collectors.toList());
		}
		return giangVienList;
	}

	public List<DinhDucManh_GiangVien> thongKeGiangVienTheoBoMon(DinhDucManh_BoMon boMon) {
		List<DinhDucManh_GiangVien> giangVienList = this.giangVienList.stream().filter(g -> g.getBoMon().getId() == boMon.getId()).collect(Collectors.toList());
		return giangVienList;
	}

	public List<DinhDucManh_GiangVien> thongKeGiangVienTheoChucDanh(DinhDucManh_GiangVien.ChucDanh chucDanh) {
		List<DinhDucManh_GiangVien> giangVienList = this.giangVienList.stream().filter(g -> g.getChucDanh().equals(chucDanh)).collect(Collectors.toList());
		return giangVienList;
	}

	public List<DinhDucManh_GiangVien> thongKeGiangVienTheoLoai(DinhDucManh_GiangVien.LoaiGiangVien loaiGiangVien) {
		List<DinhDucManh_GiangVien> giangVienList;
		if (loaiGiangVien == DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU) {
			giangVienList = this.giangVienList.stream().filter(g -> g instanceof DinhDucManh_GiangVienCoHuu).collect(Collectors.toList());
		} else {
			giangVienList = this.giangVienList.stream().filter(g -> g instanceof DinhDucManh_GiangVienThinhGiang).collect(Collectors.toList());
		}
		return giangVienList;
	}
}
