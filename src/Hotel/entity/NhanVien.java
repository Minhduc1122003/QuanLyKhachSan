package Hotel.entity;

public class NhanVien {

    private String MaNV;
    private String TenNV;
    private String SoDT;
    private boolean ChucVu = false;
    private String MatKhau;
    private String Email;
    private boolean GioiTinh = false;
    private String img;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, String SoDT, String MatKhau, String Email, String img) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SoDT = SoDT;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.img = img;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public boolean isChucVu() {
        return ChucVu;
    }

    public void setChucVu(boolean ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
