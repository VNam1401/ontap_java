/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
//import Array

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import util.FileHelPer;

/**
 *
 * @author ADMIN
 */
public class QLSinhVien {
    //Tạo Dssinhvien trong lớp sinh viên

    private ArrayList<SinhVien> dsSinhVien;

    //Khởi tạo danh sách sinh viên không tham số của danh sách sinh viên trống
    public QLSinhVien() {
        dsSinhVien = new ArrayList<>();
    }
//khởi tạo danh sách sinh viên có sẵn

    public QLSinhVien(ArrayList<SinhVien> ds) {
        this.dsSinhVien = ds;
    }

    //Insert code getter dssinhvien
    public ArrayList<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }
//insert code getter dssinhvien

    public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
        this.dsSinhVien = dsSinhVien;
    }

    //khởi tạo docdanhsachsinhvien đọc file từ văn bản (data.txt)
    public void DocDanhSachSinhVien(String filename) {

        dsSinhVien.clear();  //làm sạch  danh sách
        //sinh viên cài đặt
        //Đọc file txt (data.txt)
        ArrayList<String> data = FileHelPer.readFileText(filename);
        //Lặp qua từng dòng item trong data
        //Ghi thành công trả về true , ghi không thành công ngược lại
        for (String item : data) {
            String[] arr = item.split(";");
            SinhVien sv = new SinhVien();
            sv.setMaso(arr[0]);
            sv.setHoten(arr[1]);
            sv.setGioitinh(Boolean.valueOf(arr[2]));
            sv.setDiemTB(Double.valueOf(arr[3]));
            dsSinhVien.add(sv);
        }
    }
//khởi tạo ghidanhsachsinhvien để ghi vào file

    public boolean GhiDanhSachSinhVien(String filename) {
        //sinh viên cài đặt   
        //b1. convert dsSinhVien sang chuoi
//Tạo danh sách thêm dòng vào data ghi dữ liệu vào data.txt
        ArrayList<String> data = new ArrayList<>();
        for (SinhVien sv : dsSinhVien) {
            String item = sv.getMaso() + ";" + sv.getHoten() + ";" + sv.isGioitinh() + ";" + sv.getDiemTB();
            data.add(item);
        }
        //Ghi thành công trả về true , ghi không thành công ngược lại
        if (FileHelPer.writeFileText(filename, data)) {
            return true;
        } else {
            return false;
        }
    }
// thêm sinh viên
    public boolean themSV(SinhVien sv) {
        //sinh viên cài đặt
        //kiểm tra có danh sách sinh viên nếu có trả về true trùng danh sách không thêm trả về false
        if (!dsSinhVien.contains(sv)) {
            dsSinhVien.add(sv);
            return true;
        } else {
            return false;
        }
    }
// remove để xoá maso của danh sách sinh viên
    public void xoaSV(String maso) {
        //sinh viên cài đặt
        dsSinhVien.remove(new SinhVien(maso));
    }
//sắp xếp học lực từ thấp đến cao
    public void sapXepTheoHocLuc() {
        Comparator<SinhVien> tieuchi = (sv1, sv2) -> {
            return Double.compare(sv1.getDiemTB(), sv2.getDiemTB());
        };

        Collections.sort(dsSinhVien, tieuchi);
    }

}
