
package Hotel.entity;


public class DatPhong {
    int MaDP;
    String NgayDen;
    String NgayDi;
    String MaNV;
    String MaKH;
    String MaPhong;
    String MaKieuThue;

    public DatPhong() {
    }

    public DatPhong(int MaDP, String NgayDen, String NgayDi, String MaNV, String MaKH, String MaPhong, String MaKieuThue) {
        this.MaDP = MaDP;
        this.NgayDen = NgayDen;
        this.NgayDi = NgayDi;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.MaPhong = MaPhong;
        this.MaKieuThue = MaKieuThue;
    }

    public int getMaDP() {
        return MaDP;
    }

    public void setMaDP(int MaDP) {
        this.MaDP = MaDP;
    }

    public String getNgayDen() {
        return NgayDen;
    }

    public void setNgayDen(String NgayDen) {
        this.NgayDen = NgayDen;
    }

    public String getNgayDi() {
        return NgayDi;
    }

    public void setNgayDi(String NgayDi) {
        this.NgayDi = NgayDi;
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

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaKieuThue() {
        return MaKieuThue;
    }

    public void setMaKieuThue(String MaKieuThue) {
        this.MaKieuThue = MaKieuThue;
    }
    
    
}
