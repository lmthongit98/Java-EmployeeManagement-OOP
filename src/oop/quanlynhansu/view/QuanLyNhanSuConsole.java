package oop.quanlynhansu.view;

import oop.quanlynhansu.controller.QuanLyNhanSu;
import oop.quanlynhansu.model.*;

import java.util.Scanner;

public class QuanLyNhanSuConsole {
    private  QuanLyNhanSu quanLyNhanSu;
    private  CongTy congTy;
    static Scanner sc;

    public QuanLyNhanSuConsole(){
        quanLyNhanSu = new QuanLyNhanSu();
        congTy = new CongTy();
        sc = new Scanner(System.in);
    }




    private void themNhanSu(){
        System.out.println("1. Employee");
        System.out.println("2. Manager");
        System.out.println("3. Director");
        int choice = -1;
        NhanSu nhanSu = null;
        do {
            System.out.println("Enter your choice: ");
            choice =  Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Enter employee info: ");
                    nhanSu= new NhanVien();
                    break;
                case 2:
                    System.out.println("Enter manager info: ");
                    nhanSu = new TruongPhong();
                    break;
                case 3:
                    System.out.println("Enter director info: ");
                    nhanSu = new GiamDoc();
                    break;
                default:
                    System.out.println("Your choice is invalid ! ");
                    break;
            }
        }while (choice != 1 && choice != 2 && choice != 3);

        nhanSu.nhapThongTin(sc);
        if (this.quanLyNhanSu.them(nhanSu)){
            System.out.println("Add successfully!");
        }else{
            System.out.println("Add failed!");
        }
    }

    private void xoaNhanSu(){
        System.out.println("Nhập vào mã nhân sự muốn xóa: ");
        int  maSo = Integer.parseInt(sc.nextLine());
        if(this.quanLyNhanSu.xoa(maSo)){
            System.out.println("Xóa thành công !");
        }else{
            System.out.println("Xóa thất bại !");
        }
    }

    public void processOptions(){
        int choice = -1;
          while (true){
              showMenu();
              System.out.print("Enter your choice: ");
              choice = Integer.parseInt(sc.nextLine());
              switch (choice){
                  case 1:
                      //Nhap thong tin cong ty
                      congTy.nhap(sc);
                      congTy.xuat();
                      break;
                  case 2:
                      //Phan bo nhan vien vao truong phong
                        this.quanLyNhanSu.phanBoNhanVien(sc);
                        break;
                  case 3:
                      this.themNhanSu();
                      break;
                  case 4:
                      //Xoa nhan su
                      this.xoaNhanSu();
                      break;
                  case 5:
                      //Xuat thong tin toan bo nguoi trong cong ty
                      this.inDanhSachNhanSu();
                      break;
                  case 6:
                      //Tinh va xuat tong luong trong cong ty
                      break;
                  case 7:
                      //Tim nhan vien thuong co luong cao nhat
                      break;
                  case 8:
                      //Tim truong phong co so luong nhan vien duoi quyen nhieu nhat
                      break;
                  case 9:
                      //Sap xep nhan vien toan cong ty theo thu tu abc
                      break;
                  case 10:
                      //Sap xep nhan vien toan cong ty theo thu tu giam dan
                      break;
                  case 11:
                      //Tim giam doc co so luong co phan nhieu nhat
                      break;
                  case 12:
                      //Tinh va xuat tong thu nhap cua tung giam doc
                      break;
                  default:
                      break;
              }
              System.out.print("Enter any key to continue:");
              sc.nextLine();
          }

    }

    public void showMenu(){
        System.out.println("1. Nhập thông tin công ty.");
        System.out.println("2. Phân bổ nhân viên vào trưởng phòng.");
        System.out.println("3. Thêm nhân sự.");
        System.out.println("4. Xóa nhân sự.");
        System.out.println("5. Xuất thông tin toàn bộ người trong công ty.");
        System.out.println("6. Tính và xuất tổng lương cho từng công ty.");
        System.out.println("7. Tìm nhân viên thường có lương cao nhất.");
        System.out.println("8. Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất.");
        System.out.println("9. Sắp xếp nhân viên toàn công ty theo thứ tự abc.");
        System.out.println("10. Sắp xếp nhân viên toàn công ty theo thứ tự giảm dần.");
        System.out.println("11. Tìm giám đốc có số lượng cổ phần nhiều nhất.");
        System.out.println("12. Tính và xuất tổng thu nhập của từng giám đốc.");
    }

    public void inDanhSachNhanSu(){
        String leftAlignFormat = "| %-5d | %-16s | %-13s | %-11.1f | %-13f | %-14s | %-14s | %-33s |%n";
        System.out.format("+-------+------------------+---------------+-------------+---------------+----------------+----------------+-----------------------------------+%n");
        System.out.format("| Mã số |  Họ tên          | Số điện thoại | Số ngày làm | Lương một ngày|      Lương     |     Chức vụ    | Trưởng phòng/Số nhân viên/Cổ phần |%n");
        System.out.format("+-------+------------------+---------------+-------------+---------------+----------------+----------------+-----------------------------------+%n");
        for (int i = 0; i < quanLyNhanSu.getDanhSachNhanSu().size(); i++) {

            System.out.format(leftAlignFormat,
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getMaSo(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getHoTen(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getSoDienThoai(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getSoNgayLam(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getLuongMotNgay(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).tinhLuong(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getChucVu(),
                    this.quanLyNhanSu.getDanhSachNhanSu().get(i).getThongTinThem()
                    );
        }
        System.out.format("+-------+------------------+---------------+-------------+---------------+----------------+----------------+-----------------------------------+%n");
    }

    public void start(){
        this.processOptions();
    }
}
