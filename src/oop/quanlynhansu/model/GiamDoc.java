package oop.quanlynhansu.model;

import java.util.Scanner;

public class GiamDoc extends NhanSu{
    private int coPhan;

    public GiamDoc(){
        super();
        this.maSo = STT++;
        this.luongMotNgay = 300;
    }

    public GiamDoc(String hoTen, String soDienThoai, float soNgayLam, float luongMotNgay, int coPhan) {
        super(hoTen, soDienThoai, soNgayLam, luongMotNgay);
        this.coPhan = coPhan;
        this.maSo = STT++;
    }

    public int getCoPhan() {
        return coPhan;
    }

    public void setCoPhan(int coPhan) {
        this.coPhan = coPhan;
    }

    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        System.out.println("Nhập số cổ phần: ");
        this.coPhan = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void xuatThongTin() {
        System.out.println("===THÔNG TIN GIÁM ĐỐC===");
        super.xuatThongTin();
        System.out.println("coPhan = " + coPhan);
    }

    @Override
    public String getThongTinThem() {
        return this.coPhan + "";
    }

    @Override
    public float tinhLuong() {
        return this.luongMotNgay * this.soNgayLam;
    }

    @Override
    public String getChucVu() {
        return "Giám đốc";
    }

}
