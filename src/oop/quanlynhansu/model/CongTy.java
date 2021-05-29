package oop.quanlynhansu.model;

import java.util.Scanner;

public class CongTy {
    private String tenCongTy;
    private String maSoThue;
    private double doanhThuThang;

    public  CongTy(){

    }

    public CongTy(String tenCongTy, String maSoThue, double doanhThuThang) {
        this.tenCongTy = tenCongTy;
        this.maSoThue = maSoThue;
        this.doanhThuThang = doanhThuThang;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public double getDoanhThuThang() {
        return doanhThuThang;
    }

    public void setDoanhThuThang(double doanhThuThang) {
        this.doanhThuThang = doanhThuThang;
    }

    public void nhap(Scanner sc){
        System.out.print("Nhập tên công ty: ");
        this.tenCongTy = sc.nextLine();
        System.out.print("Nhập mã số thuế: ");
        this.maSoThue = sc.nextLine();
        System.out.print("Nhập danh thu tháng: ");
        this.doanhThuThang = Double.parseDouble(sc.nextLine());
    }

    public void xuat(){
        System.out.println("===THÔNG TIN CÔNG TY===");
        System.out.println("Tên công ty: " + this.tenCongTy);
        System.out.println("Mã số thuế: " + this.maSoThue);
        System.out.println("Danh thu tháng: " + this.doanhThuThang);
    }


}
