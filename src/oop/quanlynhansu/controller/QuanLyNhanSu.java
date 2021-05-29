package oop.quanlynhansu.controller;

import oop.quanlynhansu.model.NhanSu;
import oop.quanlynhansu.model.NhanVien;
import oop.quanlynhansu.model.TruongPhong;

import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyNhanSu {
    private ArrayList<NhanSu> danhSachNhanSu;

    public ArrayList<NhanSu> getDanhSachNhanSu() {
        return danhSachNhanSu;
    }

    public QuanLyNhanSu(){
        this.danhSachNhanSu = new ArrayList<NhanSu>();
    }

    private boolean tangSoLuongNhanVienCuaTruongPhong(NhanVien nv){
        //Duyệt danh sách trưởng phòng để tìm trưởng phòng của nhân viên này
        for (int  i = 0; i < danhSachNhanSu.size(); i++ ){
            if(danhSachNhanSu.get(i) instanceof TruongPhong){
                TruongPhong tp = (TruongPhong)danhSachNhanSu.get(i);
                if(tp.getMaSo() == nv.getMaTruongPhong()){
                    System.out.println("The manager info of this employee: ");
                    tp.setSoNhanVien(tp.getSoNhanVien()+1);
                    tp.xuatThongTin();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean them(NhanSu nhanSu) {
        if(nhanSu == null){
            return  false;
        }
        //Nếu là nhân viên, sau khi thêm phải tăng số lượng nhân viên của trưởng phòng giám sát lên 1
        if(nhanSu instanceof NhanVien && ((NhanVien) nhanSu).getMaTruongPhong() != -1){
            NhanVien nv = (NhanVien)nhanSu;
            if(this.tangSoLuongNhanVienCuaTruongPhong(nv)){
                nv.xuatThongTin();
                danhSachNhanSu.add(nhanSu);
                return true;
            }else{
                nv.setMaTruongPhong(-1); // reset if failed
                System.out.println("Không tìm thấy trưởng phòng của nhân viên này !");
                return false;
            }
        }
        danhSachNhanSu.add(nhanSu);
        nhanSu.xuatThongTin();
        return true;
    }

    public boolean phanBoNhanVienVaoTruogPhong(Scanner sc){
        int maso = -1;
        System.out.println("Enter the employee's id: ");
        maso = Integer.parseInt(sc.nextLine());
        for (NhanSu ns: danhSachNhanSu) {
            if(ns.getMaSo() == maso){
                if(ns instanceof NhanVien){
                    System.out.print("Enter manager's id of this employee: ");
                    NhanVien nv = (NhanVien)ns;
                    nv.setMaTruongPhong(Integer.parseInt(sc.nextLine()));
                    if(this.tangSoLuongNhanVienCuaTruongPhong(nv)){
                        nv.xuatThongTin();
                        System.out.println("Assign successfully!");
                        return true;
                    }else{
                        nv.setMaTruongPhong(-1); // reset if failed
                        System.out.println("Không tìm thấy trưởng phòng của nhân viên này !");
                        return false;
                    }
                }else{
                    System.out.println("This is not an employee!");
                    return false;
                }
            }
        }
        System.out.println("This id is not found!");
        return false;
    }

    public boolean xoa(int maSo){
        //kiem tra ma nhan vien co ton
        boolean found = false;
        NhanSu nhanSu = null;
        for(NhanSu ns : danhSachNhanSu){
            if(ns.getMaSo() == maSo){
                nhanSu = ns;
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("Không tìm thấy mã nhân sự!");
            return false;
        }

        //Neu la truong phong
        if (nhanSu instanceof TruongPhong){
            TruongPhong tp = (TruongPhong) nhanSu;
            for (NhanSu ns: danhSachNhanSu){
                if (ns instanceof NhanVien){
                    NhanVien nv = (NhanVien)ns;
                    if(nv.getMaTruongPhong() != -1 && nv.getMaTruongPhong() == tp.getMaSo()){
                        nv.setMaTruongPhong(-1); // ngat tham chieu den truong phong
                        nv.xuatThongTin();
                        danhSachNhanSu.remove(tp); // Xoa truong phong;
                        return true;
                    }
                }
            }
        }

        //Neu la nhan vien thuong
        if(nhanSu instanceof NhanVien){
            NhanVien nv = (NhanVien) nhanSu;
            if(nv.getMaTruongPhong() != -1){
                for (NhanSu ns : danhSachNhanSu){
                    if(ns instanceof TruongPhong){
                        TruongPhong tp = (TruongPhong) ns;
                        if(tp.getMaSo() == nv.getMaTruongPhong()){
                            tp.setSoNhanVien(tp.getSoNhanVien() -1);
                            tp.xuatThongTin();
                            danhSachNhanSu.remove(nv);
                            return true;
                        }
                    }
                }
            }
        }

        //Neu la giam doc
        danhSachNhanSu.remove(nhanSu);
        return  true;
    }
}
