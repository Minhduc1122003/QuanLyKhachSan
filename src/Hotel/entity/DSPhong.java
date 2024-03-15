
package Hotel.entity;

/**
 *
 * @author Admin
 */
public class DSPhong {
    String maPhong;
    String tenPhong;
    String maLoaiPhong;
    String trangThai;

    public DSPhong() {
    }

    public DSPhong(String maPhong, String tenPhong, String maLoaiPhong, String trangThai) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.maLoaiPhong = maLoaiPhong;
        this.trangThai = trangThai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
            
}
