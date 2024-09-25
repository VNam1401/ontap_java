/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class SinhVien {
// khai bao
    private String maso;
    private String hoten;
    private boolean gioitinh;
    private double diemTB;

    //Khoi tao khong tham so
    public SinhVien() {
    }
//khoi tao co tham so
    public SinhVien(String maso) {
        this.maso = maso;
    }

    public SinhVien(String maso, String hoten, boolean gioitinh, double diemTB) {
        this.maso = maso;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.diemTB = diemTB;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }
//Tinh diem trung binh bang if
    public String getHocLuc() {
        String kq = "";
        if (diemTB < 5) {
            kq = "Yếu";
        } else if (diemTB < 6.5) {
            kq = "Trung bình";
        } else if (diemTB < 7.5) {
            kq = "Khá";
        } else if (diemTB < 9) {
            kq = "Giỏi";
        } else {
            kq = "Xuất sắc";
        }
        return kq;
    }
//Ghi de phuong thuc override Cua sinh vien
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SinhVien other = (SinhVien) obj;
        return Objects.equals(this.maso, other.maso);
    }   
    
}
