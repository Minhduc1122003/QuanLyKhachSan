/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.entity;

/**
 *
 * @author Admin
 */
public class DichVu {

    String MaDV;
    String TenDV;
    float DonGia;

    public DichVu() {
    }

    public DichVu( String MaDV, String TenDV, float DonGia) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.DonGia = DonGia;
    }


    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    
}
