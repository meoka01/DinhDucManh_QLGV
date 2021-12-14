package dinhducmanh_qlgv;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DinhDucManh_App {
    public static int idGiangVien = 1;

    public static void main(String[] args) {
        DinhDucManh_QuanLy quanLy = new DinhDucManh_QuanLy();

        DinhDucManh_Khoa khoaCNTT = new DinhDucManh_Khoa(1, "Khoa CNTT");
        DinhDucManh_Khoa khoaKinhTe = new DinhDucManh_Khoa(2, "Khoa Kinh Tế");
        List<DinhDucManh_Khoa> khoaList = new ArrayList<>();
        khoaList.add(khoaCNTT);
        khoaList.add(khoaKinhTe);

        DinhDucManh_BoMon boMonCNPM = new DinhDucManh_BoMon(1, "Bộ môn Công nghệ phần mềm", khoaCNTT);
        DinhDucManh_BoMon boMonKHMT = new DinhDucManh_BoMon(2, "Bộ môn Khoa học máy tính", khoaCNTT);
        DinhDucManh_BoMon boMonMMT = new DinhDucManh_BoMon(3, "Bộ môn Mạng máy tính", khoaCNTT);
        DinhDucManh_BoMon boMonKTDN = new DinhDucManh_BoMon(4, "Bộ môn Kế toán doanh nghiệp", khoaKinhTe);
        DinhDucManh_BoMon boMonQTDN = new DinhDucManh_BoMon(5, "Bộ môn Quản trị doanh nghiệp", khoaKinhTe);
        List<DinhDucManh_BoMon> boMonList = new ArrayList<>();
        boMonList.add(boMonCNPM);
        boMonList.add(boMonKHMT);
        boMonList.add(boMonMMT);
        boMonList.add(boMonKTDN);
        boMonList.add(boMonQTDN);

        Scanner console = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Đinh Đức Mạnh - CHƯƠNG TRÌNH QUẢN LÝ GIẢNG VIÊN");
                System.out.println("Vui lòng nhập số từ 1 -> 7 để sử dụng các chức năng tương ứng bên dưới: ");
                System.out.println("1. Thêm giảng viên");
                System.out.println("2. Hiển thị thông tin giảng viên");
                System.out.println("3. Sửa thông tin giảng viên");
                System.out.println("4. Xóa thông tin giảng viên");
                System.out.println("5. Tìm kiếm");
                System.out.println("6. Sắp xếp");
                System.out.println("7. Thống kê");
                int input = Integer.parseInt(console.nextLine());
                if (input == 1) {
                    inputData(console, quanLy, boMonList, khoaList);
                } else if (input == 2) {
                    System.out.println("Danh sách giảng viên: ");
                    printData(quanLy);
                } else if (input == 3) {
                    editData(console, quanLy, boMonList, khoaList);
                } else if (input == 4) {
                    removeData(console, quanLy);
                } else if (input == 5) {
                    searchData(console, quanLy);
                } else if (input == 6) {
                    sortData(console, quanLy);
                } else if (input == 7) {
                    thongKeData(console, quanLy, boMonList);
                } else {
                    throw new Exception("Lựa chọn sai chức năng. Vui lòng nhập lại...");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            console.close();
        }
    }

    private static void thongKeData(Scanner console, DinhDucManh_QuanLy quanLy, List<DinhDucManh_BoMon> boMonList) {
        try {
            while (true) {
                System.out.println("Vui lòng nhập số từ 1->2 tương ứng chức năng để thực hiện thống kê ");
                System.out.println("1. Thống kê giảng viên trong bộ môn");
                System.out.println("2. Thống kê giảng viên theo chức danh");
                System.out.println("3. Thống kê giảng viên theo loại giảng viên");
                int so = Integer.parseInt(console.nextLine());
                List<DinhDucManh_GiangVien> giangVienList;
                if (so == 1) {
                    System.out.println("Chọn bộ môn theo danh sách sau: ");
                    boMonList.forEach(b -> System.out.println("Mã : " + b.getId() + ", tên: " + b.getTen()));
                    System.out.print("Nhập mã bộ môn bạn muốn chọn: ");
                    int idBoMon = Integer.parseInt(console.nextLine());
                    Optional<DinhDucManh_BoMon> optBoMon = boMonList.stream().filter(b -> b.getId() == idBoMon).findFirst();
                    DinhDucManh_BoMon boMon = optBoMon.orElseThrow(() -> new Exception("Không tồn tại bộ môn!!"));
                    giangVienList = quanLy.thongKeGiangVienTheoBoMon(boMon);
                } else if (so == 2) {
                    System.out.println("Chọn chức danh giảng viên theo danh sách sau: ");
                    System.out.println("Mã: 1, " + DinhDucManh_GiangVien.ChucDanh.GIAO_SU);
                    System.out.println("Mã: 2, " + DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU);
                    System.out.println("Mã: 3, " + DinhDucManh_GiangVien.ChucDanh.TIEN_SI);
                    System.out.println("Mã: 4, " + DinhDucManh_GiangVien.ChucDanh.THAC_SY);
                    System.out.println("Mã: 5, " + DinhDucManh_GiangVien.ChucDanh.CU_NHAN);
                    System.out.println("Mã: 6, " + DinhDucManh_GiangVien.ChucDanh.KHAC);
                    System.out.print("Nhập mã chức danh giảng viên bạn muốn chọn: ");
                    int idChucDanh = Integer.parseInt(console.nextLine());
                    DinhDucManh_GiangVien.ChucDanh chucDanh;
                    if (idChucDanh == 1) {
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.GIAO_SU;
                    } else if (idChucDanh == 2) {
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU;
                    } else if (idChucDanh == 3){
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.TIEN_SI;
                    } else if (idChucDanh == 4) {
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.THAC_SY;
                    } else if (idChucDanh == 5) {
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.CU_NHAN;
                    } else if (idChucDanh == 6) {
                        chucDanh = DinhDucManh_GiangVien.ChucDanh.KHAC;
                    } else {
                        throw new Exception("Không tồn tại loại chức danh!!");
                    }
                    giangVienList = quanLy.thongKeGiangVienTheoChucDanh(chucDanh);
                } else if (so == 3) {
                    System.out.println("Chọn loại giảng viên theo danh sách sau: ");
                    System.out.println("Mã: 1, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU);
                    System.out.println("Mã: 2, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG);
                    int idLoaiGiangVien = Integer.parseInt(console.nextLine());
                    DinhDucManh_GiangVien.LoaiGiangVien loaiGiangVien;
                    if (idLoaiGiangVien == 1) {
                        loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU;
                    } else if(idLoaiGiangVien == 2) {
                        loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG;
                    } else {
                        throw new Exception("Không tồn tại loại giảng viên!!");
                    }
                    giangVienList = quanLy.thongKeGiangVienTheoLoai(loaiGiangVien);
                } else {
                    throw new Exception("Lựa chọn sai chức năng. Vui lòng chọn lại");
                }
                System.out.println("Danh sách giảng viên sau khi thống kê: ");
                quanLy.inThongTinGiangVien(giangVienList);

                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void sortData(Scanner console, DinhDucManh_QuanLy quanLy) {
        try {
            while (true) {
                System.out.println("Vui lòng nhập số từ 1->2 tương ứng chức năng để thực hiện sắp xếp ");
                System.out.println("1. Sắp xếp giảng viên theo ngày sinh giảm dần");
                System.out.println("2. Sắp xếp giảng viên theo tên (kí tự alpha-bet) tăng dần");
                int so = Integer.parseInt(console.nextLine());
                List<DinhDucManh_GiangVien> giangVienList;
                if (so == 1) {
                    giangVienList = quanLy.sapXepGiangVien(so);
                } else if (so == 2) {
                    giangVienList = quanLy.sapXepGiangVien(so);
                } else {
                    throw new Exception("Lựa chọn sai chức năng. Vui lòng chọn lại");
                }
                System.out.println("Danh sách giảng viên sau khi sắp xếp: ");
                quanLy.inThongTinGiangVien(giangVienList);

                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void searchData(Scanner console, DinhDucManh_QuanLy quanLy) {
        try {
            while (true) {
                System.out.println("Vui lòng nhập số từ 1->2 tương ứng chức năng để thực hiện tìm kiếm ");
                System.out.println("1. Tìm kiếm giảng viên theo tên");
                System.out.println("2. Tìm kiếm giảng viên theo địa chỉ");
                int so = Integer.parseInt(console.nextLine());
                List<DinhDucManh_GiangVien> giangVienList;
                if (so == 1) {
                    System.out.print("Nhập tên giảng viên: ");
                    String ten = console.nextLine();
                    giangVienList = quanLy.timKiem(1, ten);
                } else if (so == 2) {
                    System.out.print("Nhập địa chỉ giảng viên: ");
                    String diaChi = console.nextLine();
                    giangVienList = quanLy.timKiem(2, diaChi);
                } else {
                    throw new Exception("Lựa chọn sai chức năng. Vui lòng chọn lại");
                }
                System.out.println("Danh sách giảng viên sau tìm kiếm: ");
                quanLy.inThongTinGiangVien(giangVienList);

                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void editData(Scanner console, DinhDucManh_QuanLy quanLy, List<DinhDucManh_BoMon> boMonList, List<DinhDucManh_Khoa> khoaList) {
        try {
            while (true) {
                System.out.println("Danh sách giảng viên hiện tại: ");
                quanLy.inThongTinGiangVien(quanLy.getGiangVienList());
                System.out.print("Chọn mã giảng viên bạn muốn sửa: ");
                int idMaGV = Integer.parseInt(console.nextLine());

                System.out.println("Chọn thông tin giảng viên cần sửa: ");
                System.out.print("Nhập tên: ");
                String ten = console.nextLine();
                System.out.print("Nhập ngày sinh, định dạng mm/dd/yyyy (VD: 30/04/1980): ");
                String ngaySinh = console.nextLine();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
                System.out.print("Nhập địa chỉ: ");
                String diaChi = console.nextLine();

                System.out.println("Chọn khoa theo danh sách sau: ");
                khoaList.forEach(b -> System.out.println("Mã : " + b.getId() + ", tên: " + b.getTen()));
                System.out.print("Nhập mã khoa bạn muốn chọn: ");
                int idKhoa = Integer.parseInt(console.nextLine());
                Optional<DinhDucManh_Khoa> optKhoa = khoaList.stream().filter(k -> k.getId() == idKhoa).findFirst();
                DinhDucManh_Khoa khoa = optKhoa.orElseThrow(() -> new Exception("Không tồn tại khoa đã chọn"));

                System.out.println("Chọn bộ môn theo danh sách sau: ");
                List<DinhDucManh_BoMon> dsBoMonTheoKhoa = boMonList.stream().filter(b -> b.getKhoa().getId() == khoa.getId()).collect(Collectors.toList());
                dsBoMonTheoKhoa.forEach(b -> System.out.println("Mã : " + b.getId() + ", tên: " + b.getTen()));
                System.out.print("Nhập mã bộ môn bạn muốn chọn: ");
                int idBoMon = Integer.parseInt(console.nextLine());
                Optional<DinhDucManh_BoMon> optBoMon = dsBoMonTheoKhoa.stream().filter(b -> b.getId() == idBoMon).findFirst();
                DinhDucManh_BoMon boMon = optBoMon.orElseThrow(() -> new Exception("Không tồn tại bộ môn!!"));

                System.out.println("Chọn chức danh giảng viên theo danh sách sau: ");
                System.out.println("Mã: 1, " + DinhDucManh_GiangVien.ChucDanh.GIAO_SU);
                System.out.println("Mã: 2, " + DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU);
                System.out.println("Mã: 3, " + DinhDucManh_GiangVien.ChucDanh.TIEN_SI);
                System.out.println("Mã: 4, " + DinhDucManh_GiangVien.ChucDanh.THAC_SY);
                System.out.println("Mã: 5, " + DinhDucManh_GiangVien.ChucDanh.CU_NHAN);
                System.out.println("Mã: 6, " + DinhDucManh_GiangVien.ChucDanh.KHAC);
                System.out.print("Nhập mã chức danh giảng viên bạn muốn chọn: ");
                int idChucDanh = Integer.parseInt(console.nextLine());
                DinhDucManh_GiangVien.ChucDanh chucDanh;
                if (idChucDanh == 1) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.GIAO_SU;
                } else if (idChucDanh == 2) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU;
                } else if (idChucDanh == 3){
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.TIEN_SI;
                } else if (idChucDanh == 4) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.THAC_SY;
                } else if (idChucDanh == 5) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.CU_NHAN;
                } else if (idChucDanh == 6) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.KHAC;
                } else {
                    throw new Exception("Không tồn tại loại chức danh!!");
                }

                System.out.println("Chọn loại giảng viên theo danh sách sau: ");
                System.out.println("Mã: 1, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU);
                System.out.println("Mã: 2, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG);
                System.out.print("Nhập mã loại giảng viên bạn muốn chọn: ");
                int idLoaiGiangVien = Integer.parseInt(console.nextLine());
                DinhDucManh_GiangVien.LoaiGiangVien loaiGiangVien;
                if (idLoaiGiangVien == 1) {
                    loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU;
                } else if(idLoaiGiangVien == 2) {
                    loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG;
                } else {
                    throw new Exception("Không tồn tại loại giảng viên!!");
                }
                if (loaiGiangVien == DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU) {
                    System.out.print("Nhập bậc lương đối với giảng viên cơ hữu: ");
                    int bacLuong = Integer.parseInt(console.nextLine());
                    quanLy.suaThongTinGiangVien(idMaGV, ten, date, diaChi, khoa, boMon, chucDanh, bacLuong);
                } else {
                    System.out.print("Nhập số tiết dạy/ 1 tuần đối với giảng viên thỉnh giảng: ");
                    int soTietDay = Integer.parseInt(console.nextLine());
                    quanLy.suaThongTinGiangVien(idMaGV, ten, date, diaChi, khoa, boMon, chucDanh, soTietDay);
                }
                System.out.println("Sửa thông tin giảng viên thành công.");
                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void removeData(Scanner console, DinhDucManh_QuanLy quanLy) {
        try {
            while (true) {
                System.out.println("Danh sách giảng viên hiện tại: ");
                quanLy.inThongTinGiangVien(quanLy.getGiangVienList());
                System.out.print("Chọn mã giảng viên bạn muốn xóa: ");
                int idMaGV = Integer.parseInt(console.nextLine());
                quanLy.xoaGiangVien(idMaGV);
                System.out.println("Xóa giảng viên thành công");
                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printData(DinhDucManh_QuanLy quanLy) {
        quanLy.inThongTinGiangVien(quanLy.getGiangVienList());
    }

    private static void inputData(Scanner console, DinhDucManh_QuanLy quanLy, List<DinhDucManh_BoMon> boMonList, List<DinhDucManh_Khoa> khoaList) {
        try {
            while (true) {
                System.out.println("1. Nhập dữ liệu giảng viên");
                System.out.print("Nhập tên: ");
                String ten = console.nextLine();
                System.out.print("Nhập ngày sinh, định dạng mm/dd/yyyy (VD: 30/04/1980): ");
                String ngaySinh = console.nextLine();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
                System.out.print("Nhập địa chỉ: ");
                String diaChi = console.nextLine();

                System.out.println("Chọn khoa theo danh sách sau: ");
                khoaList.forEach(b -> System.out.println("Mã : " + b.getId() + ", tên: " + b.getTen()));
                System.out.print("Nhập mã khoa bạn muốn chọn: ");
                int idKhoa = Integer.parseInt(console.nextLine());
                Optional<DinhDucManh_Khoa> optKhoa = khoaList.stream().filter(k -> k.getId() == idKhoa).findFirst();
                DinhDucManh_Khoa khoa = optKhoa.orElseThrow(() -> new Exception("Không tồn tại khoa đã chọn"));

                System.out.println("Chọn bộ môn theo danh sách sau: ");
                List<DinhDucManh_BoMon> dsBoMonTheoKhoa = boMonList.stream().filter(b -> b.getKhoa().getId() == khoa.getId()).collect(Collectors.toList());
                dsBoMonTheoKhoa.forEach(b -> System.out.println("Mã : " + b.getId() + ", tên: " + b.getTen()));
                System.out.print("Nhập mã bộ môn bạn muốn chọn: ");
                int idBoMon = Integer.parseInt(console.nextLine());
                Optional<DinhDucManh_BoMon> optBoMon = dsBoMonTheoKhoa.stream().filter(b -> b.getId() == idBoMon).findFirst();
                DinhDucManh_BoMon boMon = optBoMon.orElseThrow(() -> new Exception("Không tồn tại bộ môn!!"));

                System.out.println("Chọn chức danh giảng viên theo danh sách sau: ");
                System.out.println("Mã: 1, " + DinhDucManh_GiangVien.ChucDanh.GIAO_SU);
                System.out.println("Mã: 2, " + DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU);
                System.out.println("Mã: 3, " + DinhDucManh_GiangVien.ChucDanh.TIEN_SI);
                System.out.println("Mã: 4, " + DinhDucManh_GiangVien.ChucDanh.THAC_SY);
                System.out.println("Mã: 5, " + DinhDucManh_GiangVien.ChucDanh.CU_NHAN);
                System.out.println("Mã: 6, " + DinhDucManh_GiangVien.ChucDanh.KHAC);
                System.out.print("Nhập mã chức danh giảng viên bạn muốn chọn: ");
                int idChucDanh = Integer.parseInt(console.nextLine());
                DinhDucManh_GiangVien.ChucDanh chucDanh;
                if (idChucDanh == 1) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.GIAO_SU;
                } else if (idChucDanh == 2) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.PHO_GIAO_SU;
                } else if (idChucDanh == 3){
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.TIEN_SI;
                } else if (idChucDanh == 4) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.THAC_SY;
                } else if (idChucDanh == 5) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.CU_NHAN;
                } else if (idChucDanh == 6) {
                    chucDanh = DinhDucManh_GiangVien.ChucDanh.KHAC;
                } else {
                    throw new Exception("Không tồn tại loại chức danh!!");
                }

                System.out.println("Chọn loại giảng viên theo danh sách sau: ");
                System.out.println("Mã: 1, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU);
                System.out.println("Mã: 2, " + DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG);
                System.out.print("Nhập mã loại giảng viên bạn muốn chọn: ");
                int idLoaiGiangVien = Integer.parseInt(console.nextLine());
                DinhDucManh_GiangVien.LoaiGiangVien loaiGiangVien;
                if (idLoaiGiangVien == 1) {
                    loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU;
                } else if(idLoaiGiangVien == 2) {
                    loaiGiangVien = DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_THINH_GIANG;
                } else {
                    throw new Exception("Không tồn tại loại giảng viên!!");
                }

                if (loaiGiangVien == DinhDucManh_GiangVien.LoaiGiangVien.GIANG_VIEN_CO_HUU) {
                    System.out.print("Nhập bậc lương đối với giảng viên cơ hữu: ");
                    int bacLuong = Integer.parseInt(console.nextLine());
                    DinhDucManh_GiangVien giangVienCoHuu = new DinhDucManh_GiangVienCoHuu(idGiangVien++, ten, date, diaChi, boMon, khoa, chucDanh, bacLuong);
                    quanLy.themGiangVien(giangVienCoHuu);
                } else {
                    System.out.print("Nhập số tiết dạy/ 1 tuần đối với giảng viên thỉnh giảng: ");
                    int soTietDay = Integer.parseInt(console.nextLine());
                    DinhDucManh_GiangVien giangVienThinhGiang = new DinhDucManh_GiangVienThinhGiang(idGiangVien++, ten, date, diaChi, boMon, khoa, chucDanh, soTietDay);
                    quanLy.themGiangVien(giangVienThinhGiang);
                }
                System.out.println("Thêm giảng viên thành công");
                System.out.print("Bạn có muốn thực hiện tiếp không? (Y/N) ");
                String yn = console.nextLine();
                if ("N".equalsIgnoreCase(yn)) {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
