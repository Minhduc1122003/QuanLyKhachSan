package Hotel.dao;

import Hotel.entity.DatPhong;
import Hotel.entity.KhachHang;
import Hotel.entity.indentity;
import Hotel.utils.JdbcHelPer;
import Hotel.utils.ShareHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DatPhongDAO {

    public void insert(DatPhong model) {
        String sql = "INSERT INTO DatPhong (NgayDen, NgayDi, MaNV, MaKH, MaPhong, MaKieuThue) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelPer.executeUpdate(sql,
                model.getNgayDen(),
                model.getNgayDi(),
                model.getMaNV(),
                model.getMaKH(),
                model.getMaPhong(),
                model.getMaKieuThue()
        );
    }

    public List<DatPhong> select() {
        String sql = "SELECT * FROM DatPhong";
        return select(sql);
    }

    public List<DatPhong> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM DatPhong WHERE MaKH LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE MaDP LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE MaNV LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE MaKieuThue LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE MaPhong LIKE ? "
                + "UNION SELECT * FROM KhachHang WHERE MaKH LIKE ?";
        return select(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public DatPhong findById(String manv) {
        String sql = "SELECT * FROM DatPhong WHERE MaKH=?";
        List<DatPhong> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;

    }

    public DatPhong findByMaDP(int maDP) {
        String sql = "SELECT * FROM DatPhong WHERE MaDP=?";
        List<DatPhong> list = select(sql, maDP);
        return list.size() > 0 ? list.get(0) : null;

    }

    public List<DatPhong> findByMaPhong(String maphong) {
        String sql = "SELECT * FROM DatPhong WHERE MaPhong=?";
        return select(sql, maphong);

    }


     

    private List<DatPhong> select(String sql, Object... args) {
        List<DatPhong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    DatPhong model = readFromResultSet(rs);
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

    private DatPhong readFromResultSet(ResultSet rs) throws SQLException {
        DatPhong model = new DatPhong();
        model.setMaDP(rs.getInt("MaDP"));
        model.setNgayDen(rs.getString("NgayDen"));
        model.setNgayDi(rs.getString("NgayDi"));
        model.setMaNV(rs.getString("MaNV"));
        model.setMaKH(rs.getString("MaKH"));
        model.setMaPhong(rs.getString("MaPhong"));
        model.setMaKieuThue(rs.getString("MaKieuThue"));

        return model;

    }

}
