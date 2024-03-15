package Hotel.dao;

import Hotel.entity.DichVuDaThue;

import Hotel.utils.JdbcHelPer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuDaThueDAO {

    public void insert(DichVuDaThue model) {
        String sql = "INSERT INTO DichVuDaThue ( MaDP, MADV, TenDV, DonGia) VALUES ( ?, ?, ?, ?)";
        JdbcHelPer.executeUpdate(sql,
                model.getMaDP(),
                model.getMaDV(),
                model.getTenDV(),
                model.getDonGia()
        );
    }

    public List<DichVuDaThue> findById(int maDP, Object... args) {
        String sql = "SELECT * FROM DichVuDaThue WHERE MaDP=?";
        List<DichVuDaThue> list = select(sql, maDP);
        return list;
    }

    public void delete(int MaDP) {
        String sql = "DELETE FROM DichVuDaThue WHERE MaDP=?";
        JdbcHelPer.executeUpdate(sql, MaDP);
    }

    private List<DichVuDaThue> select(String sql, Object... args) {
        List<DichVuDaThue> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelPer.executeQuery(sql, args);
                while (rs.next()) {
                    DichVuDaThue model = readFromResultSet(rs);
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

    private DichVuDaThue readFromResultSet(ResultSet rs) throws SQLException {
        DichVuDaThue model = new DichVuDaThue();
        model.setMaDVDT(rs.getInt("MaDVDT"));
        model.setMaDP(rs.getInt("MaDP"));
        model.setMaDV(rs.getString("MaDV"));
        model.setTenDV(rs.getString("TenDV"));
        model.setDonGia(rs.getFloat("DonGia"));

        return model;

    }
}
