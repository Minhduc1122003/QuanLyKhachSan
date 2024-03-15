/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonThanhToan {

    private String MaHD;
    private int MaDP;
    private String MaNV;
    private String MaKH;
    private String NgayThue;
    private String NgayTraD;
    private String ngayLap;
    private float thanhTien;
    
   @Override
    public String toString() {
        return this.ngayLap + " (" + this.ngayLap + ")";
    }

    public HoaDonThanhToan() {
    }

    public HoaDonThanhToan(String MaHD, int MaDP, String MaNV, String MaKH, String NgayThue, String NgayTraD, String ngayLap, float thanhTien) {
        this.MaHD = MaHD;
        this.MaDP = MaDP;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayThue = NgayThue;
        this.NgayTraD = NgayTraD;
        this.ngayLap = ngayLap;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaDP() {
        return MaDP;
    }

    public void setMaDP(int MaDP) {
        this.MaDP = MaDP;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String NgayThue) {
        this.NgayThue = NgayThue;
    }

    public String getNgayTraD() {
        return NgayTraD;
    }

    public void setNgayTraD(String NgayTraD) {
        this.NgayTraD = NgayTraD;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
