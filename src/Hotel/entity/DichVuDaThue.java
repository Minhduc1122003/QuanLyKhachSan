/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.entity;

/**
 *
 * @author Admin
 */
public class DichVuDaThue {

    
    int MaDVDT;
    int MaDP;
    String MaDV;
    String TenDV;
    float DonGia;

    public DichVuDaThue() {
    }

    public DichVuDaThue(int MaDVDT, int MaDP, String MaDV, String TenDV, float DonGia) {
        this.MaDVDT = MaDVDT;
        this.MaDP = MaDP;
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.DonGia = DonGia;
    }

    public int getMaDVDT() {
        return MaDVDT;
    }

    public void setMaDVDT(int MaDVDT) {
        this.MaDVDT = MaDVDT;
    }

    public int getMaDP() {
        return MaDP;
    }

    public void setMaDP(int MaDP) {
        this.MaDP = MaDP;
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
