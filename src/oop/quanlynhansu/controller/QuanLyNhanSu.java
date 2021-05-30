package oop.quanlynhansu.controller;

import oop.quanlynhansu.model.GiamDoc;
import oop.quanlynhansu.model.NhanSu;
import oop.quanlynhansu.model.NhanVien;
import oop.quanlynhansu.model.TruongPhong;
import oop.quanlynhansu.view.util.PrintFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLyNhanSu {
    private ArrayList<NhanSu> danhSachNhanSu;

    public ArrayList<NhanSu> getDanhSachNhanSu() {
        return danhSachNhanSu;
    }

    public QuanLyNhanSu(){
        this.danhSachNhanSu = new ArrayList<NhanSu>();
        danhSachNhanSu.add(new NhanVien("Tuấn", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Vương", "1141234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Thiên", "1151234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nhất", "1161234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Trịnh", "1171234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Duy", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Đồng", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Thành Bảo", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Triệu", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Hâm", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Hâm", "1131234567", 20, 100));

        danhSachNhanSu.add(new TruongPhong("Hoàn", "1131234567", 20, 200));
        danhSachNhanSu.add(new TruongPhong("Khoa", "1131234567", 20, 200));

        danhSachNhanSu.add(new GiamDoc("Phúc", "2251234567", 20, 500, 30));
    }


    public boolean them(NhanSu nhanSu) {
        if(nhanSu == null){
            return  false;
        }
        danhSachNhanSu.add(nhanSu);
        nhanSu.xuatThongTin();
        return true;
    }



    public void phanBoNhanVien(Scanner sc){
        int luaChon = -1;
        List<NhanVien> dsNhanVienChuaPhanBo = new ArrayList<NhanVien>();
        List<TruongPhong> dsTruongPhong = new ArrayList<TruongPhong>();
        for (NhanSu ns : danhSachNhanSu){
            if(ns instanceof NhanVien){
                NhanVien nv = (NhanVien) ns;
                if(nv.getMaTruongPhong() == 0){
                    dsNhanVienChuaPhanBo.add(nv);
                }
            }else if(ns instanceof TruongPhong){
                dsTruongPhong.add((TruongPhong) ns);
            }
        }

        //Phân bổ các nhân viên vào trưởng phòng
        for(NhanVien nv : dsNhanVienChuaPhanBo){
            System.out.println("=====--PHÂN BỔ NHÂN VIÊN--=====");
            nv.xuatThongTin();
            System.out.println("/nChọn trưởng phòng để phân bổ: ");
            for (int i = 0; i < dsTruongPhong.size(); i++) {
                TruongPhong tp = dsTruongPhong.get(i);
                System.out.printf("%d. %20s %5d\n", i+1, tp.getHoTen(), tp.getMaSo());
            }
            System.out.println("0. Không phân bổ.");
            System.out.println("-1. Thoát chức năng phân bổ.");
            System.out.print("Lựa chọn: ");
            luaChon = Integer.parseInt(sc.nextLine());

            if(luaChon == 0)
                continue;

            if(luaChon == -1)
                return;

            if(luaChon > 0 && luaChon <= dsTruongPhong.size()) {
                nv.setMaTruongPhong(dsTruongPhong.get(luaChon-1).getMaSo());
                dsTruongPhong.get(luaChon-1).tangSoNhanVien();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng tiến hành phân bổ lần sau.");
            }
        }
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
                    if(nv.getMaTruongPhong() != 0 && nv.getMaTruongPhong() == tp.getMaSo()){
                        nv.setMaTruongPhong(0); // ngat tham chieu den truong phon
                    }
                }
            }
            danhSachNhanSu.remove(tp); // Xoa truong phong;
            return true;
        }

        //Neu la nhan vien thuong
        if(nhanSu instanceof NhanVien){
            NhanVien nv = (NhanVien) nhanSu;
            if(nv.getMaTruongPhong() != 0){
                for (NhanSu ns : danhSachNhanSu){
                    if(ns instanceof TruongPhong){
                        TruongPhong tp = (TruongPhong) ns;
                        if(tp.getMaSo() == nv.getMaTruongPhong()){
                            tp.setSoNhanVien(tp.getSoNhanVien() -1);
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
