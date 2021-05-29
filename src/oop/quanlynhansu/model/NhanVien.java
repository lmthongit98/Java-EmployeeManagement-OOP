package oop.quanlynhansu.model;

import java.util.Scanner;

public class NhanVien extends NhanSu{
    private  int maTruongPhong;

    public  NhanVien(){
        super();
        this.maTruongPhong = -1;
        this.luongMotNgay = 100;
    }

    public int getMaTruongPhong() {
        return maTruongPhong;
    }

    public void setMaTruongPhong(int maTruongPhong) {
        this.maTruongPhong = maTruongPhong;
    }

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        System.out.println("Nhập mã Trưởng Phòng: ");
        this.maTruongPhong = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void xuatThongTin() {
        System.out.println("===THÔNG TIN NHÂN VIÊN===");
        super.xuatThongTin();
        System.out.println("maTruongPhong = " + maTruongPhong);
    }

    @Override
    public float tinhLuong() {
        return this.luongMotNgay * this.soNgayLam;
    }

    @Override
    public String getChucVu() {
        return "Nhân viên";
    }
}
