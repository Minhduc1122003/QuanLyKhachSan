package Hotel.dao;

import Hotel.entity.NhanVien;
import Hotel.utils.JdbcHelPer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, TenNV, SoDT, ChucVu, MatKhau, Email, GioiTinh, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        JdbcHelPer.executeUpdate(sql,
                model.getMaNV(),
                model.getTenNV(),
                model.getSoDT(),
                model.isChucVu(),
                model.getMatKhau(),
                model.getEmail(),
                model.isGioiTinh(),
                model.getImg()
        );
    }

    public void updatePassword(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=? WHERE MaNV=?";
        JdbcHelPer.executeUpdate(sql,
                model.getMatKhau(),
                model.getMaNV());
    }
    // chưa đụng tới.

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET TenNV=?, SoDT=?,ChucVu=?,MatKhau=?,Email=?,GioiTinh=?,HinhAnh=? WHERE MaNV=?";
        JdbcHelPer.executeUpdate(sql,
                model.getTenNV(),
                model.getSoDT(),
                model.isChucVu(),
                model.getMatKhau(),
                model.getEmail(),
                model.isGioiTinh(),
                model.getImg(),
                model.getMaNV()
        );
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelPer.executeUpdate(sql, MaNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    public NhanVien findById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;

    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                if (rs != null) {
                    while (rs.next()) {
                        NhanVien model = readFromResultSet(rs);
                        list.add(model);
                    }
                }
            } finally {
                if (rs != null) {
                    Connection connection;
                    try (Statement statement = rs.getStatement()) {
                        connection = statement.getConnection();
                        rs.close();
                    }
                    connection.close();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setTenNV(rs.getString("TenNV"));
        model.setSoDT(rs.getString("SoDT"));
        model.setChucVu(rs.getBoolean("ChucVu"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setEmail(rs.getString("Email"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setImg(rs.getString("HinhAnh"));
        return model;

    }
}
