package oop.quanlynhansu.model;

import java.util.Scanner;

public class TruongPhong extends NhanSu{
    private int soNhanVien;

    public TruongPhong(){
        super();
        this.maSo = STT++;
        this.luongMotNgay = 200;
    }

    public TruongPhong(String hoTen, String soDienThoai, float soNgayLam, float luongMotNgay) {
        super(hoTen, soDienThoai, soNgayLam, luongMotNgay);
        this.maSo = STT++;
    }

    public void tangSoNhanVien(){
        this.soNhanVien++;
    }

    public int getSoNhanVien() {
        return soNhanVien;
    }

    public void setSoNhanVien(int soNhanVien) {
        this.soNhanVien = soNhanVien;
    }

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
//        System.out.println("Nhập số lượng nhân viên dưới quyền: ");
//        this.soNhanVien = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void xuatThongTin() {
        System.out.println("===THÔNG TIN TRƯỞNG PHÒNG===");
        super.xuatThongTin();
        System.out.println("soNhanVien = " + soNhanVien);
    }

    @Override
    public String getThongTinThem() {
        return this.soNhanVien + "";
    }

    @Override
    public float tinhLuong() {
        return this.luongMotNgay * this.soNgayLam + 100 * this.soNhanVien;
    }

    @Override
    public String getChucVu() {
        return "Trưởng phòng";
    }
}
