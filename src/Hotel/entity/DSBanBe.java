/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.entity;

/**
 *
 * @author Admin
 */
public class DSBanBe {

    int MaDS;
    String MaNV;
    boolean BanBe;
    String MaNVKB;

    public DSBanBe() {
    }

    public DSBanBe(int MaDS, String MaNV, boolean BanBe, String MaNVKB) {
        this.MaDS = MaDS;
        this.MaNV = MaNV;
        this.BanBe = BanBe;
        this.MaNVKB = MaNVKB;
    }

    public int getMaDS() {
        return MaDS;
    }

    public void setMaDS(int MaDS) {
        this.MaDS = MaDS;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public boolean isBanBe() {
        return BanBe;
    }

    public void setBanBe(boolean BanBe) {
        this.BanBe = BanBe;
    }

    public String getMaNVKB() {
        return MaNVKB;
    }

    public void setMaNVKB(String MaNVKB) {
        this.MaNVKB = MaNVKB;
    }

}
