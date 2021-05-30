package oop.quanlynhansu.model;

import java.util.Scanner;

public abstract class NhanSu {
    protected int maSo;
    protected String hoTen;
    protected String soDienThoai;
    protected float soNgayLam;
    protected float luongMotNgay;
    protected static int STT = 1;

    public NhanSu(){

    }

    public NhanSu(String hoTen, String soDienThoai, float soNgayLam, float luongMotNgay) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.soNgayLam = soNgayLam;
        this.luongMotNgay = luongMotNgay;
    }

    public int getMaSo() {
        return maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public float getSoNgayLam() {
        return soNgayLam;
    }

    public float getLuongMotNgay() {
        return luongMotNgay;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setSoNgayLam(float soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public void setLuongMotNgay(float luongMotNgay) {
        this.luongMotNgay = luongMotNgay;
    }

    public void nhapThongTin(Scanner sc){
        System.out.println("Nhập họ tên: ");
        this.hoTen = sc.nextLine();
        System.out.println("Nhập số điện thoại: ");
        this.soDienThoai = sc.nextLine();
        System.out.println("Nhập số ngày làm: ");
        this.soNgayLam = Float.parseFloat(sc.nextLine());
    }

    public void xuatThongTin(){
        System.out.println("maSo = " + maSo);
        System.out.println("hoTen = " + hoTen);
        System.out.println("soDienThoai = " + soDienThoai);
        System.out.println("soNgayLam = " + soNgayLam);
        System.out.println("luongMotNgay = " + luongMotNgay);
    }

    public abstract String getThongTinThem();

    public abstract float tinhLuong();

    public abstract String getChucVu();

}
