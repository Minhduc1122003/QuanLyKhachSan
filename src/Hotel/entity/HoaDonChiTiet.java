package Hotel.entity;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet {

    String MaHD;
    String MaDV;
    String Ngaythue;
    float PhuThu;

    public HoaDonChiTiet() {
    }

    
    public HoaDonChiTiet(String MaHD, String MaDV, String Ngaythue, float PhuThu) {
        this.MaHD = MaHD;
        this.MaDV = MaDV;
        this.Ngaythue = Ngaythue;
        this.PhuThu = PhuThu;
    }

    
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getNgaythue() {
        return Ngaythue;
    }

    public void setNgaythue(String Ngaythue) {
        this.Ngaythue = Ngaythue;
    }

    public float getPhuThu() {
        return PhuThu;
    }

    public void setPhuThu(float PhuThu) {
        this.PhuThu = PhuThu;
    }
    
    
    
}
