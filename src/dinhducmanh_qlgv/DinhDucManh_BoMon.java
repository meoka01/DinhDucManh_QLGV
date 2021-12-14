package dinhducmanh_qlgv;

public class DinhDucManh_BoMon {
	private int id;
	private String ten;
	private DinhDucManh_Khoa khoa;

	public DinhDucManh_BoMon(int id, String name, DinhDucManh_Khoa khoa) {
		this.id = id;
		this.ten = name;
		this.khoa = khoa;
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

	public DinhDucManh_Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(DinhDucManh_Khoa khoa) {
		this.khoa = khoa;
	}

}
