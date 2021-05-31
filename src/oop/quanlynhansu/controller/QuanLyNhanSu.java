package oop.quanlynhansu.controller;

import oop.quanlynhansu.controller.util.UtilQLNS;
import oop.quanlynhansu.model.GiamDoc;
import oop.quanlynhansu.model.NhanSu;
import oop.quanlynhansu.model.NhanVien;
import oop.quanlynhansu.model.TruongPhong;
import oop.quanlynhansu.view.util.PrintFormat;

import java.lang.reflect.Array;
import java.util.*;

public class QuanLyNhanSu {
    private ArrayList<NhanSu> danhSachNhanSu;

    public ArrayList<NhanSu> getDanhSachNhanSu() {
        return danhSachNhanSu;
    }

    public QuanLyNhanSu(){
        this.danhSachNhanSu = new ArrayList<NhanSu>();
        danhSachNhanSu.add(new NhanVien("Nguyễn Văn Tuấn", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Lê Văn Vương", "1141234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Trần Văn Đại", "1151234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Văn Châu", "1161234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Thị Trịnh", "1171234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Văn Duy", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Văn Bảo", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Thành An", "1131234567", 20, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Quốc Triệu", "1131234567", 22, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Công Hậu", "1131234567", 25, 100));
        danhSachNhanSu.add(new NhanVien("Nguyễn Văn Hâm", "1131234567", 25, 100));

        danhSachNhanSu.add(new TruongPhong("Nguyễn Văn Hoàn", "1131234567", 20, 200));
        danhSachNhanSu.add(new TruongPhong("Nguyễn Văn Khoa", "1131234567", 20, 200));

        danhSachNhanSu.add(new GiamDoc("Nguyễn Văn Phúc", "2251234567", 20, 500, 30));
        danhSachNhanSu.add(new GiamDoc("Nguyễn Văn Nam", "2251234567", 20, 500, 70));
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

    public double tinhTongLuongCongTy(){
        double tongLuong = 0;
        for(NhanSu ns : danhSachNhanSu){
            tongLuong += ns.tinhLuong();
        }

        return tongLuong;
    }

    public List<NhanVien> timNhanVienThuongCoLuongCaoNhat(){
        float maxLuong = 0;
        List<NhanVien> dsNhanVienLuongCaoNhat = new ArrayList<NhanVien>();

        //tim luong cao nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof NhanVien && ns.tinhLuong() > maxLuong){
                maxLuong = ns.tinhLuong();
            }
        }

        //tim nhan vien co luong cao nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof NhanVien && ns.tinhLuong() == maxLuong){
                dsNhanVienLuongCaoNhat.add((NhanVien) ns);
            }
        }

        return dsNhanVienLuongCaoNhat;
    }


    public List<TruongPhong> timTruongPhongCoSoLuongNhanVienNhieuNhat(){
        int soLuongNVMax = 0;
        List<TruongPhong> dsTruongPhongCoNhieuNhanVienNhat = new ArrayList<TruongPhong>();

        //tim luong cao nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof TruongPhong && ((TruongPhong) ns).getSoNhanVien() > soLuongNVMax){
                soLuongNVMax =((TruongPhong) ns).getSoNhanVien();
            }
        }

        //tim nhan vien co luong cao nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof TruongPhong && ((TruongPhong) ns).getSoNhanVien() == soLuongNVMax){
                dsTruongPhongCoNhieuNhanVienNhat.add((TruongPhong)ns);
            }
        }

        return dsTruongPhongCoNhieuNhanVienNhat;
    }

    public List<GiamDoc> timGiamDocCoSoLuongCoPhanNhieuNhat(){
        int soCoPhanMax = 0;
        List<GiamDoc> dsGiamDocCoSoCoPhanNhieuNhat = new ArrayList<GiamDoc>();

        //tim co phan cao nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof GiamDoc && ((GiamDoc) ns).getCoPhan() > soCoPhanMax){
                soCoPhanMax = ((GiamDoc) ns).getCoPhan();
            }
        }

        //tim giam doc co co phan nhieu nhat
        for (NhanSu ns : danhSachNhanSu){
            if (ns instanceof GiamDoc && ((GiamDoc) ns).getCoPhan() == soCoPhanMax){
                dsGiamDocCoSoCoPhanNhieuNhat.add((GiamDoc) ns);
            }
        }

        return dsGiamDocCoSoCoPhanNhieuNhat;
    }

    public void sapXepNhanVienTheoThuTuAlpha(){
        Collections.sort(this.danhSachNhanSu, (first, second) -> {
            return UtilQLNS.tachTen(first.getHoTen()).compareTo(UtilQLNS.tachTen(second.getHoTen()));
        });
    }

    public void sapXepNhanVienTheoThuTuLuongGiamDan(){
        Collections.sort(this.danhSachNhanSu, (first, second) -> {
            if (first.tinhLuong() - second.tinhLuong() > 0)
                return -1;
            return 1;
        });
    }


    public void tinhVaXuatTongThuNhapCuaTungGiamDoc(double doanhThuThang){
        double loiNhuanCongTy = doanhThuThang - this.tinhTongLuongCongTy();
        System.out.println("TỔNG THU NHẬP CỦA CỦA TỪNG GIÁM ĐỐC");
        for(NhanSu ns : danhSachNhanSu){
            if(ns instanceof GiamDoc){
                double tongThuNhap = ns.tinhLuong() + (((GiamDoc) ns).getCoPhan() * loiNhuanCongTy)/100;
                System.out.format("Giám đốc: %s - Tổng thu nhập: %f%n", ns.getHoTen(), tongThuNhap);
            }
        }
    }


}
