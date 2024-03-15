package Hotel.dao;

import Hotel.entity.KhachHang;
import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangDAO {

    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, SoDT, CCCD, GioiTinh, DiaChi) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelPer.executeUpdate(sql,
                model.getMaKH(),
                model.getTenKH(),
                model.getSoDT(),
                model.getCCCD(),
                model.isGioiTinh(),
                model.getDiaChi()
        );
    }
    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE TenKH LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE SoDT LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE CCCD LIKE ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<KhachHang> select() {
        String sql = "SELECT * FROM KhachHang";
        return select(sql);
    }

    public KhachHang findById(String manv) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;

    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("MaKH"));
        model.setTenKH(rs.getString("TenKH"));
        model.setSoDT(rs.getString("SoDT"));
        model.setCCCD(rs.getString("CCCD"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDiaChi(rs.getString("DiaChi"));

        return model;

    }
}
